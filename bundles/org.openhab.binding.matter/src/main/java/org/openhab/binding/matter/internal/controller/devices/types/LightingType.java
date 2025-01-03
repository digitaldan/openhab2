/**
 * Copyright (c) 2010-2024 Contributors to the openHAB project
 *
 * See the NOTICE file(s) distributed with this work for additional
 * information.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 */
package org.openhab.binding.matter.internal.controller.devices.types;

import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.matter.internal.client.model.cluster.BaseCluster;
import org.openhab.binding.matter.internal.client.model.cluster.ClusterCommand;
import org.openhab.binding.matter.internal.client.model.cluster.gen.ColorControlCluster;
import org.openhab.binding.matter.internal.client.model.cluster.gen.DeviceTypes;
import org.openhab.binding.matter.internal.client.model.cluster.gen.LevelControlCluster;
import org.openhab.binding.matter.internal.client.model.cluster.gen.OnOffCluster;
import org.openhab.binding.matter.internal.client.model.ws.AttributeChangedMessage;
import org.openhab.binding.matter.internal.controller.devices.converter.ColorControlConverter;
import org.openhab.binding.matter.internal.controller.devices.converter.GenericConverter;
import org.openhab.binding.matter.internal.controller.devices.converter.LevelControlConverter;
import org.openhab.binding.matter.internal.controller.devices.converter.OnOffConverter;
import org.openhab.binding.matter.internal.handler.MatterBaseThingHandler;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.types.Command;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dan Cunningham - Initial contribution
 *
 *         Lighting requires special handling for the OnOff, ColorControl and LevelControl clusters.
 *         For example, the Matter specification mandates Switches also must have a LevelControl cluster, even though
 *         they do not support dimming. We will filter those clusters out as well as coordinate commands among required
 *         clusters.
 *
 */
@NonNullByDefault
public class LightingType extends DeviceType {
    private final Logger logger = LoggerFactory.getLogger(LightingType.class);

    public LightingType(Integer deviceType, MatterBaseThingHandler handler, Integer endpointNumber) {
        super(deviceType, handler, endpointNumber);
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        logger.debug("Handling command for channel: {}", channelUID);
        // For dimmer and color lights handling a OnOff type, we send a onOff cluster command as we don't include an
        // OnOff converter
        if (command instanceof OnOffType onOffType && !clusterToConverters.containsKey(OnOffCluster.CLUSTER_ID)) {
            ClusterCommand onOffCommand = onOffType == OnOffType.ON ? OnOffCluster.on() : OnOffCluster.off();
            handler.sendClusterCommand(endpointNumber, OnOffCluster.CLUSTER_NAME, onOffCommand);
        } else {
            // will process onOff if onOffConverter is present and all other commands to the appropriate converter
            super.handleCommand(channelUID, command);
        }
    }

    @Override
    public void onEvent(AttributeChangedMessage message) {
        logger.debug("OnEvent: {} with value {}", message.path.attributeName, message.value);
        switch (message.path.attributeName) {
            case "onOff":
            case "currentLevel":
                if (clusterToConverters.get(OnOffCluster.CLUSTER_ID) instanceof OnOffConverter onOffCluster) {
                    onOffCluster.onEvent(message);
                }
                if (clusterToConverters
                        .get(LevelControlCluster.CLUSTER_ID) instanceof LevelControlConverter levelControlConverter) {
                    levelControlConverter.onEvent(message);
                }
                if (clusterToConverters
                        .get(ColorControlCluster.CLUSTER_ID) instanceof ColorControlConverter colorControlConverter) {
                    colorControlConverter.onEvent(message);
                }
                return;
        }
        // no matching cluster, bubble up for generic cluster processing
        super.onEvent(message);
    }

    @Override
    public void initState() {
        // default to on, and let matter tell otherwise
        OnOffType lastOnOff = OnOffType.ON;
        PercentType lastLevel = new PercentType(0);
        if (allClusters.get(OnOffCluster.CLUSTER_NAME) instanceof OnOffCluster onOffCluster) {
            lastOnOff = OnOffType.from(onOffCluster.onOff);
        }

        if (allClusters.get(LevelControlCluster.CLUSTER_NAME) instanceof LevelControlCluster levelControlCluster) {
            lastLevel = GenericConverter.levelToPercent(levelControlCluster.currentLevel);
        }

        final OnOffType finalLastOnOff = lastOnOff;
        final PercentType finalLastLevel = lastLevel;

        channelUIDToConverters.forEach((channelUID, converter) -> {
            if (converter instanceof LevelControlConverter levelControlConverter) {
                levelControlConverter.initState(finalLastOnOff == OnOffType.ON);
            } else if (converter instanceof ColorControlConverter colorControlConverter) {
                colorControlConverter.initState(finalLastOnOff == OnOffType.ON, finalLastLevel.intValue());
            } else {
                converter.initState();
            }
        });
    }

    @Override
    protected @Nullable GenericConverter<? extends BaseCluster> createConverter(BaseCluster cluster,
            Map<String, BaseCluster> allClusters, String labelPrefix) {
        logger.debug("checking converter for cluster: {}", cluster.getClass().getSimpleName());
        // Skip creating certain converters that this DeviceType will coordinate
        if ((cluster instanceof OnOffCluster && !isSwitch())
                || (cluster instanceof LevelControlCluster && (isSwitch() || isColor()))) {
            return null;
        }

        return super.createConverter(cluster, allClusters, labelPrefix);
    }

    private boolean isSwitch() {
        return deviceType.equals(DeviceTypes.OnOffLight) || deviceType.equals(DeviceTypes.OnOffLightSwitch)
                || deviceType.equals(DeviceTypes.OnOffPlugInUnit);
    }

    private boolean isColor() {
        return deviceType.equals(DeviceTypes.ExtendedColorLight)
                || deviceType.equals(DeviceTypes.ColorTemperatureLight);
    }
}
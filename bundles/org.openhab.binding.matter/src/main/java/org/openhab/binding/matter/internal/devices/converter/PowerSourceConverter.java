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
package org.openhab.binding.matter.internal.devices.converter;

import static org.openhab.binding.matter.internal.MatterBindingConstants.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.matter.internal.client.model.cluster.gen.PowerSourceCluster;
import org.openhab.binding.matter.internal.client.model.cluster.gen.PowerSourceCluster.BatChargeLevelEnum;
import org.openhab.binding.matter.internal.client.model.ws.AttributeChangedMessage;
import org.openhab.binding.matter.internal.handler.EndpointHandler;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.thing.Channel;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.ThingUID;
import org.openhab.core.thing.binding.builder.ChannelBuilder;
import org.openhab.core.types.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Dan Cunningham
 */
@NonNullByDefault
public class PowerSourceConverter extends GenericConverter<PowerSourceCluster> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public PowerSourceConverter(PowerSourceCluster cluster, EndpointHandler handler) {
        super(cluster, handler);
    }

    public Map<Channel, @Nullable StateDescription> createChannels(ThingUID thingUID) {
        Map<Channel, @Nullable StateDescription> channels = new HashMap<>();
        if (cluster.featureMap.battery) {
            if (cluster.batPercentRemaining != null) {
                Channel channel = ChannelBuilder
                        .create(new ChannelUID(thingUID, CHANNEL_POWER_BATTERYPERCENT.getId()), ITEM_TYPE_NUMBER)
                        .withType(CHANNEL_POWER_BATTERYPERCENT).withLabel(CHANNEL_LABEL_POWER_BATTERYPERCENT).build();
                channels.put(channel, null);
            }
            if (cluster.batChargeLevel != null) {
                Channel channel = ChannelBuilder
                        .create(new ChannelUID(thingUID, CHANNEL_POWER_CHARGELEVEL.getId()), ITEM_TYPE_NUMBER)
                        .withType(CHANNEL_POWER_CHARGELEVEL).withLabel(CHANNEL_LABEL_POWER_CHARGELEVEL).build();
                List<StateOption> options = new ArrayList<>();
                for (BatChargeLevelEnum mode : BatChargeLevelEnum.values()) {
                    options.add(new StateOption(mode.value.toString(), mode.label));
                }
                StateDescription stateDescription = StateDescriptionFragmentBuilder.create().withPattern("%d")
                        .withOptions(options).build().toStateDescription();
                channels.put(channel, stateDescription);
            }
        }
        return channels;
    }

    public void handleCommand(ChannelUID channelUID, Command command) {
    }

    public void onEvent(AttributeChangedMessage message) {
        Integer numberValue = message.value instanceof Number number ? number.intValue() : 0;
        switch (message.path.attributeName) {
            case "batPercentRemaining":
                updateState(CHANNEL_POWER_BATTERYPERCENT, convertToPercentage(numberValue));
                break;
            case "batChargeLevel":
                updateState(CHANNEL_POWER_CHARGELEVEL, new DecimalType(numberValue));
                break;
            default:
                logger.debug("Unknown attribute {}", message.path.attributeName);
        }
    }

    public void updateCluster(PowerSourceCluster cluster) {
        super.updateCluster(cluster);
        if (cluster.batPercentRemaining != null) {
            updateState(CHANNEL_POWER_BATTERYPERCENT, convertToPercentage(cluster.batPercentRemaining));
        }

        if (cluster.batChargeLevel != null) {
            updateState(CHANNEL_POWER_CHARGELEVEL, new DecimalType(cluster.batChargeLevel.value));
        }
    }

    /**
     * Converts a battery charge value in half-percent units to a percentage (0-100).
     * Values are expressed in half percent units, ranging from 0 to 200.
     * For example, a value of 48 is equivalent to 24%.
     * 
     * @param halfPercentValue the battery charge value in half-percent units.
     * @return the percentage of battery charge (0-100) or -1 if the value is null or invalid.
     */
    private State convertToPercentage(Integer halfPercentValue) {
        if (halfPercentValue == null || halfPercentValue < 0 || halfPercentValue > 200) {
            return UnDefType.UNDEF; // Indicates that the node is unable to assess the value or invalid input.
        }
        return new PercentType(halfPercentValue / 2);
    }
}
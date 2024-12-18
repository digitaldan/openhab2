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
package org.openhab.binding.matter.internal.controller.devices.converter;

import static org.openhab.binding.matter.internal.MatterBindingConstants.CHANNEL_FANCONTROL_MODE;
import static org.openhab.binding.matter.internal.MatterBindingConstants.CHANNEL_FANCONTROL_PERCENT;
import static org.openhab.binding.matter.internal.MatterBindingConstants.CHANNEL_LABEL_FANCONTROL_MODE;
import static org.openhab.binding.matter.internal.MatterBindingConstants.CHANNEL_LABEL_FANCONTROL_PERCENT;
import static org.openhab.binding.matter.internal.MatterBindingConstants.ITEM_TYPE_DIMMER;
import static org.openhab.binding.matter.internal.MatterBindingConstants.ITEM_TYPE_NUMBER;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.matter.internal.client.model.cluster.ClusterCommand;
import org.openhab.binding.matter.internal.client.model.cluster.gen.FanControlCluster;
import org.openhab.binding.matter.internal.client.model.ws.AttributeChangedMessage;
import org.openhab.binding.matter.internal.handler.MatterBaseThingHandler;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.library.types.IncreaseDecreaseType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.thing.Channel;
import org.openhab.core.thing.ChannelGroupUID;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.binding.builder.ChannelBuilder;
import org.openhab.core.types.Command;
import org.openhab.core.types.StateDescription;
import org.openhab.core.types.StateDescriptionFragmentBuilder;
import org.openhab.core.types.StateOption;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The {@link FanControlConverter}
 *
 * @author Dan Cunningham - Initial contribution
 */
@NonNullByDefault
public class FanControlConverter extends GenericConverter<FanControlCluster> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public FanControlConverter(FanControlCluster cluster, MatterBaseThingHandler handler, int endpointNumber,
            String labelPrefix) {
        super(cluster, handler, endpointNumber, labelPrefix);
    }

    @Override
    public Map<Channel, @Nullable StateDescription> createChannels(ChannelGroupUID thingUID) {
        Map<Channel, @Nullable StateDescription> channels = new HashMap<>();
        Channel percentChannel = ChannelBuilder
                .create(new ChannelUID(thingUID, CHANNEL_FANCONTROL_PERCENT.getId()), ITEM_TYPE_DIMMER)
                .withType(CHANNEL_FANCONTROL_PERCENT).withLabel(formatLabel(CHANNEL_LABEL_FANCONTROL_PERCENT)).build();
        channels.put(percentChannel, null);

        if (cluster.fanModeSequence != null) {
            Channel modeChannel = ChannelBuilder
                    .create(new ChannelUID(thingUID, CHANNEL_FANCONTROL_MODE.getId()), ITEM_TYPE_NUMBER)
                    .withType(CHANNEL_FANCONTROL_MODE).withLabel(CHANNEL_LABEL_FANCONTROL_MODE).build();

            List<StateOption> modeOptions = new ArrayList<>();

            modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.OFF.value.toString(),
                    FanControlCluster.FanModeEnum.OFF.label));

            switch (cluster.fanModeSequence) {
                case OFF_HIGH:
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.HIGH.value.toString(),
                            FanControlCluster.FanModeEnum.HIGH.label));
                    break;
                case OFF_HIGH_AUTO:
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.HIGH.value.toString(),
                            FanControlCluster.FanModeEnum.HIGH.label));
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.AUTO.value.toString(),
                            FanControlCluster.FanModeEnum.AUTO.label));
                    break;
                case OFF_LOW_HIGH_AUTO:
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.LOW.value.toString(),
                            FanControlCluster.FanModeEnum.LOW.label));
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.HIGH.value.toString(),
                            FanControlCluster.FanModeEnum.HIGH.label));
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.AUTO.value.toString(),
                            FanControlCluster.FanModeEnum.AUTO.label));
                    break;
                case OFF_LOW_MED_HIGH_AUTO:
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.LOW.value.toString(),
                            FanControlCluster.FanModeEnum.LOW.label));
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.MEDIUM.value.toString(),
                            FanControlCluster.FanModeEnum.MEDIUM.label));
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.HIGH.value.toString(),
                            FanControlCluster.FanModeEnum.HIGH.label));
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.AUTO.value.toString(),
                            FanControlCluster.FanModeEnum.AUTO.label));

                    break;
                case OFF_LOW_HIGH:
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.LOW.value.toString(),
                            FanControlCluster.FanModeEnum.LOW.label));
                    modeOptions.add(new StateOption(FanControlCluster.FanModeEnum.HIGH.value.toString(),
                            FanControlCluster.FanModeEnum.HIGH.label));
                    break;
                default:
                    break;
            }

            StateDescription stateDescriptionMode = StateDescriptionFragmentBuilder.create().withPattern("%d")
                    .withOptions(modeOptions).build().toStateDescription();

            channels.put(modeChannel, stateDescriptionMode);
        }
        return channels;
    }

    @Override
    public void handleCommand(ChannelUID channelUID, Command command) {
        if (channelUID.getIdWithoutGroup().equals(CHANNEL_FANCONTROL_PERCENT.getId())) {
            if (command instanceof IncreaseDecreaseType increaseDecreaseType) {
                switch (increaseDecreaseType) {
                    case INCREASE:
                        moveCommand(FanControlCluster.step(FanControlCluster.StepDirectionEnum.INCREASE, false, false));
                        break;
                    case DECREASE:
                        moveCommand(FanControlCluster.step(FanControlCluster.StepDirectionEnum.DECREASE, false, true));
                        break;
                    default:
                        break;
                }
            } else if (command instanceof PercentType percentType) {
                handler.writeAttribute(endpointNumber, FanControlCluster.CLUSTER_NAME, "percentSetting",
                        percentType.toString());
            }
        }
        if (channelUID.getIdWithoutGroup().equals(CHANNEL_FANCONTROL_MODE.getId())) {
            if (command instanceof DecimalType decimalType) {
                handler.writeAttribute(endpointNumber, FanControlCluster.CLUSTER_NAME, "fanMode",
                        decimalType.toString());
            }
        }
        super.handleCommand(channelUID, command);
    }

    @Override
    public void onEvent(AttributeChangedMessage message) {
        Integer numberValue = message.value instanceof Number number ? number.intValue() : 0;
        switch (message.path.attributeName) {
            case "fanMode":
                updateState(CHANNEL_FANCONTROL_MODE, new DecimalType(numberValue));
                break;
            case "percentSetting":
                updateState(CHANNEL_FANCONTROL_PERCENT, new PercentType(numberValue));
                break;
            default:
                logger.debug("Unknown attribute {}", message.path.attributeName);
        }
        super.onEvent(message);
    }

    @Override
    public void initState() {
        if (cluster.fanMode != null) {
            updateState(CHANNEL_FANCONTROL_MODE, new DecimalType(cluster.fanMode.value));
        }
        if (cluster.percentSetting != null) {
            updateState(CHANNEL_FANCONTROL_PERCENT, new PercentType(cluster.percentSetting));
        }
    }

    private void moveCommand(ClusterCommand command) {
        handler.sendClusterCommand(endpointNumber, FanControlCluster.CLUSTER_NAME, command);
    }
}

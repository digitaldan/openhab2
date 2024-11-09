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

import static org.openhab.binding.matter.internal.MatterBindingConstants.CHANNEL_LABEL_SWITCH_SWITCH;
import static org.openhab.binding.matter.internal.MatterBindingConstants.CHANNEL_SWITCH_SWITCH;
import static org.openhab.binding.matter.internal.MatterBindingConstants.ITEM_TYPE_NUMBER;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.matter.internal.client.model.cluster.gen.SwitchCluster;
import org.openhab.binding.matter.internal.client.model.ws.AttributeChangedMessage;
import org.openhab.binding.matter.internal.client.model.ws.EventTriggeredMessage;
import org.openhab.binding.matter.internal.handler.EndpointHandler;
import org.openhab.core.library.types.DecimalType;
import org.openhab.core.thing.Channel;
import org.openhab.core.thing.ChannelUID;
import org.openhab.core.thing.ThingUID;
import org.openhab.core.thing.binding.builder.ChannelBuilder;
import org.openhab.core.thing.type.ChannelTypeUID;
import org.openhab.core.types.Command;
import org.openhab.core.types.StateDescription;
import org.openhab.core.types.StateDescriptionFragmentBuilder;
import org.openhab.core.types.StateOption;

import com.google.gson.Gson;

/**
 * @author Dan Cunningham
 */
@NonNullByDefault
public class SwitchConverter extends GenericConverter<SwitchCluster> {
    private Gson gson = new Gson();

    public SwitchConverter(SwitchCluster cluster, EndpointHandler handler) {
        super(cluster, handler);
    }

    public Map<Channel, @Nullable StateDescription> createChannels(ThingUID thingUID) {
        Channel channel = ChannelBuilder
                .create(new ChannelUID(thingUID, CHANNEL_SWITCH_SWITCH.getId()), ITEM_TYPE_NUMBER)
                .withType(CHANNEL_SWITCH_SWITCH).withLabel(CHANNEL_LABEL_SWITCH_SWITCH).build();

        List<StateOption> options = new ArrayList<>();
        for (int i = 0; i < cluster.numberOfPositions; i++) {
            options.add(new StateOption(String.valueOf(i), "Position " + i));
        }

        StateDescription stateDescriptionMode = StateDescriptionFragmentBuilder.create().withPattern("%d")
                .withOptions(options).build().toStateDescription();
        return Collections.singletonMap(channel, stateDescriptionMode);
    }

    public void handleCommand(ChannelUID channelUID, Command command) {
        // no commands to handle
    }

    public void onEvent(AttributeChangedMessage message) {
        Integer numberValue = message.value instanceof Number number ? number.intValue() : 0;
        switch (message.path.attributeName) {
            case "currentPosition":
                cluster.currentPosition = numberValue;
                handler.updateState(CHANNEL_SWITCH_SWITCH.getId(), new DecimalType(numberValue));
                break;
        }
    }

    public void onEvent(EventTriggeredMessage message) {
        String eventName = message.path.eventName.toLowerCase();
        // TODO: check if there are any switch events that actually have more then one event data, I don't think there
        // are.
        String eventData = message.events.length >= 0 ? gson.toJson(message.events[0].data) : "{}";
        handler.triggerChannel(new ChannelTypeUID("matter:switch-" + eventName).getId(), eventData);
    }

    public void updateCluster(SwitchCluster cluster) {
        super.updateCluster(cluster);
        updateState(CHANNEL_SWITCH_SWITCH, new DecimalType(cluster.currentPosition));
    }
}
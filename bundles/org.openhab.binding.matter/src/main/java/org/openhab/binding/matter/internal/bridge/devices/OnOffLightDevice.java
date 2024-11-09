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
package org.openhab.binding.matter.internal.bridge.devices;

import java.util.Map;
import java.util.Optional;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.openhab.binding.matter.internal.bridge.MatterBridgeClient;
import org.openhab.core.items.GenericItem;
import org.openhab.core.items.Item;
import org.openhab.core.items.MetadataRegistry;
import org.openhab.core.library.items.SwitchItem;
import org.openhab.core.library.types.HSBType;
import org.openhab.core.library.types.OnOffType;
import org.openhab.core.library.types.PercentType;
import org.openhab.core.types.State;

/**
 * The {@link DimmableLightDevice}
 *
 * @author Dan Cunningham - Initial contribution
 */
@NonNullByDefault
public class OnOffLightDevice extends GenericDevice {

    public OnOffLightDevice(MetadataRegistry metadataRegistry, MatterBridgeClient client, GenericItem item) {
        super(metadataRegistry, client, item);
    }

    @Override
    public String deviceType() {
        return "OnOffLightDevice";
    }

    @Override
    public void handleMatterEvent(String clusterName, String attributeName, Object data) {
        switch (attributeName) {
            case "onOff":
                ((SwitchItem) primaryItem).send(OnOffType.from(Boolean.valueOf(data.toString())));
                break;
            case "currentLevel":
                ((SwitchItem) primaryItem).send(OnOffType.from(data.toString()));
                break;
            default:
                break;
        }
    }

    @Override
    public Map<String, Object> setupDevice() {
        dispose();
        primaryItem.addStateChangeListener(this);
        return Map.of("onOff", Optional.ofNullable(primaryItem.getStateAs(OnOffType.class))
                .orElseGet(() -> OnOffType.OFF) == OnOffType.ON);
    }

    @Override
    public void dispose() {
        primaryItem.removeStateChangeListener(this);
    }

    public void updateState(Item item, State state) {
        if (state instanceof HSBType hsb) {
            setEndpointState("onOff", "onOff", hsb.getBrightness().intValue() > 0 ? true : false);
        } else if (state instanceof PercentType percentType) {
            setEndpointState("onOff", "onOff", percentType.intValue() > 0 ? true : false);
        } else if (state instanceof OnOffType onOffType) {
            setEndpointState("onOff", "onOff", onOffType == OnOffType.ON ? true : false);
        }
    }
}
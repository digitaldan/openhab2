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
package org.openhab.binding.matter.internal;

import static org.openhab.binding.matter.internal.MatterBindingConstants.THING_TYPE_CONTROLLER;
import static org.openhab.binding.matter.internal.MatterBindingConstants.THING_TYPE_ENDPOINT;

import java.util.Set;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.matter.internal.handler.ControllerHandler;
import org.openhab.binding.matter.internal.handler.EndpointHandler;
import org.openhab.binding.matter.internal.util.MatterWebsocketService;
import org.openhab.core.thing.Bridge;
import org.openhab.core.thing.Thing;
import org.openhab.core.thing.ThingTypeUID;
import org.openhab.core.thing.binding.BaseThingHandlerFactory;
import org.openhab.core.thing.binding.ThingHandler;
import org.openhab.core.thing.binding.ThingHandlerFactory;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;

/**
 * The {@link MatterHandlerFactory} is responsible for creating things and thing
 * handlers.
 *
 * @author Dan Cunningham - Initial contribution
 */
@NonNullByDefault
@Component(configurationPid = "binding.matter", service = ThingHandlerFactory.class)
public class MatterHandlerFactory extends BaseThingHandlerFactory {
    // private final Logger logger = LoggerFactory.getLogger(MatterHandlerFactory.class);

    private static final Set<ThingTypeUID> SUPPORTED_THING_TYPES_UIDS = Set.of(THING_TYPE_CONTROLLER,
            THING_TYPE_ENDPOINT);

    private final MatterStateDescriptionOptionProvider stateDescriptionProvider;
    private final MatterWebsocketService websocketService;

    @Activate
    public MatterHandlerFactory(@Reference MatterWebsocketService websocketService,
            @Reference MatterStateDescriptionOptionProvider stateDescriptionProvider) {
        this.websocketService = websocketService;
        this.stateDescriptionProvider = stateDescriptionProvider;
    }

    @Override
    public boolean supportsThingType(ThingTypeUID thingTypeUID) {
        return SUPPORTED_THING_TYPES_UIDS.contains(thingTypeUID);
    }

    @Override
    protected @Nullable ThingHandler createHandler(Thing thing) {
        ThingTypeUID thingTypeUID = thing.getThingTypeUID();

        if (THING_TYPE_CONTROLLER.equals(thingTypeUID)) {
            return new ControllerHandler((Bridge) thing, websocketService);
        }

        // if (THING_TYPE_NODE.equals(thingTypeUID)) {
        // return new NodeHandler((Bridge) thing);
        // }

        if (THING_TYPE_ENDPOINT.equals(thingTypeUID)) {
            return new EndpointHandler(thing, stateDescriptionProvider);
        }

        return null;
    }
}
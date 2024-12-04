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
package org.openhab.binding.matter.internal.handler;

import java.math.BigInteger;

import org.eclipse.jdt.annotation.NonNullByDefault;
import org.eclipse.jdt.annotation.Nullable;
import org.openhab.binding.matter.internal.MatterChannelTypeProvider;
import org.openhab.binding.matter.internal.MatterStateDescriptionOptionProvider;
import org.openhab.binding.matter.internal.client.model.Endpoint;
import org.openhab.binding.matter.internal.client.model.cluster.BaseCluster;
import org.openhab.binding.matter.internal.client.model.cluster.gen.BridgedDeviceBasicInformationCluster;
import org.openhab.binding.matter.internal.config.EndpointConfiguration;
import org.openhab.binding.matter.internal.util.MatterUIDUtils;
import org.openhab.core.thing.*;
import org.openhab.core.thing.binding.BridgeHandler;

/**
 * The {@link BridgeEndpointHandler} is responsible for handling commands, which are
 * sent to one of the channels.
 * 
 * This class is used for handling endpoints that are "bridged" devices, although it could be used to handle any
 * endpoint(s) if needed, otherwise Endpoints are generally managed as channel groups on a Node Thing
 *
 * @author Dan Cunningham - Initial contribution
 */
@NonNullByDefault
public class BridgeEndpointHandler extends MatterBaseThingHandler {
    private Integer endpointId = 0;

    public BridgeEndpointHandler(Thing thing, MatterStateDescriptionOptionProvider stateDescriptionProvider,
            MatterChannelTypeProvider channelGroupTypeProvider) {
        super(thing, stateDescriptionProvider, channelGroupTypeProvider);
    }

    @Override
    public void initialize() {
        EndpointConfiguration config = getConfigAs(EndpointConfiguration.class);
        logger.debug("initialize endpoint {}", config.endpointId);
        endpointId = config.endpointId;
        NodeHandler handler = nodeHandler();
        if (handler == null) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.BRIDGE_UNINITIALIZED);
        } else if (handler.getThing().getStatus() != ThingStatus.ONLINE) {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.BRIDGE_OFFLINE);
        } else {
            // wait for us to be updated.
            updateStatus(ThingStatus.UNKNOWN, ThingStatusDetail.NOT_YET_READY, "Waiting for data");
        }
    }

    @Override
    public BigInteger getNodeId() {
        NodeHandler nodeHandler = nodeHandler();
        if (nodeHandler != null) {
            return nodeHandler.getNodeId();
        }
        throw new RuntimeException("Could not access parent bridge!");
    }

    @Override
    public ThingTypeUID getDynamicThingTypeUID() {
        return MatterUIDUtils.bridgeEndpointThingTypeUID(getNodeId(), endpointId);
    }

    @Override
    public boolean isBridgeType() {
        return false;
    }

    @Override
    protected void updateEndpoint(Endpoint endpoint) {
        boolean reachable = true;
        BaseCluster basicInfoObject = endpoint.clusters.get(BridgedDeviceBasicInformationCluster.CLUSTER_NAME);
        if (basicInfoObject != null) {
            BridgedDeviceBasicInformationCluster basicInfo = (BridgedDeviceBasicInformationCluster) basicInfoObject;
            reachable = !"false".equals(basicInfo.reachable);
        }
        if (reachable) {
            if (getThing().getStatus() != ThingStatus.ONLINE) {
                logger.debug("Setting Online {}", endpointId);
                updateStatus(ThingStatus.ONLINE);
            }
        } else {
            updateStatus(ThingStatus.OFFLINE, ThingStatusDetail.COMMUNICATION_ERROR,
                    "Bridge reports device as not reachable");
        }
        super.updateEndpoint(endpoint);
    }

    public Integer getEndpointId() {
        return endpointId;
    }

    protected @Nullable NodeHandler nodeHandler() {
        Bridge bridge = getBridge();
        if (bridge != null) {
            BridgeHandler handler = bridge.getHandler();
            if (handler instanceof NodeHandler nodeHandler) {
                return nodeHandler;
            }
        }
        return null;
    }
}

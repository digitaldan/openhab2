/**
 * Copyright (c) 2010-2023 Contributors to the openHAB project
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

// AUTO-GENERATED by zap. DO NOT EDIT!

package org.openhab.binding.matter.internal.client.model.cluster;

import static java.util.Map.entry;

import java.util.List;
import java.util.Map;

import org.openhab.binding.matter.internal.client.MatterClient;
import org.openhab.binding.matter.internal.client.model.cluster.types.*;

/**
 * FaultInjection
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class FaultInjectionCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "FAULT_INJECTION_CLUSTER";
    public static final int CLUSTER_ID = 0xFFF1FC06;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(13, "generatedCommandList"), entry(11, "acceptedCommandList"),
                entry(9, "eventList"), entry(7, "attributeList"), entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries(entry(17, "failAtFault"), entry(71, "failRandomlyAtFault"));
    }

    // ZCL Enums
    public enum FaultType implements JsonSerializable {
        UNSPECIFIED(0, "Unspecified"),
        SYSTEMFAULT(1, "SystemFault"),
        INETFAULT(2, "InetFault"),
        CHIPFAULT(3, "ChipFault"),
        CERTFAULT(4, "CertFault"),
        UNKNOWN_VALUE(5, "UnknownValue");

        public final int value;
        public final String label;

        private FaultType(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public FaultInjectionCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 21, "FaultInjection");
    }

    public void failAtFault(MatterClient client, FaultType type, Integer id, Integer numCallsToSkip,
            Integer numCallsToFail, Boolean takeMutex) throws Exception {
        final FaultType _type = type;
        final Integer _id = id;
        final Integer _numCallsToSkip = numCallsToSkip;
        final Integer _numCallsToFail = numCallsToFail;
        final Boolean _takeMutex = takeMutex;
        Object o = new Object() {
            public FaultType type = _type;
            public Integer id = _id;
            public Integer numCallsToSkip = _numCallsToSkip;
            public Integer numCallsToFail = _numCallsToFail;
            public Boolean takeMutex = _takeMutex;
        };
        sendCommand(client, "failAtFault", o);
    }

    public void failRandomlyAtFault(MatterClient client, FaultType type, Integer id, Integer percentage)
            throws Exception {
        final FaultType _type = type;
        final Integer _id = id;
        final Integer _percentage = percentage;
        Object o = new Object() {
            public FaultType type = _type;
            public Integer id = _id;
            public Integer percentage = _percentage;
        };
        sendCommand(client, "failRandomlyAtFault", o);
    }

    public String toString() {
        String str = "";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

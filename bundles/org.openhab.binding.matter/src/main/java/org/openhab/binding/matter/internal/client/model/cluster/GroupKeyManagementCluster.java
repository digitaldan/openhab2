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
 * GroupKeyManagement
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class GroupKeyManagementCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "GROUP_KEY_MANAGEMENT_CLUSTER";
    public static final int CLUSTER_ID = 0x003F;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(38, "groupKeyMap"), entry(117, "groupTable"),
                entry(190, "maxGroupsPerFabric"), entry(254, "maxGroupKeysPerFabric"),
                entry(13, "generatedCommandList"), entry(11, "acceptedCommandList"), entry(9, "eventList"),
                entry(7, "attributeList"), entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries(entry(21, "keySetWrite"), entry(74, "keySetRead"),
                entry(122, "keySetReadResponse"), entry(155, "keySetRemove"), entry(181, "keySetReadAllIndices"),
                entry(204, "keySetReadAllIndicesResponse"));
    }

    class GroupInfoMapStruct implements JsonSerializable {
        public List<Integer> groupId; // group_id
        public List<Integer> endpoints; // endpoint_no
        public String groupName; // char_string
        public Integer fabricIndex; // fabric_idx

        public GroupInfoMapStruct(List<Integer> groupId, List<Integer> endpoints, String groupName,
                Integer fabricIndex) {
            this.groupId = groupId;
            this.endpoints = endpoints;
            this.groupName = groupName;
            this.fabricIndex = fabricIndex;
        }

        public String toJson() {
            String out = "{";
            out += "\"groupId\" : " + groupId + ",";
            out += "\"endpoints\" : " + endpoints + ",";
            out += "\"groupName\" : " + groupName + ",";
            out += "\"fabricIndex\" : " + fabricIndex + "";
            out += "}";
            return out;
        }
    }

    class GroupKeyMapStruct implements JsonSerializable {
        public List<Integer> groupId; // group_id
        public Integer groupKeySetID; // int16u
        public Integer fabricIndex; // fabric_idx

        public GroupKeyMapStruct(List<Integer> groupId, Integer groupKeySetID, Integer fabricIndex) {
            this.groupId = groupId;
            this.groupKeySetID = groupKeySetID;
            this.fabricIndex = fabricIndex;
        }

        public String toJson() {
            String out = "{";
            out += "\"groupId\" : " + groupId + ",";
            out += "\"groupKeySetID\" : " + groupKeySetID + ",";
            out += "\"fabricIndex\" : " + fabricIndex + "";
            out += "}";
            return out;
        }
    }

    class GroupKeySetStruct implements JsonSerializable {
        public Integer groupKeySetID; // int16u
        public GroupKeySecurityPolicyEnum groupKeySecurityPolicy; // GroupKeySecurityPolicyEnum
        public String epochKey0; // octet_string
        public Long epochStartTime0; // epoch_us
        public String epochKey1; // octet_string
        public Long epochStartTime1; // epoch_us
        public String epochKey2; // octet_string
        public Long epochStartTime2; // epoch_us

        public GroupKeySetStruct(Integer groupKeySetID, GroupKeySecurityPolicyEnum groupKeySecurityPolicy,
                String epochKey0, Long epochStartTime0, String epochKey1, Long epochStartTime1, String epochKey2,
                Long epochStartTime2) {
            this.groupKeySetID = groupKeySetID;
            this.groupKeySecurityPolicy = groupKeySecurityPolicy;
            this.epochKey0 = epochKey0;
            this.epochStartTime0 = epochStartTime0;
            this.epochKey1 = epochKey1;
            this.epochStartTime1 = epochStartTime1;
            this.epochKey2 = epochKey2;
            this.epochStartTime2 = epochStartTime2;
        }

        public String toJson() {
            String out = "{";
            out += "\"groupKeySetID\" : " + groupKeySetID + ",";
            out += "\"groupKeySecurityPolicy\" : " + groupKeySecurityPolicy + ",";
            out += "\"epochKey0\" : " + epochKey0 + ",";
            out += "\"epochStartTime0\" : " + epochStartTime0 + ",";
            out += "\"epochKey1\" : " + epochKey1 + ",";
            out += "\"epochStartTime1\" : " + epochStartTime1 + ",";
            out += "\"epochKey2\" : " + epochKey2 + ",";
            out += "\"epochStartTime2\" : " + epochStartTime2 + "";
            out += "}";
            return out;
        }
    }

    // ZCL Enums
    public enum GroupKeySecurityPolicyEnum implements JsonSerializable {
        TRUSTFIRST(0, "TrustFirst"),
        CACHEANDSYNC(1, "CacheAndSync"),
        UNKNOWN_VALUE(2, "UnknownValue");

        public final int value;
        public final String label;

        private GroupKeySecurityPolicyEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    // ZCL Bitmaps
    public static class Feature implements JsonSerializable {
        public boolean cacheAndSync;

        public Feature(boolean cacheAndSync) {
            this.cacheAndSync = cacheAndSync;
        }

        public String toJson() {
            String out = "{";
            out += "\"cacheAndSync\" : " + cacheAndSync + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static Feature fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new Feature(keys[0]);
        }
    }

    public GroupKeyMapStruct[] groupKeyMap; // 38 GroupKeyMapStruct
    public GroupInfoMapStruct[] groupTable; // 117 GroupInfoMapStruct
    public Integer maxGroupsPerFabric; // 190 int16u
    public Integer maxGroupKeysPerFabric; // 254 int16u
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public GroupKeyManagementCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 28, "GroupKeyManagement");
    }

    public void keySetWrite(MatterClient client, GroupKeySetStruct[] groupKeySet) throws Exception {
        final GroupKeySetStruct[] _groupKeySet = groupKeySet;
        Object o = new Object() {
            public GroupKeySetStruct[] groupKeySet = _groupKeySet;
        };
        sendCommand(client, "keySetWrite", o);
    }

    public void keySetRead(MatterClient client, Integer groupKeySetID) throws Exception {
        final Integer _groupKeySetID = groupKeySetID;
        Object o = new Object() {
            public Integer groupKeySetID = _groupKeySetID;
        };
        sendCommand(client, "keySetRead", o);
    }

    public void keySetReadResponse(MatterClient client, GroupKeySetStruct[] groupKeySet) throws Exception {
        final GroupKeySetStruct[] _groupKeySet = groupKeySet;
        Object o = new Object() {
            public GroupKeySetStruct[] groupKeySet = _groupKeySet;
        };
        sendCommand(client, "keySetReadResponse", o);
    }

    public void keySetRemove(MatterClient client, Integer groupKeySetID) throws Exception {
        final Integer _groupKeySetID = groupKeySetID;
        Object o = new Object() {
            public Integer groupKeySetID = _groupKeySetID;
        };
        sendCommand(client, "keySetRemove", o);
    }

    public void keySetReadAllIndices(MatterClient client) throws Exception {
        Object o = new Object() {
        };
        sendCommand(client, "keySetReadAllIndices", o);
    }

    public void keySetReadAllIndicesResponse(MatterClient client, Integer groupKeySetIDs) throws Exception {
        final Integer _groupKeySetIDs = groupKeySetIDs;
        Object o = new Object() {
            public Integer groupKeySetIDs = _groupKeySetIDs;
        };
        sendCommand(client, "keySetReadAllIndicesResponse", o);
    }

    public String toString() {
        String str = "";
        str += "groupKeyMap : " + groupKeyMap + "\n";
        str += "groupTable : " + groupTable + "\n";
        str += "maxGroupsPerFabric : " + maxGroupsPerFabric + "\n";
        str += "maxGroupKeysPerFabric : " + maxGroupKeysPerFabric + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

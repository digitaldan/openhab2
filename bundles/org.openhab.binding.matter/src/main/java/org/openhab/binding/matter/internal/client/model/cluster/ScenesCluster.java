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
 * Scenes
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class ScenesCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "SCENES_CLUSTER";
    public static final int CLUSTER_ID = 0x0005;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(68, "sceneCount"), entry(147, "currentScene"),
                entry(216, "currentGroup"), entry(280, "sceneValid"), entry(329, "nameSupport"),
                entry(369, "lastConfiguredBy"), entry(403, "sceneTableSize"), entry(434, "fabricSceneInfo"),
                entry(13, "generatedCommandList"), entry(11, "acceptedCommandList"), entry(9, "eventList"),
                entry(7, "attributeList"), entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries(entry(41, "addScene"), entry(260, "addSceneResponse"), entry(95, "viewScene"),
                entry(267, "viewSceneResponse"), entry(136, "removeScene"), entry(270, "removeSceneResponse"),
                entry(167, "removeAllScenes"), entry(273, "removeAllScenesResponse"), entry(192, "storeScene"),
                entry(276, "storeSceneResponse"), entry(211, "recallScene"), entry(224, "getSceneMembership"),
                entry(279, "getSceneMembershipResponse"), entry(236, "enhancedAddScene"),
                entry(282, "enhancedAddSceneResponse"), entry(245, "enhancedViewScene"),
                entry(285, "enhancedViewSceneResponse"), entry(253, "copyScene"), entry(288, "copySceneResponse"));
    }

    class AttributeValuePair implements JsonSerializable {
        public List<Integer> attributeID; // attrib_id
        public Integer attributeValue; // int32u

        public AttributeValuePair(List<Integer> attributeID, Integer attributeValue) {
            this.attributeID = attributeID;
            this.attributeValue = attributeValue;
        }

        public String toJson() {
            String out = "{";
            out += "\"attributeID\" : " + attributeID + ",";
            out += "\"attributeValue\" : " + attributeValue + "";
            out += "}";
            return out;
        }
    }

    class ExtensionFieldSet implements JsonSerializable {
        public List<Integer> clusterID; // cluster_id
        public AttributeValuePair attributeValueList; // AttributeValuePair

        public ExtensionFieldSet(List<Integer> clusterID, AttributeValuePair attributeValueList) {
            this.clusterID = clusterID;
            this.attributeValueList = attributeValueList;
        }

        public String toJson() {
            String out = "{";
            out += "\"clusterID\" : " + clusterID + ",";
            out += "\"attributeValueList\" : " + attributeValueList + "";
            out += "}";
            return out;
        }
    }

    class SceneInfoStruct implements JsonSerializable {
        public Integer sceneCount; // int8u
        public Integer currentScene; // int8u
        public List<Integer> currentGroup; // group_id
        public Boolean sceneValid; // boolean
        public Integer remainingCapacity; // int8u
        public Integer fabricIndex; // fabric_idx

        public SceneInfoStruct(Integer sceneCount, Integer currentScene, List<Integer> currentGroup, Boolean sceneValid,
                Integer remainingCapacity, Integer fabricIndex) {
            this.sceneCount = sceneCount;
            this.currentScene = currentScene;
            this.currentGroup = currentGroup;
            this.sceneValid = sceneValid;
            this.remainingCapacity = remainingCapacity;
            this.fabricIndex = fabricIndex;
        }

        public String toJson() {
            String out = "{";
            out += "\"sceneCount\" : " + sceneCount + ",";
            out += "\"currentScene\" : " + currentScene + ",";
            out += "\"currentGroup\" : " + currentGroup + ",";
            out += "\"sceneValid\" : " + sceneValid + ",";
            out += "\"remainingCapacity\" : " + remainingCapacity + ",";
            out += "\"fabricIndex\" : " + fabricIndex + "";
            out += "}";
            return out;
        }
    }

    // ZCL Bitmaps
    public static class CopyModeBitmap implements JsonSerializable {
        public boolean copyAllScenes;

        public CopyModeBitmap(boolean copyAllScenes) {
            this.copyAllScenes = copyAllScenes;
        }

        public String toJson() {
            String out = "{";
            out += "\"copyAllScenes\" : " + copyAllScenes + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static CopyModeBitmap fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new CopyModeBitmap(keys[0]);
        }
    }

    public static class Feature implements JsonSerializable {
        public boolean sceneNames;
        public boolean explicit;
        public boolean tableSize;
        public boolean fabricScenes;

        public Feature(boolean sceneNames, boolean explicit, boolean tableSize, boolean fabricScenes) {
            this.sceneNames = sceneNames;
            this.explicit = explicit;
            this.tableSize = tableSize;
            this.fabricScenes = fabricScenes;
        }

        public String toJson() {
            String out = "{";
            out += "\"sceneNames\" : " + sceneNames + ",";
            out += "\"explicit\" : " + explicit + ",";
            out += "\"tableSize\" : " + tableSize + ",";
            out += "\"fabricScenes\" : " + fabricScenes + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static Feature fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new Feature(keys[0], keys[1], keys[2], keys[3]);
        }
    }

    public static class NameSupportBitmap implements JsonSerializable {
        public boolean sceneNames;

        public NameSupportBitmap(boolean sceneNames) {
            this.sceneNames = sceneNames;
        }

        public String toJson() {
            String out = "{";
            out += "\"sceneNames\" : " + sceneNames + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static NameSupportBitmap fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new NameSupportBitmap(keys[0]);
        }
    }

    public Integer sceneCount; // 68 int8u
    public Integer currentScene; // 147 int8u
    public List<Integer> currentGroup; // 216 group_id
    public Boolean sceneValid; // 280 boolean
    public NameSupportBitmap nameSupport; // 329 NameSupportBitmap
    public Long lastConfiguredBy; // 369 node_id
    public Integer sceneTableSize; // 403 int16u
    public SceneInfoStruct[] fabricSceneInfo; // 434 SceneInfoStruct
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public ScenesCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 64, "Scenes");
    }

    public void addScene(MatterClient client, List<Integer> groupID, Integer sceneID, Integer transitionTime,
            String sceneName, ExtensionFieldSet extensionFieldSets) throws Exception {
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        final Integer _transitionTime = transitionTime;
        final String _sceneName = sceneName;
        final ExtensionFieldSet _extensionFieldSets = extensionFieldSets;
        Object o = new Object() {
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
            public Integer transitionTime = _transitionTime;
            public String sceneName = _sceneName;
            public ExtensionFieldSet extensionFieldSets = _extensionFieldSets;
        };
        sendCommand(client, "addScene", o);
    }

    public void addSceneResponse(MatterClient client, Integer status, List<Integer> groupID, Integer sceneID)
            throws Exception {
        final Integer _status = status;
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        Object o = new Object() {
            public Integer status = _status;
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
        };
        sendCommand(client, "addSceneResponse", o);
    }

    public void viewScene(MatterClient client, List<Integer> groupID, Integer sceneID) throws Exception {
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        Object o = new Object() {
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
        };
        sendCommand(client, "viewScene", o);
    }

    public void viewSceneResponse(MatterClient client, Integer status, List<Integer> groupID, Integer sceneID,
            Integer transitionTime, String sceneName, ExtensionFieldSet extensionFieldSets) throws Exception {
        final Integer _status = status;
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        final Integer _transitionTime = transitionTime;
        final String _sceneName = sceneName;
        final ExtensionFieldSet _extensionFieldSets = extensionFieldSets;
        Object o = new Object() {
            public Integer status = _status;
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
            public Integer transitionTime = _transitionTime;
            public String sceneName = _sceneName;
            public ExtensionFieldSet extensionFieldSets = _extensionFieldSets;
        };
        sendCommand(client, "viewSceneResponse", o);
    }

    public void removeScene(MatterClient client, List<Integer> groupID, Integer sceneID) throws Exception {
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        Object o = new Object() {
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
        };
        sendCommand(client, "removeScene", o);
    }

    public void removeSceneResponse(MatterClient client, Integer status, List<Integer> groupID, Integer sceneID)
            throws Exception {
        final Integer _status = status;
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        Object o = new Object() {
            public Integer status = _status;
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
        };
        sendCommand(client, "removeSceneResponse", o);
    }

    public void removeAllScenes(MatterClient client, List<Integer> groupID) throws Exception {
        final List<Integer> _groupID = groupID;
        Object o = new Object() {
            public List<Integer> groupID = _groupID;
        };
        sendCommand(client, "removeAllScenes", o);
    }

    public void removeAllScenesResponse(MatterClient client, Integer status, List<Integer> groupID) throws Exception {
        final Integer _status = status;
        final List<Integer> _groupID = groupID;
        Object o = new Object() {
            public Integer status = _status;
            public List<Integer> groupID = _groupID;
        };
        sendCommand(client, "removeAllScenesResponse", o);
    }

    public void storeScene(MatterClient client, List<Integer> groupID, Integer sceneID) throws Exception {
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        Object o = new Object() {
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
        };
        sendCommand(client, "storeScene", o);
    }

    public void storeSceneResponse(MatterClient client, Integer status, List<Integer> groupID, Integer sceneID)
            throws Exception {
        final Integer _status = status;
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        Object o = new Object() {
            public Integer status = _status;
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
        };
        sendCommand(client, "storeSceneResponse", o);
    }

    public void recallScene(MatterClient client, List<Integer> groupID, Integer sceneID, Integer transitionTime)
            throws Exception {
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        final Integer _transitionTime = transitionTime;
        Object o = new Object() {
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
            public Integer transitionTime = _transitionTime;
        };
        sendCommand(client, "recallScene", o);
    }

    public void getSceneMembership(MatterClient client, List<Integer> groupID) throws Exception {
        final List<Integer> _groupID = groupID;
        Object o = new Object() {
            public List<Integer> groupID = _groupID;
        };
        sendCommand(client, "getSceneMembership", o);
    }

    public void getSceneMembershipResponse(MatterClient client, Integer status, Integer capacity, List<Integer> groupID,
            Integer sceneList) throws Exception {
        final Integer _status = status;
        final Integer _capacity = capacity;
        final List<Integer> _groupID = groupID;
        final Integer _sceneList = sceneList;
        Object o = new Object() {
            public Integer status = _status;
            public Integer capacity = _capacity;
            public List<Integer> groupID = _groupID;
            public Integer sceneList = _sceneList;
        };
        sendCommand(client, "getSceneMembershipResponse", o);
    }

    public void enhancedAddScene(MatterClient client, List<Integer> groupID, Integer sceneID, Integer transitionTime,
            String sceneName, ExtensionFieldSet extensionFieldSets) throws Exception {
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        final Integer _transitionTime = transitionTime;
        final String _sceneName = sceneName;
        final ExtensionFieldSet _extensionFieldSets = extensionFieldSets;
        Object o = new Object() {
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
            public Integer transitionTime = _transitionTime;
            public String sceneName = _sceneName;
            public ExtensionFieldSet extensionFieldSets = _extensionFieldSets;
        };
        sendCommand(client, "enhancedAddScene", o);
    }

    public void enhancedAddSceneResponse(MatterClient client, Integer status, List<Integer> groupID, Integer sceneID)
            throws Exception {
        final Integer _status = status;
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        Object o = new Object() {
            public Integer status = _status;
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
        };
        sendCommand(client, "enhancedAddSceneResponse", o);
    }

    public void enhancedViewScene(MatterClient client, List<Integer> groupID, Integer sceneID) throws Exception {
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        Object o = new Object() {
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
        };
        sendCommand(client, "enhancedViewScene", o);
    }

    public void enhancedViewSceneResponse(MatterClient client, Integer status, List<Integer> groupID, Integer sceneID,
            Integer transitionTime, String sceneName, ExtensionFieldSet extensionFieldSets) throws Exception {
        final Integer _status = status;
        final List<Integer> _groupID = groupID;
        final Integer _sceneID = sceneID;
        final Integer _transitionTime = transitionTime;
        final String _sceneName = sceneName;
        final ExtensionFieldSet _extensionFieldSets = extensionFieldSets;
        Object o = new Object() {
            public Integer status = _status;
            public List<Integer> groupID = _groupID;
            public Integer sceneID = _sceneID;
            public Integer transitionTime = _transitionTime;
            public String sceneName = _sceneName;
            public ExtensionFieldSet extensionFieldSets = _extensionFieldSets;
        };
        sendCommand(client, "enhancedViewSceneResponse", o);
    }

    public void copyScene(MatterClient client, CopyModeBitmap mode, List<Integer> groupIdentifierFrom,
            Integer sceneIdentifierFrom, List<Integer> groupIdentifierTo, Integer sceneIdentifierTo) throws Exception {
        final CopyModeBitmap _mode = mode;
        final List<Integer> _groupIdentifierFrom = groupIdentifierFrom;
        final Integer _sceneIdentifierFrom = sceneIdentifierFrom;
        final List<Integer> _groupIdentifierTo = groupIdentifierTo;
        final Integer _sceneIdentifierTo = sceneIdentifierTo;
        Object o = new Object() {
            public CopyModeBitmap mode = _mode;
            public List<Integer> groupIdentifierFrom = _groupIdentifierFrom;
            public Integer sceneIdentifierFrom = _sceneIdentifierFrom;
            public List<Integer> groupIdentifierTo = _groupIdentifierTo;
            public Integer sceneIdentifierTo = _sceneIdentifierTo;
        };
        sendCommand(client, "copyScene", o);
    }

    public void copySceneResponse(MatterClient client, Integer status, List<Integer> groupIdentifierFrom,
            Integer sceneIdentifierFrom) throws Exception {
        final Integer _status = status;
        final List<Integer> _groupIdentifierFrom = groupIdentifierFrom;
        final Integer _sceneIdentifierFrom = sceneIdentifierFrom;
        Object o = new Object() {
            public Integer status = _status;
            public List<Integer> groupIdentifierFrom = _groupIdentifierFrom;
            public Integer sceneIdentifierFrom = _sceneIdentifierFrom;
        };
        sendCommand(client, "copySceneResponse", o);
    }

    public String toString() {
        String str = "";
        str += "sceneCount : " + sceneCount + "\n";
        str += "currentScene : " + currentScene + "\n";
        str += "currentGroup : " + currentGroup + "\n";
        str += "sceneValid : " + sceneValid + "\n";
        str += "nameSupport : " + nameSupport + "\n";
        str += "lastConfiguredBy : " + lastConfiguredBy + "\n";
        str += "sceneTableSize : " + sceneTableSize + "\n";
        str += "fabricSceneInfo : " + fabricSceneInfo + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

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

import org.openhab.binding.matter.internal.client.model.cluster.types.*;

/**
 * RefrigeratorAlarm
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class RefrigeratorAlarmCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "REFRIGERATOR_ALARM_CLUSTER";
    public static final int CLUSTER_ID = 0x0057;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(67, "mask"), entry(145, "state"), entry(214, "supported"),
                entry(13, "generatedCommandList"), entry(11, "acceptedCommandList"), entry(9, "eventList"),
                entry(7, "attributeList"), entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries();
    }

    // ZCL Bitmaps
    public static class AlarmMap implements JsonSerializable {
        public boolean doorOpen;

        public AlarmMap(boolean doorOpen) {
            this.doorOpen = doorOpen;
        }

        public String toJson() {
            String out = "{";
            out += "\"doorOpen\" : " + doorOpen + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static AlarmMap fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new AlarmMap(keys[0]);
        }
    }

    public AlarmMap mask; // 67 AlarmMap
    public AlarmMap state; // 145 AlarmMap
    public AlarmMap supported; // 214 AlarmMap
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public RefrigeratorAlarmCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 57, "RefrigeratorAlarm");
    }

    public String toString() {
        String str = "";
        str += "mask : " + mask + "\n";
        str += "state : " + state + "\n";
        str += "supported : " + supported + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

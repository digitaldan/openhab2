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
 * AirQuality
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class AirQualityCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "AIR_QUALITY_CLUSTER";
    public static final int CLUSTER_ID = 0x005B;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(25, "airQuality"), entry(13, "generatedCommandList"),
                entry(11, "acceptedCommandList"), entry(9, "eventList"), entry(7, "attributeList"),
                entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries();
    }

    // ZCL Enums
    public enum AirQualityEnum implements JsonSerializable {
        UNKNOWN(0, "Unknown"),
        GOOD(1, "Good"),
        FAIR(2, "Fair"),
        MODERATE(3, "Moderate"),
        POOR(4, "Poor"),
        VERY_POOR(5, "Very poor"),
        EXTREMELY_POOR(6, "Extremely poor"),
        UNKNOWN_VALUE(7, "UnknownValue");

        public final int value;
        public final String label;

        private AirQualityEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    // ZCL Bitmaps
    public static class Feature implements JsonSerializable {
        public boolean fair;
        public boolean moderate;
        public boolean veryPoor;
        public boolean extremelyPoor;

        public Feature(boolean fair, boolean moderate, boolean veryPoor, boolean extremelyPoor) {
            this.fair = fair;
            this.moderate = moderate;
            this.veryPoor = veryPoor;
            this.extremelyPoor = extremelyPoor;
        }

        public String toJson() {
            String out = "{";
            out += "\"fair\" : " + fair + ",";
            out += "\"moderate\" : " + moderate + ",";
            out += "\"veryPoor\" : " + veryPoor + ",";
            out += "\"extremelyPoor\" : " + extremelyPoor + "";
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

    public AirQualityEnum airQuality; // 25 AirQualityEnum
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public AirQualityCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 15, "AirQuality");
    }

    public String toString() {
        String str = "";
        str += "airQuality : " + airQuality + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

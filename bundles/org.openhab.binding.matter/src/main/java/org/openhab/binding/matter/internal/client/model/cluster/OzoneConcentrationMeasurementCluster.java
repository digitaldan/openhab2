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
 * OzoneConcentrationMeasurement
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class OzoneConcentrationMeasurementCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "OZONE_CONCENTRATION_MEASUREMENT_CLUSTER";
    public static final int CLUSTER_ID = 0x0415;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(815, "measuredValue"), entry(820, "minMeasuredValue"),
                entry(825, "maxMeasuredValue"), entry(830, "peakMeasuredValue"), entry(835, "peakMeasuredValueWindow"),
                entry(840, "averageMeasuredValue"), entry(848, "averageMeasuredValueWindow"), entry(852, "uncertainty"),
                entry(856, "measurementUnit"), entry(860, "measurementMedium"), entry(864, "levelValue"),
                entry(13, "generatedCommandList"), entry(11, "acceptedCommandList"), entry(9, "eventList"),
                entry(7, "attributeList"), entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries();
    }

    // ZCL Enums
    public enum LevelValueEnum implements JsonSerializable {
        UNKNOWN(0, "Unknown"),
        LOW(1, "Low"),
        MEDIUM(2, "Medium"),
        HIGH(3, "High"),
        CRITICAL(4, "Critical"),
        UNKNOWN_VALUE(5, "UnknownValue");

        public final int value;
        public final String label;

        private LevelValueEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum MeasurementMediumEnum implements JsonSerializable {
        AIR(0, "Air"),
        WATER(1, "Water"),
        SOIL(2, "Soil"),
        UNKNOWN_VALUE(3, "UnknownValue");

        public final int value;
        public final String label;

        private MeasurementMediumEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum MeasurementUnitEnum implements JsonSerializable {
        PPM(0, "PPM"),
        PPB(1, "PPB"),
        PPT(2, "PPT"),
        MGM3(3, "MGM3"),
        UGM3(4, "UGM3"),
        NGM3(5, "NGM3"),
        PM3(6, "PM3"),
        BQM3(7, "BQM3"),
        UNKNOWN_VALUE(8, "UnknownValue");

        public final int value;
        public final String label;

        private MeasurementUnitEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    // ZCL Bitmaps
    public static class Feature implements JsonSerializable {
        public boolean numericMeasurement;
        public boolean levelIndication;
        public boolean mediumLevel;
        public boolean criticalLevel;
        public boolean peakMeasurement;
        public boolean averageMeasurement;

        public Feature(boolean numericMeasurement, boolean levelIndication, boolean mediumLevel, boolean criticalLevel,
                boolean peakMeasurement, boolean averageMeasurement) {
            this.numericMeasurement = numericMeasurement;
            this.levelIndication = levelIndication;
            this.mediumLevel = mediumLevel;
            this.criticalLevel = criticalLevel;
            this.peakMeasurement = peakMeasurement;
            this.averageMeasurement = averageMeasurement;
        }

        public String toJson() {
            String out = "{";
            out += "\"numericMeasurement\" : " + numericMeasurement + ",";
            out += "\"levelIndication\" : " + levelIndication + ",";
            out += "\"mediumLevel\" : " + mediumLevel + ",";
            out += "\"criticalLevel\" : " + criticalLevel + ",";
            out += "\"peakMeasurement\" : " + peakMeasurement + ",";
            out += "\"averageMeasurement\" : " + averageMeasurement + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static Feature fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new Feature(keys[0], keys[1], keys[2], keys[3], keys[4], keys[5]);
        }
    }

    public Float measuredValue; // 815 single
    public Float minMeasuredValue; // 820 single
    public Float maxMeasuredValue; // 825 single
    public Float peakMeasuredValue; // 830 single
    public Integer peakMeasuredValueWindow; // 835 elapsed_s
    public Float averageMeasuredValue; // 840 single
    public Integer averageMeasuredValueWindow; // 848 elapsed_s
    public Float uncertainty; // 852 single
    public MeasurementUnitEnum measurementUnit; // 856 MeasurementUnitEnum
    public MeasurementMediumEnum measurementMedium; // 860 MeasurementMediumEnum
    public LevelValueEnum levelValue; // 864 LevelValueEnum
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public OzoneConcentrationMeasurementCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 102, "OzoneConcentrationMeasurement");
    }

    public String toString() {
        String str = "";
        str += "measuredValue : " + measuredValue + "\n";
        str += "minMeasuredValue : " + minMeasuredValue + "\n";
        str += "maxMeasuredValue : " + maxMeasuredValue + "\n";
        str += "peakMeasuredValue : " + peakMeasuredValue + "\n";
        str += "peakMeasuredValueWindow : " + peakMeasuredValueWindow + "\n";
        str += "averageMeasuredValue : " + averageMeasuredValue + "\n";
        str += "averageMeasuredValueWindow : " + averageMeasuredValueWindow + "\n";
        str += "uncertainty : " + uncertainty + "\n";
        str += "measurementUnit : " + measurementUnit + "\n";
        str += "measurementMedium : " + measurementMedium + "\n";
        str += "levelValue : " + levelValue + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

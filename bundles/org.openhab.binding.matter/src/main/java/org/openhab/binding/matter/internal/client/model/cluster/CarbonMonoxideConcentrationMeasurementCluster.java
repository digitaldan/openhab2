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
 * CarbonMonoxideConcentrationMeasurement
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class CarbonMonoxideConcentrationMeasurementCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "CARBON_MONOXIDE_CONCENTRATION_MEASUREMENT_CLUSTER";
    public static final int CLUSTER_ID = 0x040C;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(476, "measuredValue"), entry(500, "minMeasuredValue"),
                entry(522, "maxMeasuredValue"), entry(544, "peakMeasuredValue"), entry(563, "peakMeasuredValueWindow"),
                entry(577, "averageMeasuredValue"), entry(589, "averageMeasuredValueWindow"), entry(602, "uncertainty"),
                entry(613, "measurementUnit"), entry(625, "measurementMedium"), entry(637, "levelValue"),
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

    public Float measuredValue; // 476 single
    public Float minMeasuredValue; // 500 single
    public Float maxMeasuredValue; // 522 single
    public Float peakMeasuredValue; // 544 single
    public Integer peakMeasuredValueWindow; // 563 elapsed_s
    public Float averageMeasuredValue; // 577 single
    public Integer averageMeasuredValueWindow; // 589 elapsed_s
    public Float uncertainty; // 602 single
    public MeasurementUnitEnum measurementUnit; // 613 MeasurementUnitEnum
    public MeasurementMediumEnum measurementMedium; // 625 MeasurementMediumEnum
    public LevelValueEnum levelValue; // 637 LevelValueEnum
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public CarbonMonoxideConcentrationMeasurementCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 12, "CarbonMonoxideConcentrationMeasurement");
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

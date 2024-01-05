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
 * FanControl
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class FanControlCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "FAN_CONTROL_CLUSTER";
    public static final int CLUSTER_ID = 0x0202;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(31, "fanMode"), entry(111, "fanModeSequence"),
                entry(187, "percentSetting"), entry(251, "percentCurrent"), entry(307, "speedMax"),
                entry(351, "speedSetting"), entry(389, "speedCurrent"), entry(421, "rockSupport"),
                entry(449, "rockSetting"), entry(477, "windSupport"), entry(501, "windSetting"),
                entry(523, "airflowDirection"), entry(13, "generatedCommandList"), entry(11, "acceptedCommandList"),
                entry(9, "eventList"), entry(7, "attributeList"), entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries(entry(15, "step"));
    }

    // ZCL Enums
    public enum AirflowDirectionEnum implements JsonSerializable {
        FORWARD(0, "Forward"),
        REVERSE(1, "Reverse"),
        UNKNOWN_VALUE(2, "UnknownValue");

        public final int value;
        public final String label;

        private AirflowDirectionEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum FanModeEnum implements JsonSerializable {
        OFF(0, "Off"),
        LOW(1, "Low"),
        MEDIUM(2, "Medium"),
        HIGH(3, "High"),
        ON(4, "On"),
        AUTO(5, "Auto"),
        SMART(6, "Smart"),
        UNKNOWN_VALUE(7, "UnknownValue");

        public final int value;
        public final String label;

        private FanModeEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum FanModeSequenceEnum implements JsonSerializable {
        OFF_LOW_MED_HIGH(0, "Off/Low/Med/High"),
        OFF_LOW_HIGH(1, "Off/Low/High"),
        OFF_LOW_MED_HIGH_AUTO(2, "Off/Low/Med/High/Auto"),
        OFF_LOW_HIGH_AUTO(3, "Off/Low/High/Auto"),
        OFF_HIGH_AUTO(4, "Off/High/Auto"),
        OFF_HIGH(5, "Off/High"),
        UNKNOWN_VALUE(6, "UnknownValue");

        public final int value;
        public final String label;

        private FanModeSequenceEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum StepDirectionEnum implements JsonSerializable {
        INCREASE(0, "Increase"),
        DECREASE(1, "Decrease"),
        UNKNOWN_VALUE(2, "UnknownValue");

        public final int value;
        public final String label;

        private StepDirectionEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    // ZCL Bitmaps
    public static class Feature implements JsonSerializable {
        public boolean multiSpeed;
        public boolean auto;
        public boolean rocking;
        public boolean wind;
        public boolean step;
        public boolean airflowDirection;

        public Feature(boolean multiSpeed, boolean auto, boolean rocking, boolean wind, boolean step,
                boolean airflowDirection) {
            this.multiSpeed = multiSpeed;
            this.auto = auto;
            this.rocking = rocking;
            this.wind = wind;
            this.step = step;
            this.airflowDirection = airflowDirection;
        }

        public String toJson() {
            String out = "{";
            out += "\"multiSpeed\" : " + multiSpeed + ",";
            out += "\"auto\" : " + auto + ",";
            out += "\"rocking\" : " + rocking + ",";
            out += "\"wind\" : " + wind + ",";
            out += "\"step\" : " + step + ",";
            out += "\"airflowDirection\" : " + airflowDirection + "";
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

    public static class RockBitmap implements JsonSerializable {
        public boolean rockLeftRight;
        public boolean rockUpDown;
        public boolean rockRound;

        public RockBitmap(boolean rockLeftRight, boolean rockUpDown, boolean rockRound) {
            this.rockLeftRight = rockLeftRight;
            this.rockUpDown = rockUpDown;
            this.rockRound = rockRound;
        }

        public String toJson() {
            String out = "{";
            out += "\"rockLeftRight\" : " + rockLeftRight + ",";
            out += "\"rockUpDown\" : " + rockUpDown + ",";
            out += "\"rockRound\" : " + rockRound + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static RockBitmap fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new RockBitmap(keys[0], keys[1], keys[2]);
        }
    }

    public static class WindBitmap implements JsonSerializable {
        public boolean sleepWind;
        public boolean naturalWind;

        public WindBitmap(boolean sleepWind, boolean naturalWind) {
            this.sleepWind = sleepWind;
            this.naturalWind = naturalWind;
        }

        public String toJson() {
            String out = "{";
            out += "\"sleepWind\" : " + sleepWind + ",";
            out += "\"naturalWind\" : " + naturalWind + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static WindBitmap fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new WindBitmap(keys[0], keys[1]);
        }
    }

    public FanModeEnum fanMode; // 31 FanModeEnum
    public FanModeSequenceEnum fanModeSequence; // 111 FanModeSequenceEnum
    public Integer percentSetting; // 187 percent
    public Integer percentCurrent; // 251 percent
    public Integer speedMax; // 307 int8u
    public Integer speedSetting; // 351 int8u
    public Integer speedCurrent; // 389 int8u
    public RockBitmap rockSupport; // 421 RockBitmap
    public RockBitmap rockSetting; // 449 RockBitmap
    public WindBitmap windSupport; // 477 WindBitmap
    public WindBitmap windSetting; // 501 WindBitmap
    public AirflowDirectionEnum airflowDirection; // 523 AirflowDirectionEnum
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public FanControlCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 20, "FanControl");
    }

    public void step(MatterClient client, StepDirectionEnum direction, Boolean wrap, Boolean lowestOff)
            throws Exception {
        final StepDirectionEnum _direction = direction;
        final Boolean _wrap = wrap;
        final Boolean _lowestOff = lowestOff;
        Object o = new Object() {
            public StepDirectionEnum direction = _direction;
            public Boolean wrap = _wrap;
            public Boolean lowestOff = _lowestOff;
        };
        sendCommand(client, "step", o);
    }

    public String toString() {
        String str = "";
        str += "fanMode : " + fanMode + "\n";
        str += "fanModeSequence : " + fanModeSequence + "\n";
        str += "percentSetting : " + percentSetting + "\n";
        str += "percentCurrent : " + percentCurrent + "\n";
        str += "speedMax : " + speedMax + "\n";
        str += "speedSetting : " + speedSetting + "\n";
        str += "speedCurrent : " + speedCurrent + "\n";
        str += "rockSupport : " + rockSupport + "\n";
        str += "rockSetting : " + rockSetting + "\n";
        str += "windSupport : " + windSupport + "\n";
        str += "windSetting : " + windSetting + "\n";
        str += "airflowDirection : " + airflowDirection + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

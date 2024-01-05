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
 * BallastConfiguration
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class BallastConfigurationCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "BALLAST_CONFIGURATION_CLUSTER";
    public static final int CLUSTER_ID = 0x0301;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(20, "physicalMinLevel"), entry(102, "physicalMaxLevel"),
                entry(178, "ballastStatus"), entry(243, "minLevel"), entry(300, "maxLevel"),
                entry(344, "intrinsicBallastFactor"), entry(383, "ballastFactorAdjustment"), entry(415, "lampQuantity"),
                entry(446, "lampType"), entry(473, "lampManufacturer"), entry(497, "lampRatedHours"),
                entry(518, "lampBurnHours"), entry(540, "lampAlarmMode"), entry(559, "lampBurnHoursTripPoint"),
                entry(13, "generatedCommandList"), entry(11, "acceptedCommandList"), entry(9, "eventList"),
                entry(7, "attributeList"), entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries();
    }

    // ZCL Bitmaps
    public static class BallastStatusBitmap implements JsonSerializable {
        public boolean ballastNonOperational;
        public boolean lampFailure;

        public BallastStatusBitmap(boolean ballastNonOperational, boolean lampFailure) {
            this.ballastNonOperational = ballastNonOperational;
            this.lampFailure = lampFailure;
        }

        public String toJson() {
            String out = "{";
            out += "\"ballastNonOperational\" : " + ballastNonOperational + ",";
            out += "\"lampFailure\" : " + lampFailure + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static BallastStatusBitmap fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new BallastStatusBitmap(keys[0], keys[1]);
        }
    }

    public static class LampAlarmModeBitmap implements JsonSerializable {
        public boolean lampBurnHours;

        public LampAlarmModeBitmap(boolean lampBurnHours) {
            this.lampBurnHours = lampBurnHours;
        }

        public String toJson() {
            String out = "{";
            out += "\"lampBurnHours\" : " + lampBurnHours + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static LampAlarmModeBitmap fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new LampAlarmModeBitmap(keys[0]);
        }
    }

    public Integer physicalMinLevel; // 20 int8u
    public Integer physicalMaxLevel; // 102 int8u
    public BallastStatusBitmap ballastStatus; // 178 BallastStatusBitmap
    public Integer minLevel; // 243 int8u
    public Integer maxLevel; // 300 int8u
    public Integer intrinsicBallastFactor; // 344 int8u
    public Integer ballastFactorAdjustment; // 383 int8u
    public Integer lampQuantity; // 415 int8u
    public String lampType; // 446 char_string
    public String lampManufacturer; // 473 char_string
    public Integer lampRatedHours; // 497 int24u
    public Integer lampBurnHours; // 518 int24u
    public LampAlarmModeBitmap lampAlarmMode; // 540 LampAlarmModeBitmap
    public Integer lampBurnHoursTripPoint; // 559 int24u
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public BallastConfigurationCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 7, "BallastConfiguration");
    }

    public String toString() {
        String str = "";
        str += "physicalMinLevel : " + physicalMinLevel + "\n";
        str += "physicalMaxLevel : " + physicalMaxLevel + "\n";
        str += "ballastStatus : " + ballastStatus + "\n";
        str += "minLevel : " + minLevel + "\n";
        str += "maxLevel : " + maxLevel + "\n";
        str += "intrinsicBallastFactor : " + intrinsicBallastFactor + "\n";
        str += "ballastFactorAdjustment : " + ballastFactorAdjustment + "\n";
        str += "lampQuantity : " + lampQuantity + "\n";
        str += "lampType : " + lampType + "\n";
        str += "lampManufacturer : " + lampManufacturer + "\n";
        str += "lampRatedHours : " + lampRatedHours + "\n";
        str += "lampBurnHours : " + lampBurnHours + "\n";
        str += "lampAlarmMode : " + lampAlarmMode + "\n";
        str += "lampBurnHoursTripPoint : " + lampBurnHoursTripPoint + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

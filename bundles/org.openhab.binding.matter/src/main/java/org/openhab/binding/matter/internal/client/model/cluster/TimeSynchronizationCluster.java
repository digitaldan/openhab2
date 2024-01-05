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
 * TimeSynchronization
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class TimeSynchronizationCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "TIME_SYNCHRONIZATION_CLUSTER";
    public static final int CLUSTER_ID = 0x0038;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(84, "UTCTime"), entry(164, "granularity"), entry(231, "timeSource"),
                entry(289, "trustedTimeSource"), entry(337, "defaultNTP"), entry(376, "timeZone"),
                entry(413, "DSTOffset"), entry(444, "localTime"), entry(471, "timeZoneDatabase"),
                entry(495, "NTPServerAvailable"), entry(521, "timeZoneListMaxSize"), entry(543, "DSTOffsetListMaxSize"),
                entry(562, "supportsDNSResolve"), entry(13, "generatedCommandList"), entry(11, "acceptedCommandList"),
                entry(9, "eventList"), entry(7, "attributeList"), entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries(entry(53, "setUTCTime"), entry(103, "setTrustedTimeSource"),
                entry(141, "setTimeZone"), entry(172, "setTimeZoneResponse"), entry(195, "setDSTOffset"),
                entry(213, "setDefaultNTP"));
    }

    class DSTOffsetStruct implements JsonSerializable {
        public Integer offset; // int32s
        public Long validStarting; // epoch_us
        public Long validUntil; // epoch_us

        public DSTOffsetStruct(Integer offset, Long validStarting, Long validUntil) {
            this.offset = offset;
            this.validStarting = validStarting;
            this.validUntil = validUntil;
        }

        public String toJson() {
            String out = "{";
            out += "\"offset\" : " + offset + ",";
            out += "\"validStarting\" : " + validStarting + ",";
            out += "\"validUntil\" : " + validUntil + "";
            out += "}";
            return out;
        }
    }

    class FabricScopedTrustedTimeSourceStruct implements JsonSerializable {
        public Long nodeID; // node_id
        public List<Integer> endpoint; // endpoint_no

        public FabricScopedTrustedTimeSourceStruct(Long nodeID, List<Integer> endpoint) {
            this.nodeID = nodeID;
            this.endpoint = endpoint;
        }

        public String toJson() {
            String out = "{";
            out += "\"nodeID\" : " + nodeID + ",";
            out += "\"endpoint\" : " + endpoint + "";
            out += "}";
            return out;
        }
    }

    class TimeZoneStruct implements JsonSerializable {
        public Integer offset; // int32s
        public Long validAt; // epoch_us
        public String name; // char_string

        public TimeZoneStruct(Integer offset, Long validAt, String name) {
            this.offset = offset;
            this.validAt = validAt;
            this.name = name;
        }

        public String toJson() {
            String out = "{";
            out += "\"offset\" : " + offset + ",";
            out += "\"validAt\" : " + validAt + ",";
            out += "\"name\" : " + name + "";
            out += "}";
            return out;
        }
    }

    class TrustedTimeSourceStruct implements JsonSerializable {
        public Integer fabricIndex; // fabric_idx
        public Long nodeID; // node_id
        public List<Integer> endpoint; // endpoint_no

        public TrustedTimeSourceStruct(Integer fabricIndex, Long nodeID, List<Integer> endpoint) {
            this.fabricIndex = fabricIndex;
            this.nodeID = nodeID;
            this.endpoint = endpoint;
        }

        public String toJson() {
            String out = "{";
            out += "\"fabricIndex\" : " + fabricIndex + ",";
            out += "\"nodeID\" : " + nodeID + ",";
            out += "\"endpoint\" : " + endpoint + "";
            out += "}";
            return out;
        }
    }

    // ZCL Enums
    public enum GranularityEnum implements JsonSerializable {
        NOTIMEGRANULARITY(0, "NoTimeGranularity"),
        MINUTESGRANULARITY(1, "MinutesGranularity"),
        SECONDSGRANULARITY(2, "SecondsGranularity"),
        MILLISECONDSGRANULARITY(3, "MillisecondsGranularity"),
        MICROSECONDSGRANULARITY(4, "MicrosecondsGranularity"),
        UNKNOWN_VALUE(5, "UnknownValue");

        public final int value;
        public final String label;

        private GranularityEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum StatusCode implements JsonSerializable {
        TIMENOTACCEPTED(2, "TimeNotAccepted"),
        UNKNOWN_VALUE(0, "UnknownValue");

        public final int value;
        public final String label;

        private StatusCode(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum TimeSourceEnum implements JsonSerializable {
        NONE(0, "None"),
        UNKNOWN(1, "Unknown"),
        ADMIN(2, "Admin"),
        NODETIMECLUSTER(3, "NodeTimeCluster"),
        NONMATTERSNTP(4, "NonMatterSNTP"),
        NONMATTERNTP(5, "NonMatterNTP"),
        MATTERSNTP(6, "MatterSNTP"),
        MATTERNTP(7, "MatterNTP"),
        MIXEDNTP(8, "MixedNTP"),
        NONMATTERSNTPNTS(9, "NonMatterSNTPNTS"),
        NONMATTERNTPNTS(10, "NonMatterNTPNTS"),
        MATTERSNTPNTS(11, "MatterSNTPNTS"),
        MATTERNTPNTS(12, "MatterNTPNTS"),
        MIXEDNTPNTS(13, "MixedNTPNTS"),
        CLOUDSOURCE(14, "CloudSource"),
        PTP(15, "PTP"),
        GNSS(16, "GNSS"),
        UNKNOWN_VALUE(17, "UnknownValue");

        public final int value;
        public final String label;

        private TimeSourceEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum TimeZoneDatabaseEnum implements JsonSerializable {
        FULL(0, "Full"),
        PARTIAL(1, "Partial"),
        NONE(2, "None"),
        UNKNOWN_VALUE(3, "UnknownValue");

        public final int value;
        public final String label;

        private TimeZoneDatabaseEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    // ZCL Bitmaps
    public static class Feature implements JsonSerializable {
        public boolean timeZone;
        public boolean NTPClient;
        public boolean NTPServer;
        public boolean timeSyncClient;

        public Feature(boolean timeZone, boolean NTPClient, boolean NTPServer, boolean timeSyncClient) {
            this.timeZone = timeZone;
            this.NTPClient = NTPClient;
            this.NTPServer = NTPServer;
            this.timeSyncClient = timeSyncClient;
        }

        public String toJson() {
            String out = "{";
            out += "\"timeZone\" : " + timeZone + ",";
            out += "\"NTPClient\" : " + NTPClient + ",";
            out += "\"NTPServer\" : " + NTPServer + ",";
            out += "\"timeSyncClient\" : " + timeSyncClient + "";
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

    public Long UTCTime; // 84 epoch_us
    public GranularityEnum granularity; // 164 GranularityEnum
    public TimeSourceEnum timeSource; // 231 TimeSourceEnum
    public TrustedTimeSourceStruct[] trustedTimeSource; // 289 TrustedTimeSourceStruct
    public String defaultNTP; // 337 char_string
    public TimeZoneStruct[] timeZone; // 376 TimeZoneStruct
    public DSTOffsetStruct[] DSTOffset; // 413 DSTOffsetStruct
    public Long localTime; // 444 epoch_us
    public TimeZoneDatabaseEnum timeZoneDatabase; // 471 TimeZoneDatabaseEnum
    public Boolean NTPServerAvailable; // 495 boolean
    public Integer timeZoneListMaxSize; // 521 int8u
    public Integer DSTOffsetListMaxSize; // 543 int8u
    public Boolean supportsDNSResolve; // 562 boolean
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public TimeSynchronizationCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 83, "TimeSynchronization");
    }

    public void setUTCTime(MatterClient client, Long UTCTime, GranularityEnum granularity, TimeSourceEnum timeSource)
            throws Exception {
        final Long _UTCTime = UTCTime;
        final GranularityEnum _granularity = granularity;
        final TimeSourceEnum _timeSource = timeSource;
        Object o = new Object() {
            public Long UTCTime = _UTCTime;
            public GranularityEnum granularity = _granularity;
            public TimeSourceEnum timeSource = _timeSource;
        };
        sendCommand(client, "setUTCTime", o);
    }

    public void setTrustedTimeSource(MatterClient client, FabricScopedTrustedTimeSourceStruct[] trustedTimeSource)
            throws Exception {
        final FabricScopedTrustedTimeSourceStruct[] _trustedTimeSource = trustedTimeSource;
        Object o = new Object() {
            public FabricScopedTrustedTimeSourceStruct[] trustedTimeSource = _trustedTimeSource;
        };
        sendCommand(client, "setTrustedTimeSource", o);
    }

    public void setTimeZone(MatterClient client, TimeZoneStruct[] timeZone) throws Exception {
        final TimeZoneStruct[] _timeZone = timeZone;
        Object o = new Object() {
            public TimeZoneStruct[] timeZone = _timeZone;
        };
        sendCommand(client, "setTimeZone", o);
    }

    public void setTimeZoneResponse(MatterClient client, Boolean DSTOffsetRequired) throws Exception {
        final Boolean _DSTOffsetRequired = DSTOffsetRequired;
        Object o = new Object() {
            public Boolean DSTOffsetRequired = _DSTOffsetRequired;
        };
        sendCommand(client, "setTimeZoneResponse", o);
    }

    public void setDSTOffset(MatterClient client, DSTOffsetStruct[] DSTOffset) throws Exception {
        final DSTOffsetStruct[] _DSTOffset = DSTOffset;
        Object o = new Object() {
            public DSTOffsetStruct[] DSTOffset = _DSTOffset;
        };
        sendCommand(client, "setDSTOffset", o);
    }

    public void setDefaultNTP(MatterClient client, String defaultNTP) throws Exception {
        final String _defaultNTP = defaultNTP;
        Object o = new Object() {
            public String defaultNTP = _defaultNTP;
        };
        sendCommand(client, "setDefaultNTP", o);
    }

    public String toString() {
        String str = "";
        str += "UTCTime : " + UTCTime + "\n";
        str += "granularity : " + granularity + "\n";
        str += "timeSource : " + timeSource + "\n";
        str += "trustedTimeSource : " + trustedTimeSource + "\n";
        str += "defaultNTP : " + defaultNTP + "\n";
        str += "timeZone : " + timeZone + "\n";
        str += "DSTOffset : " + DSTOffset + "\n";
        str += "localTime : " + localTime + "\n";
        str += "timeZoneDatabase : " + timeZoneDatabase + "\n";
        str += "NTPServerAvailable : " + NTPServerAvailable + "\n";
        str += "timeZoneListMaxSize : " + timeZoneListMaxSize + "\n";
        str += "DSTOffsetListMaxSize : " + DSTOffsetListMaxSize + "\n";
        str += "supportsDNSResolve : " + supportsDNSResolve + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

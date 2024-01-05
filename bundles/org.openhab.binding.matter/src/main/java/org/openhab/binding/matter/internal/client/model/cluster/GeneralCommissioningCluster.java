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
 * GeneralCommissioning
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class GeneralCommissioningCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "GENERAL_COMMISSIONING_CLUSTER";
    public static final int CLUSTER_ID = 0x0030;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(37, "breadcrumb"), entry(116, "basicCommissioningInfo"),
                entry(189, "regulatoryConfig"), entry(253, "locationCapability"),
                entry(309, "supportsConcurrentConnection"), entry(13, "generatedCommandList"),
                entry(11, "acceptedCommandList"), entry(9, "eventList"), entry(7, "attributeList"),
                entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries(entry(20, "armFailSafe"), entry(73, "armFailSafeResponse"),
                entry(121, "setRegulatoryConfig"), entry(156, "setRegulatoryConfigResponse"),
                entry(182, "commissioningComplete"), entry(203, "commissioningCompleteResponse"));
    }

    class BasicCommissioningInfo implements JsonSerializable {
        public Integer failSafeExpiryLengthSeconds; // int16u
        public Integer maxCumulativeFailsafeSeconds; // int16u

        public BasicCommissioningInfo(Integer failSafeExpiryLengthSeconds, Integer maxCumulativeFailsafeSeconds) {
            this.failSafeExpiryLengthSeconds = failSafeExpiryLengthSeconds;
            this.maxCumulativeFailsafeSeconds = maxCumulativeFailsafeSeconds;
        }

        public String toJson() {
            String out = "{";
            out += "\"failSafeExpiryLengthSeconds\" : " + failSafeExpiryLengthSeconds + ",";
            out += "\"maxCumulativeFailsafeSeconds\" : " + maxCumulativeFailsafeSeconds + "";
            out += "}";
            return out;
        }
    }

    // ZCL Enums
    public enum CommissioningErrorEnum implements JsonSerializable {
        OK(0, "OK"),
        VALUEOUTSIDERANGE(1, "ValueOutsideRange"),
        INVALIDAUTHENTICATION(2, "InvalidAuthentication"),
        NOFAILSAFE(3, "NoFailSafe"),
        BUSYWITHOTHERADMIN(4, "BusyWithOtherAdmin"),
        UNKNOWN_VALUE(5, "UnknownValue");

        public final int value;
        public final String label;

        private CommissioningErrorEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum RegulatoryLocationTypeEnum implements JsonSerializable {
        INDOOR(0, "Indoor"),
        OUTDOOR(1, "Outdoor"),
        INDOOROUTDOOR(2, "IndoorOutdoor"),
        UNKNOWN_VALUE(3, "UnknownValue");

        public final int value;
        public final String label;

        private RegulatoryLocationTypeEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public Long breadcrumb; // 37 int64u
    public BasicCommissioningInfo basicCommissioningInfo; // 116 BasicCommissioningInfo
    public RegulatoryLocationTypeEnum regulatoryConfig; // 189 RegulatoryLocationTypeEnum
    public RegulatoryLocationTypeEnum locationCapability; // 253 RegulatoryLocationTypeEnum
    public Boolean supportsConcurrentConnection; // 309 boolean
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public GeneralCommissioningCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 27, "GeneralCommissioning");
    }

    public void armFailSafe(MatterClient client, Integer expiryLengthSeconds, Long breadcrumb) throws Exception {
        final Integer _expiryLengthSeconds = expiryLengthSeconds;
        final Long _breadcrumb = breadcrumb;
        Object o = new Object() {
            public Integer expiryLengthSeconds = _expiryLengthSeconds;
            public Long breadcrumb = _breadcrumb;
        };
        sendCommand(client, "armFailSafe", o);
    }

    public void armFailSafeResponse(MatterClient client, CommissioningErrorEnum errorCode, String debugText)
            throws Exception {
        final CommissioningErrorEnum _errorCode = errorCode;
        final String _debugText = debugText;
        Object o = new Object() {
            public CommissioningErrorEnum errorCode = _errorCode;
            public String debugText = _debugText;
        };
        sendCommand(client, "armFailSafeResponse", o);
    }

    public void setRegulatoryConfig(MatterClient client, RegulatoryLocationTypeEnum newRegulatoryConfig,
            String countryCode, Long breadcrumb) throws Exception {
        final RegulatoryLocationTypeEnum _newRegulatoryConfig = newRegulatoryConfig;
        final String _countryCode = countryCode;
        final Long _breadcrumb = breadcrumb;
        Object o = new Object() {
            public RegulatoryLocationTypeEnum newRegulatoryConfig = _newRegulatoryConfig;
            public String countryCode = _countryCode;
            public Long breadcrumb = _breadcrumb;
        };
        sendCommand(client, "setRegulatoryConfig", o);
    }

    public void setRegulatoryConfigResponse(MatterClient client, CommissioningErrorEnum errorCode, String debugText)
            throws Exception {
        final CommissioningErrorEnum _errorCode = errorCode;
        final String _debugText = debugText;
        Object o = new Object() {
            public CommissioningErrorEnum errorCode = _errorCode;
            public String debugText = _debugText;
        };
        sendCommand(client, "setRegulatoryConfigResponse", o);
    }

    public void commissioningComplete(MatterClient client) throws Exception {
        Object o = new Object() {
        };
        sendCommand(client, "commissioningComplete", o);
    }

    public void commissioningCompleteResponse(MatterClient client, CommissioningErrorEnum errorCode, String debugText)
            throws Exception {
        final CommissioningErrorEnum _errorCode = errorCode;
        final String _debugText = debugText;
        Object o = new Object() {
            public CommissioningErrorEnum errorCode = _errorCode;
            public String debugText = _debugText;
        };
        sendCommand(client, "commissioningCompleteResponse", o);
    }

    public String toString() {
        String str = "";
        str += "breadcrumb : " + breadcrumb + "\n";
        str += "basicCommissioningInfo : " + basicCommissioningInfo + "\n";
        str += "regulatoryConfig : " + regulatoryConfig + "\n";
        str += "locationCapability : " + locationCapability + "\n";
        str += "supportsConcurrentConnection : " + supportsConcurrentConnection + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

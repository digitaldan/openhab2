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
 * NetworkCommissioning
 * 
 * @author Dan Cunningham - Initial contribution
 */
public class NetworkCommissioningCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "NETWORK_COMMISSIONING_CLUSTER";
    public static final int CLUSTER_ID = 0x0031;

    static {
        ATTRIBUTE_MAPPING = Map.ofEntries(entry(131, "maxNetworks"), entry(199, "networks"),
                entry(262, "scanMaxTimeSeconds"), entry(318, "connectMaxTimeSeconds"), entry(360, "interfaceEnabled"),
                entry(396, "lastNetworkingStatus"), entry(429, "lastNetworkID"), entry(457, "lastConnectErrorValue"),
                entry(482, "supportedWiFiBands"), entry(504, "supportedThreadFeatures"), entry(526, "threadVersion"),
                entry(13, "generatedCommandList"), entry(11, "acceptedCommandList"), entry(9, "eventList"),
                entry(7, "attributeList"), entry(5, "featureMap"), entry(2, "clusterRevision"));
        COMMAND_MAPPING = Map.ofEntries(entry(85, "scanNetworks"), entry(131, "scanNetworksResponse"),
                entry(162, "addOrUpdateWiFiNetwork"), entry(190, "addOrUpdateThreadNetwork"),
                entry(209, "removeNetwork"), entry(223, "networkConfigResponse"), entry(235, "connectNetwork"),
                entry(244, "connectNetworkResponse"), entry(252, "reorderNetwork"), entry(259, "queryIdentity"),
                entry(266, "queryIdentityResponse"));
    }

    class NetworkInfoStruct implements JsonSerializable {
        public String networkID; // octet_string
        public Boolean connected; // boolean
        public String networkIdentifier; // octet_string
        public String clientIdentifier; // octet_string

        public NetworkInfoStruct(String networkID, Boolean connected, String networkIdentifier,
                String clientIdentifier) {
            this.networkID = networkID;
            this.connected = connected;
            this.networkIdentifier = networkIdentifier;
            this.clientIdentifier = clientIdentifier;
        }

        public String toJson() {
            String out = "{";
            out += "\"networkID\" : " + networkID + ",";
            out += "\"connected\" : " + connected + ",";
            out += "\"networkIdentifier\" : " + networkIdentifier + ",";
            out += "\"clientIdentifier\" : " + clientIdentifier + "";
            out += "}";
            return out;
        }
    }

    class ThreadInterfaceScanResultStruct implements JsonSerializable {
        public Integer panId; // int16u
        public Long extendedPanId; // int64u
        public String networkName; // char_string
        public Integer channel; // int16u
        public Integer version; // int8u
        public String extendedAddress; // octet_string
        public Integer rssi; // int8s
        public Integer lqi; // int8u

        public ThreadInterfaceScanResultStruct(Integer panId, Long extendedPanId, String networkName, Integer channel,
                Integer version, String extendedAddress, Integer rssi, Integer lqi) {
            this.panId = panId;
            this.extendedPanId = extendedPanId;
            this.networkName = networkName;
            this.channel = channel;
            this.version = version;
            this.extendedAddress = extendedAddress;
            this.rssi = rssi;
            this.lqi = lqi;
        }

        public String toJson() {
            String out = "{";
            out += "\"panId\" : " + panId + ",";
            out += "\"extendedPanId\" : " + extendedPanId + ",";
            out += "\"networkName\" : " + networkName + ",";
            out += "\"channel\" : " + channel + ",";
            out += "\"version\" : " + version + ",";
            out += "\"extendedAddress\" : " + extendedAddress + ",";
            out += "\"rssi\" : " + rssi + ",";
            out += "\"lqi\" : " + lqi + "";
            out += "}";
            return out;
        }
    }

    class WiFiInterfaceScanResultStruct implements JsonSerializable {
        public WiFiSecurityBitmap security; // WiFiSecurityBitmap
        public String ssid; // octet_string
        public String bssid; // octet_string
        public Integer channel; // int16u
        public WiFiBandEnum wiFiBand; // WiFiBandEnum
        public Integer rssi; // int8s

        public WiFiInterfaceScanResultStruct(WiFiSecurityBitmap security, String ssid, String bssid, Integer channel,
                WiFiBandEnum wiFiBand, Integer rssi) {
            this.security = security;
            this.ssid = ssid;
            this.bssid = bssid;
            this.channel = channel;
            this.wiFiBand = wiFiBand;
            this.rssi = rssi;
        }

        public String toJson() {
            String out = "{";
            out += "\"security\" : " + security + ",";
            out += "\"ssid\" : " + ssid + ",";
            out += "\"bssid\" : " + bssid + ",";
            out += "\"channel\" : " + channel + ",";
            out += "\"wiFiBand\" : " + wiFiBand + ",";
            out += "\"rssi\" : " + rssi + "";
            out += "}";
            return out;
        }
    }

    // ZCL Enums
    public enum NetworkCommissioningStatusEnum implements JsonSerializable {
        SUCCESS(0, "Success"),
        OUTOFRANGE(1, "OutOfRange"),
        BOUNDSEXCEEDED(2, "BoundsExceeded"),
        NETWORKIDNOTFOUND(3, "NetworkIDNotFound"),
        DUPLICATENETWORKID(4, "DuplicateNetworkID"),
        NETWORKNOTFOUND(5, "NetworkNotFound"),
        REGULATORYERROR(6, "RegulatoryError"),
        AUTHFAILURE(7, "AuthFailure"),
        UNSUPPORTEDSECURITY(8, "UnsupportedSecurity"),
        OTHERCONNECTIONFAILURE(9, "OtherConnectionFailure"),
        IPV6FAILED(10, "IPV6Failed"),
        IPBINDFAILED(11, "IPBindFailed"),
        UNKNOWNERROR(12, "UnknownError"),
        UNKNOWN_VALUE(13, "UnknownValue");

        public final int value;
        public final String label;

        private NetworkCommissioningStatusEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    public enum WiFiBandEnum implements JsonSerializable {
        T2G4(0, "2G4"),
        T3G65(1, "3G65"),
        T5G(2, "5G"),
        T6G(3, "6G"),
        T60G(4, "60G"),
        T1G(5, "1G"),
        UNKNOWN_VALUE(6, "UnknownValue");

        public final int value;
        public final String label;

        private WiFiBandEnum(int value, String label) {
            this.value = value;
            this.label = label;
        }

        public String toJson() {
            return "\"" + this.label + "\"";
        }
    };

    // ZCL Bitmaps
    public static class Feature implements JsonSerializable {
        public boolean wiFiNetworkInterface;
        public boolean threadNetworkInterface;
        public boolean ethernetNetworkInterface;
        public boolean perDeviceCredentials;

        public Feature(boolean wiFiNetworkInterface, boolean threadNetworkInterface, boolean ethernetNetworkInterface,
                boolean perDeviceCredentials) {
            this.wiFiNetworkInterface = wiFiNetworkInterface;
            this.threadNetworkInterface = threadNetworkInterface;
            this.ethernetNetworkInterface = ethernetNetworkInterface;
            this.perDeviceCredentials = perDeviceCredentials;
        }

        public String toJson() {
            String out = "{";
            out += "\"wiFiNetworkInterface\" : " + wiFiNetworkInterface + ",";
            out += "\"threadNetworkInterface\" : " + threadNetworkInterface + ",";
            out += "\"ethernetNetworkInterface\" : " + ethernetNetworkInterface + ",";
            out += "\"perDeviceCredentials\" : " + perDeviceCredentials + "";
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

    public static class ThreadCapabilitiesBitmap implements JsonSerializable {
        public boolean isBorderRouterCapable;
        public boolean isRouterCapable;
        public boolean isSleepyEndDeviceCapable;
        public boolean isFullThreadDevice;
        public boolean isSynchronizedSleepyEndDeviceCapable;

        public ThreadCapabilitiesBitmap(boolean isBorderRouterCapable, boolean isRouterCapable,
                boolean isSleepyEndDeviceCapable, boolean isFullThreadDevice,
                boolean isSynchronizedSleepyEndDeviceCapable) {
            this.isBorderRouterCapable = isBorderRouterCapable;
            this.isRouterCapable = isRouterCapable;
            this.isSleepyEndDeviceCapable = isSleepyEndDeviceCapable;
            this.isFullThreadDevice = isFullThreadDevice;
            this.isSynchronizedSleepyEndDeviceCapable = isSynchronizedSleepyEndDeviceCapable;
        }

        public String toJson() {
            String out = "{";
            out += "\"isBorderRouterCapable\" : " + isBorderRouterCapable + ",";
            out += "\"isRouterCapable\" : " + isRouterCapable + ",";
            out += "\"isSleepyEndDeviceCapable\" : " + isSleepyEndDeviceCapable + ",";
            out += "\"isFullThreadDevice\" : " + isFullThreadDevice + ",";
            out += "\"isSynchronizedSleepyEndDeviceCapable\" : " + isSynchronizedSleepyEndDeviceCapable + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static ThreadCapabilitiesBitmap fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new ThreadCapabilitiesBitmap(keys[0], keys[1], keys[2], keys[3], keys[4]);
        }
    }

    public static class WiFiSecurityBitmap implements JsonSerializable {
        public boolean unencrypted;
        public boolean wep;
        public boolean wpaPersonal;
        public boolean wpa2Personal;
        public boolean wpa3Personal;
        public boolean wpa3MatterPdc;

        public WiFiSecurityBitmap(boolean unencrypted, boolean wep, boolean wpaPersonal, boolean wpa2Personal,
                boolean wpa3Personal, boolean wpa3MatterPdc) {
            this.unencrypted = unencrypted;
            this.wep = wep;
            this.wpaPersonal = wpaPersonal;
            this.wpa2Personal = wpa2Personal;
            this.wpa3Personal = wpa3Personal;
            this.wpa3MatterPdc = wpa3MatterPdc;
        }

        public String toJson() {
            String out = "{";
            out += "\"unencrypted\" : " + unencrypted + ",";
            out += "\"wep\" : " + wep + ",";
            out += "\"wpaPersonal\" : " + wpaPersonal + ",";
            out += "\"wpa2Personal\" : " + wpa2Personal + ",";
            out += "\"wpa3Personal\" : " + wpa3Personal + ",";
            out += "\"wpa3MatterPdc\" : " + wpa3MatterPdc + "";
            out += "}";
            return out;
        }

        @SuppressWarnings({ "unchecked", "null" })
        public static WiFiSecurityBitmap fromJson(String json) {
            Map<String, Boolean> m = GSON.fromJson(json, Map.class);
            Boolean[] keys = m.values().toArray(new Boolean[0]);
            return new WiFiSecurityBitmap(keys[0], keys[1], keys[2], keys[3], keys[4], keys[5]);
        }
    }

    public Integer maxNetworks; // 131 int8u
    public NetworkInfoStruct[] networks; // 199 NetworkInfoStruct
    public Integer scanMaxTimeSeconds; // 262 int8u
    public Integer connectMaxTimeSeconds; // 318 int8u
    public Boolean interfaceEnabled; // 360 boolean
    public NetworkCommissioningStatusEnum lastNetworkingStatus; // 396 NetworkCommissioningStatusEnum
    public String lastNetworkID; // 429 octet_string
    public Integer lastConnectErrorValue; // 457 int32s
    public WiFiBandEnum supportedWiFiBands; // 482 WiFiBandEnum
    public ThreadCapabilitiesBitmap supportedThreadFeatures; // 504 ThreadCapabilitiesBitmap
    public Integer threadVersion; // 526 int16u
    public List<Integer> generatedCommandList; // 13 command_id
    public List<Integer> acceptedCommandList; // 11 command_id
    public List<Integer> eventList; // 9 event_id
    public List<Integer> attributeList; // 7 attrib_id
    public Map<String, Boolean> featureMap; // 5 bitmap32
    public Integer clusterRevision; // 2 int16u

    public NetworkCommissioningCluster(long nodeId, int endpointId) {
        super(nodeId, endpointId, 98, "NetworkCommissioning");
    }

    public void scanNetworks(MatterClient client, String ssid, Long breadcrumb) throws Exception {
        final String _ssid = ssid;
        final Long _breadcrumb = breadcrumb;
        Object o = new Object() {
            public String ssid = _ssid;
            public Long breadcrumb = _breadcrumb;
        };
        sendCommand(client, "scanNetworks", o);
    }

    public void scanNetworksResponse(MatterClient client, NetworkCommissioningStatusEnum networkingStatus,
            String debugText, WiFiInterfaceScanResultStruct[] wiFiScanResults,
            ThreadInterfaceScanResultStruct[] threadScanResults) throws Exception {
        final NetworkCommissioningStatusEnum _networkingStatus = networkingStatus;
        final String _debugText = debugText;
        final WiFiInterfaceScanResultStruct[] _wiFiScanResults = wiFiScanResults;
        final ThreadInterfaceScanResultStruct[] _threadScanResults = threadScanResults;
        Object o = new Object() {
            public NetworkCommissioningStatusEnum networkingStatus = _networkingStatus;
            public String debugText = _debugText;
            public WiFiInterfaceScanResultStruct[] wiFiScanResults = _wiFiScanResults;
            public ThreadInterfaceScanResultStruct[] threadScanResults = _threadScanResults;
        };
        sendCommand(client, "scanNetworksResponse", o);
    }

    public void addOrUpdateWiFiNetwork(MatterClient client, String ssid, String credentials, Long breadcrumb,
            String networkIdentity, String clientIdentifier, String possessionNonce) throws Exception {
        final String _ssid = ssid;
        final String _credentials = credentials;
        final Long _breadcrumb = breadcrumb;
        final String _networkIdentity = networkIdentity;
        final String _clientIdentifier = clientIdentifier;
        final String _possessionNonce = possessionNonce;
        Object o = new Object() {
            public String ssid = _ssid;
            public String credentials = _credentials;
            public Long breadcrumb = _breadcrumb;
            public String networkIdentity = _networkIdentity;
            public String clientIdentifier = _clientIdentifier;
            public String possessionNonce = _possessionNonce;
        };
        sendCommand(client, "addOrUpdateWiFiNetwork", o);
    }

    public void addOrUpdateThreadNetwork(MatterClient client, String operationalDataset, Long breadcrumb)
            throws Exception {
        final String _operationalDataset = operationalDataset;
        final Long _breadcrumb = breadcrumb;
        Object o = new Object() {
            public String operationalDataset = _operationalDataset;
            public Long breadcrumb = _breadcrumb;
        };
        sendCommand(client, "addOrUpdateThreadNetwork", o);
    }

    public void removeNetwork(MatterClient client, String networkID, Long breadcrumb) throws Exception {
        final String _networkID = networkID;
        final Long _breadcrumb = breadcrumb;
        Object o = new Object() {
            public String networkID = _networkID;
            public Long breadcrumb = _breadcrumb;
        };
        sendCommand(client, "removeNetwork", o);
    }

    public void networkConfigResponse(MatterClient client, NetworkCommissioningStatusEnum networkingStatus,
            String debugText, Integer networkIndex, String clientIdentity, String possessionSignature)
            throws Exception {
        final NetworkCommissioningStatusEnum _networkingStatus = networkingStatus;
        final String _debugText = debugText;
        final Integer _networkIndex = networkIndex;
        final String _clientIdentity = clientIdentity;
        final String _possessionSignature = possessionSignature;
        Object o = new Object() {
            public NetworkCommissioningStatusEnum networkingStatus = _networkingStatus;
            public String debugText = _debugText;
            public Integer networkIndex = _networkIndex;
            public String clientIdentity = _clientIdentity;
            public String possessionSignature = _possessionSignature;
        };
        sendCommand(client, "networkConfigResponse", o);
    }

    public void connectNetwork(MatterClient client, String networkID, Long breadcrumb) throws Exception {
        final String _networkID = networkID;
        final Long _breadcrumb = breadcrumb;
        Object o = new Object() {
            public String networkID = _networkID;
            public Long breadcrumb = _breadcrumb;
        };
        sendCommand(client, "connectNetwork", o);
    }

    public void connectNetworkResponse(MatterClient client, NetworkCommissioningStatusEnum networkingStatus,
            String debugText, Integer errorValue) throws Exception {
        final NetworkCommissioningStatusEnum _networkingStatus = networkingStatus;
        final String _debugText = debugText;
        final Integer _errorValue = errorValue;
        Object o = new Object() {
            public NetworkCommissioningStatusEnum networkingStatus = _networkingStatus;
            public String debugText = _debugText;
            public Integer errorValue = _errorValue;
        };
        sendCommand(client, "connectNetworkResponse", o);
    }

    public void reorderNetwork(MatterClient client, String networkID, Integer networkIndex, Long breadcrumb)
            throws Exception {
        final String _networkID = networkID;
        final Integer _networkIndex = networkIndex;
        final Long _breadcrumb = breadcrumb;
        Object o = new Object() {
            public String networkID = _networkID;
            public Integer networkIndex = _networkIndex;
            public Long breadcrumb = _breadcrumb;
        };
        sendCommand(client, "reorderNetwork", o);
    }

    public void queryIdentity(MatterClient client, String keyIdentifier, String possessionNonce) throws Exception {
        final String _keyIdentifier = keyIdentifier;
        final String _possessionNonce = possessionNonce;
        Object o = new Object() {
            public String keyIdentifier = _keyIdentifier;
            public String possessionNonce = _possessionNonce;
        };
        sendCommand(client, "queryIdentity", o);
    }

    public void queryIdentityResponse(MatterClient client, String identity, String possessionSignature)
            throws Exception {
        final String _identity = identity;
        final String _possessionSignature = possessionSignature;
        Object o = new Object() {
            public String identity = _identity;
            public String possessionSignature = _possessionSignature;
        };
        sendCommand(client, "queryIdentityResponse", o);
    }

    public String toString() {
        String str = "";
        str += "maxNetworks : " + maxNetworks + "\n";
        str += "networks : " + networks + "\n";
        str += "scanMaxTimeSeconds : " + scanMaxTimeSeconds + "\n";
        str += "connectMaxTimeSeconds : " + connectMaxTimeSeconds + "\n";
        str += "interfaceEnabled : " + interfaceEnabled + "\n";
        str += "lastNetworkingStatus : " + lastNetworkingStatus + "\n";
        str += "lastNetworkID : " + lastNetworkID + "\n";
        str += "lastConnectErrorValue : " + lastConnectErrorValue + "\n";
        str += "supportedWiFiBands : " + supportedWiFiBands + "\n";
        str += "supportedThreadFeatures : " + supportedThreadFeatures + "\n";
        str += "threadVersion : " + threadVersion + "\n";
        str += "generatedCommandList : " + generatedCommandList + "\n";
        str += "acceptedCommandList : " + acceptedCommandList + "\n";
        str += "eventList : " + eventList + "\n";
        str += "attributeList : " + attributeList + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

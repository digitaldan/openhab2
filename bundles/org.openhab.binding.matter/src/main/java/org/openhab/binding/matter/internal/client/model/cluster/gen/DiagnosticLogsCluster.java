/**
 * Copyright (c) 2010-2024 Contributors to the openHAB project
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

// AUTO-GENERATED, DO NOT EDIT!

package org.openhab.binding.matter.internal.client.model.cluster.gen;

import java.math.BigInteger;
import java.util.LinkedHashMap;
import java.util.Map;

import org.openhab.binding.matter.internal.client.model.cluster.BaseCluster;
import org.openhab.binding.matter.internal.client.model.cluster.ClusterCommand;

/**
 * DiagnosticLogs
 *
 * @author Dan Cunningham - Initial contribution
 */
public class DiagnosticLogsCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "DiagnosticLogs";
    public static final int CLUSTER_ID = 0x0032;

    public Integer clusterRevision; // 65533 ClusterRevision

    // Enums
    public enum IntentEnum {
        END_USER_SUPPORT(0, "EndUserSupport"),
        NETWORK_DIAG(1, "NetworkDiag"),
        CRASH_LOGS(2, "CrashLogs");

        public final Integer value;
        public final String label;

        private IntentEnum(Integer value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public enum StatusEnum {
        SUCCESS(0, "Success"),
        EXHAUSTED(1, "Exhausted"),
        NO_LOGS(2, "NoLogs"),
        BUSY(3, "Busy"),
        DENIED(4, "Denied");

        public final Integer value;
        public final String label;

        private StatusEnum(Integer value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public enum TransferProtocolEnum {
        RESPONSE_PAYLOAD(0, "ResponsePayload"),
        BDX(1, "Bdx");

        public final Integer value;
        public final String label;

        private TransferProtocolEnum(Integer value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public DiagnosticLogsCluster(BigInteger nodeId, int endpointId) {
        super(nodeId, endpointId, 50, "DiagnosticLogs");
    }

    // commands
    /**
     * Reception of this command starts the process of retrieving diagnostic logs from a Node.
     */
    public static ClusterCommand retrieveLogsRequest(IntentEnum intent, TransferProtocolEnum requestedProtocol,
            String transferFileDesignator) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("intent", intent);
        map.put("requestedProtocol", requestedProtocol);
        map.put("transferFileDesignator", transferFileDesignator);

        return new ClusterCommand("retrieveLogsRequest", map);
    }

    public String toString() {
        String str = "";
        str += "clusterRevision : " + clusterRevision + "\n";
        return str;
    }
}

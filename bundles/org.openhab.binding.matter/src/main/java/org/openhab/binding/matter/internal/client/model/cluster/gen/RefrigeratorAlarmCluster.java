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
import java.util.List;
import java.util.Map;
import java.util.LinkedHashMap;

import org.openhab.binding.matter.internal.client.model.cluster.BaseCluster;
import org.openhab.binding.matter.internal.client.model.cluster.ClusterCommand;
import org.openhab.binding.matter.internal.client.model.cluster.gen.DataTypes.*;

/**
 * RefrigeratorAlarm
 *
 * @author Dan Cunningham - Initial contribution
 */
public class RefrigeratorAlarmCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "RefrigeratorAlarm";
    public static final int CLUSTER_ID = 0x0057;

    public Integer clusterRevision; // 65533 ClusterRevision 
    public FeatureMap featureMap; // 65532 FeatureMap 



    // Bitmaps
    public static class AlarmBitmap {
        public boolean doorOpen;
        public AlarmBitmap(boolean doorOpen){
            this.doorOpen = doorOpen;
        }
    }
    public static class FeatureMap {
        public FeatureMap(){
        }
    }

    public RefrigeratorAlarmCluster(BigInteger nodeId, int endpointId) {
        super(nodeId, endpointId, 87, "RefrigeratorAlarm");
    }

    

    public String toString() {
        String str = "";
        str += "clusterRevision : " + clusterRevision + "\n";
        str += "featureMap : " + featureMap + "\n";
        return str;
    }
}
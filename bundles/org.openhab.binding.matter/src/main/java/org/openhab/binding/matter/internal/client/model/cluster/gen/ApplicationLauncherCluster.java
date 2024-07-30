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
 * ApplicationLauncher
 *
 * @author Dan Cunningham - Initial contribution
 */
public class ApplicationLauncherCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "ApplicationLauncher";
    public static final int CLUSTER_ID = 0x050C;

    public Integer clusterRevision; // 65533 ClusterRevision 
    public FeatureMap featureMap; // 65532 FeatureMap 
    /**
    * This attribute shall specify the list of supported application catalogs, where each entry in the list is the CSA-issued vendor ID for the catalog. The DIAL registry (see [DIAL Registry]) shall use value 0x0000.
It is expected that Content App Platform providers will have their own catalog vendor ID (set to their own Vendor ID) and will assign an ApplicationID to each Content App.
    */
    public List<Integer> catalogList; // 0 list R V
    /**
    * This attribute shall specify the current in-focus application, identified using an Application ID, catalog vendor ID and the corresponding endpoint number when the application is represented by a Content App endpoint. A null shall be used to indicate there is no current in-focus application.
    */
    public ApplicationEPStruct currentApp; // 1 ApplicationEPStruct R V
    //Structs
    /**
    * This indicates a global identifier for an Application given a catalog.
    */
     public class ApplicationStruct {
        /**
        * This field shall indicate the CSA-issued vendor ID for the catalog. The DIAL registry shall use value 0x0000.
Content App Platform providers will have their own catalog vendor ID (set to their own Vendor ID) and will assign an ApplicationID to each Content App.
        */
        public Integer catalogVendorId; // uint16
        /**
        * This field shall indicate the application identifier, expressed as a string, such as &quot;PruneVideo&quot; or &quot;Company X&quot;. This field shall be unique within a catalog.
For the DIAL registry catalog, this value shall be the DIAL prefix (see [DIAL Registry]).
        */
        public String applicationId; // string
        public ApplicationStruct(Integer catalogVendorId, String applicationId) {
            this.catalogVendorId = catalogVendorId;
            this.applicationId = applicationId;
        }
     }
    /**
    * This specifies an app along with its corresponding endpoint.
    */
     public class ApplicationEPStruct {
        public ApplicationStruct application; // ApplicationStruct
        public Integer endpoint; // endpoint-no
        public ApplicationEPStruct(ApplicationStruct application, Integer endpoint) {
            this.application = application;
            this.endpoint = endpoint;
        }
     }


    //Enums
    public enum StatusEnum {
        SUCCESS(0, "Success"),
        APP_NOT_AVAILABLE(1, "AppNotAvailable"),
        SYSTEM_BUSY(2, "SystemBusy");
        public final Integer value;
        public final String label;
        private StatusEnum(Integer value, String label){
            this.value = value;
            this.label = label;
        }
    }

    // Bitmaps
    public static class FeatureMap {
        /**
        * Support for attributes and commands required for endpoint to support launching any application within the supported application catalogs
        */
        public boolean aP;
        public FeatureMap(boolean aP){
            this.aP = aP;
        }
    }

    public ApplicationLauncherCluster(BigInteger nodeId, int endpointId) {
        super(nodeId, endpointId, 1292, "ApplicationLauncher");
    }

    
    //commands
    /**
    * Upon receipt of this command, the server shall launch the application with optional data. The application shall be either
  • the specified application, if the Application Platform feature is supported;
  • otherwise the application corresponding to the endpoint.
The endpoint shall launch and bring to foreground the requisite application if the application is not already launched and in foreground. The Status attribute shall be updated to ActiveVisibleFocus on the Application Basic cluster of the Endpoint corresponding to the launched application. The Status attribute shall be updated on any other application whose Status may have changed as a result of this command. The CurrentApp attribute, if supported, shall be updated to reflect the new application in the foreground.
This command returns a Launcher Response.
    */
    public static ClusterCommand launchApp(ApplicationStruct application, String data) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("application", application);
        map.put("data", data);

        return new ClusterCommand("launchApp", map);
    }
    /**
    * Upon receipt of this command, the server shall stop the application if it is running. The application shall be either
  • the specified application, if the Application Platform feature is supported;
  • otherwise the application corresponding to the endpoint.
The Status attribute shall be updated to Stopped on the Application Basic cluster of the Endpoint corresponding to the stopped application. The Status attribute shall be updated on any other application whose Status may have changed as a result of this command.
This command returns a Launcher Response.
    */
    public static ClusterCommand stopApp(ApplicationStruct application) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("application", application);

        return new ClusterCommand("stopApp", map);
    }
    /**
    * Upon receipt of this command, the server shall hide the application. The application shall be either
  • the specified application, if the Application Platform feature is supported;
  • otherwise the application corresponding to the endpoint.
The endpoint may decide to stop the application based on manufacturer specific behavior or resource constraints if any. The Status attribute shall be updated to ActiveHidden or Stopped, depending on the action taken, on the Application Basic cluster of the Endpoint corresponding to the application on which the action was taken. The Status attribute shall be updated on any other
application whose Status may have changed as a result of this command. This command returns a Launcher Response.
    */
    public static ClusterCommand hideApp(ApplicationStruct application) {
        Map<String, Object> map = new LinkedHashMap<>();
        map.put("application", application);

        return new ClusterCommand("hideApp", map);
    }

    public String toString() {
        String str = "";
        str += "clusterRevision : " + clusterRevision + "\n";
        str += "featureMap : " + featureMap + "\n";
        str += "catalogList : " + catalogList + "\n";
        str += "currentApp : " + currentApp + "\n";
        return str;
    }
}
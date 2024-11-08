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

import org.openhab.binding.matter.internal.client.model.cluster.BaseCluster;

/**
 * FormaldehydeConcentrationMeasurement
 *
 * @author Dan Cunningham - Initial contribution
 */
public class FormaldehydeConcentrationMeasurementCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "FormaldehydeConcentrationMeasurement";
    public static final int CLUSTER_ID = 0x042B;

    public FormaldehydeConcentrationMeasurementCluster(BigInteger nodeId, int endpointId) {
        super(nodeId, endpointId, 1067, "FormaldehydeConcentrationMeasurement");
    }

    public String toString() {
        String str = "";
        return str;
    }
}

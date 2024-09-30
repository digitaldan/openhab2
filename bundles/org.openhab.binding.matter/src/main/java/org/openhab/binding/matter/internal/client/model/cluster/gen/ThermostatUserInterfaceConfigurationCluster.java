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
import org.openhab.binding.matter.internal.client.model.cluster.gen.DataTypes.*;

/**
 * ThermostatUserInterfaceConfiguration
 *
 * @author Dan Cunningham - Initial contribution
 */
public class ThermostatUserInterfaceConfigurationCluster extends BaseCluster {

    public static final String CLUSTER_NAME = "ThermostatUserInterfaceConfiguration";
    public static final int CLUSTER_ID = 0x0204;

    public Integer clusterRevision; // 65533 ClusterRevision
    /**
     * Indicates the units of the temperature displayed on the thermostat screen.
     */
    public TemperatureDisplayModeEnum temperatureDisplayMode; // 0 TemperatureDisplayModeEnum RW VO
    /**
     * Indicates the level of functionality that is available to the user via the keypad.
     */
    public KeypadLockoutEnum keypadLockout; // 1 KeypadLockoutEnum RW VM
    /**
     * This attribute is used to hide the weekly schedule programming functionality or menu on a thermostat from a user
     * to prevent local user programming of the weekly schedule. The schedule programming may still be performed via a
     * remote interface, and the thermostat may operate in schedule programming mode.
     * This attribute is designed to prevent local tampering with or disabling of schedules that may have been
     * programmed by users or service providers via a more capable remote interface. The programming schedule shall
     * continue to run even though it is not visible to the user locally at the thermostat.
     */
    public ScheduleProgrammingVisibilityEnum scheduleProgrammingVisibility; // 2 ScheduleProgrammingVisibilityEnum RW VM

    // Enums
    public enum TemperatureDisplayModeEnum {
        CELSIUS(0, "Celsius"),
        FAHRENHEIT(1, "Fahrenheit");

        public final Integer value;
        public final String label;

        private TemperatureDisplayModeEnum(Integer value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    /**
     * The interpretation of the various levels is device-dependent.
     */
    public enum KeypadLockoutEnum {
        NO_LOCKOUT(0, "NoLockout"),
        LOCKOUT1(1, "Lockout1"),
        LOCKOUT2(2, "Lockout2"),
        LOCKOUT3(3, "Lockout3"),
        LOCKOUT4(4, "Lockout4"),
        LOCKOUT5(5, "Lockout5");

        public final Integer value;
        public final String label;

        private KeypadLockoutEnum(Integer value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public enum ScheduleProgrammingVisibilityEnum {
        SCHEDULE_PROGRAMMING_PERMITTED(0, "ScheduleProgrammingPermitted"),
        SCHEDULE_PROGRAMMING_DENIED(1, "ScheduleProgrammingDenied");

        public final Integer value;
        public final String label;

        private ScheduleProgrammingVisibilityEnum(Integer value, String label) {
            this.value = value;
            this.label = label;
        }
    }

    public ThermostatUserInterfaceConfigurationCluster(BigInteger nodeId, int endpointId) {
        super(nodeId, endpointId, 516, "ThermostatUserInterfaceConfiguration");
    }

    public String toString() {
        String str = "";
        str += "clusterRevision : " + clusterRevision + "\n";
        str += "temperatureDisplayMode : " + temperatureDisplayMode + "\n";
        str += "keypadLockout : " + keypadLockout + "\n";
        str += "scheduleProgrammingVisibility : " + scheduleProgrammingVisibility + "\n";
        return str;
    }
}
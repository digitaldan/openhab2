# Matter Binding

The Matter Binding for openHAB allows seamless integration with Matter-compatible devices.

**Please note this binding requires openHAB 4.3 snapshot builds or later that support discovery codes when searching for devices.** 

# General Matter Ecosystem Overview

Matter is an open-source connectivity standard for smart home devices, allowing seamless communication between a wide range of devices, controllers, and ecosystems. 

Below is a high-level overview of the Matter ecosystem as well as common terminology used in the Matter standard.

## Matter and IPv6

Matter **requires** IPv6 to be enabled and be routable between openHAB and the Matter device.  This means IPv6 needs to be enabled on the host openHAB is running, and the network must be able route IPv6 unicast and multicast messages.  Docker, VLANs, subnets and other configurations can prohibit Matter from working if not configured correctly. See the [IPv6 Requirements](#IPv6-Requirements) section for more information.

## Matter Devices

### Nodes and Endpoints

In the Matter ecosystem, a **node** represents a single device that joins a Matter network and will have a locally routable IPv6 address. A **node** can have multiple **endpoints**, which are logical representations of specific features or functionalities of the device. For example, a smart thermostat (node) may have an endpoint for general thermostat control (heating, cooling, current temperature, operating state, etc....) and another endpoint for humidity sensing.  Many devices will only have a single endpoint.  [Matter Bridges](#bridges) will expose multiple endpoints for each device they are bridging, and the bridge itself will be a node.

**Example:**
- A Thermostat node with an endpoint for general temperature control and another endpoint for a remote temperature or humidity sensor.

### Controllers

A **controller** manages the interaction between Matter devices and other parts of the network. Controllers can send commands, receive updates, and facilitate device communication. They also handle the commissioning process when new devices are added to the network.

**Example:**
- openHAB or another smart home hub or a smartphone app that manages your smart light bulbs, door locks, and sensors (Google Home, Apple Home, Amazon Alexa, etc...)

### Bridges

A **bridge** is a special type of node that connects non-Matter devices to a Matter network, effectively translating between protocols. Bridges allow legacy devices to be controlled via the Matter standard.

openHAB fully supports connecting to Matter bridges. In addition, openHAB has experimental support for running its own Matter bridge service, exposing openHAB items as Matter endpoints to 3rd party systems.  See [Matter Bridge](#Matter-Bridge) for information on running a Bridge server. 

**Example:**
- A bridge that connects Zigbee or Z-Wave devices, making them accessible within a Matter ecosystem. The Ikea Dirigera and Philips Hue Bridge both act as matter bridges and are supported in openHAB.

### Thread Border Routers

A **Thread Border Router** is a device that allows devices connected via Thread (a low-power wireless protocol) to communicate with devices on other networks, such as Wi-Fi or Ethernet. It facilitates IPv6-based communication between Thread networks and the local IP network.

**Example:**
- An Open Thread Boarder Router (open source) as well as recent versions of Apple TVs, Amazon Echos and Google Nest Hubs all have embedded thread boarder routers. 

## IPv6 and Network Connectivity

Matter devices operate over an IPv6 network, and obtaining an IPv6 address is required for communication. Devices can connect to the network via different interfaces:

### Ethernet
Ethernet-connected Matter devices receive an IPv6 address through standard DHCPv6 or stateless address auto-configuration (SLAAC).

### Wi-Fi
Wi-Fi-enabled Matter devices also receive an IPv6 address using DHCPv6 or SLAAC. They rely on the existing Wi-Fi infrastructure for communication within the Matter ecosystem.

### Thread
Thread-based Matter devices connect to the network via a **Thread Border Router**. They receive an IPv6 address from the Thread router

## IPv6 Requirements

For Matter devices to function correctly, **IPv6 must be enabled** and supported in both the local network (router) and the Matter controllers. Without IPv6, devices won’t be able to communicate properly within the Matter ecosystem. Ensure that your router has IPv6 enabled and that any Matter controllers (like smart hubs, apps or openHAB) are configured to support IPv6 as well.

**Note that environments like Docker require special configurations to enable IPv6**

### Enabling IPv6 Thread Connectivity on Linux Hosts
It is important to make sure that Route Announcements (RA) and Route Information Options (RIO) are enabled on your host so that Thread boarder routers can announce routes to the Thread network. 
This is done by setting the following sysctl options:

1. `net.ipv6.conf.wlan0.accept_ra` should be at least `1` if ip forwarding is not enabled, and `2` otherwise.
2. `net.ipv6.conf.wlan0.accept_ra_rt_info_max_plen` should not be smaller than `64`.

the `accept_ra` is defaulted to `1` for most distributions. 

There may be other network daemons which will override this option (for example, dhcpcd on Raspberry Pi will override accept_ra to 0). 

You can check the accept_ra value with:
```
$ sudo sysctl -n net.ipv6.conf.wlan0.accept_ra
0
```

And set the value to 1 (or 2 in case IP forwarding is enabled) with:
```
$ sudo sysctl -w net.ipv6.conf.wlan0.accept_ra=1
Net.ipv6.conf.wlan0.accept_ra = 1
```

The accept_ra_rt_info_max_plen option on most Linux distributions is default to 0, set it to 64 with:
```
$ sudo sysctl -w net.ipv6.conf.wlan0.accept_ra_rt_info_max_plen=64
net.ipv6.conf.wlan0.accept_ra_rt_info_max_plen = 64
```

To make these changes permanent, add the following lines to `/etc/sysctl.conf`:
```
net.ipv6.conf.eth0.accept_ra=1
net.ipv6.conf.eth0.accept_ra_rt_info_max_plen=64
```

Raspberry Pi users may need to add the following lines to `/etc/dhcpcd.conf` to prevent dhcpcd from overriding the accept_ra value:
```
noipv6
noipv6rs
```

***NOTE:  Please ensure you use the right interface name for your network interface.*** The above examples use `wlan0` and `eth0` as examples.  You can find the correct interface name by running `ip a` and looking for the interface that has an IPv6 address assigned to it. 

## Matter Commissioning and Pairing Codes

Commissioning a Matter device involves securely adding it to the network using a **pairing code**. This process ensures that only authorized devices can join the network.

### Pairing Code from the Device
When commissioning a new Matter device, it typically has a printed QR code or numeric pairing code that you scan or enter during setup. This pairing code allows the controller to establish a secure connection to the device and add it to the network. Once a device pairing code is in use, it can not be used again to pair other controllers.

### Additional Pairing Code from a Controller
If a device has already been commissioned and you want to add it to another Matter controller, the existing controller can generate an additional pairing code. This is useful when sharing access to a device across multiple hubs or apps. Apple Home, Google Home, Amazon Alexa and openHAB all support generating pairing codes for existing paired devices.

### Example:
- When setting up a smart lock, you may scan a QR code directly from the lock, or use the 11 digit pairing code printed on it to pair it with openHAB. If you later want to control the lock from another app or hub, you would retrieve a new pairing code directly from openHAB.

# Matter Binding Configuration

## Supported Things

The Matter Binding supports the following types of things:

- `controller`: The main controller that interfaces with Matter devices. It requires the configuration parameter  `nodeId` which sets the local Matter node ID for this controller (must be unique in the fabric).
- `endpoint`: Represents an individual endpoint within the Matter network. Configuration parameters include `nodeId` and `endpointId`.

## Discovery

Matter controllers must be added manually.  Endpoint (devices) will be discovered when a `pairCode` is used to search for a device to add. 

### Device Pairing: General

In order to pair a device, you must have an 11 digit manual pairing code (eg 123-4567-8901 or 12345678901) or a QR Code (eg MT:ABCDEF1234567890123).  If the device has not been paired before, use the code provided by the manufacturer and **ensure the device is in pairing mode**, refer to your devices instructions for pairing for more information.

If the device is paired with another Matter ecosystem (Apple, Google, Amazon, etc..) use that ecosystem to generate a pairing code and search for devices.  The pairing code and device will only be available for commissioning for a limited time.  Refer to the ecosystem that generated the code for the exact duration (typically 1-15 minutes).

### Device Pairing: Thread Devices

Thread devices require a Thread Border Router and a bluetooth enabled device to facilitate the thread joining process (typically a mobile device).  Until there is a supported thread border router integration in openHAB and the openHAB mobile apps, it's strongly recommended to pair the device to a commercial router with thread support first (Apple TV 4k, Google Nest Hub 2, Amazon Gen 4 Echo, etc... ), then generate a matter pairing code and add the device normally.  This will still allow openHAB to have direct access to the device using only the embedded thread border router and does not interact with the underlying providers home automation stack.

Support for using a Open Thread Border Router has been verified to work and will be coming soon to openHAB, but in some cases requires strong expertise in IPv6 routing as well as support in our mobile clients. 

## Binding Configuration

This binding does not require any general configuration settings.

## Thing Configuration

### Controller Thing Configuration

The controller thing must be created manually before devices can be discovered.

| Name                      | Type    | Description                                                                                                                       | Default | Required | Advanced |
|---------------------------|---------|-----------------------------------------------------------------------------------------------------------------------------------|---------|----------|----------|
| nodeId                    | number  | The matter node ID for this controller                                                                                            | 0       | yes      | no       |
| decommissionNodesOnDelete | boolean | Decommission (unpair) Matter nodes from the Matter network when the last endpoint thing of a node is deleted/removed from openHAB | false    | yes      | no       |

### Endpoint Thing Configuration

Endpoints are discovered automatically (see [Discovery](#Discovery) for more information).

| Name       | Type   | Description                        | Default | Required | Advanced |
|------------|--------|------------------------------------|---------|----------|----------|
| nodeId     | text   | The node ID of the endpoint        | N/A     | yes      | no       |
| endpointId | number | The endpoint ID within the node    | N/A     | yes      | no       |

## Channels

### Controller Channels
Controller have no channels.

### Endpoint Channels
Endpoint channels are dynamically added based on the endpoint type and matter cluster supported.
Possible channels include:

## Endpoint Channels

| Channel ID                           | Type                     | Label                        | Description                                                                                                                                                                                                                                                          | Category    | ReadOnly | Pattern     |
|--------------------------------------|--------------------------|------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-------------|----------|-------------|
| battery-voltage                      | Number:ElectricPotential | Battery Voltage              | The current battery voltage                                                                                                                                                                                                                                          | Energy      | true     | %.1f %unit% |
| battery-alarm                        | String                   | Battery Alarm                | The battery alarm state                                                                                                                                                                                                                                              | Energy      | true     |             |
| colorcontrol-color                   | Color                    | Color                        | The color channel allows to control the color of a light. It is also possible to dim values and switch the light on and off.                                                                                                                                         | ColorLight  |          |             |
| colorcontrol-temperature             | Dimmer                   | Color Temperature            | Sets the color temperature of the light                                                                                                                                                                                                                              | ColorLight  |          |             |
| door-state                           | Switch                   | Door Lock State              | Locks and unlocks the door and maintains the lock state                                                                                                                                                                                                              | Door        |          |             |
| fancontrol-fanmode                   | Number                   | Fan Mode                     | Set the fan mode                                                                                                                                                                                                                                                     | HVAC        |          |             |
| onoffcontrol-onoff                   | Switch                   | Switch                       | Switches the power on and off                                                                                                                                                                                                                                        | Light       |          |             |
| levelcontrol-level                   | Dimmer                   | Dimmer                       | Sets the level of the light                                                                                                                                                                                                                                          | Light       |          |             |
| modeselect-mode                      | Number                   | Mode Select                  | Selection of 1 or more states                                                                                                                                                                                                                                        |             | false    | %d          |
| switch-switch                        | Number                   | Switch                       | Indication of a switch or remote being activated                                                                                                                                                                                                                     |             | true     | %d          |
| switch-switchlatched                 | Trigger                  | Switched Latched Trigger     | This trigger shall indicate the new value of the CurrentPosition attribute as a JSON object, i.e. after the move.                                                                                                                                                    |             |          |             |
| switch-initialpress                  | Trigger                  | Initial Press Trigger        | This trigger shall indicate the new value of the CurrentPosition attribute as a JSON object, i.e. while pressed.                                                                                                                                                     |             |          |             |
| switch-longpress                     | Trigger                  | Long Press Trigger           | This trigger shall indicate the new value of the CurrentPosition attribute as a JSON object, i.e. while pressed.                                                                                                                                                     |             |          |             |
| switch-shortrelease                  | Trigger                  | Short Release Trigger        | This trigger shall indicate the previous value of the CurrentPosition attribute as a JSON object, i.e. just prior to release.                                                                                                                                        |             |          |             |
| switch-longrelease                   | Trigger                  | Long Release Trigger         | This trigger shall indicate the previous value of the CurrentPosition attribute as a JSON object, i.e. just prior to release.                                                                                                                                        |             |          |             |
| switch-multipressongoing             | Trigger                  | Multi-Press Ongoing Trigger  | This trigger shall indicate 2 numeric fields as a JSON object. The first is the new value of the CurrentPosition attribute, i.e. while pressed. The second is the multi press code with a value of N when the Nth press of a multi-press sequence has been detected. |             |          |             |
| switch-multipresscomplete            | Trigger                  | Multi-Press Complete Trigger | This trigger shall indicate 2 numeric fields as a JSON object. The first is the new value of the CurrentPosition attribute, i.e. while pressed. The second is how many times the momentary switch has been pressed in a multi-press sequence.                        |             |          |             |
| thermostat-localtemperature          | Number:Temperature       | Local Temperature            | Indicates the local temperature provided by the thermostat                                                                                                                                                                                                           | HVAC        | true     | %.1f %unit% |
| thermostat-outdoortemperature        | Number:Temperature       | Outdoor Temperature          | Indicates the outdoor temperature provided by the thermostat                                                                                                                                                                                                         | HVAC        | true     | %.1f %unit% |
| thermostat-occupiedheating           | Number:Temperature       | Occupied Heating Setpoint    | Set the heating temperature when the room is occupied                                                                                                                                                                                                                | HVAC        |          | %.1f %unit% |
| thermostat-occupiedcooling           | Number:Temperature       | Occupied Cooling Setpoint    | Set the cooling temperature when the room is occupied                                                                                                                                                                                                                | HVAC        |          | %.1f %unit% |
| thermostat-unoccupiedheating         | Number:Temperature       | Unoccupied Heating Setpoint  | Set the heating temperature when the room is unoccupied                                                                                                                                                                                                              | HVAC        |          | %.1f %unit% |
| thermostat-unoccupiedcooling         | Number:Temperature       | Unoccupied Cooling Setpoint  | Set the cooling temperature when the room is unoccupied                                                                                                                                                                                                              | HVAC        |          | %.1f %unit% |
| thermostat-systemmode                | Number                   | System Mode                  | Set the system mode of the thermostat                                                                                                                                                                                                                                | HVAC        |          |             |
| thermostat-runningmode               | Number                   | Running Mode                 | The running mode of the thermostat                                                                                                                                                                                                                                   | HVAC        | true     |             |
| thermostat-heatingdemand             | Number:Dimensionless     | Heating Demand               | The level of heating currently demanded by the thermostat                                                                                                                                                                                                            | HVAC        | true     | %.0f %%     |
| thermostat-coolingdemand             | Number:Dimensionless     | Cooling Demand               | The level of cooling currently demanded by the thermostat                                                                                                                                                                                                            | HVAC        | true     | %.0f %%     |
| windowcovering-lift                  | Rollershutter            | Window Covering Lift         | Sets the window covering level - supporting open/close and up/down type commands                                                                                                                                                                                     | Blinds      |          | %.0f %%     |
| fancontrol-percent                   | Dimmer                   | Fan Control Percent          | The current fan speed percentage level                                                                                                                                                                                                                               | HVAC        | true     | %.0f %%     |
| fancontrol-mode                      | Number                   | Fan Control Mode             | The current mode of the fan                                                                                                                                                                                                                                          | HVAC        |          |             |
| temperaturemeasurement-measuredvalue | Number:Temperature       | Temperature                  | The measured temperature                                                                                                                                                                                                                                             | Temperature | true     | %.1f %unit% |
| occupancysensing-occupied            | Switch                   | Occupancy                    | Indicates if an occupancy sensor is triggered                                                                                                                                                                                                                        | HVAC        | true     |             |


## Full Example

### Thing Configuration
```java
Thing configuration example for the Matter controller:
Thing matter:controller:myController [ nodeId="1" ]

Thing configuration example for a Matter endpoint:
Thing matter:endpoint:myEndpoint [ nodeId="12345678901234567890", endpointId=1 ]
```

### Item Configuration
```java
Dimmer MyEndpointDimmer "My Endpoint Dimmer" { channel="matter:endpoint:myController:myEndpoint:levelcontrol-level" }
```

### Sitemap Configuration
```perl
Optional Sitemap configuration:
sitemap home label="Home"
{
    Frame label="Matter Devices"
    {
        Dimmer item=MyEndpointDimmer
    }
}
```

# Matter Bridge

**EXPERIMENTAL**

openHAB can also expose Items and Item groups as Matter devices to 3rd party Matter clients like Google Home, Apple Home and Amazon Alexa. This allows local control for those ecosystems and can be used instead of cloud based integrations for features like voice assistants. 

## Configuration

The openHAB matter bridge uses Metadata tags with the key "matter", similar to the Alexa, Google Assistant and Apple Homekit integrations.  Matter Metadata tag values generally follow the Matter "Device Type" and "Cluster" specification as much as possible.  Items and item groups are initially tagged with a Matter "Device Type", which are Matter designations for common device types like lights, thermostats, locks, window coverings, etc... For single items, like a light switch or dimmer, simply tagging the item with the Matter device type is enough.  For more complicated devices, like thermostats, A group item is tagged with the device type, and its child members are tagged with the cluster attribute(s) that it will be associated with.  Multiple attributes use a comma delimited format like `attribute1, attribute2, ... attributeN`

Pairing codes and other options can be found in the MainUI under "Settings -> Add-on Settings -> Matter Binding"

### Device Types

| Type               | Item/Group  | Tag            | Notes                 |
|--------------------|-------------|----------------|-----------------------|
| On Off Light       | Item        | OnOffLight     |                       |
| Dimmable Light     | Item        | DimmableLight  |                       |
| Color Light        | Item, Group | ColorLight     | (not implemented yet) |
| Thermostat         | Group       | Thermostat     |                       |
| Window Covering    | Item, Group | WindowCovering | (not implemented yet) |
| Temperature Sensor | Item        | Temperature    | (not implemented yet) |
| Humidity Sensor    | Item        | Humidity       | (not implemented yet) |
| Door Lock          | Group       | DoorLock       | (not implemented yet) |


### Thermostat member tags

| Item                  | Tag               | Options
|-----------------------|-------------------|
| Current Temperature   | localTemperature  |
| Outdoor Temperature   | outdoorTemperature|
| Heating Setpoint      | occupiedHeatingSetpoint   |
| Cooling Setpoint      | occupiedCoolingSetpoint   |
| System Mode           | systemMode        | [OFF=0,AUTO=1,COOL=3,HEAT=4,EMERGENCY_HEAT=5,PRECOOLING=6,FAN_ONLY=7,DRY=8,SLEEP=9]
| Running Mode          | runningMode       |

### Example

```perl
Dimmer TestDimmer  "Test Dimmer [%d%%]" {matter="DimmableLight"}

Group                 Test_HVAC                 "Thermostat"                              ["HVAC"]                         {matter="Thermostat" }
Number:Temperature    Test_HVAC_Temperature     "Temperature [%d °F]"      (Test_HVAC)    ["Measurement","Temperature"]    {matter="localTemperature"}
Number:Temperature    Test_HVAC_HeatSetpoint    "Heat Setpoint [%d °F]"    (Test_HVAC)    ["Setpoint", "Temperature"]      {matter="occupiedHeatingSetpoint"}
Number:Temperature    Test_HVAC_CoolSetpoint    "Cool Setpoint [%d °F]"    (Test_HVAC)    ["Setpoint", "Temperature"]      {matter="occupiedCoolingSetpoint"}
Number                Test_HVAC_Mode            "Mode [%s]"                (Test_HVAC)    ["Control" ]                     {matter="systemMode" [OFF=0, HEAT=1, COOL=2, AUTO=3]}
```
// Include this first to auto-register Crypto, Network and Time Node.js implementations
import "@matter/node";

import { VendorId } from "@matter/types";
import { logEndpoint, DeviceCommissioner } from "@matter/protocol";
import { Endpoint, EndpointServer, ServerNode } from "@matter/node";
import { AggregatorEndpoint } from "@matter/node/endpoints";
import { Environment, Logger } from "@matter/general";
import { GenericDeviceType } from "./devices/GenericDeviceType";
import { OnOffDeviceType } from "./devices/OnOffDeviceType";
import { OnOffPlugInDeviceType } from "./devices/OnOffPlugInDeviceType";
import { DimmableDeviceType } from "./devices/DimmableDeviceType";
import { ThermostatDeviceType } from "./devices/ThermostatDeviceType";
import { WindowCoveringDeviceType } from "./devices/WindowCoveringDeviceType";
import { BridgeController } from "./BridgeController";
import { DoorLockDeviceType } from "./devices/DoorLockDeviceType";
import { TemperatureSensorType } from "./devices/TemperatureSensorType";
import { HumiditySensorType } from "./devices/HumiditySensorType";
import { OccupancySensorDeviceType } from "./devices/OccupancySensorDeviceType";
import { ContactSensorDeviceType } from "./devices/ContactSensorDeviceType";
import { FanModeDeviceType } from "./devices/FanModeDeviceType";
import { ColorDeviceType } from "./devices/ColorDeviceType";
import { BridgeEvent, BridgeEventType, EventType } from "../MessageTypes";


const logger = Logger.get("DeviceNode");

export class DeviceNode {
    private server!: ServerNode;
    #environment: Environment = Environment.default;

    private aggregator!: Endpoint<AggregatorEndpoint>;
    private devices: Map<string, GenericDeviceType> = new Map();
    private inCommission = false;

    constructor(private bridgeController: BridgeController, private storagePath: string, private resetStorage: boolean, private deviceName: string, private vendorName: string, private passcode: number, private discriminator: number, private vendorId: number, private productName: string, private productId: number, private port: number, private uniqueId: string) {
    }

    async init() {
        logger.info(`Device Node Storage location: ${this.storagePath} (Directory)`);
        this.#environment.vars.set('storage.path', this.storagePath)
        if (this.resetStorage) {
            this.#environment.vars.set("storage.clear", true);
            this.resetStorage = false;
        }
        /**
         * Create a Matter ServerNode, which contains the Root Endpoint and all relevant data and configuration
         */
        try {
            this.server = await ServerNode.create({
                // Required: Give the Node a unique ID which is used to store the state of this node
                id: this.uniqueId,

                // Provide Network relevant configuration like the port
                // Optional when operating only one device on a host, Default port is 5540
                network: {
                    port: this.port,
                },

                // Provide Commissioning relevant settings
                // Optional for development/testing purposes
                commissioning: {
                    passcode: this.passcode,
                    discriminator: this.discriminator,

                },

                // Provide Node announcement settings
                // Optional: If Ommitted some development defaults are used
                productDescription: {
                    name: this.deviceName,
                    deviceType: AggregatorEndpoint.deviceType,
                },

                // Provide defaults for the BasicInformation cluster on the Root endpoint
                // Optional: If Omitted some development defaults are used
                basicInformation: {
                    vendorName: this.vendorName,
                    vendorId: VendorId(this.vendorId),
                    nodeLabel: this.productName,
                    productName: this.productName,
                    productLabel: this.productName,
                    productId: this.productId,
                    serialNumber: `openHAB-${this.uniqueId}`,
                    uniqueId: this.uniqueId,
                },
            });
            logger.info(`ServerNode created with ID: ${this.server.id}`);
            this.aggregator = new Endpoint(AggregatorEndpoint, { id: "aggregator" });
            await this.server.add(this.aggregator);


            //reset this for future restarts
            this.#environment.vars.set("storage.clear", false);
        } catch (e) {
            logger.error(`Error starting server: ${e}`);
            throw e;
        }
    }

    async close() {
        await this.server?.close();
        this.devices.clear();
    }

    getCommissioningState() {
        return {
            pairingCodes: {
                manualPairingCode: this.server.state.commissioning.pairingCodes.manualPairingCode,
                qrPairingCode: this.server.state.commissioning.pairingCodes.qrPairingCode
            },
            commissioningWindowOpen : !this.server.state.commissioning.commissioned || this.inCommission
        }
    }

    async addEndpoint(deviceType: string, id: string, nodeLabel: string, productName: string, productLabel: string, serialNumber: string, attributeMap: { [key: string]: any }) {
        //const deviceType = this.deviceTypes[endpointType];
        let device: GenericDeviceType | null = null;

        if (this.devices.has(id)) {
            throw new Error(`Device ${id} already exists! Call 'resetEndpoints' first and try again.`);
        }

        if (!this.aggregator) {
            throw new Error(`Aggregator not initialized, aborting.`);
        }

        switch (deviceType) {
            case "OnOffLight":
                device = new OnOffDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "OnOffPlugInUnit":
                device = new OnOffPlugInDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "DimmableLight":
                device = new DimmableDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "Thermostat":
                device = new ThermostatDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "WindowCovering":
                device = new WindowCoveringDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "DoorLock":
                device = new DoorLockDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "TemperatureSensor":
                device = new TemperatureSensorType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "HumiditySensor":
                device = new HumiditySensorType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "OccupancySensor":
                device = new OccupancySensorDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "ContactSensor":
                device = new ContactSensorDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "FanMode":
                device = new FanModeDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "ColorLight":
                device = new ColorDeviceType(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            default:
                throw new Error(`Unsupported device type ${deviceType}`);
        }
        if (device != null) {
            this.devices.set(id, device);
            await this.aggregator.add(device.endpoint);
        }

    }

    async resetBridge() {
        await this.close();
        return this.init();
    }

    async startBridge() {
        if (this.devices.size == 0) {
            throw new Error("No devices added, not starting");
        }
        logEndpoint(EndpointServer.forEndpoint(this.server));
        await this.server.start();
        //this.#sendCommissioningCodes();
    }

    async setEndpointState(endpointId: string, clusterName: string, stateName: string, stateValue: any) {
        const device = this.devices.get(endpointId);
        if (device) {
            device.updateState(clusterName, stateName, stateValue);
        }
    }

    async openCommissioningWindow() {
        const dc = this.server.env.get(DeviceCommissioner);
        logger.debug('opening basic commissioning window')
        await dc.allowBasicCommissioning(() => {
            this.inCommission = false;
            logger.debug('commissioning window closed')
            const be: BridgeEvent = {
                type: BridgeEventType.EventTriggered,
                data: {
                    eventName: "commissioningWindowClosed",
                    data: {}
                }
            }
            this.bridgeController.ws.sendEvent(EventType.BridgeEvent, be);
        });
        this.inCommission = true;
        logger.debug('basic commissioning window open')
    }

    async closeCommissioningWindow() {
        const dc = this.server.env.get(DeviceCommissioner);
        logger.debug('closing basic commissioning window')
        await dc.endCommissioning();
    }

    // #sendCommissioningCodes() {
    //     if (!this.server.state.commissioning.commissioned || this.inCommission) {
    //         const be: BridgeEvent = {
    //             type: BridgeEventType.EventTriggered,
    //             data: {
    //                 eventName: "commissioningWindowOpen",
    //                 data: {
    //                     manualPairingCode: this.server.state.commissioning.pairingCodes.manualPairingCode,
    //                     qrPairingCode: this.server.state.commissioning.pairingCodes.qrPairingCode
    //                 }
    //             }
    //         }
    //         this.bridgeController.ws.sendEvent(EventType.BridgeEvent, be);
    //     } else {
    //         const be: BridgeEvent = {
    //             type: BridgeEventType.EventTriggered,
    //             data: {
    //                 eventName: "commissioningWindowClosed",
    //                 data: {}
    //             }
    //         }
    //         this.bridgeController.ws.sendEvent(EventType.BridgeEvent, be);
    //     }
    //}
}
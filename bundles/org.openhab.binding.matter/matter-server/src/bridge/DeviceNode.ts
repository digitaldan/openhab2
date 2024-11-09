// Include this first to auto-register Crypto, Network and Time Node.js implementations
import "@matter/node";

import { VendorId } from "@matter/types";
import { logEndpoint, EndpointInterface } from "@matter/protocol";
import { Endpoint, EndpointServer, MutableEndpoint, ServerNode } from "@matter/node";
import { AggregatorEndpoint } from "@matter/node/endpoints";
import { Environment, Logger } from "@matter/general";
import { GenericDevice } from "./devices/GenericDevice";
import { OnOffDevice } from "./devices/OnOffDevice";
import { DimmableDevice } from "./devices/DimmableDevice";
import { ThermoDevice } from "./devices/ThermoDevice";
import { BridgeController } from "./BridgeController";
const logger = Logger.get("DeviceNode");

export class DeviceNode {
    private server!: ServerNode;
    #environment: Environment = Environment.default;

    private aggregator!: Endpoint<AggregatorEndpoint>;
    private devices: Map<string, GenericDevice> = new Map();

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
            await this.server.start();

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

    getPairingCodes() {
        if (this.server.state.commissioning.commissioned) {
            return {
                manualPairingCode: null,
                qrPairingCode: null
            }
        }
        return {
            manualPairingCode: this.server.state.commissioning.pairingCodes.manualPairingCode,
            qrPairingCode: this.server.state.commissioning.pairingCodes.qrPairingCode
        }
    }

    async addEndpoint(deviceType: string, id: string, nodeLabel: string, productName: string, productLabel: string, serialNumber: string, attributeMap: { [key: string]: any }) {
        //const deviceType = this.deviceTypes[endpointType];
        let device: GenericDevice | null = null;

        if (this.devices.has(id)) {
            logger.error(`Device ${id} already exists! Call 'resetEndpoints' first and try again.`);
            return;
        }

        switch (deviceType) {
            case "OnOffLightDevice":
                device = new OnOffDevice(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "DimmableLightDevice":
                device = new DimmableDevice(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            case "ThermostatDevice":
                device = new ThermoDevice(this.bridgeController, attributeMap, id, nodeLabel, productName, productLabel, serialNumber);
                break;
            default:
                logger.error(`Unsupported device type ${deviceType}`);
        }
        if (device != null) {
            this.devices.set(id, device);
            await this.aggregator.add(device.endpoint);
            /**
            * Log the endpoint structure for debugging reasons and to allow to verify anything is correct
            */
            //logEndpoint(EndpointServer.forEndpoint(this.server));
        }

    }

    async resetBridge() {
        await this.close();
        return this.init();
    }

    async setEndpointState(endpointId: string, clusterName: string, stateName: string, stateValue: any) {
        const device = this.devices.get(endpointId);
        if (device) {
            device.updateState(clusterName, stateName, stateValue);
        }
    }
}
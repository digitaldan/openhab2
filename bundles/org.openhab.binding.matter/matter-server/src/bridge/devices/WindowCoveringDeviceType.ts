import { Endpoint } from "@matter/node";
import { WindowCoveringDevice } from "@matter/node/devices/window-covering";
import { MovementDirection, MovementType, WindowCoveringServer } from '@matter/node/behaviors/window-covering';
import { WindowCovering } from '@matter/main/clusters';
import { GenericDeviceType } from './GenericDeviceType';

export class WindowCoveringDeviceType extends GenericDeviceType {

    override createEndpoint(clusterValues: Record<string, any>) {
        const features: WindowCovering.Feature[] = [];
        features.push(WindowCovering.Feature.Lift);
        features.push(WindowCovering.Feature.PositionAwareLift);

        const endpoint = new Endpoint(WindowCoveringDevice.with(this.createWindowCoveringServer().with(
            ...features,
        ), ...this.defaultClusterServers()), {
            ...this.endPointDefaults(),
            ...clusterValues
        });
        endpoint.events.windowCovering.operationalStatus$Changed.on(value => {
            this.sendBridgeEvent("windowCovering", "operationalStatus", value);
        });
        return endpoint
    }

    override defaultClusterValues() {
        return {
            windowCovering: {
                currentPositionLiftPercent100ths: 0
            }
        }
    }

    //this allows us to get all commands to move the device, not just if it thinks the position has changed
    private createWindowCoveringServer(): typeof WindowCoveringServer {
        const parent = this;
        return class extends WindowCoveringServer {
            override async handleMovement(type: MovementType, reversed: boolean, direction: MovementDirection, targetPercent100ths?: number): Promise<void> {
                if (targetPercent100ths != null) {
                    await parent.sendBridgeEvent("windowCovering", "targetPositionLiftPercent100ths", targetPercent100ths);
                }
                //let the bridge set this target locally, openHAB will set the currentPositionLiftPercent100ths as shade values change
                return super.handleMovement(type, reversed, direction, targetPercent100ths);
            }
        };
    }
}
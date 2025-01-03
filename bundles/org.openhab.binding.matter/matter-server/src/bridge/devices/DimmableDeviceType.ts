import { Endpoint } from "@matter/node";
import { DimmableLightDevice } from "@matter/node/devices/dimmable-light";
import { GenericDeviceType } from './GenericDeviceType';
import { LevelControl, OnOff } from "@matter/main/clusters";

export class DimmableDeviceType extends GenericDeviceType {

    override createEndpoint(clusterValues: Record<string, any>) {
        const endpoint = new Endpoint(DimmableLightDevice.with(
            this.createOnOffServer().with(OnOff.Feature.Lighting),
            this.createLevelControlServer().with(LevelControl.Feature.Lighting),
            ...this.defaultClusterServers()), {
            ...this.endPointDefaults(),
            ...clusterValues
        });
        return endpoint;
    }

    override defaultClusterValues() {
        return {
            levelControl: {
                currentLevel: 0
            },
            onOff: {
                onOff: false
            },
        }
    }
}
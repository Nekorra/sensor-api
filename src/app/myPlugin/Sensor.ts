import { registerPlugin } from '@capacitor/core';

export interface SensorPlugin {
    getValue(options: { sens: string }): Promise<{ value: any }>;
    start(options: { sens: string }): any;
    stop(): any;
}


const sensors = registerPlugin<SensorPlugin>('SensorPlugin')  

export default sensors;
import { Component } from '@angular/core';
import { Platform } from '@ionic/angular';
import { BatteryStatus } from '@ionic-native/battery-status/ngx';

import SensorPlugin from '../myPlugin/Sensor'
@Component({
  selector: 'app-home',
  templateUrl: 'home.page.html',
  styleUrls: ['home.page.scss'],
})
export class HomePage {

  proximity: any;

  accelerometerX: any;
  accelerometerY: any;
  accelerometerZ: any;

  gyroscopeX: any;
  gyroscopeY: any;
  gyroscopeZ: any;

  gravityX: any;
  gravityY: any;
  gravityZ: any;

  light: any;

  linearX: any;
  linearY: any;
  linearZ: any;

  magneticX: any;
  magneticY: any;
  magneticZ: any;

  orientationX: any;
  orientationY: any;
  orientationZ: any;

  pressure: any;

  temperature: any;

  battery: number = 0;
  isCharging: string | undefined;

  constructor(
    private platform: Platform,
    private batteryStatus: BatteryStatus,
    ) {
    this.platform.ready().then(() => {
      this.getSensorData();
    });
  }

  async getSensorData() {
    await SensorPlugin.start({sens: "PROXIMITY"});
    await SensorPlugin.start({sens: "ACCELEROMETER"});
    await SensorPlugin.start({sens: "GYROSCOPE"});
    await SensorPlugin.start({sens: "GRAVITY"});
    await SensorPlugin.start({sens: "LIGHT"});
    await SensorPlugin.start({sens: "LINEAR_ACCELERATION"});
    await SensorPlugin.start({sens: "MAGNETIC_FIELD"});
    await SensorPlugin.start({sens: "ORIENTATION"});
    await SensorPlugin.start({sens: "PRESSURE"});

    setInterval(() => {
      SensorPlugin.getValue({sens: "PROXIMITY"}).then((values) => {
        this.proximity = values.value[0];
      });  
    }, 300)

    setInterval(() => {
      SensorPlugin.getValue({sens: "ACCELEROMETER"}).then((values) => {
        this.accelerometerX = Math.round(values.value[0] * 10000000) / 10000000;
        this.accelerometerY = Math.round(values.value[1] * 10000000) / 10000000;
        this.accelerometerZ = Math.round(values.value[2] * 10000000) / 10000000;
      });  
    }, 300)

    setInterval(() => {
      SensorPlugin.getValue({sens: "GYROSCOPE"}).then((values) => {
        this.gyroscopeX = Math.round(values.value[0] * 10000000) / 10000000;
        this.gyroscopeY = Math.round(values.value[1] * 10000000) / 10000000;
        this.gyroscopeZ = Math.round(values.value[2] * 10000000) / 10000000;
      });  
    }, 300)

    setInterval(() => {
      SensorPlugin.getValue({sens: "GRAVITY"}).then((values) => {
        this.gravityX = Math.round(values.value[0] * 10000000) / 10000000;
        this.gravityY = Math.round(values.value[1] * 10000000) / 10000000;
        this.gravityZ = Math.round(values.value[2] * 10000000) / 10000000;
      });  
    }, 300)

    setInterval(() => {
      SensorPlugin.getValue({sens: "LIGHT"}).then((values) => {
        this.light =  Math.round(values.value[0] * 10000000) / 10000000;
      });  
    }, 300)

    setInterval(() => {
      SensorPlugin.getValue({sens: "LINEAR_ACCELERATION"}).then((values) => {
        this.linearX = Math.round(values.value[0] * 10000000) / 10000000;
        this.linearY = Math.round(values.value[1] * 10000000) / 10000000;
        this.linearZ = Math.round(values.value[2] * 10000000) / 10000000;
      });  
    }, 300)

    setInterval(() => {
      SensorPlugin.getValue({sens: "MAGNETIC_FIELD"}).then((values) => {
        this.magneticX = Math.round(values.value[0] * 10000000) / 10000000;
        this.magneticY = Math.round(values.value[1] * 10000000) / 10000000;
        this.magneticZ = Math.round(values.value[2] * 10000000) / 10000000;
      });  
    }, 300)

    setInterval(() => {
      SensorPlugin.getValue({sens: "ORIENTATION"}).then((values) => {
        this.orientationX = Math.round(values.value[0] * 10000000) / 10000000;
        this.orientationY = Math.round(values.value[1] * 10000000) / 10000000;
        this.orientationZ = Math.round(values.value[2] * 10000000) / 10000000;
      });  
    }, 300)

    setInterval(() => {
      SensorPlugin.getValue({sens: "PRESSURE"}).then((values) => {
        this.pressure = Math.round(values.value[0] * 10000000) / 10000000;
      });  
    }, 300)

    this.batteryStatus.onChange().subscribe(status => {
      this.battery = status.level;
      if (status.isPlugged  == true) {
        this.isCharging = "Charging";
      } else {
        this.isCharging = "Not Charging";
      }
    });

  }

  

}

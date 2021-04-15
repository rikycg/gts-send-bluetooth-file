import { WebPlugin } from '@capacitor/core';
import { SendBluetoothPlugin } from './definitions';

export class SendBluetoothWeb extends WebPlugin implements SendBluetoothPlugin {
  constructor() {
    super({
      name: 'SendBluetooth',
      platforms: ['web'],
    });
  }

  async echo(options: { value: string }): Promise<{ value: string }> {
    return options;
  }

  async sendFile(options: { path: string }): Promise<any> {
    return options;
  }

}

const SendBluetooth = new SendBluetoothWeb();

export { SendBluetooth };

import { registerWebPlugin } from '@capacitor/core';
registerWebPlugin(SendBluetooth);

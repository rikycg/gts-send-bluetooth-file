declare module '@capacitor/core' {
  interface PluginRegistry {
    SendBluetooth: SendBluetoothPlugin;
  }
}

export interface SendBluetoothPlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  sendFile(options: { path: string }): Promise<any>;
}

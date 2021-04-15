package com.guilts.plugins.bluetooth;
import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import androidx.core.content.FileProvider;
import com.getcapacitor.JSObject;
import com.getcapacitor.NativePlugin;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;

import java.io.File;

@NativePlugin(
    permissions={
        Manifest.permission.READ_EXTERNAL_STORAGE,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    }
)
public class SendBluetooth extends Plugin {

    @PluginMethod
    public void echo(PluginCall call) {
        String value = call.getString("value");

        JSObject ret = new JSObject();
        ret.put("value", value);
        call.success(ret);
    }

    @PluginMethod
    public void sendFile(PluginCall call) {
        String path = call.getString("path");
        JSObject result = new JSObject();
        String route = Environment.getExternalStorageDirectory().getPath() + File.separator + path;
        if(hasRequiredPermissions()) {
            File file = new File(route);
            if (!file.exists()) {
                call.reject('The file not exist');
                return;
            }
            Intent intentShare = new Intent(Intent.ACTION_SEND);
            intentShare.setType("application/pdf");
            Uri uri = FileProvider.getUriForFile(getContext(), getContext().getApplicationContext().getPackageName() + ".provider", file);
            intentShare.putExtra(Intent.EXTRA_STREAM, uri);
            startActivityForResult(call, Intent.createChooser(intentShare, "Share the file ..."),1);
        }
        call.resolve();
    }
}

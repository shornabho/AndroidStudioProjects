package com.mcs.android.broadcastreceiver;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Switch wifiSwitch;
    private Switch bluetoothSwitch;
    private Button captureButton;
    private ImageView imageViewCapturedImage;
    private WifiManager wifiManager;
    private BluetoothAdapter bluetoothAdapter;

    private static final int CAMERA_PERMISSION_CODE = 101;
    private static final int CAMERA_REQUEST_CODE = 102;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Wifi
        wifiSwitch = (Switch) findViewById(R.id.wifiSwitch);
        wifiManager = (WifiManager) getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        wifiSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    wifiManager.setWifiEnabled(true);
                    wifiSwitch.setText("Wifi On");
                }
                else {
                    wifiManager.setWifiEnabled(false);
                    wifiSwitch.setText("Wifi Off");
                }
            }
        });

        if (wifiManager.isWifiEnabled()) {
            wifiSwitch.setChecked(true);
            wifiSwitch.setText("Wifi On");
        }
        else {
            wifiSwitch.setChecked(false);
            wifiSwitch.setText("Wifi Off");
        }

        // Bluetooth
        bluetoothSwitch = (Switch) findViewById(R.id.bluetoothSwitch);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Device does not support Bluetooth", Toast.LENGTH_SHORT).show();
            bluetoothSwitch.setClickable(false);
            bluetoothSwitch.setTextColor(getColor(R.color.material_on_surface_disabled));
        }
        else {
            if (bluetoothAdapter.isEnabled()) {
                bluetoothSwitch.setChecked(true);
                bluetoothSwitch.setText("Bluetooth On");
            }
            else {
                bluetoothSwitch.setChecked(false);
                bluetoothSwitch.setText("Bluetooth Off");
            }

            bluetoothSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                    if (isChecked) {
                        bluetoothAdapter.enable();
                        bluetoothSwitch.setText("Bluetooth On");
                    }
                    else {
                        bluetoothAdapter.disable();
                        bluetoothSwitch.setText("Bluetooth Off");
                    }
                }
            });
        }

        // Camera
        captureButton = findViewById(R.id.captureButton);
        imageViewCapturedImage = findViewById(R.id.imageViewCapturedImage);
        captureButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                askForCameraPermission();
            }
        });
    }

    private void askForCameraPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.CAMERA}, CAMERA_PERMISSION_CODE);
        } else {
            launchCamera();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == CAMERA_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                launchCamera();
            }
            else {
                Toast.makeText(this, "Camera permission is required.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void launchCamera() {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST_CODE) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            imageViewCapturedImage.setImageBitmap(captureImage);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        registerReceiver(wifiStateReceiver, new IntentFilter(WifiManager.WIFI_STATE_CHANGED_ACTION));
        registerReceiver(bluetoothStateReceiver, new IntentFilter(bluetoothAdapter.ACTION_STATE_CHANGED));
    }

    @Override
    protected void onStop() {
        super.onStop();
        unregisterReceiver(wifiStateReceiver);
        unregisterReceiver(bluetoothStateReceiver);
    }

    private BroadcastReceiver wifiStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            int wifiStateExtra = intent.getIntExtra(WifiManager.EXTRA_WIFI_STATE, WifiManager.WIFI_STATE_UNKNOWN);

            switch (wifiStateExtra) {
                case WifiManager.WIFI_STATE_ENABLING:
                    Toast.makeText(context, "Turning Wifi On", Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_ENABLED:
                    wifiSwitch.setChecked(true);
                    wifiSwitch.setText("Wifi On");
                    break;
                case WifiManager.WIFI_STATE_DISABLING:
                    Toast.makeText(context, "Turning Wifi Off", Toast.LENGTH_SHORT).show();
                    break;
                case WifiManager.WIFI_STATE_DISABLED:
                    wifiSwitch.setChecked(false);
                    wifiSwitch.setText("Wifi Off");
                    break;
            }
        }
    };

    private BroadcastReceiver bluetoothStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            int state;
            BluetoothDevice bluetoothDevice;

            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, -1);
                switch (state) {
                    case BluetoothAdapter.STATE_TURNING_ON:
                        Toast.makeText(context, "Turning Bluetooth On", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_ON:
                        bluetoothSwitch.setChecked(true);
                        bluetoothSwitch.setText("Bluetooth On");
                        break;
                    case BluetoothAdapter.STATE_TURNING_OFF:
                        Toast.makeText(context, "Turning Bluetooth Off", Toast.LENGTH_SHORT).show();
                        break;
                    case BluetoothAdapter.STATE_OFF:
                        bluetoothSwitch.setChecked(false);
                        bluetoothSwitch.setText("Bluetooth Off");
                        break;
                }
            }
        }
    };

}
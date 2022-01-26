package com.jerry.customir;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Service;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.ConsumerIrManager;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    private ConsumerIrManager consumerIrManager;

    private static final int IR_CARRIER_FREQUENCY_38KHZ = 38000;
    private Vibrator mVibrator;

    private int[] rawData_nightlight = {8730,4420, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,570, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,570, 530,1620, 530,570, 530,1620, 530,570, 530,570, 530,570, 530,520, 530,620, 480,620, 480,1670, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1620, 530};
    private int[] rawData_On = {8730,4420, 480,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,570, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,570, 530,1670, 480,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,570, 530,570, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530};
    private int[] rawData_Off = {8730,4420, 530,570, 480,620, 480,620, 480,570, 530,570, 530,570, 530,570, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,570, 480,1670, 530,570, 530,570, 480,620, 480,570, 530,570, 530,570, 530,570, 530,570, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,1670, 480};
    private int[] rawData_arrow_up = {8730,4420, 480,570, 530,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,570, 530,1670, 480,620, 480,570, 530,1670, 480,570, 530,570, 530,570, 530,1620, 530,570, 530,1670, 480,1670, 480,620, 480,1670, 530,1620, 530,1620, 530};
    private int[] rawData_arrow_left = {8730,4420, 530,570, 530,570, 480,620, 480,570, 530,570, 530,570, 530,570, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,1670, 530,570, 480,620, 480,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,570, 530,570, 530,1670, 480,570, 530,570, 530,570, 530,570, 480,1670, 530,1620, 530,1670, 480};
    private int[] rawData_lightness = {8730,4370, 530,620, 480,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1620, 580,1620, 530,1620, 530,1670, 480,620, 480,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,570, 530};
    private int[] rawData_arrow_right = {8730,4420, 480,620, 480,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1620, 530,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,570, 530,1670, 480,620, 480,570, 530,1670, 480,570, 530,570, 530,570, 530,1620, 530,570, 530,1670, 480,1670, 480,620, 480,1670, 480,1670, 530,1620, 530};
    private int[] rawData_arrow_down = {8780,4370, 530,570, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,570, 530,570, 530,1620, 530,1670, 480,1670, 530,1620, 530,570, 530,570, 480,620, 480,1670, 530,570, 480,620, 480,570, 530,570, 530,1670, 480,1670, 480,1670, 530};
    private int[] rawData_auxiliary1 = {8730,4420, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,570, 530,1620, 530,1620, 530,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,570, 530,570, 530,1620, 530,570, 530,1620, 530,1670, 480,570, 530,570, 530,570, 530,1620, 530,570, 530,1620, 530,570, 530,570, 530,1620, 530,1670, 480,1670, 480};
    private int[] rawData_auxiliary2 = {8730,4370, 530,620, 480,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,570, 530,570, 530,1670, 480,570, 530,1670, 480,1670, 480,620, 480,620, 480,570, 530,1670, 480,570, 530,1670, 480,620, 480,570, 530,1670, 480,1670, 530,1620, 530};
    private int[] rawData_auxiliary3 = {8730,4420, 530,570, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,570, 530,570, 530,1620, 530,570, 530,1670, 480,1670, 480,620, 480,570, 530,570, 530,1670, 480,570, 530,1670, 480,620, 480,570, 530,1670, 480,1670, 530,1620, 530};
    private int[] rawData_warmth_decrease = {8730,4420, 530,570, 530,570, 480,620, 480,620, 480,570, 530,570, 530,570, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,570, 530,1620, 530,1670, 480,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,620, 480,570, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480};
    private int[] rawData_warmth_increase = {8730,4370, 530,620, 480,570, 530,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530,1670, 480,620, 480,570, 530,1670, 480,620, 530,520, 530,570, 530,570, 530,570, 530,570, 480,1670, 530,570, 480,1670, 530,1620, 530,1670, 480,1670, 530,1620, 530,1620, 530};
    private int[] rawData_segment = {8730,4420, 480,570, 530,570, 530,570, 530,570, 480,620, 480,570, 530,570, 530,1670, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480,1670, 530,1620, 530,570, 530,570, 530,1620, 530,1670, 480,570, 530,570, 530,570, 530,570, 480,620, 480,1670, 530,570, 480,620, 480,1670, 480,1670, 530,1620, 530,1670, 480,1670, 480};


    /**
     * The IR carrier frequency in Hertz.
     */
    private int irCarrierFrequency = IR_CARRIER_FREQUENCY_38KHZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mVibrator = (Vibrator) getApplication().getSystemService(Service.VIBRATOR_SERVICE);

    }
    @Override
    protected void onStart() {
        super.onStart();

        consumerIrManager = getConsumerIrManager(getApplicationContext());
        if (consumerIrManager != null && consumerIrManager.hasIrEmitter()) {
            initView();

            // Get the IR carrier frequency range and print to console.
            ConsumerIrManager.CarrierFrequencyRange[] carrierFrequencies = consumerIrManager.getCarrierFrequencies();
            for (ConsumerIrManager.CarrierFrequencyRange carrierFrequency : carrierFrequencies) {

                Log.d(TAG, "onCreate: " + carrierFrequency.toString());
                Log.d(TAG, "onCreate: " +
                        String.format("IR Carrier Frequency: %d - %d", carrierFrequency.getMinFrequency(),
                                carrierFrequency.getMaxFrequency()));
            }
        } else {
            Toast.makeText(this, "您的设备不支持红外线发射功能", Toast.LENGTH_SHORT).show();
        }
    }

    private void initView() {
        Button btn_nightlight = findViewById(R.id.btn_nightlight);
        btn_nightlight.setEnabled(true);
        btn_nightlight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_nightlight); }
        });

        Button btn_enable = findViewById(R.id.btn_enable);
        btn_enable.setEnabled(true);
        btn_enable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_On); }
        });

        Button btn_disable = findViewById(R.id.btn_disable);
        btn_disable.setEnabled(true);
        btn_disable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_Off); }
        });

        Button btn_arrow_up = findViewById(R.id.btn_arrow_up);
        btn_arrow_up.setEnabled(true);
        btn_arrow_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_arrow_up); }
        });

        Button btn_arrow_left = findViewById(R.id.btn_arrow_left);
        btn_arrow_left.setEnabled(true);
        btn_arrow_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_arrow_left); }
        });

        Button btn_lightness = findViewById(R.id.btn_lightness);
        btn_lightness.setEnabled(true);
        btn_lightness.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_lightness); }
        });


        Button btn_arrow_right = findViewById(R.id.btn_arrow_right);
        btn_arrow_right.setEnabled(true);
        btn_arrow_right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_arrow_right); }
        });

        Button btn_arrow_down = findViewById(R.id.btn_arrow_down);
        btn_arrow_down.setEnabled(true);
        btn_arrow_down.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_arrow_down); }
        });

        Button btn_auxiliary1 = findViewById(R.id.btn_auxiliary1);
        btn_auxiliary1.setEnabled(true);
        btn_auxiliary1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_auxiliary1); }
        });

        Button btn_auxiliary2 = findViewById(R.id.btn_auxiliary2);
        btn_auxiliary2.setEnabled(true);
        btn_auxiliary2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_auxiliary2); }
        });

        Button btn_auxiliary3 = findViewById(R.id.btn_auxiliary3);
        btn_auxiliary3.setEnabled(true);
        btn_auxiliary3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_auxiliary3); }
        });
        Button btn_warmth_decrease = findViewById(R.id.btn_warmth_decrease);
        btn_warmth_decrease.setEnabled(true);
        btn_warmth_decrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_warmth_decrease); }
        });
        Button btn_warmth_increase = findViewById(R.id.btn_warmth_increase);
        btn_warmth_increase.setEnabled(true);
        btn_warmth_increase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_warmth_increase); }
        });
        Button btn_segment = findViewById(R.id.btn_segment);
        btn_segment.setEnabled(true);
        btn_segment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { irTransmit(rawData_segment); }
        });
    }

    private boolean hasSystemFeatureConsumerIr(Context context) {
        return context.getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CONSUMER_IR);
    }

    private ConsumerIrManager getConsumerIrManager(Context context) {
        if (hasSystemFeatureConsumerIr(context)) {
            return (ConsumerIrManager) context.getSystemService(Context.CONSUMER_IR_SERVICE);
        }
        return null;
    }

    private void irTransmit(int[] pattern) {
        // The alternating on/off pattern in microseconds to transmit.
        consumerIrManager.transmit(irCarrierFrequency, pattern);
        mVibrator.vibrate(new long[]{1000, 1000}, -1);
        mVibrator.cancel();
    }
}
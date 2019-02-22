package com.w3epic.accelerometertest;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    TextView tvAcceleration;
    ImageView ivBall;
    LinearLayout board;

    private SensorManager sensorManager;
    float ax, ay, az;   // these are the acceleration in x,y and z axis
    float minx = Integer.MAX_VALUE, maxx = Integer.MIN_VALUE;
    float miny = Integer.MAX_VALUE, maxy = Integer.MIN_VALUE;
    float minz = Integer.MAX_VALUE, maxz = Integer.MIN_VALUE;
    float[] mGravity = null;
    float[] mGeomagnetic = null;
    float azimuth, pitch, roll;

    static double getX(double g) {
        // x = -5.906662766·10-16 x2 + 15.85117227 x + 155.5
        // x = -5.906662766·10-16 x2 - 15.85117227 x + 155.5
        double x = -5.906662766 * Math.pow(10, -16) * g*g - 15.85117227 * g + 155.5;
        return x;
    }

    static double getY(double g) {
        // y = 1.181332553·10-15 x2 + 21.15188583 x + 207.5
        double y = 1.181332553 * Math.pow(10, -15) * g*g + 21.15188583 * g + 207.5;
        return y;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_NORMAL);
        sensorManager.registerListener(this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD), SensorManager.SENSOR_DELAY_NORMAL);

        tvAcceleration = findViewById(R.id.tvAcceleration);
        ivBall = findViewById(R.id.ivBall);
        board = findViewById(R.id.board);
        //ivBall.setX(155.5f);
        //ivBall.setY(207.5f);
    }

    @Override
    public void onAccuracyChanged(Sensor arg0, int arg1) {
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        // read accelerometer
        // https://stackoverflow.com/questions/5180187/how-do-i-use-the-android-accelerometer
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            ax = event.values[0];
            ay = event.values[1];
            az = event.values[2];
            tvAcceleration.setText("X: " + ax + "\tY: " + ay + "\tZ: " + az);

            DisplayMetrics displayMetrics = this.getResources().getDisplayMetrics();
            float dpHeight = board.getHeight();//displayMetrics.heightPixels / displayMetrics.density;
            float dpWidth = board.getWidth();//displayMetrics.widthPixels / displayMetrics.density;

            // range x: 0 - 311; y: 0-415
            //ivBall.setX((float)getX(ax));
            //ivBall.setY((float)getY(ay));
            Log.d("xy", "x: " + ivBall.getX() + ", y: " + ivBall.getY());
            Log.d("xy", "dpWidth: " + dpWidth + ", dpHeight: " + dpHeight);

            if (ivBall.getX() >= 0 && ivBall.getX() <= dpWidth - 65 && ax > 3) {
                ivBall.setX(ivBall.getX() - 10);
            }
            if (ivBall.getX() + 10 >= 0 && ivBall.getX() <= dpWidth - 200  && ax < -3) {
                ivBall.setX(ivBall.getX() + 10);
            }

            if (ivBall.getY() + 10 >= 0 && ivBall.getY() <= dpHeight - 200 && ay > 3) {
                ivBall.setY(ivBall.getY() + 10);
            }
            if (ivBall.getY() >= 0 && ivBall.getY() <= dpHeight - 65 && ay < -3) {
                ivBall.setY(ivBall.getY() - 10);
            }

            ivBall.setScaleX((float)az / 10);
            ivBall.setScaleY((float)az / 10);


            //LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            //lp.setMargins((int)getX(ax), 415, 0, 0);
            //ivBall.setLayoutParams(lp);


            //ViewGroup.MarginLayoutParams marginParams = (ViewGroup.MarginLayoutParams) ivBall.getLayoutParams();
            //marginParams.setMargins((int)getX(ax), 415, 0, 0);

            Log.d("accelerometer", ax + " " + ay + " " + az);
            Log.d("debug", "marginLeft: " + getX(ax) + ", marginTop: " + getY(ay));

//
//            minx = Math.min(minx, ax);
//            maxx = Math.max(maxx, ax);
//            miny = Math.min(miny, ay);
//            maxy = Math.max(maxy, ay);
//            minz = Math.min(minz, az);
//            maxz = Math.max(maxz, az);
//
//            Log.d("accelerometer", ax + " " + ay + " " + az);
//            Log.d("min/max x",  " min: " + minx + ", max: " + maxx);
//            Log.d("min/max y",  " min: " + miny + ", max: " + maxy);
//            Log.d("min/max z",  " min: " + minz + ", max: " + maxz);
//            Log.d("getResolution", String.valueOf(event.sensor.getResolution()));
//
//            SystemClock.sleep(250);
        }
        // https://stackoverflow.com/questions/27106607/get-rotation-and-display-in-degrees
        /*if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            mGravity = event.values;

        if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            mGeomagnetic = event.values;

        //Log.d("Debug", String.valueOf(mGravity) + String.valueOf(mGeomagnetic));

        if (mGravity != null && mGeomagnetic != null) {
            float R[] = new float[9];
            float I[] = new float[9];

            boolean success = SensorManager.getRotationMatrix(R, I, mGravity, mGeomagnetic);

            if (success) {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimuth = orientation[0]; // orientation contains: azimuth, pitch and roll
                pitch = orientation[1];
                roll = orientation[2];

                Log.d("Debug", azimuth + ", " + pitch + ", " + roll);
            }
        }*/
    }
}

package com.learn.app_vs_wild;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;

public class CompassActivity extends AppCompatActivity implements SensorEventListener {

    // cette variable repressente l'image du compas
    private ImageView ressourceImageCompass;
    // cette variable représente un tableau de float de taille max 3 pour la gravité
    private float[] myGravity = new float[3];
    // cette variable représente un tableau de float de taille max 3 pour la geometrie
    private float[] myGeomagnetic = new float[3];
    // cette variable représente l'angle pour le compas
    private float azimuth = 0f;
    // cette variable représente la correction à apporté a l'azimuth
    private float correctionAzimuth = 0f;
    // cette variable représente la l'état sensoriel du telephone
    private SensorManager mySensorManager;


    /**
     * Activité de la page
     */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compass);
        //récupération de l'id pour l'attribué a la variable ressourceImageCompass
        ressourceImageCompass = findViewById(R.id.compas);
        //récupéretion de l'etat sensoriel du téléphone
        mySensorManager =(SensorManager)getSystemService(SENSOR_SERVICE);

    }

    /**
     *
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    protected void onResume()
    {
        super.onResume();
        mySensorManager.registerListener(this, mySensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD),
                SensorManager.SENSOR_DELAY_GAME);
        mySensorManager.registerListener(this, mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    /**
     *
     */
    @Override
    protected void onPause(){
        super.onPause();
        mySensorManager.unregisterListener(this);
    }

    /**
     * Cette surcharge de méthode permet de mettre a jour les informations de l'état sensorielle du téléphone
     * @param event
     */
    @Override
    public void onSensorChanged(SensorEvent event) {
        final float alpla = 0.97f;
        /**
         * Syncronise l'activity actuelle avec l'état sensorielle
         */
        synchronized (this)
        {
            /**
             * condition comparant le type de sensor si TYPE_ACCELEROMETER (l'accélérometre)
             */
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER)
            {
                myGravity[0] = alpla* myGravity[0]+(1-alpla)*event.values[0];
                myGravity[1] = alpla* myGravity[1]+(1-alpla)*event.values[1];
                myGravity[2] = alpla* myGravity[2]+(1-alpla)*event.values[2];
            }
            /**
             * condition comparant le type de sensor si TYPE_MAGNETIC_FIELD ( magnétique )
             */
            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD)
            {
                myGeomagnetic[0] = alpla*myGeomagnetic[0]+(1-alpla)*event.values[0];
                myGeomagnetic[1] = alpla*myGeomagnetic[1]+(1-alpla)*event.values[1];
                myGeomagnetic[2] = alpla*myGeomagnetic[2]+(1-alpla)*event.values[2];
            }
            
            float R[] = new float[9];
            float I[] = new float[9];
            boolean success = SensorManager.getRotationMatrix(R,I, myGravity,myGeomagnetic);

            /**
             * condition pour modifié l'animation en fonction de l'angle
             */
            if(success)
            {
                float orientation[] = new float[3];
                SensorManager.getOrientation(R,orientation);
                azimuth = (float)Math.toDegrees(orientation[0]);
                azimuth = (azimuth+360)%360;

                Animation animation = new RotateAnimation(-correctionAzimuth,
                        -azimuth,
                        Animation.RELATIVE_TO_SELF,
                        0.5f,
                        Animation.RELATIVE_TO_SELF,
                        0.5f);

                correctionAzimuth = azimuth;

                animation.setDuration(500);
                animation.setRepeatCount(0);
                animation.setFillAfter(true);

                ressourceImageCompass.startAnimation(animation);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy)
    {

    }
}

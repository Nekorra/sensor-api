package io.ionic.starter;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import java.util.ArrayList;


@CapacitorPlugin(name = "SensorPlugin")
public class SensorPlugin extends Plugin implements SensorEventListener {

  JSObject sensorData;
  String TYPE_SENSOR;

  private SensorManager mSensorManager; // Sensor manager
  Sensor mSensor;                     // Compass sensor returned by sensor manager

  /**
   * Constructor.
   */
  public SensorPlugin() {
    this.sensorData = new JSObject();
    this.TYPE_SENSOR = "";
  }


  @PluginMethod
  public void stop(PluginCall call) {
    this.mSensorManager.unregisterListener(this);
    this.sensorData = new JSObject();
  }

  @PluginMethod
  public void getValue(PluginCall call) throws JSONException {
    String sens = call.getString("sens");
    JSObject ret = new JSObject();
    ret.put("value", this.sensorData.get(sens));
    call.resolve(ret);
  }

  @PluginMethod
  public void start(PluginCall call) throws JSONException {

    synchronized (this) {
      if (mSensorManager == null) {
        mSensorManager = (SensorManager)this.getActivity().getSystemService(Context.SENSOR_SERVICE);
      }
    }


    String sens = call.getString("sens");
    @SuppressWarnings("deprecation")
    List<Sensor> list = new ArrayList<Sensor>();

    if(sens.equals("PROXIMITY")){
      list = mSensorManager.getSensorList(Sensor.TYPE_PROXIMITY);
    } else if(sens.equals("ACCELEROMETER")){
      list = mSensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
    } else if(sens.equals("GRAVITY")){
      list = mSensorManager.getSensorList(Sensor.TYPE_GRAVITY);
    } else if(sens.equals("GYROSCOPE")){
      list = mSensorManager.getSensorList(Sensor.TYPE_GYROSCOPE);
    } else if(sens.equals("GYROSCOPE_UNCALIBRATED")){
      list = mSensorManager.getSensorList(Sensor.TYPE_GYROSCOPE_UNCALIBRATED);
    } else if(sens.equals("LINEAR_ACCELERATION")){
      list = mSensorManager.getSensorList(Sensor.TYPE_LINEAR_ACCELERATION);
    } else if(sens.equals("ROTATION_VECTOR")){
      list = mSensorManager.getSensorList(Sensor.TYPE_ROTATION_VECTOR);
    } else if(sens.equals("SIGNIFICANT_MOTION")){
      list = mSensorManager.getSensorList(Sensor.TYPE_SIGNIFICANT_MOTION);
    } else if(sens.equals("STEP_COUNTER")){
      list = mSensorManager.getSensorList(Sensor.TYPE_STEP_COUNTER);
    } else if(sens.equals("STEP_DETECTOR")){
      list = mSensorManager.getSensorList(Sensor.TYPE_STEP_DETECTOR);
    } else if(sens.equals("GAME_ROTATION_VECTOR")){
      list = mSensorManager.getSensorList(Sensor.TYPE_GAME_ROTATION_VECTOR);
    } else if(sens.equals("GEOMAGNETIC_ROTATION_VECTOR")){
      list = mSensorManager.getSensorList(Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR);
    } else if(sens.equals("MAGNETIC_FIELD")){
      list = mSensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD);
    } else if(sens.equals("MAGNETIC_FIELD_UNCALIBRATED")){
      list = mSensorManager.getSensorList(Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED);
    } else if(sens.equals("ORIENTATION")){
      list = mSensorManager.getSensorList(Sensor.TYPE_ORIENTATION);
    } else if(sens.equals("AMBIENT_TEMPERATURE")){
      list = mSensorManager.getSensorList(Sensor.TYPE_AMBIENT_TEMPERATURE);
    } else if(sens.equals("LIGHT")){
      list = mSensorManager.getSensorList(Sensor.TYPE_LIGHT);
    } else if(sens.equals("PRESSURE")){
      list = mSensorManager.getSensorList(Sensor.TYPE_PRESSURE);
    } else if(sens.equals("RELATIVE_HUMIDITY")){
      list = mSensorManager.getSensorList(Sensor.TYPE_RELATIVE_HUMIDITY);
    } else if(sens.equals("TEMPERATURE")){
      list = mSensorManager.getSensorList(Sensor.TYPE_TEMPERATURE);
    }

    // If found, then register as listener
    if (list != null && list.size() > 0) {
      this.mSensor = list.get(0);
      this.mSensorManager.registerListener(this, this.mSensor, SensorManager.SENSOR_DELAY_NORMAL);
      this.sensorData.put(sens, "");
    }
    call.resolve();
  }

  /**
   * Stop listening to compass sensor.
   */
  public void onAccuracyChanged(Sensor sensor, int accuracy) {
    // TODO Auto-generated method stub
  }


  public void onSensorChanged(SensorEvent event) {
    try {
      Sensor sensor = event.sensor;
      if (sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("ACCELEROMETER", value);

      }
      if (sensor.getType() == Sensor.TYPE_AMBIENT_TEMPERATURE) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("AMBIENT_TEMPERATURE", value);


      }
      if (sensor.getType() == Sensor.TYPE_GAME_ROTATION_VECTOR) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("GAME_ROTATION_VECTOR", value);


      }
      if (sensor.getType() == Sensor.TYPE_GEOMAGNETIC_ROTATION_VECTOR) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("GEOMAGNETIC_ROTATION_VECTOR", value);


      }
      if (sensor.getType() == Sensor.TYPE_GRAVITY) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("GRAVITY", value);


      }
      if (sensor.getType() == Sensor.TYPE_GYROSCOPE) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("GYROSCOPE", value);


      }
      if (sensor.getType() == Sensor.TYPE_GYROSCOPE_UNCALIBRATED) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("GYROSCOPE_UNCALIBRATED", value);


      }
      if (sensor.getType() == Sensor.TYPE_LIGHT) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("LIGHT", value);


      }
      if (sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("LINEAR_ACCELERATION", value);


      }
      if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("MAGNETIC_FIELD", value);


      }
      if (sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD_UNCALIBRATED) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("MAGNETIC_FIELD_UNCALIBRATED", value);

      }
      if (sensor.getType() == Sensor.TYPE_ORIENTATION) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("ORIENTATION", value);


      }
      if (sensor.getType() == Sensor.TYPE_PRESSURE) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("PRESSURE", value);


      }
      if (sensor.getType() == Sensor.TYPE_PROXIMITY) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("PROXIMITY", value);

      }
      if (sensor.getType() == Sensor.TYPE_RELATIVE_HUMIDITY) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("RELATIVE_HUMIDITY", value);

      }
      if (sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("ROTATION_VECTOR", value);


      }
      if (sensor.getType() == Sensor.TYPE_SIGNIFICANT_MOTION) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("SIGNIFICANT_MOTION", value);


      }
      if (sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("STEP_COUNTER", value);

      }
      if (sensor.getType() == Sensor.TYPE_STEP_DETECTOR) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("STEP_DETECTOR", value);


      }
      if (sensor.getType() == Sensor.TYPE_TEMPERATURE) {
        JSONArray value = new JSONArray();
        for(int i=0;i < event.values.length;i++){
          value.put(Float.parseFloat(event.values[i]+""));
        }
        this.sensorData.put("TEMPERATURE", value);
      }

    } catch (JSONException e) {
      e.printStackTrace();
    }
  }



}

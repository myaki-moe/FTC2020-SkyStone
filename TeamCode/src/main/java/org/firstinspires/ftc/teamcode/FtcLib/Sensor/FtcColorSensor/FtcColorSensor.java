package org.firstinspires.ftc.teamcode.FtcLib.Sensor.FtcColorSensor;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FtcColorSensor {

    private ColorSensor colorSensor = null;

    public FtcColorSensor(String name , HardwareMap hardwareMap) {
        colorSensor = hardwareMap.get(ColorSensor.class,name);
    }

    public int getRed(){
        return colorSensor.red();
    }
    public int getGreen(){
        return colorSensor.green();
    }
    public int getBlue(){
        return colorSensor.blue();
    }
    public int getAlpha(){
        return colorSensor.alpha();
    }
    public int getHue(){
        return colorSensor.argb();
    }

}

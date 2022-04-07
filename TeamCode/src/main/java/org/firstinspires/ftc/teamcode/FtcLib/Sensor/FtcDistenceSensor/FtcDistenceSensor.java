package org.firstinspires.ftc.teamcode.FtcLib.Sensor.FtcDistenceSensor;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class FtcDistenceSensor extends Thread{

    public DistanceSensor distanceSensor = null;
    public volatile double distance = 0;
    public boolean flag = true;

    public FtcDistenceSensor(String name, HardwareMap hardwareMap){
        distanceSensor = hardwareMap.get(DistanceSensor.class,name);
        this.start();
    }

    public void run(){
        while (flag)
        {
            distance = distanceSensor.getDistance(DistanceUnit.MM);
            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}

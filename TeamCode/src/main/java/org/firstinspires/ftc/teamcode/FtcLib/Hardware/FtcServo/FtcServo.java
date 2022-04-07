package org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcServo;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FtcServo {

    public Servo servo = null;
    public double maxAngle;

    public FtcServo(String name, HardwareMap hardwareMap) {
        servo = hardwareMap.get(Servo.class, name);
        this.maxAngle = 180;
    }

    public FtcServo(String name, HardwareMap hardwareMap,double maxAngle) {
        servo = hardwareMap.get(Servo.class, name);
        this.maxAngle = maxAngle;
    }

    public void setReversed(boolean reversed){
        if (reversed) servo.setDirection(Servo.Direction.REVERSE);
        else servo.setDirection(Servo.Direction.FORWARD);
    }

    public void setAngle(double angle){
        servo.setPosition(angle/maxAngle);
    }
}

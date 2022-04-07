package org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcCrServo;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class FtcCrServo {

    public CRServo crServo = null;

    public FtcCrServo(String name, HardwareMap hardwareMap){
        crServo = hardwareMap.get(CRServo.class,name);
    }

    public void setReversed(boolean reversed){
        if (reversed) crServo.setDirection(CRServo.Direction.REVERSE);
        else crServo.setDirection(CRServo.Direction.FORWARD);
    }

    public void setPower(double power){
        crServo.setPower(power);
    }
}

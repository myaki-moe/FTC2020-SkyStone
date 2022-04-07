package org.firstinspires.ftc.teamcode.FtcLib.SubSystem.Rotate;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor.FtcMotor;
import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcServo.FtcServo;

public class Rotate {

    private final double conutPerRevolotion = 288;
    private final double gearRatio = 4;

    FtcMotor arm = null;
    FtcServo rotate = null;

    public Rotate(FtcMotor arm, FtcServo rotate){
        this.arm = arm;
        this.rotate = rotate;
    }

    public void rstMotorAngle(){
        arm.resetEncoder();
    }

    public double getMotorAngle(){
        return (arm.getEncoder()/conutPerRevolotion/gearRatio)*360;
    }

    public void setMotorAngle(double angle,double speed){
        arm.setRotateTo(speed,(int)((angle/360)*conutPerRevolotion*gearRatio));
    }
    public void setMotorAngle(double angle){
        arm.setRotateTo(500,(int)((angle/360)*conutPerRevolotion*gearRatio));
    }
    public void setServoAngle(double angle){
        rotate.setAngle(angle);
    }
}

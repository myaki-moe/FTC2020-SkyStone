package org.firstinspires.ftc.teamcode.FtcLib.SubSystem.Elevator;

import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor.FtcMotor;

public class Elevator {

    private FtcMotor ftcMotor;
    public final double stick_length = 140;
    public final double screw_length = 90;
    public final double Screw_lead = 20;

    public Elevator(FtcMotor ftcMotor){
        this.ftcMotor = ftcMotor;
    }

    public void rstMotorEncoder(){
        ftcMotor.resetEncoder();
    }

    public void setSpeed(double speed){
        speed = (stick_length*stick_length);
        ftcMotor.setSpeed(speed);
    }

    public double getScrewPosition(){
        return ftcMotor.getEncoder()/1150.0*Screw_lead;
    }

    public void stop(){
        ftcMotor.setSpeed(0);
    }

}

package org.firstinspires.ftc.teamcode.FtcLib.SubSystem.InTake;

import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor.FtcMotor;

public class InTake {
    private FtcMotor left, right;
    public double maxSpeed;

    public InTake(FtcMotor left, FtcMotor right, double maxSpeed) {
        this.left = left;
        this.right = right;
        this.maxSpeed = maxSpeed;

        this.left.setReversed(true);

        this.left.setBrake(true);
        this.right.setBrake(true);
    }

    public void start(){
        left.setDisable(false);
        right.setDisable(false);
        left.setSpeed(maxSpeed);
        right.setSpeed(maxSpeed);
    }
    public void start(double speed){
        left.setDisable(false);
        right.setDisable(false);
        left.setSpeed(speed);
        right.setSpeed(speed);
    }
    public void start(double lspeed,double rspeed){
        left.setDisable(false);
        right.setDisable(false);
        left.setSpeed(lspeed);
        right.setSpeed(rspeed);
    }
    public void stop(){
        left.setSpeed(0);
        right.setSpeed(0);
        left.setDisable(true);
        right.setDisable(true);
    }
}

package org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor;


public class FtcPIDFCoefficients {
    public double speed_p,speed_i,speed_d,speed_f,position_p;
    public PIDFMode pidfMode;
    public FtcPIDFCoefficients(double speed_p,double speed_i,double speed_d,double speed_f,
                               double position_p){
        this.speed_p = speed_p;
        this.speed_i = speed_i;
        this.speed_d = speed_d;
        this.speed_f = speed_f;
        this.position_p = position_p;
        this.pidfMode = PIDFMode.POSITION;
    }
    public FtcPIDFCoefficients(double speed_p,double speed_i,double speed_d,double speed_f){
        this.speed_p = speed_p;
        this.speed_i = speed_i;
        this.speed_d = speed_d;
        this.speed_f = speed_f;
        this.position_p = 0;
        this.pidfMode = PIDFMode.VELOCITY;
    }
    public FtcPIDFCoefficients(){
        this.speed_p = 0;
        this.speed_i = 0;
        this.speed_d = 0;
        this.speed_f = 0;
        this.position_p = 0;
        this.pidfMode = PIDFMode.OPENLOOP;
    }
    public enum  PIDFMode{POSITION, VELOCITY, OPENLOOP};
}

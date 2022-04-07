package org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor;

public class FtcPIDFCoefficientsTable {
    public static FtcPIDFCoefficients CHASSIS_POSITION = new FtcPIDFCoefficients
            (7,3,3,10,5);
    public static FtcPIDFCoefficients CHASSIS_VELOCITY = new FtcPIDFCoefficients
            (7,3,3,10);
    public static FtcPIDFCoefficients INTAKE = new FtcPIDFCoefficients
            (10,5,0,20);
    public static FtcPIDFCoefficients LIFT = new FtcPIDFCoefficients
            (4,3,2,10);
    public static FtcPIDFCoefficients ARM = new FtcPIDFCoefficients
            (20,10,5,0,2);
}

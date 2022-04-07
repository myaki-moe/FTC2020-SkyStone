package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.ConvertAxes2D.Vector2D;
import org.firstinspires.ftc.teamcode.FtcLib.Sensor.FtcIMU.FtcIMU;

@TeleOp(name = "IMU测试",group = "测试")
@Disabled
public class IMUTest extends OpMode {
    FtcIMU imu = null;
    @Override
    public void init() {
        imu = new FtcIMU("imu",hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a){
            imu.resetReference();
        }
        telemetry.addData("note","press a to reset reference");
        telemetry.addData("rawyaw",imu.getRawYaw());
        telemetry.addData("yaw",imu.getYaw());
        Vector2D speed = imu.getSpeedVector();
        Vector2D position = imu.getPositionVector();
        telemetry.addData("speed x",speed.x);
        telemetry.addData("speed y",speed.y);

        telemetry.addData("position y",position.y);
        telemetry.addData("position y",position.y);
    }
}

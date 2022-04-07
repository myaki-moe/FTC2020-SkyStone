package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcLib.Sensor.FtcDistenceSensor.FtcDistenceSensor;

@TeleOp(name = "距离传感器测试", group = "测试")
@Disabled
public class DistenceSensorTest extends OpMode {

    FtcDistenceSensor ftcDistenceSensor = null;

    @Override
    public void init() {
        ftcDistenceSensor = new FtcDistenceSensor("d1",hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("distence",ftcDistenceSensor.getDistence());
    }
}

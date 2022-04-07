package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.ColorSensor;

@TeleOp(name = "颜色",group = "测试")
@Disabled
public class ColorTest extends OpMode {
    private ColorSensor colorSensor;

    @Override
    public void init() {
        colorSensor = hardwareMap.get(ColorSensor.class,"c1");
    }

    @Override
    public void loop() {
        telemetry.addData("r",colorSensor.red());
        telemetry.addData("g",colorSensor.green());
        telemetry.addData("b",colorSensor.blue());
        telemetry.addData("a",colorSensor.alpha());
    }
}

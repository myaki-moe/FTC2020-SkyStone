package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.FtcLib.Sensor.FtcColorSensor.FtcColorSensor;
import org.firstinspires.ftc.teamcode.FtcLib.Sensor.FtcDistenceSensor.FtcDistenceSensor;

@TeleOp(name = "所有传感器测试",group = "测试")
@Disabled
public class AllSensorTest extends OpMode {

    FtcDistenceSensor lfRange,lbRangle,rbRangle;
    FtcColorSensor lColor,rColor,rfColor;

    @Override
    public void init() {
        lfRange = new FtcDistenceSensor("lf range",hardwareMap);
        lbRangle = new FtcDistenceSensor("lb range",hardwareMap);
        rbRangle = new FtcDistenceSensor("rb range",hardwareMap);
        lColor = new FtcColorSensor("l color",hardwareMap);
        rColor = new FtcColorSensor("r color",hardwareMap);
        rfColor = new FtcColorSensor("rf color",hardwareMap);
    }

    @Override
    public void loop() {
        telemetry.addData("lfRange",lfRange.distance);
        telemetry.addData("lbRangle",lbRangle.distance);
        telemetry.addData("rbRangle",rbRangle.distance);

        telemetry.addData("lColor r",lColor.getRed());
        telemetry.addData("lColor g",lColor.getGreen());
        telemetry.addData("lColor b",lColor.getBlue());

        telemetry.addData("rColor r",rColor.getRed());
        telemetry.addData("rColor g",rColor.getGreen());
        telemetry.addData("rColor b",rColor.getBlue());

        telemetry.addData("rfColor r",rfColor.getRed());
        telemetry.addData("rfColor g",rfColor.getGreen());
        telemetry.addData("rfColor b",rfColor.getBlue());
    }
}

package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcServo.FtcServo;

@TeleOp(name = "伺服测试", group = "测试")
@Disabled
public class ServoTest extends OpMode {
    FtcServo ftcServo = null;

    @Override
    public void init() {
        ftcServo = new FtcServo("pull1", hardwareMap);
    }

    @Override
    public void loop() {
        ftcServo.servo.setPosition(gamepad1.left_stick_y);
        telemetry.addData("pos",gamepad1.left_stick_y*180);

    }


}

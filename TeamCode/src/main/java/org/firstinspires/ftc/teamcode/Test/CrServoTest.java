package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcCrServo.FtcCrServo;

@TeleOp(name = "393测试", group = "测试")
@Disabled
public class CrServoTest extends OpMode {

    FtcCrServo ftcCrServo = null;

    @Override
    public void init() {
        ftcCrServo = new FtcCrServo("cr1",hardwareMap);
    }

    @Override
    public void loop() {
        ftcCrServo.setPower(gamepad1.left_stick_y);
    }
}

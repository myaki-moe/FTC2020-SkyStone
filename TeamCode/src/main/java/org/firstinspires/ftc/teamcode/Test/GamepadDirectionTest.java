package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.ConvertAxes2D.Vector2D;
import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.GamePadDirectionFix.DirectionFix;
import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.GamePadDirectionFix.DirectionFixMode;

@TeleOp(name = "八方向测试", group = "测试")
@Disabled
public class GamepadDirectionTest extends OpMode {
    DirectionFix directionFix = null;

    @Override
    public void init() {
        directionFix = new DirectionFix(DirectionFixMode.EIGHT_DIRECTIONS);
    }

    @Override
    public void loop() {
        Vector2D vector2D = directionFix.getDirection(gamepad1);
        telemetry.addData("x", vector2D.x);
        telemetry.addData("y", vector2D.y);
        telemetry.addData("a", directionFix.getRotationSpeed(gamepad1));

    }
}

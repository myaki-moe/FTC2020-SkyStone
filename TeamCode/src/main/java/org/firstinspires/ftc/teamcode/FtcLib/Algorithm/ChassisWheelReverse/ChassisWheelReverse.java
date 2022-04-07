package org.firstinspires.ftc.teamcode.FtcLib.Algorithm.ChassisWheelReverse;

import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.ConvertAxes2D.Vector2D;

public class ChassisWheelReverse {
    public static void getReverseVector(Vector2D direction, Vector2D speed) {
        if (speed.x > 100) direction.x = -speed.x * 1;
        if (speed.y > 100) direction.y = -speed.y * 1;
    }
}

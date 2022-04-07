package org.firstinspires.ftc.teamcode.FtcLib.Algorithm.ConvertAxes2D;

public class ConvertAxes2D {
    public static void cvtAxes(Vector2D vector2D, double angle) {
        double x = vector2D.x;
        double y = vector2D.y;
        vector2D.x = x * Math.cos(Math.toRadians(angle)) + y * Math.sin(Math.toRadians(angle));
        vector2D.y = y * Math.cos(Math.toRadians(angle)) - x * Math.sin(Math.toRadians(angle));
    }
}

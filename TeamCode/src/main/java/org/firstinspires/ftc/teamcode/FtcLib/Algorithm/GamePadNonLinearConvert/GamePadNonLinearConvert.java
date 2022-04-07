package org.firstinspires.ftc.teamcode.FtcLib.Algorithm.GamePadNonLinearConvert;

import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.ConvertAxes2D.Vector2D;

public class GamePadNonLinearConvert {
    public static void nonLinearCvt(Vector2D vector2D) {
        vector2D.x = cvt(vector2D.x);
        vector2D.y = cvt(vector2D.y);
    }
    //        if (input >= 1) return input;
//        if (input > 0 && input < 1) return 0.2;
//
//        if (input > -1 && input < 0) return -0.2;
//        if (input <= -1) return input;

    private static double cvt(double input) {
         if (input >= 1) return input;
         if (input > 0.5 && input < 1) return input / 1.5;
         if (input > 0 && input < 0.5) return 0.2;

         if (input > -0.5 && input < 0) return -0.2;
         if (input < -0.5 && input > -1) return input / 1.5;
         if (input <= 1) return input;
        return 0;
    }
}

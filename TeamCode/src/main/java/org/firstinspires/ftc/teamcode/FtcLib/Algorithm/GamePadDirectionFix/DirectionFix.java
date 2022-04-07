package org.firstinspires.ftc.teamcode.FtcLib.Algorithm.GamePadDirectionFix;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.ConvertAxes2D.Vector2D;


public class DirectionFix {

    private DirectionFixMode directionFixMode;

    public DirectionFix(DirectionFixMode directionFixMode) {
        this.directionFixMode = directionFixMode;
    }

    public void setMode(DirectionFixMode directionFixMode) {
        this.directionFixMode = directionFixMode;
    }

    public float getRotationSpeed(Gamepad gamepad) {
        return gamepad.left_trigger - gamepad.right_trigger;
    }

    public Vector2D getDirection(Gamepad gamepad) {

        Vector2D vector2D = new Vector2D(0, 0);

        if (gamepad.atRest()) {
            return vector2D;
        }

        float x = gamepad.left_stick_x;
        float y = -gamepad.left_stick_y;//手柄摇杆上下是反的

        if (directionFixMode == DirectionFixMode.RAW) {

            vector2D.x = x;
            vector2D.y = y;

        } else if (directionFixMode == DirectionFixMode.FOUR_DIRECTIONS) {

            double power = Math.sqrt(Math.abs(x) * Math.abs(x) + Math.abs(y) * Math.abs(y));
            double angle = calc_angle(x, y);
            vector2D = calc_four_directions(angle, power);

        } else if (directionFixMode == DirectionFixMode.EIGHT_DIRECTIONS) {

            double power = Math.sqrt(Math.abs(x) * Math.abs(x) + Math.abs(y) * Math.abs(y));
            double angle = calc_angle(x, y);
            vector2D = calc_eight_directions(angle, power);

        }
        return vector2D;
    }

    private double calc_angle(float x, float y) {
        double degree = 0;

        if (x == 0 || y == 0) {
            if (x > 0) degree = 0;
            else if (x < 0) degree = 180;
            else if (x == 0 && y < 0) degree = 270;
            else if (x == 0 && y > 0) degree = 90;
            return degree;
        }

        degree = Math.atan(y / x);    //求反三角函数正切
        degree = Math.toDegrees(degree);
        if (x > 0 && y > 0) degree = 0 + degree;
        else if (x < 0 && y > 0) degree = 180 + degree;
        else if (x < 0 && y < 0) degree = 180 + degree;
        else if (x > 0 && y < 0) degree = 360 + degree;

        return degree;
    }

    private Vector2D calc_eight_directions(double degree, double power) {

        Vector2D vector2D = new Vector2D();

        if ((degree >= 0 && degree <= 30) || degree >= 330) {
            vector2D.x = power;
            vector2D.y = 0;
            //右
        } else if (degree > 30 && degree < 60) {
            vector2D.x = power;
            vector2D.y = power;
            //右前
        } else if (degree >= 60 && degree <= 120) {
            vector2D.x = 0;
            vector2D.y = power;
            //前
        } else if (degree > 120 && degree < 150) {
            vector2D.x = -power;
            vector2D.y = power;
            //左前
        } else if (degree >= 150 && degree <= 210) {
            vector2D.x = -power;
            vector2D.y = 0;
            //左
        } else if (degree > 210 && degree < 240) {
            vector2D.x = -power;
            vector2D.y = -power;
            //左后
        } else if (degree >= 240 && degree <= 300) {
            vector2D.x = 0;
            vector2D.y = -power;
            //后
        } else if (degree > 300 && degree < 330) {
            vector2D.x = power;
            vector2D.y = -power;
            //右后
        }
        return vector2D;
    }

    private Vector2D calc_four_directions(double degree, double power) {

        Vector2D vector2D = new Vector2D();

        if ((degree >= 0 && degree < 45) || degree > 315) {
            vector2D.x = power;
            vector2D.y = 0;
            //右
        } else if (degree >= 45 && degree <= 135) {
            vector2D.x = 0;
            vector2D.y = power;
            //前
        } else if (degree > 135 && degree < 225) {
            vector2D.x = -power;
            vector2D.y = 0;
            //左
        } else if (degree >= 225 && degree <= 315) {
            vector2D.x = 0;
            vector2D.y = -power;
            //后
        }
        return vector2D;
    }
}

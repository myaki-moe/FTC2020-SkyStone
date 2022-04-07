package org.firstinspires.ftc.teamcode.FtcLib.Sensor.FtcIMU;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.ConvertAxes2D.Vector2D;

public class FtcIMU {
    public BNO055IMU imu;
    public float reference = 0;

    public FtcIMU(String name, HardwareMap hardwareMap) {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.calibrationDataFile = "AdafruitIMUCalibration.json";
        parameters.loggingEnabled = false;
        parameters.mode = BNO055IMU.SensorMode.IMU;
        parameters.useExternalCrystal = true;

        imu = hardwareMap.get(BNO055IMU.class, name);
        imu.initialize(parameters);
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1);
    }

    public void resetReference() {
        reference = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }

    public void reset() {
        imu.stopAccelerationIntegration();
        imu.startAccelerationIntegration(new Position(), new Velocity(), 1);
    }

    public float getYaw() {
        float rotation = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle - reference;
        ;
        if (Math.abs(rotation) > 180) {
            if (reference >= 0) rotation += 360;
            if (reference < 0) rotation -= 360;
        }
        return rotation;
    }

    public float getRawYaw() {
        return imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES).firstAngle;
    }

    public Vector2D getSpeedVector() {
        Velocity velocity = imu.getVelocity();
        return new Vector2D(velocity.xVeloc, velocity.yVeloc);
    }

    public Vector2D getPositionVector() {
        Position position = imu.getPosition();
        return new Vector2D(position.x, position.y);
    }
}

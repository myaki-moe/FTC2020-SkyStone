package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.ConvertAxes2D.Vector2D;
import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.GamePadDirectionFix.DirectionFixMode;
import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.GamePadNonLinearConvert.GamePadNonLinearConvert;
import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.Test.Test;
import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor.FtcMotor;
import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor.FtcPIDFCoefficientsTable;
import org.firstinspires.ftc.teamcode.FtcLib.Algorithm.GamePadDirectionFix.DirectionFix;
import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcServo.FtcServo;
import org.firstinspires.ftc.teamcode.FtcLib.Sensor.FtcIMU.FtcIMU;
import org.firstinspires.ftc.teamcode.FtcLib.SubSystem.Elevator.Elevator;
import org.firstinspires.ftc.teamcode.FtcLib.SubSystem.InTake.InTake;
import org.firstinspires.ftc.teamcode.FtcLib.SubSystem.OmniWheel.OmniWheel;
import org.firstinspires.ftc.teamcode.FtcLib.SubSystem.Rotate.Rotate;


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "自动", group = "测试")

public class FTCAutonomous extends OpMode {

    Test xt, yt;

    private FtcMotor lf, lb, rf, rb, lc, rc, mlift, armSpin;
    private FtcServo hand, arm, pull1, pull2, symbol, carry1, carry2;
    private FtcIMU imu;

    private OmniWheel omniWheel;
    private InTake inTake;
    private Rotate rotate;

    private DirectionFix directionFix;

    @Override
    public void init() {

        xt = new Test(0.5, 0.5);
        yt = new Test(0.3, 0.6);

        lf = new FtcMotor("lf", hardwareMap, FtcPIDFCoefficientsTable.CHASSIS_VELOCITY);
        lb = new FtcMotor("lb", hardwareMap, FtcPIDFCoefficientsTable.CHASSIS_VELOCITY);
        rf = new FtcMotor("rf", hardwareMap, FtcPIDFCoefficientsTable.CHASSIS_VELOCITY);
        rb = new FtcMotor("rb", hardwareMap, FtcPIDFCoefficientsTable.CHASSIS_VELOCITY);
        lf.setBrake(true);
        lb.setBrake(true);
        rf.setBrake(true);
        rb.setBrake(true);

        lc = new FtcMotor("lc", hardwareMap, FtcPIDFCoefficientsTable.INTAKE);
        rc = new FtcMotor("rc", hardwareMap, FtcPIDFCoefficientsTable.INTAKE);
        rc.setReversed(true);

        mlift = new FtcMotor("lift", hardwareMap, FtcPIDFCoefficientsTable.LIFT);
        mlift.setBrake(true);

        armSpin = new FtcMotor("arm", hardwareMap, FtcPIDFCoefficientsTable.ARM);

////////////////////////////////////////////////////////////////////////////////////////////////////
        hand = new FtcServo("hand", hardwareMap);
        arm = new FtcServo("rotate", hardwareMap);

        pull1 = new FtcServo("pull1", hardwareMap);
        pull2 = new FtcServo("pull2", hardwareMap);
        pull2.setReversed(true);

        symbol = new FtcServo("symbol", hardwareMap);
        symbol.setAngle(100);

        carry1 = new FtcServo("carry1", hardwareMap);
        carry2 = new FtcServo("carry2", hardwareMap);
        carry2.setReversed(true);
//        imu = new FtcIMU("imu1", hardwareMap);

        omniWheel = new OmniWheel(3000);
        directionFix = new DirectionFix(DirectionFixMode.FOUR_DIRECTIONS);
        inTake = new InTake(lc, rc, 5000);
        rotate = new Rotate(armSpin, arm);
        rotate.rstMotorAngle();
    }


    public void start() {
        hand.setAngle(60);
        symbol.setAngle(100);
        pull1.setAngle(180);
        pull2.setAngle(90);
        carry1.setAngle(30);
        carry2.setAngle(30);
    }


    @Override
    public void loop() {
        chassis_task();
        lift_task();
        inTake_task();
        machineArm_task();

    }


    private void chassis_task() {

        Vector2D direction = directionFix.getDirection(gamepad1);

        if (gamepad1.dpad_up) direction.y = 1;
        if (gamepad1.dpad_down) direction.y = -1;
        if (gamepad1.dpad_left) direction.x = -1;
        if (gamepad1.dpad_right) direction.x = 1;

        xt.set = direction.x;
        yt.set = direction.y;

        double aSpeed = directionFix.getRotationSpeed(gamepad1);

        if (direction.x != 0 || direction.y != 0) {
            aSpeed = aSpeed * 800 * (xt.result + yt.result) / 2;
        } else {
            aSpeed = aSpeed * 300;
        }

        omniWheel.setSpeed(xt.result * 500, yt.result * 500, aSpeed);

        lf.setSpeed(-omniWheel.lfSpeed);
        lb.setSpeed(-omniWheel.lbSpeed);
        rf.setSpeed(-omniWheel.rfSpeed);
        rb.setSpeed(-omniWheel.rbSpeed);
    }


    private void lift_task() {
    }


    private void inTake_task() {
    }


    private void machineArm_task() {
        if (gamepad1.y) {
            carry1.setAngle(30);
            carry2.setAngle(30);
        }

        if (gamepad1.a) {
            carry1.setAngle(180);
            carry2.setAngle(180);
        }
        if (gamepad1.x) {
            pull2.setAngle(0);
        }
        if (gamepad1.b) {
            pull2.setAngle(90);
        }
        if (gamepad1.left_bumper) {
            pull1.setAngle(0);
        }

        if (gamepad1.right_bumper) {
            pull1.setAngle(180);
        }
    }

    public void stop() {
        xt.flag = false;
        yt.flag = false;
    }

}

package org.firstinspires.ftc.teamcode.TeleOp;

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


@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "手动", group = "测试")

public class FTCTeleOp extends OpMode {

    Test xt, yt;

    private FtcMotor lf, lb, rf, rb, lc, rc, mlift, armSpin;
    private FtcServo hand, arm, pull1, pull2, symbol, carry1, carry2;
    private FtcIMU imu;

    private OmniWheel omniWheel;
    private Elevator elevator;
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
        pull1.setAngle(100);
        pull2.setAngle(0);
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
        GamePadNonLinearConvert.nonLinearCvt(direction);

        xt.set = direction.x;
        yt.set = direction.y;

        double aSpeed = directionFix.getRotationSpeed(gamepad1);

        if (direction.x != 0 || direction.y != 0) {
            aSpeed = aSpeed * 1000 * (xt.result + yt.result) / 2;
        } else {
            aSpeed = aSpeed * 500;
        }

        if (gamepad1.dpad_up || gamepad1.dpad_down || gamepad1.dpad_left || gamepad1.dpad_right) {
            if (gamepad1.dpad_up) omniWheel.setSpeed(0, 300, 0);
            if (gamepad1.dpad_down) omniWheel.setSpeed(0, -300, 0);
            if (gamepad1.dpad_left) omniWheel.setSpeed(-300, 0, 0);
            if (gamepad1.dpad_right) omniWheel.setSpeed(300, 0, 0);
        } else {
            omniWheel.setSpeed(xt.result * 1200, yt.result * 1200, aSpeed);
        }

        lf.setSpeed(-omniWheel.lfSpeed);
        lb.setSpeed(-omniWheel.lbSpeed);
        rf.setSpeed(-omniWheel.rfSpeed);
        rb.setSpeed(-omniWheel.rbSpeed);
    }


    private void lift_task() {
        mlift.setSpeed(gamepad2.right_stick_y * 800);
    }


    private void inTake_task() {
        if (gamepad1.y) {
            inTake.start(500, 1000);
        } else if (gamepad1.a) {
            inTake.start(-500, -500);
        } else {
            inTake.stop();
        }
    }


    private void machineArm_task() {
        if (gamepad2.left_bumper) {
            hand.setAngle(0);
        }
        if (gamepad2.right_bumper) {
            hand.setAngle(60);
        }

        if (gamepad2.y) {
            carry1.setAngle(30);
            carry2.setAngle(30);
        }

        if (gamepad2.a) {
            carry1.setAngle(180);
            carry2.setAngle(180);
        }
        if (gamepad2.x) {
            symbol.setAngle(100);
        }

        if (gamepad2.b) {
            symbol.setAngle(130);
        }

        if (gamepad1.left_bumper) {
            rotate.setServoAngle(180);
            rotate.setMotorAngle(160, 1000);

        } else if (gamepad1.right_bumper) {
            rotate.setServoAngle(180);
            rotate.setMotorAngle(0, 1000);
        } else if (gamepad1.x) {
            if (rotate.getMotorAngle() < 45) {
                rotate.setMotorAngle(180, 1000);
                rotate.setServoAngle(180);
            } else {
                rotate.setMotorAngle(180, 1000);
                rotate.setServoAngle(0);
            }
        } else {
            if (rotate.getMotorAngle() > 160) {
                rotate.setServoAngle(0);
            } else {
                rotate.setServoAngle(180);
            }
            rotate.setMotorAngle(0, 500);
        }
    }

    public void stop() {
        xt.flag = false;
        yt.flag = false;
    }

}

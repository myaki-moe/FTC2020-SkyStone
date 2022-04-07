package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor.FtcMotor;
import org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor.FtcPIDFCoefficientsTable;
import org.firstinspires.ftc.teamcode.FtcLib.SubSystem.InTake.InTake;

@TeleOp(name = "吸纳测试",group = "测试")
@Disabled

public class InTakeTest extends OpMode {
    FtcMotor left, right;
    InTake inTake;

    @Override
    public void init() {
        left = new FtcMotor("m1", hardwareMap, FtcPIDFCoefficientsTable.CHASSIS_VELOCITY);
        right = new FtcMotor("m2", hardwareMap, FtcPIDFCoefficientsTable.CHASSIS_VELOCITY);
        inTake = new InTake(left, right, 500);
    }

    int cnt = 0;

    @Override
    public void loop() {
        if (cnt == 0) {
            inTake.start();
            cnt = 1;
        } else if (cnt == 1) {
            inTake.stop();
            cnt = 2;
        } else if (cnt == 2) {
            inTake.start(100);
            cnt = 3;
        } else if (cnt == 3) {
            inTake.stop();
            cnt = 0;
        }

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

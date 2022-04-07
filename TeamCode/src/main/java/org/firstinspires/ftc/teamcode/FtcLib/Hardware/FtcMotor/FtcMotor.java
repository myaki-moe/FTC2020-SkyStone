package org.firstinspires.ftc.teamcode.FtcLib.Hardware.FtcMotor;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;


public class FtcMotor {

    public DcMotorEx motor;
    public FtcPIDFCoefficients.PIDFMode pidfMode = null;

    public FtcMotor(String name, HardwareMap hardwareMap) {
        motor =(DcMotorEx) hardwareMap.get(DcMotor.class, name);
    }

    public FtcMotor(String name, HardwareMap hardwareMap,FtcPIDFCoefficients ftcPIDFCoefficients) {
        motor = (DcMotorEx)hardwareMap.get(DcMotor.class, name);
        setMotor(ftcPIDFCoefficients);
    }

    public void setMotor(FtcPIDFCoefficients ftcPIDFCoefficients) {
        this.pidfMode = ftcPIDFCoefficients.pidfMode;

        if (ftcPIDFCoefficients.pidfMode == FtcPIDFCoefficients.PIDFMode.POSITION) {
            motor.setPositionPIDFCoefficients(ftcPIDFCoefficients.position_p);
            motor.setVelocityPIDFCoefficients(ftcPIDFCoefficients.speed_p, ftcPIDFCoefficients.speed_i,
                    ftcPIDFCoefficients.speed_d, ftcPIDFCoefficients.speed_f);
        }
        if (ftcPIDFCoefficients.pidfMode == FtcPIDFCoefficients.PIDFMode.VELOCITY) {
            motor.setVelocityPIDFCoefficients(ftcPIDFCoefficients.speed_p, ftcPIDFCoefficients.speed_i,
                    ftcPIDFCoefficients.speed_d, ftcPIDFCoefficients.speed_f);
            motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        }
        if (ftcPIDFCoefficients.pidfMode == FtcPIDFCoefficients.PIDFMode.OPENLOOP) {
            motor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        }
    }

    public void setReversed(boolean reversed) {
        if (reversed) motor.setDirection(DcMotorEx.Direction.REVERSE);
        else motor.setDirection(DcMotorEx.Direction.FORWARD);
    }

    public void setBrake(boolean brake) {
        if (brake) motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        else motor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.FLOAT);
    }

    public void setDisable(boolean disable){
        if (disable) motor.setMotorDisable();
        else motor.setMotorEnable();
    }

    public void resetEncoder() {
        DcMotorEx.RunMode runMode = motor.getMode();
        motor.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(runMode);
    }

    public int getEncoder() {
        return motor.getCurrentPosition();
    }

    public void setSpeed(double degreePerSec) {
        motor.setVelocity(degreePerSec, AngleUnit.DEGREES);
    }

    public double getSpeed(){
        return motor.getVelocity(AngleUnit.DEGREES);
    }

    public void setPower(double power){
        motor.setPower(power);
    }
    public double getPower(){
        return motor.getPower();
    }

    public void setRotateTo(double degreePerSec, int target) {
        motor.setTargetPosition(target);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
        motor.setVelocity(degreePerSec, AngleUnit.DEGREES);
    }

    public void setRotateFor(double degreePerSec, int target) {
        motor.setVelocity(degreePerSec, AngleUnit.DEGREES);
        motor.setTargetPosition(motor.getCurrentPosition() + target);
        motor.setMode(DcMotorEx.RunMode.RUN_TO_POSITION);
    }

    public void stop(){
        if (pidfMode == FtcPIDFCoefficients.PIDFMode.OPENLOOP){
            motor.setPower(0);
        }
        else{
            motor.setVelocity(0,AngleUnit.DEGREES);
            motor.setPower(0);
        }
    }

    public boolean isRunning(){
        return motor.isBusy();
    }

}

package org.firstinspires.ftc.teamcode.FtcLib.SubSystem.OmniWheel;


public class OmniWheel {
    public double xSpeedSet, ySpeedSet, aSpeedSet;
    public double lfSpeed,lbSpeed,rfSpeed,rbSpeed;
    public double maxSpeed;

    public int lfEncoder,lbEncoder,rfEncoder,rbEncoder;

    public OmniWheel(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public void setXspeed(double xSpeed) {
        this.xSpeedSet = xSpeed;
        calcSpeed();
    }

    public void setYspeed(double ySpeed) {
        this.ySpeedSet = ySpeed;
        calcSpeed();
    }

    public void setAspeed(double aSpeed) {
        this.aSpeedSet = aSpeed;
        calcSpeed();
    }

    public void setSpeed(double xSpeed, double ySpeed, double aSpeed) {
        this.xSpeedSet = xSpeed;
        this.ySpeedSet = ySpeed;
        this.aSpeedSet = aSpeed;
        calcSpeed();
    }

    public void calcSpeed() {
        lfSpeed = xSpeedSet + ySpeedSet - aSpeedSet;
        lbSpeed = -xSpeedSet + ySpeedSet - aSpeedSet;
        rfSpeed = xSpeedSet - ySpeedSet - aSpeedSet;
        rbSpeed = -xSpeedSet - ySpeedSet - aSpeedSet;

        double max = Math.abs(lfSpeed);
        if (max < Math.abs(lbSpeed)) max = Math.abs(lbSpeed);
        if (max < Math.abs(rfSpeed)) max = Math.abs(rfSpeed);
        if (max < Math.abs(rbSpeed)) max = Math.abs(rbSpeed);
        //如果最大速度大于限定速度就按比例缩小所有车轮速度
        if (max > maxSpeed) {
            lfSpeed = (lfSpeed/max)*maxSpeed;
            lbSpeed = (lbSpeed/max)*maxSpeed;
            rfSpeed = (rfSpeed/max)*maxSpeed;
            rbSpeed = (rbSpeed/max)*maxSpeed;
        }
    }

    public void setEncoder(int xEncoder, int yEncoder, int aEncoder) {
        lfEncoder = xEncoder + yEncoder - aEncoder;
        lbEncoder = -xEncoder + yEncoder - aEncoder;
        rfEncoder = xEncoder - yEncoder - aEncoder;
        rbEncoder = -xEncoder - yEncoder - aEncoder;
    }
}

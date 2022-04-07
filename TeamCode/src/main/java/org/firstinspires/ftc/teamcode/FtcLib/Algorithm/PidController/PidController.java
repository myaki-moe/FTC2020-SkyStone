package org.firstinspires.ftc.teamcode.FtcLib.Algorithm.PidController;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PidController {

    private double Kp, Ki, Kd;
    private double err_sum;
    private ElapsedTime elapsedTime;
    private double lastTime;
    private double pMax, iMax, dMax;

    public PidController(double Kp, double Ki, double Kd) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;

        pMax = Double.MAX_VALUE;
        iMax = Double.MAX_VALUE;
        dMax = Double.MAX_VALUE;

        elapsedTime = new ElapsedTime();
        lastTime = 0;
    }

    public PidController(double Kp, double Ki, double Kd, double pMAX, double iMax, double dMax) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;

        this.pMax = pMAX;
        this.iMax = iMax;
        this.dMax = dMax;

        elapsedTime = new ElapsedTime();
        lastTime = 0;
    }

    public double pid_calc(double set, double actual) {
        double err = set - actual;
        err_sum += err;
        double p = err * Kp;
        double i = err_sum * Ki;
        double d = err / (elapsedTime.milliseconds() - lastTime) * Kd;
        if (p > pMax) p = pMax;
        if (i > iMax) i = iMax;
        if (d > dMax) d = dMax;
        lastTime = elapsedTime.milliseconds();
        return p + i - d;
    }

    public void reset() {
        err_sum = 0;
        lastTime = 0;
        elapsedTime.reset();
    }
}

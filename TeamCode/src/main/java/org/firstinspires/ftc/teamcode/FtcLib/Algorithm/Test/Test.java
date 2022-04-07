package org.firstinspires.ftc.teamcode.FtcLib.Algorithm.Test;

public class Test extends Thread {

    public boolean flag = true;
    public volatile double set = 0;
    public volatile double result = 0;

    private double t1,t2;

    public Test(double t1, double t2) {
        this.t1 = t1;
        this.t2 = t2;
        this.start();
    }

    public void run() {
        while (flag) {
            double err = set - result;
            if (err >= 0) {
                result += err * t1;
            } else {
                result += err * t2;
            }

            try {
                Thread.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}

package org.firstinspires.ftc.teamcode.Test;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.openftc.revextensions2.ExpansionHubEx;

@TeleOp(name = "主控测试", group = "测试")
@Disabled
public class RevHubExTest extends OpMode {

    ExpansionHubEx expansionHubEx = null;

    @Override
    public void init() {
        expansionHubEx = hardwareMap.get(ExpansionHubEx.class,"Expansion Hub 1");
        expansionHubEx.setLedColor(255,255,255);
        expansionHubEx.setPhoneChargeEnabled(true);
    }

    @Override
    public void loop() {

    }
}

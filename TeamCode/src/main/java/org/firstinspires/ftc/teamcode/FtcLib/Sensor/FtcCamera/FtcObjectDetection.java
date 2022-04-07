package org.firstinspires.ftc.teamcode.FtcLib.Sensor.FtcCamera;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class FtcObjectDetection {

    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";

    private static final String VUFORIA_KEY = "AR0/zTT/////AAAAmdIL8I+g30YLo3EYuz3yAVA07nKyaJEVgMhWll6Na0ZrAnVTbnCofSVje6SKFXqgLYoReTavM8NfIDaY9qhKBLrqLsgJRkQBEAhigOVa02Tm8L46Z1ARtAVwJCuMsT22966JlshpbxVXra2Vns+juHYRAEh7uI0jguxlmn1NbgEGw4HJJndCLntwnUMGHDuqS2r4PsBUBL+dVrhRfuOXfzG8A77/68LRSXiImK7UEAi2HR5FLTj/QyzPaV1bYbxIqLJcUEuqkZ92jFMGyL1HbxW2SaEjjZsoqd2hTmMum3oO5ZiLD9oX7T11aeCCzLBieV4eKFUnydvgnAIrTZYmLkDfZBHLa1Y1S7P2y8ZS1iCV";


    private TFObjectDetector tfod;

    public FtcObjectDetection(HardwareMap hardwareMap) {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;
        VuforiaLocalizer vuforia = ClassFactory.getInstance().createVuforia(parameters);

        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

    public void active() {
        tfod.activate();
    }

    public void shutdown() {
        tfod.shutdown();
    }

    public List<Recognition> getAllRecognitions() {
        return tfod.getRecognitions();
    }

    public Recognition getNearest() {
        List<Recognition> Recognitions = tfod.getRecognitions();
        int index = 0;
        float maxHeight = 0;

        for (int i = 0; i < Recognitions.size(); i++) {
            float height = Recognitions.get(i).getHeight();
            if (height > maxHeight){
                maxHeight = height;
                index = i;
            }
        }
        return Recognitions.get(index);
    }

    public float getNearestXpos() {
        List<Recognition> Recognitions = tfod.getRecognitions();
        int index = 0;
        float maxHeight = 0;

        for (int i = 0; i < Recognitions.size(); i++) {
            float height = Recognitions.get(i).getHeight();
            if (height > maxHeight){
                maxHeight = height;
                index = i;
            }
        }
        return Recognitions.get(index).getLeft();
    }
}

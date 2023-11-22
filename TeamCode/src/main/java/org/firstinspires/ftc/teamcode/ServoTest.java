package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "_121servoattempt (Blocks to Java)")
public class ServoTest extends LinearOpMode {

    private Servo leftgrabber;

    /**
     * This function is executed when this Op Mode is selected from the Driver Station.
     */
    @Override
    public void runOpMode() {
        double ServoPosition;
        double ServoSpeed;

        leftgrabber = hardwareMap.get(Servo.class, "left grabber");

        // Set servo to mid position
        ServoPosition = 0.5;
        ServoSpeed = 0.01;
        waitForStart();
        while (opModeIsActive()) {
            // Use gamepad X and B to open close servo
            if (gamepad1.b) {
                ServoPosition += ServoSpeed;
            }
            if (gamepad1.x) {
                ServoPosition -= ServoSpeed;
            }
            // Keep Servo position in valid range
            ServoPosition = Math.min(Math.max(ServoPosition, 0), 1);
            leftgrabber.setPosition(ServoPosition);
            telemetry.addData("Servo", ServoPosition);
            telemetry.update();
            sleep(20);
        }
    }
}
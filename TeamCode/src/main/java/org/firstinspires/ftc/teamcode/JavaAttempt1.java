package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.ServoController;

public class JavaAttempt1 {

    @TeleOp(name = "_1116servoattempt2 (Blocks to Java)")
    public class _1116servoattempt2 extends LinearOpMode {

        Servo rightgrabber;
        DcMotor arm;
        DcMotor backRight;
        ServoController ControlHub_ServoController;
        DcMotor frontLeft;
        Servo leftgrabber;
        DcMotor backLeft;
        DcMotor elbow;
        DcMotor frontRight;
        /**
         * This function is executed when this Op Mode is selected from the Driver Station.
         */
        @Override
        public void runOpMode() {
            int desiredPosition;
            int startingPosition;
            double ServoSpeedRight;
            double ServoSpeedLeft;
            double ServoPositionLeft;
            double ServoPostitionRight;
            int left_arm;
            float x1;
            float y1;
            float y2;
            float x2;

            rightgrabber = hardwareMap.get(Servo.class, "right grabber");
            arm = hardwareMap.get(DcMotor.class, "arm");
            backRight = hardwareMap.get(DcMotor.class, "backRight");
            ControlHub_ServoController = hardwareMap.get(ServoController.class, "Control Hub");
            frontLeft = hardwareMap.get(DcMotor.class, "frontLeft");
            leftgrabber = hardwareMap.get(Servo.class, "left grabber");
            backLeft = hardwareMap.get(DcMotor.class, "backLeft");
            frontRight = hardwareMap.get(DcMotor.class, "frontRight");
            elbow = hardwareMap.get(DcMotor.class,"elbow" );
            // Put initialization blocks here.
            desiredPosition = 2500;
            startingPosition = 0;
            rightgrabber.setDirection(Servo.Direction.REVERSE);
            ServoSpeedRight = 0.01;
            ServoSpeedLeft = 0.01;
            ServoPositionLeft = 0;
            ServoPostitionRight = 0;
            arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
            ControlHub_ServoController.pwmEnable();
            ServoPositionLeft = Math.min(Math.max(ServoPositionLeft, 0), 1);  // would this be coded 1,0
            ServoPostitionRight = Math.min(Math.max(ServoPostitionRight, 0), 1);  // would this be coded 1,0
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            arm.setTargetPosition(startingPosition);
            arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            left_arm = 0;
            waitForStart();
            if (opModeIsActive()) {
                // Put run blocks here.
                while (opModeIsActive()) {
                    if (gamepad1.x) {
                        ServoPositionLeft += ServoSpeedLeft;
                        ServoPostitionRight += ServoSpeedRight;
                    }
                    if (gamepad1.y) {
                        ServoPositionLeft -= ServoSpeedLeft;
                        ServoPostitionRight -= ServoSpeedRight;
                    }
                    leftgrabber.setPosition(ServoPositionLeft);
                    rightgrabber.setPosition(ServoPostitionRight);
                    telemetry.addData("Servo Position", leftgrabber.getPosition());
                    if (gamepad1.a) {
                        // Each tick= 0.019 inch
                        arm.setTargetPosition(desiredPosition);
                        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        arm.setPower(0.6);
                    }
                    if (gamepad1.b) {
                        arm.setTargetPosition(startingPosition);
                        arm.setMode(DcMotor.RunMode.RUN_TO_POSITION);
                        arm.setPower(0.25);
                    }
                    if (gamepad2.left_stick_y>0) {
                        elbow.setPower(0.4);
                    }
                    if (gamepad2.left_stick_y<0) {
                        elbow.setPower(-0.4);
                    }
                    telemetry.addData("Encoder Position", arm.getCurrentPosition());
                    telemetry.addData("Desired Position", arm.getTargetPosition());
                    x1 = gamepad1.left_stick_x;
                    y1 = -gamepad1.left_stick_y;
                    y2 = -gamepad1.right_stick_y;
                    x2 = gamepad1.right_stick_x;
                    // Put loop blocks here.
                    telemetry.update();
                    if (gamepad1.left_bumper) {
                        backLeft.setPower(0.25 * (y1 + x1));
                        frontLeft.setPower(0.25 * (y1 + x1));
                        backRight.setPower(0.25 * (y1 - x1));
                        frontRight.setPower(0.25 * (y1 - x1));
                        backLeft.setPower(0.25 * (y2 + x2));
                        backRight.setPower(0.25 * (y2 + x2));
                        frontLeft.setPower(0.25 * (y2 - x2));
                        frontRight.setPower(0.25 * (y2 - x2));
                    } else if (gamepad1.right_bumper) {
                        backLeft.setPower(0.5 * (y1 + x1));
                        frontLeft.setPower(0.5 * (y1 + x1));
                        backRight.setPower(0.5 * (y1 - x1));
                        frontRight.setPower(0.5 * (y1 - x1));
                        backLeft.setPower(0.5 * (y2 + x2));
                        backRight.setPower(0.5 * (y2 + x2));
                        frontLeft.setPower(0.5 * (y2 - x2));
                        frontRight.setPower(0.5 * (y2 - x2));
                    } else {
                        backLeft.setPower(1 * (y1 + x1));
                        frontLeft.setPower(1 * (y1 + x1));
                        backRight.setPower(1 * (y1 - x1));
                        frontRight.setPower(1 * (y1 - x1));
                        backLeft.setPower(1 * (y2 + x2));
                        backRight.setPower(1 * (y2 + x2));
                        frontLeft.setPower(1 * (y2 - x2));
                        frontRight.setPower(1 * (y2 - x2));
                    }
                }
            }
        }
    }
}
//plastic dinosoars
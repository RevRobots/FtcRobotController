package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name="Potatoes")
public class ArmstrongDrive extends OpMode {
    DcMotor leftFront, rightFront, leftBack, rightBack;
    DcMotor arm;
    DcMotor turret;
    DcMotor lift;
    Servo rightFoundation, leftFoundation;
    CRServo leftClaw, rightClaw;
    double driveSpeedLimiter = 2;

    @Override
    public void init() {
        leftFront = hardwareMap.dcMotor.get("leftFront");
        rightFront = hardwareMap.dcMotor.get("rightFront");
        leftBack = hardwareMap.dcMotor.get("leftBack");
        rightBack = hardwareMap.dcMotor.get("rightBack");
        arm = hardwareMap.dcMotor.get("arm");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightFoundation = hardwareMap.servo.get("rightFoundation");
        leftFoundation = hardwareMap.servo.get("leftFoundation");
        leftClaw = hardwareMap.crservo.get("leftClaw");
        rightClaw = hardwareMap.crservo.get("rightClaw");
        turret = hardwareMap.dcMotor.get("turret");
        lift = hardwareMap.dcMotor.get("lift");
        leftFront.setPower(0);
        rightFront.setPower(0);
        leftBack.setPower(0);
        rightFront.setPower(0);
        arm.setPower(0);
        rightFoundation.setPosition(.5);
        leftFoundation.setPosition(.5);
        leftClaw.setPower(0);
        rightClaw.setPower(0);
        turret.setPower(0);
        lift.setPower(0);
    }

    @Override
    public void loop() {

        leftFront.setPower((gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x) / driveSpeedLimiter);
        rightFront.setPower((-gamepad1.left_stick_y - gamepad1.right_stick_x - gamepad1.left_stick_x) / driveSpeedLimiter);
        leftBack.setPower((gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x) / driveSpeedLimiter);
        rightBack.setPower((-gamepad1.left_stick_y - gamepad1.right_stick_x + gamepad1.left_stick_x) / driveSpeedLimiter);
        if (gamepad2.right_trigger > 0) {
            arm.setPower((-gamepad2.right_trigger) / 2);
        } else if (gamepad2.left_trigger > 0) {
            arm.setPower((gamepad2.left_trigger) / 2);
        } else {
            arm.setPower(.1);
        }
        if (gamepad1.right_bumper) {
            rightFoundation.setPosition(1);
            leftFoundation.setPosition(0);
        } else if (gamepad1.left_bumper) {
            rightFoundation.setPosition(0.5);
            leftFoundation.setPosition(0.5);
        }
        if (gamepad2.right_bumper) {
            leftClaw.setPower(1);
            rightClaw.setPower(-1);
        } else if (gamepad2.left_bumper) {
            leftClaw.setPower(-1);
            rightClaw.setPower(1);
        } else {
            leftClaw.setPower(0);
            rightClaw.setPower(0);
        }
        turret.setPower((-gamepad2.right_stick_x) / 2);
        lift.setPower(gamepad2.right_stick_y);
        if (gamepad1.y) {
            driveSpeedLimiter = 1;
        }
        if (gamepad1.b) {
            driveSpeedLimiter = 4;
        }
        if (gamepad1.a) {
            driveSpeedLimiter = 2;
        }
        if (gamepad1.x) {
            driveSpeedLimiter = 1.333;

        }
    }
}


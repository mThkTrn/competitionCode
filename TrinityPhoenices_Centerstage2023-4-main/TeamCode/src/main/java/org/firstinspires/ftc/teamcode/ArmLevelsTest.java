package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.TrinRobot;

import java.util.EnumMap;



@TeleOp(name = "ArmLevelsTest", group = "TeleOP")
public class ArmLevelsTest extends LinearOpMode {

    TrinRobot robot = new TrinRobot(this);
    int start_base;
    int start_elbow;
//    Gamepad gamepad2 = gamepad1; // uncomment for IAN MODE

    private void mecanumDrive(){
        double x = gamepad1.left_stick_x;
        double y = -gamepad1.left_stick_y;
        double r = gamepad1.right_stick_x;
        robot.drivePower(x, y, r);


    }
    private void moveArm(){
        robot.armPower(gamepad2.right_stick_y, gamepad2.left_stick_y);
    }
    private void armToPos(){
        if(gamepad2.dpad_up){
            telemetry.addData("dpadState", "up");
            //start_pos
            robot.armPos(start_base, start_elbow);
        }
        if(gamepad2.dpad_down){
            telemetry.addData("dpadState", "down");
            robot.armPos(start_base + 675, start_elbow - 365);
        }
    }
    @Override
    public void runOpMode() {
        robot.init_robot();

        start_base = robot.armBase.getCurrentPosition();
        start_elbow = robot.armElbow.getCurrentPosition();
        telemetry.addData("startBase", start_base);
        telemetry.addData("startElbow", start_elbow);
        waitForStart();
        while (opModeIsActive()) {
            //mecanumDrive();
            //moveArm();
            armToPos();
            telemetry.addData("arm base position", robot.armBase.getCurrentPosition());
            telemetry.addData("arm elbow position", robot.armElbow.getCurrentPosition());
            telemetry.update();

            if(gamepad1.left_bumper){
                robot.planeLaunch.setPosition(-100);
            }
            if(gamepad1.right_bumper){
                robot.planeLaunch.setPosition(0);
            }

        }
    }
}
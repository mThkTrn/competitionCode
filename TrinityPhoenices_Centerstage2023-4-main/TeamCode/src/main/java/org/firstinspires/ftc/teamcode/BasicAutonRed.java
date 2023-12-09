
//package org.firstinspires.ftc.teamcode;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//package org.firstinspires.ftc.robotcontroller.external.samples;
//
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.Disabled;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.util.ElapsedTime;


package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.TrinRobot;

@Autonomous(name = "Basic Auto Red", group = "Robot")
public class BasicAutonRed extends LinearOpMode{
    TrinRobot robot = new TrinRobot(this);


    public void runOpMode(){

        robot.init_robot();
        waitForStart();
        //robot.drive_for(1, 0, 0, 2);
        //robot.drive_for(0, 1, 0, 5);
        robot.drive_spike(1);
    }


}

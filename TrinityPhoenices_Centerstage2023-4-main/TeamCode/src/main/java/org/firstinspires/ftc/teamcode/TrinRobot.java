package org.firstinspires.ftc.teamcode;

import java.util.concurrent.TimeUnit;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TrinRobot {
    private final OpMode opMode;

    private DcMotor frontLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backLeft = null;
    private DcMotor backRight = null;
    public Servo planeLaunch = null;
    public DcMotor armBase = null;
    public DcMotor armElbow = null;

    private double MECANUM_SPEED = 1;
    private double ROTATION_SPEED = 1;
    private double ARM_BASE_SPEED = 1;
    private double MEC_ADJ = 0.9;
    private double ARM_ELBOW_SPEED = 1;

    public TrinRobot(OpMode mode) {
        opMode = mode;
    }

    public void drivePower(double x, double y, double r){
        x *= MECANUM_SPEED;
        y *= MECANUM_SPEED;
        r *= ROTATION_SPEED;
        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(r), 1);

        frontLeft.setPower(-(y + x + r));
        frontRight.setPower((y - x - r) * MEC_ADJ);
        backLeft.setPower(-(y - x + r) * MEC_ADJ);
        backRight.setPower((y + x - r) * MEC_ADJ);
    }

    public void drive_for(double x, double y, double r, double time_len){
        long start_time = System.currentTimeMillis();
        while (System.currentTimeMillis() < start_time + 1000*time_len){
            drivePower(x, y, r);
        }
    }

    public void drive_spike(int spike_num){
        if (spike_num == 1){
            drive_for(1,0,0,0.4);
            TimeUnit.SECONDS.sleep(1);
            drive_for(0,1,0,0.4);
        } else if (spike_num == 2) {
            drive_for(0, 1, 0, 0.75);
        } else if (spike_num == 3) {
            drive_for(-1, -1, 0, 0.75);
        }
    }
    public void init_robot(){
        frontLeft = opMode.hardwareMap.get(DcMotor.class, "frontLeft");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "frontRight");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "backLeft");
        backRight = opMode.hardwareMap.get(DcMotor.class, "backRight");

        armBase = opMode.hardwareMap.get(DcMotor.class, "armBase");
        armElbow = opMode.hardwareMap.get(DcMotor.class, "armElbow");

        planeLaunch = opMode.hardwareMap.get(Servo.class, "planeLaunch");

        opMode.telemetry.addData("start", "hello world :>");
        opMode.telemetry.update();
    }
    public void armPower(double basePwr, double elbowPwr){
        armBase.setPower(basePwr*ARM_BASE_SPEED);
        armElbow.setPower(elbowPwr*ARM_ELBOW_SPEED);
    }
    public void armPos(int basePos, int elbowPos){
        armBase.setTargetPosition(basePos);
        armElbow.setTargetPosition(elbowPos);
        armBase.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armElbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        armBase.setPower(ARM_BASE_SPEED);
        armElbow.setPower(ARM_ELBOW_SPEED);
    }

}

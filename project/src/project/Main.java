package project;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.utility.Delay;

public class Main {

	static int theta;
	
	static float[] AngleSamples = new float[3];
	public static void main(String[] args) throws InterruptedException{
		
		EV3 ev3brick =(EV3) BrickFinder.getLocal();
		TextLCD text = ev3brick.getTextLCD();
		EV3GyroSensor Gyro = new EV3GyroSensor(SensorPort.S1);

		Gyro.getAngleMode(); //Tell the gyro we are in angle mode
	
		text.drawString("Robot Running!",0,0);
		
		while(!Button.ENTER.isDown()){   //Apparently infinite while loops break things, so loop until enter is pressed
			
			Gyro.getAngleMode().fetchSample(AngleSamples, 0);   //Take a sample from the gyro and write to the AngleSamples Array starting at index 0
			//text.drawString("Angle X is " + AngleSamples[0], 0, 3);  //Print Gyro Angle for debugging
	        Delay.msDelay(2);   //This may or may not be needed, but was in the tutorial I read
			
	        //DO SOME STUFF
		}
		Gyro.close();
	}
	
	public static void setSpeed(int speed){ //Set motor speeds, we should only set full speed once at the start of the code?
		Motor.A.setSpeed(speed);
		Motor.B.setSpeed(speed);
		Motor.C.setSpeed(speed);
	}
	public static void moveForward(int time){ //pass in a time in ms for the robot to drive forwards
		Motor.A.forward();
		Motor.B.forward();
		Delay.msDelay(time);
	}
	public static void moveBackward(int time){ //pass in a time in ms for the robot to drive backwards
		Motor.A.backward();
		Motor.B.backward();
		Delay.msDelay(time);
	}
	public static void moveArm(int angle){    //Pass in an angle for the robot to move to
		Motor.C.rotateTo(angle);
	}
}

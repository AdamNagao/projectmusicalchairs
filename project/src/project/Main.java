package project;

import lejos.hardware.BrickFinder;
import lejos.hardware.Button;
import lejos.hardware.ev3.EV3;
import lejos.hardware.lcd.TextLCD;
import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3ColorSensor;
import lejos.hardware.sensor.EV3GyroSensor;
import lejos.utility.Delay;

public class Main {

	static int theta;
	
	static float[] colorSamples = new float[3];
	public static void main(String[] args) throws InterruptedException{
		
		EV3 ev3brick =(EV3) BrickFinder.getLocal();
		TextLCD text = ev3brick.getTextLCD();
		EV3GyroSensor Gyro = new EV3GyroSensor(SensorPort.S1);
		EV3ColorSensor Color = new EV3ColorSensor(SensorPort.S1);
		
		Color.getColorIDMode();  //tells the sensor to return a color  (NONE, BLACK, BLUE, GREEN, YELLOW, RED, WHITE, BROWN) which is a value (0,1,2,3,4,4,5,6)
		text.drawString("Robot Running!",0,0);
		
		while(!Button.ENTER.isDown()){   //Apparently infinite while loops break things, so loop until enter is pressed
			
			Color.fetchSample(colorSamples, 0);   //Take a sample from color sensor and write to Colorsamples array
			Delay.msDelay(2);   //This may or may not be needed, but was in the tutorial I read
			text.drawString("Color sensor value is:  " + colorSamples[0], 0, 3);  //Print color value for debugging
			
			if(colorSamples[0] == 1){
				text.drawString("Color sensor reads BLACK!", 1,4);
			}
	       
			
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

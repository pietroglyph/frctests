package org.usfirst.frc.team4915.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import org.usfirst.frc.team4915.robot.commands.LifterCheckFwdLimitSwitchCmd;
import org.usfirst.frc.team4915.robot.commands.LifterRunMotorOnJoystick;

import edu.wpi.first.wpilibj.buttons.JoystickButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());

	private Robot m_robot;
	private Joystick m_mainStick;
	private Button m_lifterButton1;
	private Button m_lifterButton2;
	
	public static int joystickPort = 1;
	public static int lifterButtonId1 = 2;
	public static int lifterButtonId2 = 3;
	
	OI(Robot r)
	{
		m_robot = r;
		m_mainStick = new Joystick(joystickPort);
		m_lifterButton1 = new JoystickButton(m_mainStick, lifterButtonId1);
		m_lifterButton2 = new JoystickButton(m_mainStick, lifterButtonId2);
		m_lifterButton1.whenPressed(new LifterCheckFwdLimitSwitchCmd(r,"joystick1"));
		m_lifterButton2.whenPressed(new LifterCheckFwdLimitSwitchCmd(r,"joystick2"));
		new LifterRunMotorOnJoystick(m_robot, m_mainStick);
	}
}


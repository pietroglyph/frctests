
package org.usfirst.frc.team4915.robot.subsystems;

import org.usfirst.frc.team4915.robot.Robot;
import org.usfirst.frc.team4915.robot.RobotMap;
import org.usfirst.frc.team4915.robot.commands.LifterCheckFwdLimitSwitchCmd;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Lifter extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

	private Robot m_robot;
	private CANTalon m_mainMotor;
	private boolean m_motorIsActive;
	private Joystick m_liftStick;
	private static double DEADZONE = 0.10;
	
	public Lifter(Robot r)
	{
		m_robot = r;
		m_mainMotor = new CANTalon(RobotMap.mainMotorPort);
		m_mainMotor.enableLimitSwitch(true, true);
		m_mainMotor.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
		m_mainMotor.configPeakOutputVoltage(12.0, -12.0);
		m_mainMotor.enableBrakeMode(true);
		
	}
	
	public boolean getFwdLimitSwitch() {
		return m_mainMotor.isFwdLimitSwitchClosed();
	}
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new LifterCheckFwdLimitSwitchCmd(m_robot, "lifter"));
    }
    
    public void startMotor() {
    	if(!m_motorIsActive) {
    		m_mainMotor.enableControl();
    		m_motorIsActive = true;
    	}
    }

    public void stopMotor() {
    	if(m_motorIsActive) {
    		m_mainMotor.set(0);
    		m_mainMotor.disableControl();
    		m_motorIsActive = false;
    	}
    }
    
    public void runMotor(double speed) {
    	if(m_motorIsActive) 
    	{
    		m_mainMotor.set(speed);
    	} else {
    		m_robot.logger.warning("lifter: runMotor called when motor is inactive.");
    	}
    }
    
    public void manualCtl() { // manulCtl needs to be called to check joystick state and move the motor
    	double axisY;
    	axisY = m_liftStick.getY();
    	if (Math.abs(axisY) > DEADZONE) 
    	{
    		this.runMotor(axisY);
    	} else {
    		this.stopMotor();
    		m_robot.logger.debug("lifter: joystick within deadzone("+DEADZONE+"), not running motor.");
    	}
    }
    
    public void setLifterStick(Joystick j) {
    	m_liftStick = j;
    }
    
    
}


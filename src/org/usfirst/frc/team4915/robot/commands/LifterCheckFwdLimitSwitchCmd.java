package org.usfirst.frc.team4915.robot.commands;

import org.usfirst.frc.team4915.robot.Robot;
import org.usfirst.frc.team4915.robot.subsystems.Lifter;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class LifterCheckFwdLimitSwitchCmd extends Command {
	private Robot m_robot;
	private Lifter m_lifter;
	
	private boolean m_lifterState;
	private boolean m_motorIsActive;
	private String m_name;
	
    public LifterCheckFwdLimitSwitchCmd(Robot r, String nm) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	m_robot = r;
    	m_name = nm;
    	m_lifter = m_robot.getLifter();
    	requires(m_lifter);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	m_robot.logger.debug("LifterCheckFwdLimitSwitchCmd is initalizing.");
    	m_lifterState = m_lifter.getFwdLimitSwitch();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	if(m_lifter.getFwdLimitSwitch() != m_lifterState) {
    		System.out.println(m_name+" "+m_lifterState);
    		m_lifterState = m_lifter.getFwdLimitSwitch();
    	}
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	m_robot.logger.debug("LifterCheckFwdLimitSwitchCmd is ending.");
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	m_robot.logger.debug("LifterCheckFwdLimitSwitchCmd is interrupted.");
    }
}

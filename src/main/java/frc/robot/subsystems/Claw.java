package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;


public class Claw extends SubsystemBase {
    DoubleSolenoid solenoid;
    Compressor compressor;
    PneumaticsControlModule pneumaticHub;


    public Claw() {
        pneumaticHub = new PneumaticsControlModule(20);
        solenoid = new DoubleSolenoid(20, PneumaticsModuleType.CTREPCM, RobotMap.ID_SOLENOID_IN, RobotMap.ID_SOLENOID_OUT);
        compressor = new Compressor(PneumaticsModuleType.CTREPCM);
        pneumaticHub.enableCompressorDigital();
        // compressor.enableAnalog(80, 120);
    }

    public void openClaw() {
        solenoid.set(DoubleSolenoid.Value.kReverse);
    }

    public void closeClaw() {
        //shoulf kReverse
        solenoid.set(DoubleSolenoid.Value.kForward);
    }
    public void neutralClaw(){
        solenoid.set(DoubleSolenoid.Value.kOff);
    }

    public void enableCompressor() {
        compressor.enableAnalog(80,120); // needs to be continous enable
    }

    public void disableCompressor() {
        compressor.disable();
    }
}

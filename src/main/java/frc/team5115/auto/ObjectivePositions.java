package frc.team5115.auto;

import edu.wpi.first.wpilibj.DriverStation;

public class ObjectivePositions {

    public static char start;
    public static char switchpos;
    public static char scalepos;

    public ObjectivePositions(char wheretostart){
        start = wheretostart;
        switchpos = DriverStation.getInstance().getGameSpecificMessage().charAt(0);
        scalepos = DriverStation.getInstance().getGameSpecificMessage().charAt(1);
    }
    public boolean isCenter(){return start == 'c';}
    public boolean switchOurs(){ return start == switchpos; }
    public boolean scaleOurs(){ return start == scalepos; }
    public boolean objectivesAligned(){return (switchpos == scalepos);}


}

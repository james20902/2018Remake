package frc.team5115.auto;

import com.opencsv.CSVReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CSVInterpreter {

    CSVReader csvReader;

    double x = 0;
    double y = 0;
    int buttonPressed;
    String[] nextRecord;
    boolean finished = false;

    public CSVInterpreter(){
        try {
            csvReader = new CSVReader(Files.newBufferedReader(Paths.get("wow.csv")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void nextLine(){
        try {
            if((nextRecord = csvReader.readNext()) != null){
                x = Double.parseDouble(nextRecord[0]);
                y = Double.parseDouble(nextRecord[1]);
                buttonPressed = Integer.parseInt(nextRecord[2]);
            } else {
                finished = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            finished = true;
        }
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public int getButtonInput(){
        return buttonPressed;
    }

    public boolean isFinished(){
        return finished;
    }
}

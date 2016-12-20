package Utils;

import Model.Constraints;

import java.util.Random;

/**
 * Created by Felipe on 19/12/2016.
 */
public class NumberGenerator {

    public int randomEvenStartTime () {
        Random randomNumber = new Random();
        randomNumber = new Random();
        int startTime = (randomNumber.nextInt(Constraints.LATEST_TIME-Constraints.EARLIEST_TIME) + Constraints.EARLIEST_TIME);
        if ( startTime%2 != 0 ){
            startTime = startTime+1;
        }
        return startTime;
    }

    public int randomDay () {
        Random randomNumber = new Random();
        int day = (randomNumber.nextInt(Constraints.DAY_MAX-Constraints.DAY_MIN) + Constraints.DAY_MIN);
        return day;
    }
}

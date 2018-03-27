/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  The Timer class contains methods for the SoccerSim program to call
                     regarding the ball's current time.
 *  @author       :  Brett Derham
 *  Date written  :  2018-03-26
 *  Description   :  The Timer class contains methods for the SoccerSim program to call
                     regarding the ball's current time.
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.1  2018-03-26  Brett Derham  Completed project
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.text.DecimalFormat;

public class Timer {

  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
  private double totalSec = 0;
  private double timeSlice;
  private double hour;
  private double minute;
  private double second;

  public Timer( double timeSlice ) {
    this.timeSlice = timeSlice;
  }

  public double tick() {
    totalSec += timeSlice;
    return totalSec;
  }

  public double getMinute() {
    minute = Math.floor( (totalSec - (hour*3600))/60 );
    return minute;
  }

  public double getHour() {
    hour = Math.floor( totalSec/3600 );
    return hour;
  }

  public double getCurrentSeconds() {
    second = totalSec - ( hour*3600 ) - ( minute*60 );
    return second;
  }

  public double getTotalSeconds() {
    return totalSec;
  }

  public String toString() {
    getHour();
    getMinute();
    getCurrentSeconds();
    String patternHour = "00:";
    String patternMinute = "00:";
    String patternSeconds = "00.000";
    DecimalFormat decimalFormatHour = new DecimalFormat(patternHour);
    DecimalFormat decimalFormatMinute = new DecimalFormat(patternMinute);
    DecimalFormat decimalFormatSeconds = new DecimalFormat(patternSeconds);
    String format = decimalFormatHour.format(hour) + decimalFormatMinute.format(minute) + decimalFormatSeconds.format(second);
    return format;
  }

  public static void main( String args[] ) {
    System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
    System.out.println( "  Creating a new clock: " );
    Timer clock = new Timer( 2 );
    System.out.println( "    New clock created: " + clock.toString() );
  }
}


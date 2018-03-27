/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  The Ball class contains methods for the SoccerSim program to call
                     regarding the ball's position and velocity.
 *  @author       :  Brett Derham
 *  Date written  :  2018-03-26
 *  Description   :  The Ball class contains methods for the SoccerSim program to call
                     regarding the ball's position and velocity.
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

public class Ball {

  private double[] ball = new double[5];

  public Ball( double xPosition, double yPosition, double xVelocity, double yVelocity, double timeSlice ) {
    ball[0] = xPosition;
    ball[1] = yPosition;
    ball[2] = xVelocity;
    ball[3] = yVelocity;
    ball[4] = timeSlice;
  }

  public void currentVelocity() {
    ball[2] = ball[2]-(.01*ball[2])*ball[4];
    ball[3] = ball[3]-(.01*ball[3])*ball[4];
  }

  public double[] getVelocity() {
    double velocityX = ball[2];
    double velocityY = ball[3];
    double[] currentVelocity = {velocityX, velocityY};
    return currentVelocity;
    }

  public void currentPosition() {
    ball[0] += ball[4]*ball[2];
    ball[1] += ball[4]*ball[3];
  }

  public double[] getPosition() {
    double positionX = ball[0];
    double positionY = ball[1];
    double[] currentPosition = {positionX, positionY};
    return currentPosition;
  }

  public String getUpdate() {
    StringBuilder status = new StringBuilder();
    String patternDecimal = "#.##";
    DecimalFormat decimalFormatOutput = new DecimalFormat(patternDecimal);
    status.append("Position = " + decimalFormatOutput.format(ball[0]) + " ft, " + decimalFormatOutput.format(ball[1]) + " ft");
    status.append("\t  Velocity = " + decimalFormatOutput.format(ball[2]) + " ft/s, " + decimalFormatOutput.format(ball[3]) + " ft/s");
    return status.toString();
  }

  public static void main( String args[] ) {
    System.exit( 0 );
  }
}


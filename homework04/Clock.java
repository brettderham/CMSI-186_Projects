/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-03-12  Brett Derham  Final updates and upload
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private static double hourValue;
   private static double minuteValue;
   private static double secondValue;
   private static double hourHandAngValue;
   private static double minuteHandAngValue;
   private static double timeSliceValue;
   private static double totalSeconds;


  /**
   *  Constructor goes here
   */
   public Clock() {
     totalSeconds = 0;
     hourValue = 0;
     minuteValue = 0;
     secondValue = 0;
     hourHandAngValue = 0;
     minuteHandAngValue = 0;
     timeSliceValue = 0;
   }
  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      totalSeconds += timeSliceValue;
      return totalSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {

     if (Double.parseDouble(argValue) <= MAXIMUM_DEGREE_VALUE && Double.parseDouble(argValue) >= 0) {
           return Double.parseDouble(argValue);
       } else {
           throw new NumberFormatException();
       }
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) {
     if (Double.parseDouble(argValue) >= 0 && Double.parseDouble(argValue) <= 1800) {
           timeSliceValue = Double.parseDouble(argValue);
           return Double.parseDouble(argValue);
       }
           throw new NumberFormatException();
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
     hourHandAngValue = totalSeconds * HOUR_HAND_DEGREES_PER_SECOND;
     return hourHandAngValue;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
     minuteHandAngValue= ( totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND ) - ( 360 * Math.floor( ( totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND ) / 360 ) );
     return minuteHandAngValue;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
      return Math.abs(getHourHandAngle() - getMinuteHandAngle());
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      hourValue = Math.floor( totalSeconds / 3600 );
      minuteValue = Math.floor( ( totalSeconds - ( 3600 * hourValue ) ) / 60 );
      secondValue = totalSeconds - ( 3600 * hourValue ) - ( 60 * minuteValue );
      return hourValue + ":" + minuteValue + ":" + secondValue;
   }

  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {

      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock();
      System.out.println( "    New clock created: " + clock.toString() );

      System.out.println( "\n    Testing validateAngleArg() method.");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? "    : Correct!" : clock.validateAngleArg( "0.0" ) ); }
      catch( Exception e ) { System.out.println ( ": Incorrect"); }
      System.out.print( "      sending '  -1.0 degrees', expecting Incorrect" );
      try { System.out.println( (-1.0 == clock.validateAngleArg( "-1.0" )) ? "     : Correct!" : clock.validateAngleArg( "-5.0" ) ); }
      catch( Exception e ) { System.out.println ( ": Incorrect"); }
      System.out.print( "      sending '  30.03 degrees', expecting value   30.03" );
      try { System.out.println( (30.03 == clock.validateAngleArg( "30.03" )) ? "   : Correct!" : clock.validateAngleArg( "30.03" ) ); }
      catch( Exception e ) { System.out.println ( ": Incorrect"); }
      System.out.print( "      sending '  361 degrees', expecting value   360" );
      try { System.out.println( (360 == clock.validateAngleArg( "360" )) ? "   : Correct!" : clock.validateAngleArg( "360" ) ); }
      catch( Exception e ) { System.out.println ( ": Incorrect"); }
      System.out.print( "      sending '  361 degrees', expecting Incorrect" );
      try { System.out.println( (361 == clock.validateAngleArg( "361" )) ? "   : Correct!" : clock.validateAngleArg( "361" ) ); }
      catch( Exception e ) { System.out.println ( ": Incorrect"); }

      System.out.println( "\n    Testing validateTimeSliceArg() method.");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateTimeSliceArg( "0.0" )) ? "    : Correct!" : clock.validateTimeSliceArg( "0.0" ) ); }
      catch( Exception e ) { System.out.println ( ": Incorrect"); }
      System.out.print( "      sending '  -1.0 second', expecting Incorrect" );
      try { System.out.println( (DEFAULT_TIME_SLICE_IN_SECONDS == clock.validateTimeSliceArg( "-1.0" )) ? "   : Correct!" : clock.validateTimeSliceArg( "-1.0" ) ); }
      catch( Exception e ) { System.out.println ( " : Incorrect" ); }
      System.out.print( "      sending '  1000.0 second', expecting value 1000.0 second" );
      try { System.out.println( (1000.0 == clock.validateTimeSliceArg( "1000.0" )) ? "  : Correct!" : clock.validateTimeSliceArg( "1000.0" ) ); }
      catch( Exception e ) { System.out.println ( " : Incorrect" ); }
      System.out.print( "      sending '  1800.0 second', expecting value 1800.0 second" );
      try { System.out.println( (1800.0 == clock.validateTimeSliceArg( "1800.0" )) ? "   : Correct!" : clock.validateTimeSliceArg( "1800.0" ) ); }
      catch( Exception e ) { System.out.println ( " : Incorrect" ); }
      System.out.print( "      sending '  1801.0 second', expecting Incorrect" );
      try { System.out.println( (1801.0 == clock.validateTimeSliceArg( "1801.0" )) ? "   : Correct!" : clock.validateTimeSliceArg( "1801.0" ) ); }
      catch( Exception e ) { System.out.println ( " : Incorrect" ); }

      System.out.println( "\n    Testing tick() method");
      System.out.println( "      New time slice value is 0.0" );
      clock.timeSliceValue = 0.0;
      for (int x = 0; x < 3; x++ ) {
        try { System.out.println( "Current time  is  " + clock.toString() + " and Total Second Value is  " + clock.tick() ); }
        catch( Exception e ) { System.out.println ( " : Incorrect" ); }
      }
      System.out.println( "      New time slice value is 1.0" );
      clock.timeSliceValue = 1.0;
      for (int x = 0; x < 3; x++ ) {
        try { System.out.println( "Current time  is  " + clock.toString() + " and Total Second Value is  " + clock.tick() ); }
        catch( Exception e ) { System.out.println ( " : Incorrect" ); }
      }
      System.out.println( "      New time slice value is 1600.0" );
      clock.timeSliceValue = 1597.0;
      for (int x = 0; x < 3; x++ ) {
        try { System.out.println( "Current time  is  " + clock.toString() + " and Total Second Value is  " + clock.tick() ); }
        catch( Exception e ) { System.out.println ( " : Incorrect" ); }
      }
      System.out.println( "      New time slice value is 3600.0" );
      clock.timeSliceValue = 3600.0;
      for (int x = 0; x < 3; x++ ) {
        try { System.out.println( "Current time  is  " + clock.toString() + " and Total Second Value is  " + clock.tick() ); }
        catch( Exception e ) { System.out.println ( " : Incorrect" ); }
      }

      Clock clock1 = new Clock();
      System.out.println("Time Slice Value set to 5.");
      timeSliceValue = 5;
      System.out.println("Total Seconds Value is" + clock1.getTotalSeconds());
      System.out.println("Tick! " + clock1.tick());
      System.out.println("Tick! " + clock1.tick());
      System.out.println("Tick! " + clock1.tick());
      System.out.println("Total Seconds Value is " + clock1.getTotalSeconds());
      System.out.println("Total seconds set to 5,601.");
      totalSeconds = 5601;
      System.out.println("Hour Hand Angle is " + clock1.getHourHandAngle());
      System.out.println("Minute Hand Angle is " + clock1.getMinuteHandAngle());
      System.out.println("Hand Angle is " + clock1.getHandAngle());

      Clock clock2 = new Clock();
      System.out.println("Total Seconds Value reset to 0.");
      totalSeconds = 0;
      System.out.println("Time Slice Value set to 100.");
      timeSliceValue = 100;
      System.out.println("Total Seconds Value is " + clock2.getTotalSeconds());
      System.out.println("Tick! " + clock2.tick());
      System.out.println("Tick! " + clock2.tick());
      System.out.println("Tick! " + clock2.tick());
      System.out.println("Total Seconds Value is " + clock2.getTotalSeconds());
      System.out.println("Total seconds set to 2,901.");
      totalSeconds = 2901;
      System.out.println("Hour Hand Angle is " + clock2.getHourHandAngle());
      System.out.println("Minute Hand Angle is " + clock2.getMinuteHandAngle());
      System.out.println("Hand Angle is " + clock2.getHandAngle());
   }
}

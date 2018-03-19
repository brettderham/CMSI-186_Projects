/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @see
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

 public class ClockSolver {

   /**
    *  The main program starts here
    *  remember the constraints from the project description
    *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
    *  @param  args  String array of the arguments from the command line
    *                args[0] is the angle for which we are looking
    *                args[1] is the time slice; this is optional and defaults to 60 seconds
    */
    public static void main( String args[] ) {
      double EPSILON_VALUE = .1;      // small value for double-precision comparisons
      double angle = 0.0;
      double timeSlice = 10;
      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }
      Clock clock = new Clock();
      try { angle = clock.validateAngleArg( args[0] ); }
      catch (Exception e) { System.out.println( "Sorry! Angle must be between 0 and 360 degrees!" ); }
      try { timeSlice = clock.validateTimeSliceArg( args[1] ); }
      catch (Exception e) { timeSlice = clock.validateTimeSliceArg(""); } // will set timeSlice to 60.0
      angle = ( angle > 180 ) ? angle - 180 : angle;
      System.out.println( "Angle Value = " + angle + " Time Slice = " + timeSlice + " seconds." );
      System.out.println( " \nFound angle " + angle + " at times: " );
      while( clock.getTotalSeconds() < 43200 ) {
         if ( Math.abs( clock.getHandAngle() -  angle ) <= EPSILON_VALUE ) {
           System.out.println( "   " + clock.toString() );
         }
         clock.tick();
       }

       System.exit( 0 );
    }
 }

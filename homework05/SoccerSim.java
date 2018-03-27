/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :  The SoccerSim class calls upon both Ball.java and Timer.java, in ordr to run a
                     program that simulates a soccerball/soccerballs on a field.
 *  @author       :  Brett Derham
 *  Date written  :  2018-03-26
 *  Description   :  The SoccerSim class calls upon both Ball.java and Timer.java, in ordr to run a
                     program that simulates a soccerball/soccerballs on a field. This program handle inital
                     arguments by creating balls with given positions and applied velocities. The program then
                     updates however many balls were imputted by a time slice value that happens to be the last
                     argument in the command line. The program stops when there is a collision or if the balls
                     run off the field.
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

public class SoccerSim {
  private final double DEFAULT_TIME_SLICE_IN_SECONDS = 1.0;
  private double totalSec = 0;
  private double timeSlice;
  private Ball[] ball = null;
  private int hit1;
  private int hit2;
  private int hitPole = -1;
  private int outOfBounds;
  private Timer clock;
  private double xPos;
  private double yPos;
  private double xVel;
  private double yVel;
  private int numberBalls;
  private double fieldBorder = 1000;

  public SoccerSim( String[] args ) {
      readValues(args);
      clock = new Timer(timeSlice);
  }

  public void readValues( String[] args ) {
    try {
      if( args.length < 4 ) {
        System.out.println( "Input more values please!" );
        System.exit(-2);
      } else if( args.length % 4 == 0 ) {
          numberBalls = (args.length)/4;
          ball = new Ball[numberBalls];
          timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
          for( int i = 0; i < (numberBalls); i++ ) {
            xPos = Double.parseDouble(args[4*i + 0]);
            yPos = Double.parseDouble(args[4*i + 1]);
            xVel = Double.parseDouble(args[4*i + 2]);
            yVel = Double.parseDouble(args[4*i + 3]);
            ball[i] = new Ball(xPos, yPos, xVel, yVel, timeSlice);
          }
        } else if( args.length % 4 == 1 ) {
          numberBalls = (args.length - 1)/4;
          ball = new Ball[numberBalls];
          timeSlice = Double.parseDouble(args[args.length - 1]);
          if( timeSlice < 0 || timeSlice > 1800 ) {
            System.out.println("Time Slice value must be between 0 and 1800. Enter a new value.");
            System.exit(-2);
          }
          for( int i = 0; i < (numberBalls); i++ ) {
            xPos = Double.parseDouble(args[4*i + 0]);
            yPos = Double.parseDouble(args[4*i + 1]);
            xVel = Double.parseDouble(args[4*i + 2]);
            yVel = Double.parseDouble(args[4*i + 3]);
            ball[i] = new Ball(xPos, yPos, xVel, yVel, timeSlice);
          }
        } else {
          System.out.println( "Invalid number of values. Try again!" );
          System.exit(-3);
        }
      }
      catch( NumberFormatException nfe ) {
        System.out.println( "Not a valid double." );
        System.exit(-1);
      }
    }

  public void initialUpdate() {
    System.out.println("\n\n\n\n Let's play SoccerSim!");
    System.out.println("Values Entered: " + "Time Slice: " + timeSlice + "\n1000 X 1000 feet field.");
    System.out.println("Pole Location: x = 200.0 ft & y = 675.0 ft");
    for( int i = 0; i < numberBalls; i++ ) {
      System.out.println("Ball " + (i + 1) + " update:  " + ball[i].getUpdate());
    }
    System.out.print("\n");
  }

  public void currentUpdate() {
    System.out.println("Time: " + clock.toString());
    for( int i = 0; i < numberBalls; i++ ) {
      System.out.println("Ball " + (i + 1) + " update:  " + ball[i].getUpdate());
    }
  }

  public boolean isOutofBounds() {
    for( int i = 0; i < ball.length; i++ ) {
      double[] position = ball[i].getPosition();
      double x = position[0];
      double y = position[1];
      if( Math.abs(x) > fieldBorder || Math.abs(y) > fieldBorder ) {
        outOfBounds = i;
        return true;
      }
    }
    return false;
  }

  public boolean isCollision() {
    for( int i = 0; i < ball.length; i++ ) {
      double[] position = ball[i].getPosition();
      double x = position[0];
      double y = position[1];
      for( int l = i + 1; l < ball.length; l++ ) {
        double[] positionTest = ball[l].getPosition();
        double xTest = positionTest[0];
        double yTest = positionTest[1];
        if( Math.sqrt( Math.pow((x - xTest),2) + Math.pow((y - yTest),2) ) < .741666 ) {
          hit1 = i;
          hit2 = l;
          return true;
        }
      }
    }

    for( int i = 0; i < ball.length; i++ ) {
      double[] position = ball[i].getPosition();
      double x = position[0];
      double y = position[1];
      if( Math.abs(Math.abs(x) - 200.0) < .741666 && Math.abs(Math.abs(y) - 675.0) < .741666 ) {
        hitPole = i;
        return true;
      }
    }
    return false;
  }

  public boolean atRest() {
    int ballsAtRest = 0;
    for( int i = 0; i < ball.length; i++ ) {
      double[] velocity = ball[i].getVelocity();
      double x = velocity[0];
      double y = velocity[1];
      if( (Math.abs(x) < (1.0/12.0)) && (Math.abs(y) < (1.0/12.0)) ) {
        ballsAtRest += 1;
      }
    }
    if( ballsAtRest == ball.length ) {
      return true;
    } else {
      return false;
    }
  }

  public static void main( String args[] ) {
    SoccerSim soccer1 = new SoccerSim( args );
    soccer1.initialUpdate();
    if( soccer1.isCollision() ) {
      soccer1.currentUpdate();
      if (soccer1.hitPole != -1) {
        System.out.println("Ball " + (soccer1.hitPole + 1) + " and the pole have collided!");
        System.exit( 4 );
      } else {
        System.out.println("Ball " + (soccer1.hit1 + 1) + " and ball " + (soccer1.hit2 + 1) + "have collided!");
        System.exit( 4 );
      }
    }
    if( soccer1.isOutofBounds() ) {
      System.out.println("Ball " + (soccer1.outOfBounds + 1) + " is now out of bounds.");
    }

    if( soccer1.atRest() ) {
      System.out.println("All balls are at rest.");
      System.exit( 4 );
    }

    while ( true ) {
      soccer1.clock.tick();
      for( int i = 0; i < soccer1.ball.length; i++ ) {
        soccer1.ball[i].currentPosition();
        soccer1.ball[i].currentVelocity();
      }
      soccer1.currentUpdate();
      System.out.println("");
      if( soccer1.isCollision() ) {
        if (soccer1.hitPole != -1) {
          System.out.println("Ball " + (soccer1.hitPole + 1) + " and the pole have collided!");
          System.exit( 4 );
        } else {
          System.out.println("Ball " + (soccer1.hit1 + 1) + " and ball " + (soccer1.hit2 + 1) + " have collided!");
          System.exit( 4 );
        }
      }
      if( soccer1.isOutofBounds() ) {
        System.out.println("Ball " + (soccer1.outOfBounds + 1) + " is now out of bounds.");
      }
      if( soccer1.atRest() ) {
        System.out.println("All balls are at rest.");
        System.exit( 4 );
      }

    }
  }
}


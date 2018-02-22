/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  DiceSet.java
 *  Purpose       :  Provides a class describing a set of dice
 *  Author        :  B.J. Johnson
 *  Date          :  2017-02-09
 *  Description   :  This class provides everything needed (pretty much) to describe a set of dice.  The
 *                   idea here is to have an implementing class that uses the Die.java class.  Includes
 *                   the following:
 *                   public DiceSet( int k, int n );                  // Constructor for a set of k dice each with n-sides
 *                   public int sum();                                // Returns the present sum of this set of dice
 *                   public void roll();                              // Randomly rolls all of the dice in this set
 *                   public void rollIndividual( int i );             // Randomly rolls only the ith die in this set
 *                   public int getIndividual( int i );               // Gets the value of the ith die in this set
 *                   public String toString();                        // Returns a stringy representation of this set of dice
 *                   public static String toString( DiceSet ds );     // Classwide version of the preceding instance method
 *                   public boolean isIdentical( DiceSet ds );        // Returns true iff this set is identical to the set ds
 *                   public static void main( String[] args );        // The built-in test program for this class
 *
 *  Notes         :  Stolen from Dr. Dorin pretty much verbatim, then modified to show some interesting
 *                   things about Java, and to add this header block and some JavaDoc comments.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the number of sides or pips is out of range
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-09  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2018-02-20  Brett Derham  Started DiceSet.java
 *  @version 1.0.2  2018-02-21  Brett Derham  Finished DiceSet.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public class DiceSet {

  /**
   * private instance data
   */
   private int count;
   private int sides;
   private Die[] ds = null;
   private final int MINIMUM_SIDES = 4;

   // public constructor:
  /**
   * constructor
   * @param  count int value containing total dice count
   * @param  sides int value containing the number of pips on each die
   * @throws IllegalArgumentException if one or both arguments don't make sense
   * @note   parameters are checked for validity; invalid values throw "IllegalArgumentException"
   */
   public DiceSet( int count, int sides ) {
     if ( count < 1 || sides < MINIMUM_SIDES){
       throw new IllegalArgumentException();
     }
     this.count = count;
     this.sides = sides;
     this.ds = new Die[count];
     for( int i = 0; i < count; i++ ) {
       ds[i] = new Die( sides );
       ds[i].roll();
     }
   }

  /**
   * @return the sum of all the dice values in the set
   */
   public int sum() {
     int sum = 0;
     for ( int i = 0; i < ds.length; i++ ) {
       sum += ds[i].getValue();
     }
     return sum;
   }

  /**
   * Randomly rolls all of the dice in this set
   *  NOTE: you will need to use one of the "toString()" methods to obtain
   *  the values of the dice in the set
   */
   public void roll() {
     for( int i = 0; i < ds.length; i++ ) {
      ds[i].roll();
    }
   }

  /**
   * Randomly rolls a single die of the dice in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @return the integer value of the newly rolled die
   * @trhows IllegalArgumentException if the index is out of range
   */
   public int rollIndividual( int dieIndex ) {
     return ds[dieIndex - 1].roll();
   }

  /**
   * Gets the value of the die in this set indexed by 'dieIndex'
   * @param  dieIndex int of which die to roll
   * @trhows IllegalArgumentException if the index is out of range
   */

   public int getIndividual( int dieIndex ) {
     return ds[dieIndex - 1].getValue();
   }

  /**
   * @return Public Instance method that returns a String representation of the DiceSet instance
   */
   public String toString() {
     String result = "";
     for ( int i = 0; i < count; i ++ ) {
       result += ds[i].toString();
     }
     return result;
   }

  /**
   * @return Class-wide version of the preceding instance method
   */
   public static String toString( DiceSet ds ) {
     return ds.toString();
   }

  /**
   * @return  tru iff this set is identical to the set passed as an argument
   */
   public boolean isIdentical( DiceSet dsSet ) {
     if( sum() == dsSet.sum() ) {
       return true;
     }
     return false;
   }
  /**
   * A little test main to check things out
   */
   public static void main( String[] args ) {
    try {
      System.out.println( "Hello world from the DiceSet class..." );
      DiceSet ds1 = new DiceSet(4,4);
      DiceSet ds2 = new DiceSet(5,4);
      System.out.println( "Testing toString() method" );
      System.out.println( "This set of die is " + ds1.toString() );
      System.out.println( "Testing toString() method" );
      System.out.println( "This set of die is " + DiceSet.toString(ds1));
      System.out.println( "Testing sum() method" );
      System.out.println( "The sum of the dice set is " + ds1.sum());
      System.out.println( "Testing roll() method" );
      ds1.roll();
      System.out.println("The roll was " + ds1.toString());
      System.out.println( "Testing rollIndividual() method" );
      ds1.rollIndividual(3);
      System.out.println("Rolling die number 3 you get: " + ds1.getIndividual(3));
      System.out.println("The resulting dice set is now: " + ds1.toString());
      System.out.println( "Testing isIdentical() method" );
      System.out.println("Comparing " + ds1.toString() + " with " + ds2.toString() +
                          " we get: " + ds1.isIdentical( ds2 ));

      System.out.println("Introducing new Dice Set with 5 die with 10 sides each");
      DiceSet ds3 = new DiceSet(5,10);
      System.out.println( "Testing toString() method" );
      System.out.println( "This set of die is " + ds3.toString() );
      System.out.println( "Testing toString() method" );
      System.out.println( "This set of die is " + DiceSet.toString(ds3));
      System.out.println( "Testing sum() method" );
      System.out.println( "The sum of the dice set is " + ds3.sum());
      System.out.println( "Testing roll() method" );
      ds3.roll();
      System.out.println("The roll was " + ds3.toString());
      System.out.println( "Testing rollIndividual() method" );
      ds3.rollIndividual(4);
      System.out.println("Rolling die number 4 you get: " + ds3.getIndividual(4));
      System.out.println("The resulting dice set is now: " + ds3.toString());
      System.out.println( "Testing isIdentical() method" );
      System.out.println("Comparing " + ds2.toString() + " with " + ds3.toString() +
                          " we get: " + ds2.isIdentical( ds3 ));

      System.out.println("Introducing new Dice Set with 25 die with 50 sides each");
      DiceSet ds4 = new DiceSet(25,50);
      System.out.println( "Testing toString() method" );
      System.out.println( "This set of die is " + ds4.toString() );
      System.out.println( "Testing toString() method" );
      System.out.println( "This set of die is " + DiceSet.toString(ds4));
      System.out.println( "Testing sum() method" );
      System.out.println( "The sum of the dice set is " + ds4.sum());
      System.out.println( "Testing roll() method" );
      ds4.roll();
      System.out.println("The roll was " + ds4.toString());
      System.out.println( "Testing rollIndividual() method" );
      ds4.rollIndividual(20);
      System.out.println("Rolling die number 20 you get: " + ds4.getIndividual(20));
      System.out.println("The resulting dice set is now: " + ds4.toString());
      System.out.println( "Testing isIdentical() method" );
      System.out.println("Comparing " + ds3.toString() + " with " + ds4.toString() +
                          " we get: " + ds3.isIdentical( ds4 ));

      System.out.println("Introducing another Dice Set with 5 die with 4 sides each");
      DiceSet ds5 = new DiceSet(5,4);
      System.out.println( "Testing toString() method" );
      System.out.println( "This set of die is " + ds5.toString() );
      System.out.println( "Testing toString() method" );
      System.out.println( "This set of die is " + DiceSet.toString(ds5));
      System.out.println( "Testing sum() method" );
      System.out.println( "The sum of the dice set is " + ds5.sum());
      System.out.println( "Testing roll() method" );
      ds5.roll();
      System.out.println("The roll was " + ds5.toString());
      System.out.println( "Testing isIdentical() method" );
      System.out.println("Comparing " + ds5.toString() + " with " + ds5.toString() +
                          " we get: " + ds5.isIdentical( ds5 ));
    }
    catch( NullPointerException ioe ) {
        System.out.println( "Caught NullPointerException." );
    }
  }
}

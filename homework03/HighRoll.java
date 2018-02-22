/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  HighRoll.java
 *  Purpose       :  Provides a dice game to be played using two die
 *  @author       :  Brett Derham
 *  Date          :  2018-02-21
 *  Description   :  This class provides a game using two die where the user can pick from five different options. They are listed as follows.
 *                    1. Roll all the dice
 *                    2. Roll a single die
 *                    3. Calculate the score for this set
 *                    4. Save this score as a high score
 *                    5. Display the high score
 *                  The user can also quit the game by typing 'Q' or 'q'.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  NumberFormatException and  ArrayIndexOutOfBoundsException when the given invalid arguements
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-02-21  Brett Derham  Started HighRoll.java
 *  @version 1.0.1  2018-02-22  Brett Derham  Finished HighRoll.java
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 import java.io.BufferedReader;
 import java.io.InputStreamReader;
 import java.io.IOException;

public class HighRoll {

  public static void main( String args[] ) {
    DiceSet ds1 = null;
    try {
          ds1 = new DiceSet(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        }
        catch (NumberFormatException nf) {
            System.out.println("Invalid arguements");
            System.exit(1);
        } catch (ArrayIndexOutOfBoundsException aiob) {
            System.out.println("Invalid arguements");
            System.exit(2);
        }
    BufferedReader input = new BufferedReader( new InputStreamReader( System.in ));
    int score = 0;
    int hs = 0;
    System.out.println( "Let's play High Roll!" );
    while( true ) {
      String inputLine = null;
      System.out.println( "Choose one of the following");
      System.out.println("1. Roll all the dice");
      System.out.println("2. Roll a single die");
      System.out.println("3. Calculate the score for this set");
      System.out.println("4. Save this score as a high score");
      System.out.println("5. Display the high score");
      System.out.println("Enter 'Q' or 'q' to quit");
          try {
                inputLine = input.readLine();
                System.out.print("");
                if (0 == inputLine.length()) {
                    System.out.println("Enter choice");
                    continue;
                }
                if (1 < inputLine.length()) {
                    System.out.println("Only enter one character");
                    continue;
                }
                switch (inputLine.charAt(0)) {
                case '1':
                    ds1.roll();
                    System.out.println("The current Dice Set is: " + ds1.toString());
                    break;
                case '2':
                    System.out.println("Pick a die u wish to roll");
                    try {
                        inputLine = input.readLine();
                        ds1.rollIndividual(Integer.parseInt(inputLine));
                    } catch (ArrayIndexOutOfBoundsException aiob) {
                        System.out.println("Please enter an integer index within the range of the DiceSet");
                    } catch (NumberFormatException nf) {
                        System.out.println("Please enter an integer index within the range of the DiceSet");
                    }
                    System.out.println("This die is now " + ds1.getIndividual(Integer.parseInt(inputLine)));
                    System.out.println("The resulting dice set is now: " + ds1.toString());
                    break;
                case '3':
                    System.out.println("Your score is:  " + ds1.sum());
                    break;
                case '4':
                    hs = ds1.sum();
                    System.out.println("High score set to " + ds1.sum());
                    break;
                case '5':
                    System.out.println("The High Score is " + hs);
                    break;
                case 'Q':
                    System.exit(0);
                case 'q':
                    System.exit(0);
                }
            }
            catch (IOException ioe) {
                System.out.println("Caught IOException");
            }
        }
    }
}

/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Dynamic Programming as a method to make optimal change
 * @author    :  Brett Derham
 * Date       :  2018-05-03
 * Description:  A program that takes as input arguments a sequence of coin denominations [distinct positive
 * integers in no particular order], followed by an arbitrary amount of money [a non-negative integer], and
 * which outputs the optimal way of making change for that amount using those denominations [optimal meaning
 * the minimum number of coins], or if change cannot be made, outputs the message IMPOSSIBLE.
 * Notes      :  N/A
 * Warnings   :  N/A
 * Exceptions :  IllegalArgumentException for invalid inputs
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class DynamicChangeMaker {

/**
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  int[] denom - int array containing coin denomination
   *  @param  int targ - int total target targ
   *  @return Tuple that is the optimized set of coins to reach the target
   */
  public static Tuple makeChangeWithDynamicProgramming(int denom[], int targ){

	try{
		handleInitialArgs(denom, targ);
	}
	catch(IllegalArgumentException iae){
	System.out.println("BAD DATA - Impossible Tuple. Please enter arguments in the form of '<Denominations><Target>' \n" +
      "Please only use valid values for the Denominations and Target");
	return Tuple.IMPOSSIBLE;
	}
  Tuple table[][] = new Tuple[denom.length][targ + 1];
  for (int i = 0; i < denom.length; i++) {
    table[i][0] = new Tuple(denom.length);
  }
  for (int j = 0; j < denom.length; j++) {
    for (int i = 1; i <= targ; i++) {
      if (i - denom[j] < 0) {
        table[j][i] = Tuple.IMPOSSIBLE;
      } else {
          table[j][i] = new Tuple(denom.length);
          table[j][i].setElement(j, 1);
          if (table[j][i - denom[j]].isImpossible()) {
            table[j][i] = Tuple.IMPOSSIBLE;
          } else {
              table[j][i] = (table[j][i]).add(table[j][i - denom[j]]);
          }
        }
        if (j > 0 && !(table[j - 1][i].isImpossible())) {
          if (table[j][i].isImpossible() || (table[j - 1][i].total() < table[j][i].total())) {
            table[j][i] = table[j - 1][i];
          }
        }
      }
    }
    return table[denom.length - 1][targ];
  }

  /**
   *  Method to check the validity of arguments passed in
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  denoms[] - int array containing coin denomination
   *          targ -   total target amount
   *  @throws IllegalArgumentException fo invalid arguments
   */
  private static void handleInitialArgs(int denom[], int targ) throws IllegalArgumentException {
    for (int i = 0; i < denom.length; i++) {
      if (denom[i] <= 0) {
        throw new IllegalArgumentException();
      }
      for (int j = i - 1; j >= 0; j--) {
        if (denom[i] == denom[j]) {
          throw new IllegalArgumentException();
        }
      }
    }
    if (targ < 0) {
      throw new IllegalArgumentException();
    }
  }
  /**
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  Method to execute passed in arguments
   *  @param  String[] args
   *          args[0]  Coin denom
   *          args[1]  Target value
   *  @return int array of processed denom, last value of the array is the targ
   *  @throws IllegalArgumentException for invalid arguments
   */
  private static int [] execute (String[] args) throws IllegalArgumentException {
   if (args.length != 2) {
      throw new IllegalArgumentException();
    }
    String[] inputArray = args[0].split(",");
    int[] executedArgs = new int[inputArray.length+1];
    for (int i = 0; i < inputArray.length; i++) {
      try {
        executedArgs[i] = Integer.parseInt(inputArray[i]);
      }
      catch (NumberFormatException nfe) {
        throw new IllegalArgumentException();
      }
    }
    try {
        int extra = Integer.parseInt(args[1]);
          executedArgs[(executedArgs.length-1)] = extra;
	    }
    catch (NumberFormatException nfe) {
	    throw new IllegalArgumentException();
    }
    return executedArgs;
  }
  /**
   *  @see    http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  @param  String[] args
   *          args[0]  Coin denom
   *          args[1]  Target value
   *  @throws IllegalArgumentException when input arguments are "hinky"
   */

  public static void main( String[] args ) {
    try {
	    int [] value1 = execute( args );
	    int [] value2 = new int[value1.length - 1];
	    int targ = 0;
	    for (int i = 0; i < value1.length-1; i++){
		    value2[i] = value1[i];
  	  }
  	  targ = value1[value1.length-1];
	    Tuple bestChange = new Tuple(value2.length);
      String bestChangeString = "";
      bestChange = makeChangeWithDynamicProgramming(value2, targ);
      bestChangeString= bestChange.toString();
	    System.out.print("Best Change: " + bestChangeString);
      }
      catch (Exception localException) {
        System.out.println("BAD DATA - Impossible Tuple. Please enter arguments in the form of '<Denominations><Target>' \n" +
            "Please only use valid values for the Denominations and Target");
	    System.exit(0);
	  }

  }
}


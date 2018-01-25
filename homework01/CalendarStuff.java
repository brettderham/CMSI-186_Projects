/**
 *  File name     :  .java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Brett Derham
 *  Date          :  2018-01-18
 *  Description   :  This file provides the supporting methods for the CountTheDays program which will
 *                   calculate the number of days between two dates.  It shows the use of modularization
 *                   when writing Java code, and how the Java compiler can "figure things out" on its
 *                   own at "compile time".  It also provides examples of proper documentation, and uses
 *                   the source file header template as specified in the "Greeter.java" template program
 *                   file for use in CMSI 186, Spring 2017.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 *  @version 1.0.2  2018-01-18  Brett Derham  Started Homework Assignment
 *  @version 1.0.3  2018-01-23  Brett Derham  First round of edits made
 */
public class CalendarStuff {

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = SUNDAY    + 2;
   private static final int WEDNESDAY = SUNDAY    + 3;
   private static final int THURSDAY  = SUNDAY    + 4;
   private static final int FRIDAY    = SUNDAY    + 5;
   private static final int SATURDAY  = SUNDAY    + 6;
  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
   private static final int JANUARY    = 0;
   private static final int FEBRUARY   = JANUARY   + 1;
   private static final int MARCH      = JANUARY   + 2;
   private static final int APRIL      = JANUARY   + 3;
   private static final int MAY        = JANUARY   + 4;
   private static final int JUNE       = JANUARY   + 5;
   private static final int JULY       = JANUARY   + 6;
   private static final int AUGUST     = JANUARY   + 7;
   private static final int SEPTEMBER  = JANUARY   + 8;
   private static final int OCTOBER    = JANUARY   + 9;
   private static final int NOVEMBER   = JANUARY   + 10;
   private static final int DECEMBER   = JANUARY   + 11;


  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

  /**
   * The constructor for the class
   */
   public CalendarStuff() {
      System.out.println( "Constructor called..." );  // replace this with the actual code
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   *  Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
     if ( year % 4 == 0 && year % 400 == 0) {
       return true;
     }
     else if ( year % 4 == 0 && year % 100 != 0) {
       return true;
     }
     else {
       return false;
     }
   }
  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an indix, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
     if (month == 1){
       return 31;
     }
     else if ((month == 2) && (isLeapYear( year ) == true)) {
       return 29;
     }
     else if ((month == 2) && (isLeapYear( year ) == false)) {
       return 28;
     }
     else if (month == 3){
       return 31;
     }
     else if (month == 4){
       return 30;
     }
     else if (month == 5){
       return 31;
     }
     else if (month == 6){
       return 30;
     }
     else if (month == 7){
       return 31;
     }
     else if (month == 8){
       return 31;
     }
     else if (month == 9){
       return 30;
     }
     else if (month == 10){
       return 31;
     }
     else if (month == 11){
       return 30;
     }
     else {
       return 31;
     }
   }

  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if (month1 == month2 && day1 == day2 && year1 == year2){
        return true;
      }
      else {
        return false;
      }
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if (month1 == month2 && day1 == day2 && year1 == year2) {
        return 0;
      }
      else if (year1 < year2) {
        return -1;
      }
      else if (year1 == year2 && month1 > month2) {
        return -1;
      }
      else if (year1 == year2 && month1 == month2 && day1 > day2) {
        return -1;
      }
      else if (year1 > year2) {
        return 1;
      }
      else if (year1 == year2 && month1 < month2) {
        return 1;
      }
      else {
        return 1;
      }
   }

  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
   public static boolean isValidDate( long month, long day, long year ) {
      if (month == 1 && day >= 1 && day <= 31){
        return true;
      }
      else if (month == 2 && day >= 1 && day <= 28) {
        return true;
      }
      else if (month ==2 && day >= 1 && day <= 29 && isLeapYear( year ) == true) {
        return true;
      }
      else if (month == 3 && day >= 1 && day <= 31) {
        return true;
      }
      else if (month == 4 && day >= 1 && day <= 30) {
        return true;
      }
      else if (month == 5 && day >= 1 && day <= 31) {
        return true;
      }
      else if (month == 6 && day >= 1 && day <= 30) {
        return true;
      }
      else if (month == 7 && day >= 1 && day <= 31) {
        return true;
      }
      else if (month == 8 && day >= 1 && day <= 31) {
        return true;
      }
      else if (month == 9 && day >= 1 && day <= 30) {
        return true;
      }
      else if (month == 10 && day >= 1 && day <= 31) {
        return true;
      }
      else if (month == 11 && day >= 1 && day <= 30) {
        return true;
      }
      else if (month == 12 && day >= 1 && day <= 31) {
        return true;
      }
      else {
        return false;
      }
   }

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
   public static String toMonthString( int month ) {
      switch( month - 1 ) {
         default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
        default       : throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
  // public static Date(long year, long month, long day){
  //    System.out.println( "This is the " + Date );
  // }
  public static int daysBetween( int month1, int day1, int year1, int month2, int day2, int year2 ) {
  //   Date dateOne = new Date(year1, month1, day1);
  //   Date dateTwo = new Date(year2, month2, day2);
  //   long diff = dateOne.getTime() - dateTwo.getTime();
  // int dayCount = diff/(1000*60*60*24)
     int dayCount = 0;
    return dayCount;
  }
}

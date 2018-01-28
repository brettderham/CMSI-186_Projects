/**
 *  File name     :  .java
 *  Purpose       :  Provides a number of dates between two dates
 *  Author        :  Brett Derham
 *  Date          :  2018-01-18
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2018-01-25  Brett Derham  Initial writing and release
 *  @version 1.0.1  2018-01-28  Brett Derham  Fixed Complie errors
 */
public class CountTheDays {
  public static boolean isLeapYear( long year1 ) {
    if ( year1 % 4 == 0 && year1 % 400 == 0) {
      return true;
    }
    else if ( year1 % 4 == 0 && year1 % 100 != 0) {
      return true;
    }
    else {
      return false;
    }
  }
  public static long daysInMonth( long month1, long year1 ) {
    if (month1 == 1){
      return 31;
    }
    else if ((month1 == 2) && (isLeapYear( year1 ) == true)) {
      return 29;
    }
    else if ((month1 == 2) && (isLeapYear( year1 ) == false)) {
      return 28;
    }
    else if (month1 == 3){
      return 31;
    }
    else if (month1 == 4){
      return 30;
    }
    else if (month1 == 5){
      return 31;
    }
    else if (month1 == 6){
      return 30;
    }
    else if (month1 == 7){
      return 31;
    }
    else if (month1 == 8){
      return 31;
    }
    else if (month1 == 9){
      return 30;
    }
    else if (month1 == 10){
      return 31;
    }
    else if (month1 == 11){
      return 30;
    }
    else {
      return 31;
    }
  }
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
  public static long numberOfDays(long month1, long day1, long year1){
      long numberOfD = 0;
      for (long i=0; i < year1; i++) {
        if (isLeapYear(i) == true) {
          numberOfD = numberOfD + 366;
        }
        else {
          numberOfD = numberOfD + 365;
        }
      }
      for (long i=1; i < month1; i++){
        numberOfD = numberOfD + daysInMonth(i, year1);
      }
        numberOfD = numberOfD + day1;
      return numberOfD;
   }
  public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {


    long dateOne = numberOfDays(month1, day1, year1);
    long dateTwo = numberOfDays(month2, day2, year2);

    if (compareDate(month1, day1, year1, month2, day2, year2) == -1) {
      return dateTwo - dateOne;
    }
    else {
      return dateOne - dateTwo;
    }
  }

}

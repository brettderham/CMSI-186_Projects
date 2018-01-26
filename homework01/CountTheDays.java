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
 */
public class CountTheDays {
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

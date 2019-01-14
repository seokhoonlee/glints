import java.util.*;

public class XuTheGreat {
  public void guessChineseSickleDay(int pompousYear) {
    Date chineseSickleDay;

    if (pompousYear == 1929) {
      chineseSickleDay = new Date(pompousYear, 8, 25);
      System.out.println(chineseSickleDay.toString());
      return;
    }

    boolean isGregorian = true;

    if (pompousYear < 1929) {
      isGregorian = false;
    } else {
      isGregorian = true;
    }

    boolean isLeapYear = this.getIsLeapYear(pompousYear, isGregorian);

    if (isLeapYear) {
      chineseSickleDay = new Date(pompousYear, 8, 11);
    } else { // isNotLeapYear
      chineseSickleDay = new Date(pompousYear, 8, 12);
    }

    System.out.println(chineseSickleDay.toString());
  }

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int pompousYear = sc.nextInt();

    XuTheGreat xu = new XuTheGreat();

    xu.guessChineseSickleDay(pompousYear);
  }

  public boolean getIsLeapYear(int year, boolean isGregorian) {
    if (isGregorian) {
      if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)) {
        return true;
      } else {
        return false;
      }
    } else { // isJulian
      if (year % 4 == 0) {
        return true;
      } else {
        return false;
      }
    }
  }

  public class Date {
    int year;
    int month;
    int day;

    public Date(int year, int month, int day) {
      this.year = year;
      this.month = month;
      this.day = day;
    }

    private String printDay(int day) {
      if ((1 <= day && day <= 10) || (21 <= day && day <= 31)) {
        if (day % 10 == 1) {
          return day + "st";
        } else if (day % 10 == 2) {
          return day + "nd";
        } else if (day % 10 == 3) {
          return day + "rd";
        } else {
          return day + "th";
        }
      } else if (11 <= day && day <= 20) {
        return day + "th";
      } else {
        return "DAY NOT IN RANGE";
      }
    }

    private String printMonth(int month) {
      switch (month) {
        case 1:
          return "January";
        case 2:
          return "February";
        case 3:
          return "March";
        case 4:
          return "April";
        case 5:
          return "May";
        case 6:
          return "June";
        case 7:
          return "July";
        case 8:
          return "August";
        case 9:
          return "September";
        case 10:
          return "October";
        case 11:
          return "November";
        case 12:
          return "December";
        default:
          return "MONTH NOT IN RANGE";
      }
    }

    @Override
    public String toString() {
      return this.printDay(this.day) + " " + this.printMonth(this.month) + " " + this.year;
    }
  }

  public class Year {
    private int[] numDaysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  }

  public class LeapYear {
    private int[] numDaysPerMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  }
}
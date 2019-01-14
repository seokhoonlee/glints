import java.util.*;

public class XuTheGreat {
  int CHANGE_YEAR = 1929; // year that changed from Julian to Gregorian
  int CHINESE_SICKLE_DAY_NUMBER = 244; // can be changed but it will break if more than 365 - 15 (for change year)

  public class Date {
    int year;
    int month;
    int day;

    public Date(int year, int month, int day) {
      this.year = year;
      this.month = month;
      this.day = day;

      if (month > 12 || month < 1) {
        System.out.println("MONTH OUT OF RANGE");
      }

      if (day > 31 || day < 1) {
        System.out.println("DAY OUT OF RANGE");
      }
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
        System.out.println("DAY OUT OF RANGE");
        return "DAY OUT OF RANGE";
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
          System.out.println("MONTH OUT OF RANGE");
          return "MONTH OUT OF RANGE";
      }
    }

    @Override
    public String toString() {
      return this.printDay(this.day) + " " + this.printMonth(this.month) + " " + this.year;
    }
  }

  public interface DayCalculator {
    public int getDaysInMonth(int month);
  }

  public class NormalYearDayCalculator implements DayCalculator {
    private int[] numDaysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int getDaysInMonth(int month) {
      return numDaysPerMonth[month - 1];
    }
  }

  public class LeapYearDayCalculator implements DayCalculator {
    private int[] numDaysPerMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int getDaysInMonth(int month) {
      return numDaysPerMonth[month - 1];
    }
  }

  public class ChangeYearDayCalculator implements DayCalculator {
    private int[] numDaysPerMonth = {31, 15, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int getDaysInMonth(int month) {
      return numDaysPerMonth[month - 1];
    }
  }

  public void guessChineseSickleDay(int pompousYear) {
    Date chineseSickleDay = this.getChineseSickleDay(pompousYear);

    System.out.println(chineseSickleDay.toString());
  }

  public Date getChineseSickleDay(int year) {
    DayCalculator calculator = this.getDayCalculator(year);

    int remainingDays = this.CHINESE_SICKLE_DAY_NUMBER;

    int month = 1;

    while (calculator.getDaysInMonth(month) < remainingDays) {
      remainingDays -= calculator.getDaysInMonth(month);
      month++;
    }

    int day = remainingDays;

    return new Date(year, month, day);
  }

  public DayCalculator getDayCalculator(int year) {
    if (year == CHANGE_YEAR) {
      return new ChangeYearDayCalculator();
    }

    boolean isGregorian = true;

    if (year < CHANGE_YEAR) {
      isGregorian = false;
    } else {
      isGregorian = true;
    }

    boolean isLeapYear = this.getIsLeapYear(year, isGregorian);

    if (isLeapYear) {
      return new LeapYearDayCalculator();
    } else { // isNotLeapYear
      return new NormalYearDayCalculator();
    }
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

  public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);

    int pompousYear = sc.nextInt();

    XuTheGreat xu = new XuTheGreat();

    xu.guessChineseSickleDay(pompousYear);
  }
}
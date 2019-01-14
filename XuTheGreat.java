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

    @Override
    public String toString() {
      return day + " " + month + " " + year;
    }
  }

  public class Year {
    private int[] numDaysPerMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  }

  public class LeapYear {
    private int[] numDaysPerMonth = {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
  }
}
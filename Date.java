/**
 *   The Date class here is used to show how to restrict the creation of
 *   dates to admissible dates. To this end a method admissible which 
 *   checks whether inputs of the constructor form a valid date is written. 
 *   If not on constuction an IllegalArgumentException is thrown. 
 *   
 * @author Haojie Chen
 * @version 2018-12-05
 */
public class Date {

    /**
     * Three field variables for day, month, and year of types int,
     * String and int, respectively.
     */
    private int day;
    private String month;
    private int year;

    /* A static constant to list the 12 months of the year. */
    public static final String[] MONTHS = 
    {"January", "February", "March", "April", "May", "June",
     "July", "August", "September", "October", "November", "December"};

    /* A static constant to list the lengths of the 12 months of the year. */
    public static final int[] MONTH_LENGTHS =
    {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    /**
     *  @param year The input of a year such as 2016 as an int.
     *  @return true if the int is a year greater than 0.
     */
    public static boolean admissibleYear(int year) {
        return year > 0;
    }

    /**
     *  @param month The input of a month such as "October" as a String.
     *  @return true if the String represents one of the 12 months.
     */
    public static boolean admissibleMonth(String month) {
        for (String m : MONTHS) {
            if (month.equals(m)) {
                return true;
            }
        }
        return false;
    }

    /**
     *  @param year The input of a year such as 2016 as an int.
     *  @return true if and only if the year is a leap year.
     */
    public static boolean leapYear(int year) {
        /* every year divisible by 400 is a leap year */
        if (year%400 == 0) {
            return true;
            /* every year divisible by 100 (but not by 400) is not a
             * leap year 
             */
        } else if (year%100 == 0) {
            return false;
            /* every year divisible by 4 (not covered by the cases above) is a
             * leap year 
             */
        } else if (year%4 == 0) {
            return true;
            /* every other year is not a leap year */
        } else {
            return false;
        }
    }
			
    /**
     *  @param month The input of a month such as "October" as a String.
     *  @param year The input of a year such as 2016 as an int.
     *  @return The number of days in the month, e.g. 31 for "October".
     *  For February (MONTHS[1]) of leap years it is 29, else the value
     *  is looked up in the MONTH_LENGTHS array.
     */
    public static int maxNumberOfDaysPerMonth(String month, int year) {
        if (month.equals(MONTHS[1]) && leapYear(year)) {
            return 29;
        }
        for (int i = 0; i < 12; i++) {
            if (month.equals(MONTHS[i])) {
                return MONTH_LENGTHS[i];
            }
        }
        return 0;
    }

    /**
     *  @param day The input of a day such as 21 as an int.
     *  @param month The input of a month such as "October" as a String.
     *  @param year The input of a year such as 2016 as an int.
     *  @return true if the day is between 1 and the maximal number of
     *  days of the months in that year.
     *  
     */
    public static boolean admissibleDay(int day, String month, int year) {
        return 1 <= day && day <= maxNumberOfDaysPerMonth(month,year);
    }

    
    /**
     *  @param day The input of a day such as 21 as an int.
     *  @param month The input of a month such as "October" as a String.
     *  @param year The input of a year such as 2016 as an int.
     *  @return true if the day, the month, and the year are all admissible
     */
    public static boolean admissible(int day, String month, int year) {
        return admissibleYear(year) && admissibleMonth(month)
            && admissibleDay(day, month, year);
    }

    public String toString() {
        return this.day + " " + this.month + " " + this.year;
    }

    /**
     *  @param day The input of a day such as 21 as an int.
     *  @param month The input of a month such as "October" as a String.
     *  @param year The input of a year such as 2016 as an int.
     *  Note that the constructor throws an IllegalArgumentException if
     *  the date to be constructed would be not admissible.
     *  @exception IllegalArgumentException if the date to be
     *  constructed is not admissible.
     */
    public Date(int day, String month, int year) {

        if (admissible(day, month, year)) {
            this.day = day;
            this.month = month;
            this.year = year;
        } else {
            throw new
                IllegalArgumentException("Invalid date in class Date.");
        }
    }

    /**
     *  @param day The new day in a month such as 21 as an int.
     *  @exception IllegalArgumentException if the day to be
     *  set would lead to a non-admissible date.
     */
    public void setDay(int day){
        if (admissible(day, month, year)) {
            this.day = day;
        } else {
            throw new
                IllegalArgumentException("Invalid day in setDay.");
        }
    }
        
    /**
     *  @param month The new month.
     *  @exception IllegalArgumentException if the month to be
     *  set would lead to a non-admissible date.
     */
    public void setMonth(String month){
        if (admissible(day, month, year)) {
            this.month = month;
        } else {
            throw new
                IllegalArgumentException("Invalid month in setMonth.");
        }
    }
        
    /**
     *  @param year The new year.
     *  @exception IllegalArgumentException if the year to be
     *  set would lead to a non-admissible date.
     */
    public void setYear(int year){
        if (admissible(day, month, year)) {
            this.year = year;
        } else {
            throw new
                IllegalArgumentException("Invalid year in setYear.");
        }
    }

    public int getYear(){
        return year;
    }

    /*
     *  Main method for some initial tests.
     */
    public static void main(String[] args) {
        try{
            Date d1 = new Date(20, "October", 2017);
            System.out.println(d1);
            Date d2 = new Date(32, "October", 2017);
            System.out.println(d2);
            Date d3 = new Date(20, "Friday", 2017);
            System.out.println(d3);
        }
        catch (IllegalArgumentException e) {
            System.out.println("You have tried to create an invalid date.");
        }
    }
}

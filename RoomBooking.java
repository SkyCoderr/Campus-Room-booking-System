import java.util.ArrayList;

/**
 * This class is used to manage all the DateForCertainRooms objects in its array list.
 * It can check if a room on a certain day, at a certain time has been booked, if it
 * hasn't it can book them, if it has, it can cancel them. Finally, it has a displayDate()
 * method that can show all bookings for a date in the form of a table.
 *
 * @author  Haojie Chen
 * @version 2018-12-05
 */

public class RoomBooking {

    private int year;
    private String[] rooms;
    private ArrayList<DateForCertainRooms> datesForRooms;

    /**
     * Constructor of the class.
     * @param year Type int, passes year to its field variable.
     * @param rooms Type String[], passes rooms to its field variable.
     */
    public RoomBooking(int year, String[] rooms){
        this.year = year;
        this.rooms = rooms;
        datesForRooms = new ArrayList<DateForCertainRooms>(0);
    }

    /**
     * Getter for field variable year.
     * @return Type int, the year of the class.
     */
    public int getYear(){
        return year;
    }

    /**
     * Getter for field variable rooms.
     * @return Type String[], the rooms for this class.
     */
    public String[] getRooms(){
        return rooms;
    }

    /**
     * Getter for field variable datesForRooms.
     * @return Type ArrayList<DateForCertainRooms>, the DateForCertainRooms objects to be managed.
     */
    public ArrayList<DateForCertainRooms> getDatesForRooms(){
        return datesForRooms;
    }

    /**
     * This method is used to check if a date entered has already generated a corresponding
     * DateForCertainRooms object in its array list. If it is, then the method returns true,
     * if not, returns false.
     * @param date Type Date, the date to be checked.
     * @return Type boolean, it the date has been generated, return true, false otherwise.
     */
    public boolean dateGenerated(Date date){
        for (DateForCertainRooms el: datesForRooms){
            if (el.getDate() == date)
                return true;
        }
        return false;
    }

    /**
     * This method is used to get the index of the object DateForCertainRooms in the array
     * list with the argument date. It checks all elements and see which one has the same
     * date and return its index.
     * @param date Type date, the date one wants to check.
     * @return Type int, the index of the corresponding DateForCertainRooms object.
     */
    public int indexOfDateInArray(Date date){
        for (int i = 0; i < datesForRooms.size(); i++){
            if (datesForRooms.get(i).getDate() == date)
                return i;
        }
        return -1;
    }

    /**
     * This method is used to make a booking, and it returns true if the booking is
     * successful, false otherwise.
     * @param room Type String, the room one wants to book.
     * @param date Type Date, the date one wants to book.
     * @param hour Type int, the hour one wants to book.
     * @param purpose Type String, the purpose one has for booking.
     * @return Type boolean, true if the booking is successful, false otherwise.
     */
    public boolean book(String room, Date date, int hour, String purpose){
        DateForCertainRooms dateForRooms;
        if (date.getYear() == getYear()){
            if (dateGenerated(date)){
                dateForRooms = datesForRooms.get(indexOfDateInArray(date));
            } else {
                dateForRooms = new DateForCertainRooms(date, getRooms());
                datesForRooms.add(dateForRooms);
            }
            if (dateForRooms.available(room, hour)){
                dateForRooms.book(room, hour, purpose);
                return true;
            } else{
                return false;
            }
        } else{
            System.out.println("Sorry, the year you entered is different from that of the instance.");
            return false;
        }
    }

    /**
     * This method is used to cancel a booking, if there is one. If there isn't, it does nothing.
     * @param room Type String, the room to be canceled.
     * @param date Type date, the date to cancel the booking.
     * @param hour Type int, the time to be canceled.
     */
    public void cancel(String room, Date date, int hour){
        if (date.getYear() == getYear()){
            if (dateGenerated(date)){
                datesForRooms.get(indexOfDateInArray(date)).cancel(room, hour);
            } else {
                System.out.println("Sorry, there's no booking there.");
            }
        } else
            System.out.println("Sorry, the year you entered is different from that of the instance.");
    }

    /**
     * This method only serves the method displayDay() within the class, therefore it's private, it is
     * used to generate a line in the table, so every time a line is needed, this method can be called.
     * @return Type String, a line.
     */
    private String generateLine(){
        String t = "------+";
        for (int i = 0; i < getRooms().length; i ++){
            t = t + "--------------------+";
        }
        t = String.format("%s%n", t);
        return t;
    }

    /**
     * This method only serves the method displayDay() within the class, therefore it's private. it is
     * used to generate a record in the table. A record is the rooms' booking status at a certain time.
     * @param dateForRooms Type DateForCertainRooms
     * @param hour Type int
     * @return Type String, a record.
     */
    private String generateRecord(DateForCertainRooms dateForRooms, int hour){
        String t = String.format("%3s%s%s", hour, ":00", "|");
        for (int i = 0; i < getRooms().length; i ++){
            if (dateForRooms.getPurposes()[i][hour - 9] == null)
                t = t + String.format("%20s%s", "", "|");
            else
                t = t + String.format("%15s%5s%s", dateForRooms.getPurposes()[i][hour - 9], "", "|");
        }
        t = String.format("%s%n", t);
        return t;
    }

    /**
     * This method is used to generate a whole table for the booking status on a certain day.
     * @param date Type date, the date to be displayed.
     * @return
     */
    public String displayDay(Date date) {
        DateForCertainRooms dateForRooms;
        String str = String.format("%50s%n%s", date.toString(), "      |");

        for (int i = 0; i < rooms.length; i++) {
            str = String.format("%s%-20s%s", str, String.format("%5s%s", " ", rooms[i]), "|");
        }
        str = String.format("%s%n", str) + generateLine();

        if (dateGenerated(date)) {
            dateForRooms = datesForRooms.get(indexOfDateInArray(date));
        } else {
            dateForRooms = new DateForCertainRooms(date, getRooms());
            datesForRooms.add(dateForRooms);
        }

        for (int i = 9; i < 18; i++) {
            str = str + generateRecord(dateForRooms, i);
            str = str + generateLine();
        }
        return str;
    }

}

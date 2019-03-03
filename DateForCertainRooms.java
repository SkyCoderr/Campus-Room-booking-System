import java.util.Arrays;

/**
 * For each class Date, certain information has to be stored in a way so that it shows the
 * rooms and the booking status for each room on a certain day. Class Date does not do that
 * because it only has three field variables day, month and year. So that is what this class
 * is for.
 *
 * This class has field variables date of type Date, rooms type String[] and purposes of type
 * String[][]. The first one is used relative to Date, the second one relative to the rooms
 * and the third one relative to the purpose of each booking.
 *
 * @author Haojie Chen
 * @version 2018-12-05
 */

public class DateForCertainRooms {

    private String[] rooms;
    private String[][] purposes;
    private Date date;

    /**
     * Constructor of the class, with date and rooms passed to field variable, it generates
     * a new String[][] for field variable purposes.
     * @param date Type Date, passed to its field variable.
     * @param rooms Type String[], passed to its field variable.
     */
    public DateForCertainRooms(Date date, String[] rooms){
        this.date = date;
        this.rooms = rooms;
        purposes = new String[getRooms().length][9];
    }

    /**
     * Getter for field variable rooms.
     * @return Type String[], the rooms relevant this Date.
     */
    public String[] getRooms(){
        return rooms;
    }

    /**
     * Getter for field variable purposes.
     * @return Type String[][], the purposes of the bookings.
     */
    public String[][] getPurposes(){
        return purposes;
    }

    /**
     * Getter for field variable Date.
     * @return The date of the class.
     */
    public Date getDate(){
        return date;
    }

    /**
     * If an hour has been entered, evaluate if the hour is admissible, that is if it's between
     * 9 to 17.
     * @param hour Type int, the hour to be evaluated.
     * @return Type boolean, true if the hour is admissible, false otherwise.
     */
    private boolean admissibleHour(int hour){
        if (hour >= 9 && hour <=17){
            return true;
        }
        else
            return false;
    }

    /**
     * If a room has been entered, evaluate if the room is admissible, that is if it's in the
     * String[] rooms.
     * @param room Type String, the room to be evaluated.
     * @return Type boolean, true if the room is admissible, false otherwise.
     */
    private boolean admissibleRoom(String room){
        for (String el: rooms){
            if (room.equals(el)){
                return true;
            }
        }
        return false;
    }

    /**
     * Check if on this day, a certain room at a certain time is available. If a certain room
     * at a certain time is empty in String[][] purposes, it means it hasn't been booked before
     * and so is available, if it's not empty at that index then it's been booked.
     * @param room Type String, the room that one wants to book.
     * @param hour Type int, the time that one wants the book.
     * @return Type boolean, true if it's available, false otherwise.
     */
    public boolean available(String room, int hour){
        if (admissibleRoom(room) && admissibleHour(hour)){
            if (purposes[Arrays.binarySearch(rooms, room)][hour - 9] == null)
                return true;
            else
                return false;
        } else
            System.out.println("Sorry, no such room or not in working time.");
        return false;
    }

    /**
     * This method is used to make a book action on a certain date. It checks if
     * a room at a certain time is available, and books it with the purpose on
     * its corresponding index being written.
     * @param room Type String, the room one wants to book.
     * @param hour Type int, the time one wants to book.
     * @param purpose Type String, the purpose one has for booking.
     */
    public void book(String room, int hour, String purpose){
        if (admissibleRoom(room) && admissibleHour(hour)){
            if (available(room, hour))
                purposes[Arrays.binarySearch(rooms, room)][hour - 9] = purpose;
            else
                System.out.println("Sorry, the room for that time has already been booked.");
        } else
            System.out.println("Sorry, no such room or not in working time.");
    }

    /**
     * This method is used to cancel a booking on a certain date. It checks if a
     * room at a certain time has been booked, if it has, it sets the purpose at
     * the corresponding index null again to make it unbooked.
     * @param room Type String, the room one wants to cancel.
     * @param hour Type int, the time one wants to cancel.
     */
    public void cancel(String room, int hour){
        if (admissibleRoom(room) && admissibleHour(hour)){
            if (available(room, hour))
                System.out.println("Sorry, there's no booking there.");
            else
                purposes[Arrays.binarySearch(rooms, room)][hour - 9] = null;
        } else
            System.out.println("Sorry, no such room or not in working time.");
    }
}

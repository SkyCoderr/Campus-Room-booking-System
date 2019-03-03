import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * This is a test class that tests two classes for exercise three: RoomBooking and DateForCertainRooms. It tests
 * every method in these two classes.
 *
 * @author Haojie Chen
 * @version 2018-12-05
 */

public class MyTest {

    String[] rooms = new String[] {"101", "102", "103", "104"};

    Date dec05 = new Date(5, "December", 2018);
    Date dec04 = new Date(4, "December", 2018);
    Date nov15 = new Date(15, "November", 2018);
    Date feb17 = new Date(17, "February", 2017);

    DateForCertainRooms dec05ForRooms = new DateForCertainRooms(dec05, rooms);
    DateForCertainRooms dec04ForRooms = new DateForCertainRooms(dec04, rooms);
    DateForCertainRooms nov15ForRooms = new DateForCertainRooms(nov15, rooms);
    DateForCertainRooms feb17ForRooms = new DateForCertainRooms(feb17, rooms);

    RoomBooking bookings2018 = new RoomBooking(2018, rooms);
    RoomBooking bookings2017 = new RoomBooking(2017, rooms);

    // It tests the getter for variable rooms in class DateForCertainRooms.
    @Test
    public void test1(){
        String[] expected = rooms;
        String[] actual = dec05ForRooms.getRooms();
        assertTrue(Arrays.equals(expected, actual));
    }

    // It tests the getter for variable purposes in class DateForCertainRooms.
    @Test
    public void test2(){
        // Tests whether a new instance of DateForCertainRooms has an empty array purposes when instantiated.
        String[][] expected = new String[4][9];
        String[][] actual = dec05ForRooms.getPurposes();
        assertEquals(expected, actual);
    }

    // It tests the getter for variable date in class DateForCertainRooms.
    @Test
    public void test3(){
        Date expected = dec04;
        Date actual = dec04ForRooms.getDate();
        assertEquals(expected, actual);
    }

    // It tests the method available() in class DateForCertainRooms.
    @Test
    public void test4(){
        assertTrue(dec04ForRooms.available("101", 15));

        dec04ForRooms.book("100", 15, "Study");
        assertTrue(dec04ForRooms.available("101", 15));

        dec04ForRooms.book("100", 6, "Study");
        assertTrue(dec04ForRooms.available("101", 15));

        dec04ForRooms.book("101", 15, "Study");
        assertFalse(dec04ForRooms.available("101", 15));
    }

    // It tests the method book() in class DateForCertainRooms.
    @Test
    public void test5(){
        // Make the expected array same as that of the instance, and test if return true.
        String[][] expected = new String[4][9];
        expected[1][1] = "Study";
        dec04ForRooms.book("102", 10, "Study");
        String[][] actual = dec04ForRooms.getPurposes();
        assertEquals(expected, actual);

        // Make the expected array different from that of the instance, and test if return false.
        expected[1][3] = "Sing";
        dec04ForRooms.book("102", 17, "Study");
        assertFalse(expected.equals(actual));
    }

    // It tests the method cancel() in class DateForCertainRooms.
    @Test
    public void test6(){
        nov15ForRooms.book("103", 16, "Group Meeting");
        String[][] expected = new String[4][9];
        expected[2][7] = "Group Meeting";
        String[][] actual = nov15ForRooms.getPurposes();
        assertEquals(expected, actual);

        nov15ForRooms.cancel("103", 16);
        actual = nov15ForRooms.getPurposes();
        assertFalse(Arrays.equals(expected, actual));

        expected[2][7] = null;
        assertEquals(expected, actual);
    }

    // It tests the getter for variable year in class RoomBooking.
    @Test
    public void test7(){
        int expected = 2018;
        int actual = bookings2018.getYear();
        assertEquals(expected, actual);

        expected = 2017;
        assertFalse( expected == actual );
    }

    // It tests the getter for variable rooms in class RoomBooking.
    @Test
    public void test8(){
        String[] expected = rooms;
        String[] actual = bookings2018.getRooms();
        assertTrue(Arrays.equals(expected, actual));

        expected = new String[4];
        expected[0] = "";
        assertFalse(Arrays.equals(expected, actual));
    }

    // It tests the getter for variable datesForRooms in class RoomBooking.
    @Test
    public void test9(){
        ArrayList<DateForCertainRooms> expected = new ArrayList<DateForCertainRooms>(0);
        ArrayList<DateForCertainRooms> actual = bookings2018.getDatesForRooms();
        assertEquals(expected, actual);

        expected.add(dec05ForRooms);
        bookings2018.book("101", dec05, 9, "Study");
        actual = bookings2018.getDatesForRooms();
        assertTrue(expected.size() == actual.size());
    }

    // It tests the method dateGenerated() in class RoomBooking.
    @Test
    public void test10(){
        assertFalse(bookings2018.dateGenerated(dec05));

        bookings2018.book("101", dec05, 9, "Study");
        assertTrue(bookings2018.dateGenerated(dec05));

        // Although the booking is unsuccessful because the hour is 8, but the day has been generated.
        bookings2018.book("101", dec04, 8, "Study");
        assertTrue(bookings2018.dateGenerated(dec04));
    }

    // It tests the method indexOfDateInArray() method in class RoomBooking.
    @Test
    public void test11(){
        bookings2018.getDatesForRooms().add(dec05ForRooms);
        bookings2018.getDatesForRooms().add(dec04ForRooms);
        bookings2018.getDatesForRooms().add(nov15ForRooms);
        bookings2018.getDatesForRooms().add(feb17ForRooms);

        int expected = 0;
        int actual = bookings2018.indexOfDateInArray(dec05);
        assertEquals(expected, actual);

        expected = 3;
        actual = bookings2018.indexOfDateInArray(feb17);
        assertEquals(expected, actual);

        expected = 2;
        actual = bookings2018.indexOfDateInArray(nov15);
        assertEquals(expected, actual);
    }

    // It tests the method book() in class RoomBooking.
    @Test
    public void test12(){
        // for Dec 05, there isn't any booking, so the booking is successful.
        assertTrue(bookings2018.book("101", dec05, 9, "Study"));

        // a booking for the same time, at the same room has been made, unsuccessful.
        assertFalse(bookings2018.book("101", dec05, 9, "Study"));

        // the hour is 8, so the booking is unsuccessful.
        assertFalse(bookings2018.book("101", dec04, 8, "Study"));

        // the room is not in the array rooms, so the booking is unsuccessful.
        assertFalse(bookings2018.book("111", dec05, 9, "Study"));

        // the year is for Date feb17 is in 2017, which is not the year for instance bookings2018,
        // booking unsuccessful.
        assertFalse(bookings2018.book("101", feb17, 9,"Study"));

        // the instance bookings2017 is in year 2017, so it can take the date feb17.
        assertTrue(bookings2017.book("101", feb17, 9,"Study"));
    }

    // It tests the method cancel() in class RoomBooking.
    @Test
    public void test13(){
        // no bookings made yet, successful.
        assertTrue(bookings2018.book("101", dec05, 9, "Study"));
        // the booking for the time and the room has been made, unsuccessful.
        assertFalse(bookings2018.book("101", dec05, 9, "Study"));
        // cancel the booking and see if it's available again.
        bookings2018.cancel("101", dec05, 9);
        assertTrue(bookings2018.book("101", dec05, 9, "Study"));
    }

    // It displays a timetable for the booking status for a certain day.
    @Test
    public void test14(){
        bookings2018.book("101", dec05, 13, "Study");
        bookings2018.book("102", dec05, 10, "Meeting");
        bookings2018.book("103", dec05, 15, "Lab");
        bookings2018.book("104", dec05, 11, "Society");
        bookings2018.book("102", dec05, 14, "Test");
        System.out.println(bookings2018.displayDay(dec05));
    }

}

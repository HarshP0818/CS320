import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ContactTest {

    private static final String ID = "C123456789";
    private static final String FIRST = "John";
    private static final String LAST = "Doe";
    private static final String PHONE = "1234567890";
    private static final String ADDRESS = "123 Main Street";

    @Test
    public void testConstructor_withValidInput_objectCreatedSuccessfully() {
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        assertEquals(ID, contact.getContactId());
        assertEquals(FIRST, contact.getFirstName());
        assertEquals(LAST, contact.getLastName());
        assertEquals(PHONE, contact.getPhone());
        assertEquals(ADDRESS, contact.getAddress());
    }

    @Test
    public void testEquals_withIdenticalFields_returnsTrue() {
        Contact contact1 = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        Contact contact2 = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        assertEquals(contact1, contact2);
    }

    @Test
    public void testSetFirstName_withValidInput_setsValue() {
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        contact.setFirstName("Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    @Test
    public void testSetLastName_withValidInput_setsValue() {
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        contact.setLastName("Smith");
        assertEquals("Smith", contact.getLastName());
    }

    @Test
    public void testSetPhone_withValidInput_setsValue() {
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        contact.setPhone("0987654321");
        assertEquals("0987654321", contact.getPhone());
    }

    @Test
    public void testSetAddress_withValidInput_setsValue() {
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        contact.setAddress("456 Elm Street");
        assertEquals("456 Elm Street", contact.getAddress());
    }

    @Test
    public void testConstructor_withNullValues_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> new Contact(null, FIRST, LAST, PHONE, ADDRESS));
        assertThrows(IllegalArgumentException.class, () -> new Contact(ID, null, LAST, PHONE, ADDRESS));
        assertThrows(IllegalArgumentException.class, () -> new Contact(ID, FIRST, null, PHONE, ADDRESS));
        assertThrows(IllegalArgumentException.class, () -> new Contact(ID, FIRST, LAST, null, ADDRESS));
        assertThrows(IllegalArgumentException.class, () -> new Contact(ID, FIRST, LAST, PHONE, null));
    }

    @Test
    public void testConstructor_withTooLongValues_throwsException() {
        String longId = "12345678901"; // 11 chars
        String longName = "ThisIsTooLong";
        String longPhone = "12345678901";
        String longAddress = "1234567890123456789012345678901"; // 31 chars

        assertThrows(IllegalArgumentException.class, () -> new Contact(longId, FIRST, LAST, PHONE, ADDRESS));
        assertThrows(IllegalArgumentException.class, () -> new Contact(ID, longName, LAST, PHONE, ADDRESS));
        assertThrows(IllegalArgumentException.class, () -> new Contact(ID, FIRST, longName, PHONE, ADDRESS));
        assertThrows(IllegalArgumentException.class, () -> new Contact(ID, FIRST, LAST, longPhone, ADDRESS));
        assertThrows(IllegalArgumentException.class, () -> new Contact(ID, FIRST, LAST, PHONE, longAddress));
    }

    @Test
    public void testSetters_withInvalidValues_throwsException() {
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);

        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setFirstName("TooLongName"));

        assertThrows(IllegalArgumentException.class, () -> contact.setLastName(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setLastName("TooLongLast"));

        assertThrows(IllegalArgumentException.class, () -> contact.setPhone(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("12345")); // too short
        assertThrows(IllegalArgumentException.class, () -> contact.setPhone("abcdefghij")); // not digits

        assertThrows(IllegalArgumentException.class, () -> contact.setAddress(null));
        assertThrows(IllegalArgumentException.class, () -> contact.setAddress("1234567890123456789012345678901")); // 31 chars
    }
}

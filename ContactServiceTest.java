import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class ContactServiceTest {

    private ContactService service;

    // Constants for reuse
    private static final String ID = "C001";
    private static final String FIRST = "Alice";
    private static final String LAST = "Smith";
    private static final String PHONE = "1234567890";
    private static final String ADDRESS = "123 Main St";

    private static final String NEW_FIRST = "Bob";
    private static final String NEW_LAST = "Johnson";
    private static final String NEW_PHONE = "0987654321";
    private static final String NEW_ADDRESS = "456 Elm St";

    @BeforeEach
    public void setup() {
        service = new ContactService();
    }

    @Test
    public void testAddContact_withUniqueId_contactIsAdded() {
        // Arrange
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);

        // Act
        service.addContact(contact);

        // Assert
        assertEquals(contact, service.getContact(ID));
    }

    @Test
    public void testAddContact_withDuplicateId_throwsException() {
        // Arrange
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        service.addContact(contact);

        // Act & Assert
        assertThrows(IllegalArgumentException.class, () -> service.addContact(contact));
    }

    @Test
    public void testDeleteContact_withValidId_contactIsRemoved() {
        // Arrange
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        service.addContact(contact);

        // Act
        service.deleteContact(ID);

        // Assert
        assertNull(service.getContact(ID));
    }

    @Test
    public void testDeleteContact_withInvalidId_throwsException() {
        assertThrows(NoSuchElementException.class, () -> service.deleteContact("INVALID"));
    }

    @Test
    public void testUpdateContact_withValidId_contactIsUpdated() {
        // Arrange
        Contact contact = new Contact(ID, FIRST, LAST, PHONE, ADDRESS);
        service.addContact(contact);

        // Act
        service.updateContact(ID, NEW_FIRST, NEW_LAST, NEW_PHONE, NEW_ADDRESS);
        Contact updated = service.getContact(ID);

        // Assert
        Contact expected = new Contact(ID, NEW_FIRST, NEW_LAST, NEW_PHONE, NEW_ADDRESS);
        assertEquals(expected, updated);
    }

    @Test
    public void testUpdateContact_withInvalidId_throwsException() {
        assertThrows(NoSuchElementException.class, () -> service.updateContact("INVALID", NEW_FIRST, NEW_LAST, NEW_PHONE, NEW_ADDRESS));
    }

    @Test
    public void testGetContact_withNonExistentId_returnsNull() {
        assertNull(service.getContact("DOES_NOT_EXIST"));
    }
}

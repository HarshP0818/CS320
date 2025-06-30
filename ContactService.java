package Contact;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class ContactService {
    private final Map<String, Contact> contacts = new HashMap<>();

    public void addContact(Contact contact) {
        if (contacts.containsKey(contact.getContactId())) {
            throw new IllegalArgumentException("Duplicate contact ID");
        }
        contacts.put(contact.getContactId(), contact);
    }

    public void deleteContact(String contactId) {
        if (!contacts.containsKey(contactId)) {
            throw new NoSuchElementException("Contact not found");
        }
        contacts.remove(contactId);
    }

    public void updateContact(String contactId, String firstName, String lastName, String phone, String address) {
        if (!contacts.containsKey(contactId)) {
            throw new NoSuchElementException("Contact not found");
        }
        Contact contact = contacts.get(contactId);
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhone(phone);
        contact.setAddress(address);
    }

    public Contact getContact(String contactId) {
        Contact original = contacts.get(contactId);
        if (original == null) return null;
        return new Contact(
                original.getContactId(),
                original.getFirstName(),
                original.getLastName(),
                original.getPhone(),
                original.getAddress()
        );
    }
}

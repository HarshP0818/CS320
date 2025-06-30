package Contact;

import java.util.Objects;

public class Contact {
    private final String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        if (contactId == null || contactId.length() > 10) throw new IllegalArgumentException("Invalid ID");
        this.contactId = contactId;
        setFirstName(firstName);
        setLastName(lastName);
        setPhone(phone);
        setAddress(address);
    }

    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.length() > 10) throw new IllegalArgumentException("Invalid first name");
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.length() > 10) throw new IllegalArgumentException("Invalid last name");
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (phone == null || phone.length() != 10 || !phone.matches("\\d{10}"))
            throw new IllegalArgumentException("Invalid phone number");
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.length() > 30) throw new IllegalArgumentException("Invalid address");
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Contact)) return false;
        Contact contact = (Contact) o;
        return contactId.equals(contact.contactId) &&
                firstName.equals(contact.firstName) &&
                lastName.equals(contact.lastName) &&
                phone.equals(contact.phone) &&
                address.equals(contact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, firstName, lastName, phone, address);
    }
}

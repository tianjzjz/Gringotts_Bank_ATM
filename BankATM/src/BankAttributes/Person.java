package BankAttributes;

/**
 * This class has the attributes of a person.
 */
public class Person {
    protected String userName;

    public Person() {}

    public Person(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }
}

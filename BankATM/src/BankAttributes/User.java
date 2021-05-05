package BankAttributes;

/**
 * This class stands for a user (i.e. the bank manager or a customer).
 */
public class User extends Person{

    protected String password;

    public User(String userName, String password) {
        super(userName);

        this.password = password;
    }

    public String getPassword() {
        return password;
    }
}

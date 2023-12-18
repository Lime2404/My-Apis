package Temporary;

public class TemporaryPogoRegistration {
    private String email;
    private String password;

    public TemporaryPogoRegistration(String email, String password) {
        this.email = email;
        this.password = password;
    }
    TemporaryPogoRegistration(){}

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

package memoryTests;

public class Creds {
    private String email;
    private String password;

    public Creds(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}

package Temporary;

public class TemporaryPogoSRegistration {
    private String token;
    private Integer id;

    public TemporaryPogoSRegistration(String token, Integer id) {
        this.token = token;
        this.id = id;
    }

    public TemporaryPogoSRegistration(){}

    public String getToken() {
        return token;
    }

    public Integer getId() {
        return id;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}

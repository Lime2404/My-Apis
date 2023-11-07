package SimpleApis;

public class TemporaryPogoUSRegistration {
    private String error;

    public TemporaryPogoUSRegistration(String error) {
        this.error = error;
    }
    TemporaryPogoUSRegistration(){}

    public String getError() {
        return error;
    }
}

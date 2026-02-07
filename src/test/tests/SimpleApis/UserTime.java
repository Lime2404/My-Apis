package SimpleApis;

public class UserTime {
    private String first_name;
    private String last_name;

    public UserTime(String first_name, String last_name) {
        this.first_name = first_name;
        this.last_name = last_name;
    }
    public UserTime(){}

    public String getName() {
        return first_name;
    }

    public String getJob() {
        return last_name;
    }
}

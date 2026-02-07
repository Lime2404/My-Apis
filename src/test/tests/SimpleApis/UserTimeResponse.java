package SimpleApis;

public class UserTimeResponse extends UserTime {
    private String updatedAt;
    public UserTimeResponse(String first_name, String last_name, String updatedAt) {
        // вызов конструктора с полями из класса родителя
        // пример super
        super(first_name, last_name);
        this.updatedAt = updatedAt;
    }
    public UserTimeResponse(){}

    public String getUpdatedAt() {
        return updatedAt;
    }
}

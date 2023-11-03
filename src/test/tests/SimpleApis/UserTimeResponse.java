package SimpleApis;

public class UserTimeResponse extends UserTime {
    private String updatedAt;
    public UserTimeResponse(String name, String job, String updatedAt) {
        // вызов конструктора с полями из класса родителя
        super(name, job);
        this.updatedAt = updatedAt;
    }
    public UserTimeResponse(){}

    public String getUpdatedAt() {
        return updatedAt;
    }
}

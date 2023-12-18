package Temporary;

public class TemporaryYear {
    private Integer year;
    private Integer id;
    private String name;
    private String color;
    private String pantone_value;

    public TemporaryYear(Integer year, Integer id, String name, String color, String pantone_value) {
        this.year = year;
        this.id = id;
        this.name = name;
        this.color = color;
        this.pantone_value = pantone_value;
    }

    public TemporaryYear(){}

    public Integer getYear() {
        return year;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public String getPantone_value() {
        return pantone_value;
    }
}

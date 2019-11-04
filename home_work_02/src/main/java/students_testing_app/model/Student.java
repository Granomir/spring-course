package students_testing_app.model;

public class Student {
    private String name;
    private String surname;
    private int points;

    public Student() {
        points = 0;
    }

    public void addPoint() {
        points++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getPoints() {
        return points;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }
}

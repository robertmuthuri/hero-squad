package models;

import java.util.Objects;

public class Squad {
    private String name;
    private int id;

    public Squad(String name) {
        this.name = name;
    }
    // getters
    public int getId() { return id; }
    public String getName() { return name; }

    // setters
    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }

    //hashcode
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Squad squad = (Squad) o;
        return getId() == squad.getId() &&
                Objects.equals(getName(), squad.getName());
    }
    @Override
    public int hashCode() { return Objects.hash(getName(), getId()); }
}

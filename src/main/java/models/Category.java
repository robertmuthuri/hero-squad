package models;

import java.util.Objects;

public class Category {
    private String name;
    private int id;

    public Category(String name) {
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
        Category category = (Category) o;
        return getId() == category.getId() &&
                Objects.equals(getName(), category.getName());
    }
    @Override
    public int hashCode() { return Objects.hash(getName(), getId()); }
}

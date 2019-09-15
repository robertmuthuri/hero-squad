package models;

import java.util.ArrayList;
import java.util.Objects;

public class Hero {
    private String name;
    private int id;
    private int age;
    private String power;
    private String weakness;

    private int squadId;

//    private static ArrayList<Hero> instances = new ArrayList<>();

    public Hero (String name, int squadId) {
        this.name = name;
//        instances.add(this);
//        this.id = instances.size();
        this.squadId = squadId;
    }
//    public static ArrayList<Hero> getAll() { return instances; }

//    public static void clearAllHeroes() { instances.clear(); }

//    public static Hero findById(int id){ return instances.get(id-1); }

//    public void update(String name) { this.name = name; }

//    public void deleteHero() { instances.remove(id-1); }

    //getters
    public String getName() { return name; }

    public int getId() { return id; }

    public int getAge() { return age; }

    public String getPower() { return power; }

    public String getWeakness() { return weakness; }

    public int getSquadId() { return squadId; }

    //Setters
    public void setWeakness(String weakness) {
        this.weakness = weakness;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) { this.name = name; }

    public void setSquadId(int squadId) { this.squadId = squadId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hero hero = (Hero) o;
        return getId() == hero.getId() &&
                getAge() == hero.getAge() &&
                getSquadId() == hero.getSquadId() &&
                Objects.equals(getName(), hero.getName()) &&
                Objects.equals(getPower(), hero.getPower()) &&
                Objects.equals(getWeakness(), hero.getWeakness());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getId(), getAge(), getPower(), getWeakness(), getSquadId());
    }
}

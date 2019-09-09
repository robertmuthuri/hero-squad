package models;

import java.util.ArrayList;

public class Hero {
    private String name;
    private int id;
    private int age;
    private String power;
    private String weakness;

    private static ArrayList<Hero> instances = new ArrayList<>();

    public Hero (String name) {
        this.name = name;
        instances.add(this);
        this.id = instances.size();
    }
    public static ArrayList<Hero> getAll(){
        return instances;
    }

    public static void clearAllHeroes(){
        instances.clear();
    }

    public static Hero findById(int id){ return instances.get(id-1); }

    public void update(String name) { this.name = name; }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getPower() {
        return power;
    }

    public String getWeakness() {
        return weakness;
    }

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

    public void setName(String name) {
        this.name = name;
    }
}

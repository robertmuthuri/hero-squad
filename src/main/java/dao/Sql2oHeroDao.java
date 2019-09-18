package dao;

import models.Hero;
import org.sql2o.*;
import java.util.List;

public class Sql2oHeroDao implements HeroDao {

    private final Sql2o sql2o;

    public Sql2oHeroDao(Sql2o sql2o) {
        this.sql2o = sql2o; //makes the sql2o object available everywhere so we can call methods in it;
    }

    @Override
    public void add(Hero hero) {
        String sql = "INSERT INTO heroes (name, age, power, weakness, squadId) VALUES (:name, :age, :power, :weakness, :squadId)"; // raw sql
        try(Connection con = sql2o.open()) { //tries to open a connection
            int id = (int) con.createQuery(sql, true) //make a new variable
            .bind(hero) // map my argument onto the query so we can use information from it - means the object fields are mapped onto the parameters outlined in the SQL statement.
            .executeUpdate() // run the query
            .getKey(); //new row has been created so retrieve the key - int id is now the row number (row "key") of db
            hero.setId(id); // update object to set id now from database
        } catch (Sql2oException ex) {
            System.out.println(ex); // oops we have an error!
        }
    }
    // List all method
    @Override
    public List<Hero> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM heroes") //raw sql
            .executeAndFetch(Hero.class); // fetch a list
        }
    }
    // Read method
    @Override
    public Hero findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM heroes WHERE id = :id") //raw sql
            .addParameter("id", id) // key-value pair, key must match above.
            .executeAndFetchFirst(Hero.class); // fetch an individual item
        }
    }
    //update method
    @Override
    public void update (int id, String newName, int newAge, String newPower, String newWeakness, int newSquadId) {
//        String sql = "UPDATE heroes SET name = :name WHERE id = :id";
        String sql = "UPDATE heroes SET (name, age, power, weakness, squadId) = (:name, :age, :power, :weakness, :squadId) WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .addParameter("name", newName)
                    .addParameter("age", newAge)
                    .addParameter("power", newPower)
                    .addParameter("weakness", newWeakness)
                    .addParameter("squadId", newSquadId)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    //delete single method
    @Override
    public void deleteById(int id) {
        String sql = "DELETE FROM heroes WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
    // delete all method
    @Override
    public void clearAllHeroes() {
        String sql = "DELETE FROM heroes";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }
}

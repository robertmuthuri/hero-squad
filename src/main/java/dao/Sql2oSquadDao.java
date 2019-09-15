package dao;

import models.Squad;
import org.sql2o.*;

import java.util.List;

public class Sql2oSquadDao implements  SquadInterface {

    private final Sql2o sql2o;

    public Sql2oSquadDao(Sql2o sql2o) { this.sql2o = sql2o; }
    //List all items
    @Override
    public List<Squad> getAll() {
        try (Connection conn = sql2o.open()) {
        return conn.createQuery("SELECT * FROM squads WHERE id = :id")
                .executeAndFetch(Squad.class);
        }
    }
    //Add method
    @Override
    public void add(Squad squad) {
    String sql = "INSERT INTO squads (name) VALUES (:name)";
    try (Connection conn = sql2o.open()) {
        int id = (int) conn.createQuery(sql, true)
                .bind(squad)
                .executeUpdate()
                .getKey();
        squad.setId(id);
        } catch (Sql2oException ex) { System.out.println(ex); }
    }
    // Read method
    @Override
    public Squad findById(int id) {
        try (Connection conn = sql2o.open()) {
            return conn.createQuery("SELECT * FROM squads WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Squad.class);
        }
    }
}
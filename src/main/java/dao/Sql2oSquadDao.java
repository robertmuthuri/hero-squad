package dao;

import models.Squad;
import org.sql2o.*;

public class Sql2oSquadDao implements  SquadInterface {

    private final Sql2o sql2o;

    public Sql2oSquadDao(Sql2o sql2o) { this.sql2o = sql2o; }

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
}

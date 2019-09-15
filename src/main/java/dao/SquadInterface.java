package dao;

import models.Hero;
import models.Squad;

import java.util.List;

public interface SquadInterface {
    //LIST
    List<Squad> getAll();
    List<Hero> getAllHeroesBySquad(int squadId);

    //CREATE
    void add(Squad squad);

    //READ
    Squad findById(int id);

    //UPDATE
    void update(int id, String name);

    //DELETE
    void deleteById(int id);
    void deleteAllSquads();
}

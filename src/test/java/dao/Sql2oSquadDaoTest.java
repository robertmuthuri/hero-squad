package dao;

import models.Hero;
import models.Squad;
import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;

public class Sql2oSquadDaoTest {

    private Sql2oSquadDao squadDao;
    private Connection conn;

    private Sql2oHeroDao heroDao;
    
    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        squadDao = new Sql2oSquadDao(sql2o);
        conn = sql2o.open(); 
    }

    @After
    public void tearDown() throws Exception { conn.close(); }

    //helper method
    public Squad setUpNewSquad() { return new Squad("Avengers"); }

    // list all test
    @Test
    public void addedSquadsAreReturnedFromGetAll() throws Exception {
        Squad squad = setUpNewSquad();
        squadDao.add(squad);
        assertEquals(1, squadDao.getAll().size());
    }
    // list all squad heroes
    @Test
    public void getAllHeroesBySquadReturnsHeroesCorrectly() throws Exception {
        Squad squad = setUpNewSquad();
        squadDao.add(squad);
        int squadId = squad.getId();
        Hero hero = new Hero("Tony Stark", squadId);
        Hero secondHero = new Hero("Chris Evans", squadId);
        Hero thirdHero = new Hero("Chris Hemsworth", squadId);
        heroDao.add(hero);
        heroDao.add(secondHero);
        assertEquals(2, squadDao.getAllHeroesBySquad(squadId));
        assertTrue(squadDao.getAllHeroesBySquad(squadId).contains(secondHero));
        assertFalse(squadDao.getAllHeroesBySquad(squadId).contains(thirdHero));
    }
    // add test
    @Test
    public void addingSquadSetsId() throws Exception {
        Squad squad = setUpNewSquad();
        int originalSquadId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(originalSquadId, squad.getId());
    }
    //read tests
    @Test
    public void existingSquadCanBeFoundById() throws Exception {
        Squad squad = setUpNewSquad();
        squadDao.add(squad);
        Squad foundSquad = squadDao.findById(squad.getId());
        assertEquals(squad, foundSquad);
    }
    @Test
    public void noSquadsReturnsEmptyList() throws Exception {
        assertEquals(0, squadDao.getAll().size());
    }
    // update test
    @Test
    public void updateChangesSquadName() throws Exception {
        Squad squad = setUpNewSquad();
        squadDao.add(squad);
        squadDao.update(squad.getId(), "Justice League");
        Squad updatedSquad = squadDao.findById(squad.getId());
        assertNotEquals(squad.getName(), updatedSquad.getName());
    }
    // Delete test
    @Test
    public void deleteByIdDeletesTheCorrectSquad() throws Exception {
        Squad squad = setUpNewSquad();
        squadDao.add(squad);
        squadDao.deleteById(squad.getId());
        assertEquals(0, squadDao.getAll().size());
    }
    // Deletes all test
    @Test
    public void deleteAllSquadsDeletesAll() throws Exception {
        Squad squad = setUpNewSquad();
        Squad otherSquad = new Squad("Justice League");
        squadDao.add(squad);
        squadDao.add(otherSquad);
        int daoSize = squadDao.getAll().size();
        squadDao.deleteAllSquads();
        assertTrue(daoSize > 0 && daoSize > squadDao.getAll().size());
    }
}
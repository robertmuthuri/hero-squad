package dao;

import models.Squad;
import org.junit.*;
import org.sql2o.*;
import static org.junit.Assert.*;

public class Sql2oSquadDaoTest {
    private Sql2oSquadDao squadDao;
    private Connection conn; 
    
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
    // add test
    @Test
    public void addingSquadSetsId() throws Exception {
        Squad squad = setUpNewSquad();
        int originalSquadId = squad.getId();
        squadDao.add(squad);
        assertNotEquals(originalSquadId, squad.getId());
    }
    //read test
    @Test
    public void existingSquadCanBeFoundById() throws Exception {
        Squad squad = setUpNewSquad();
        squadDao.add(squad);
        Squad foundSquad = squadDao.findById(squad.getId());
        assertEquals(squad, foundSquad);
    }
}
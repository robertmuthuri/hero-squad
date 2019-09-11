package dao;

import models.Hero;
import org.sql2o.*;
import org.junit.*;
import static org.junit.Assert.*;


public class Sql2oHeroDaoTest {
    private Sql2oHeroDao heroDao; //ignore me for now. We'll create this soon.
    private Connection conn; //must be sql2o class conn

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        heroDao = new Sql2oHeroDao(sql2o); //ignore me for now
        conn = sql2o.open(); //keep connection open through entire test so it does not get erased
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addingHeroSetsId() throws Exception {
        Hero hero = setUpNewHero();
        int originalHeroId = hero.getId();
        heroDao.add(hero);
        assertNotEquals(originalHeroId, hero.getId());
    }

    @Test
    public void existingHerosCanBeFoundById() throws Exception {
        Hero hero = setUpNewHero();
        heroDao.add(hero);
        Hero foundHero = heroDao.findById(hero.getId());
        assertEquals(hero, foundHero);
    }
    @Test
    public void addedHeroesAreReturnedFromgetAll() throws Exception {
        Hero hero = setUpNewHero();
        heroDao.add(hero);
        assertEquals(1, heroDao.getAll().size());
    }

    @Test
    public void noHeroesReturnsEmptyList() throws Exception {
        assertEquals(0, heroDao.getAll().size());
    }

    //helper methods
    public Hero setUpNewHero() { return new Hero("Tony Stark"); }
}
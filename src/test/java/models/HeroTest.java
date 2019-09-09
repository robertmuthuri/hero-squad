package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Hero.clearAllHeros(); //Clears all heros before each test.
    }
    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Hero hero = new Hero("Tony Stark");
        assertTrue(hero instanceof Hero);
    }
    @Test
    public void HeroInstantiatesWithName_true() throws Exception {
        Hero hero = new Hero("Tony Stark");
        assertEquals("Tony Stark", hero.getName());
    }
    @Test
    public void AllHerosAreCorrectlyReturned_true() {
        Hero hero = new Hero("Tony Stark");
        Hero otherHero = new Hero ("Chris Evans");
        assertEquals(2, Hero.getAll().size());
    }

    @Test
    public void AllHerosContainsAllHeros_true() {
        Hero hero = new Hero("Tony Stark");
        Hero otherHero = new Hero ("Chris Evans");
        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(otherHero));
    }
}
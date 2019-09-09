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
}
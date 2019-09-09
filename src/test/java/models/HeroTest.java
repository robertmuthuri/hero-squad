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
        Hero.clearAllHeroes(); //Clears all heros before each test.
    }
    public Hero setupNewHero(){
        return new Hero("Tony Stark");
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
    public void AllHeroesAreCorrectlyReturned_true() {
        Hero hero = new Hero("Tony Stark");
        Hero otherHero = new Hero ("Chris Evans");
        assertEquals(2, Hero.getAll().size());
    }
    @Test
    public void AllHeroesContainsAllHeroes_true() {
        Hero hero = new Hero("Tony Stark");
        Hero otherHero = new Hero ("Chris Evans");
        assertTrue(Hero.getAll().contains(hero));
        assertTrue(Hero.getAll().contains(otherHero));
    }
    @Test
    public void getId_herosInstantiateWithAnID_1() throws Exception{
//        Post.clearAllHeroes();  // Remember, the test will fail without this line! We need to empty leftover Posts from previous tests!
        Hero myHero = new Hero("Tony Stark");
        assertEquals(1, myHero.getId());
    }
    @Test
    public void findReturnsCorrectHero() throws Exception {
        Hero hero = setupNewHero();
        assertEquals(1, Hero.findById(hero.getId()).getId());
    }
    @Test
    public void findReturnsCorrectHeroWhenMoreThanOneHeroExists() throws Exception {
        Hero hero = setupNewHero();
        Hero otherHero = new Hero("Captain America");
        assertEquals(2, Hero.findById(otherHero.getId()).getId());
    }
}
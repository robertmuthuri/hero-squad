package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class HeroTest {

//    @Before
//    public void setUp() throws Exception {
//    }
//
//    @After
//    public void tearDown() throws Exception {
//        Hero.clearAllHeroes(); //Clears all heros before each test.
//    }
    public Hero setupNewHero(){
        return new Hero("Tony Stark", 48, "Too awesome","Weak heart",1);
    }

    @Test
    public void NewHeroObjectGetsCorrectlyCreated_true() throws Exception {
        Hero hero = setupNewHero();
        assertTrue(hero instanceof Hero);
    }
    @Test
    public void HeroInstantiatesWithName_true() throws Exception {
        Hero hero = setupNewHero();
        assertEquals("Tony Stark", hero.getName());
    }
//    @Test
//    public void AllHeroesAreCorrectlyReturned_true() {
//        Hero hero = new Hero("Tony Stark");
//        Hero otherHero = new Hero ("Chris Evans");
//        assertEquals(2, Hero.getAll().size());
//    }
//    @Test
//    public void AllHeroesContainsAllHeroes_true() {
//        Hero hero = new Hero("Tony Stark");
//        Hero otherHero = new Hero ("Chris Evans");
//        assertTrue(Hero.getAll().contains(hero));
//        assertTrue(Hero.getAll().contains(otherHero));
//    }
//    @Test
//    public void getId_herosInstantiateWithAnID_1() throws Exception{
////        Post.clearAllHeroes();  // Remember, the test will fail without this line! We need to empty leftover Posts from previous tests!
//        Hero myHero = new Hero("Tony Stark");
//        assertEquals(1, myHero.getId());
//    }
//    @Test
//    public void findReturnsCorrectHero() throws Exception {
//        Hero hero = setupNewHero();
//        assertEquals(1, Hero.findById(hero.getId()).getId());
//    }
//    @Test
//    public void findReturnsCorrectHeroWhenMoreThanOneHeroExists() throws Exception {
//        Hero hero = setupNewHero();
//        Hero otherHero = new Hero("Captain America");
//        assertEquals(2, Hero.findById(otherHero.getId()).getId());
//    }
//    @Test
//    public void updateChangesHeroContent() throws Exception {
//        Hero hero = setupNewHero();
//        String formerName = hero.getName();
//        int formerId = hero.getId();
//
//        hero.update("Robert Muthuri");
//
//        assertEquals(formerId, hero.getId());
//        assertNotEquals(formerName, hero.getName());
//    }
//    @Test
//    public void deleteDeletesASpecificHero() throws Exception {
//        Hero hero = setupNewHero();
//        Hero otherHero = new Hero("Captain Marvel");
//        hero.deleteHero();
//        assertEquals(1, Hero.getAll().size()); //one is left
//        assertEquals(Hero.getAll().get(0).getId(), 2); //the one that was deleted has the id of 2. Why do we care?
//    }
//    @Test
//    public void deleteAllHeroesDeletesAllHeroes() throws Exception {
//        Hero hero = setupNewHero();
//        Hero otherHero = setupNewHero();
//
//        Hero.clearAllHeroes();
//        assertEquals(0, Hero.getAll().size());
//    }
}
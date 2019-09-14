package models;

import org.junit.Test;

import static org.junit.Assert.*;

public class SquadTest {
    @Test
    public void NewSquadObjectGetsCorrectlyCreated_True() throws Exception {
        Squad squad = new Squad("Avengers");
        assertTrue(squad instanceof Squad);
    }

    @Test
    public void SquadInstantiatesWithName_true() throws Exception {
        Squad squad = new Squad("Avengers");
        assertEquals("Avengers", squad.getName());
    }
}
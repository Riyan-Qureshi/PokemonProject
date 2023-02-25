package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MoveTest {
    private Move testMove;

    @BeforeEach
    void runBefore(){
        testMove = new Move("Scratch", 20);
    }

    @Test
    void testGetMoveName(){
        assertEquals("Scratch", testMove.getMoveName());
    }

    @Test
    void testGetDamage(){
        assertEquals(20, testMove.getDamage());
    }
}

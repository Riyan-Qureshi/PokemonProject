package model;

import org.json.JSONObject;
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

    @Test
    void testToJson() {
        JSONObject jsonObject = testMove.toJson();

        assertEquals("Scratch", jsonObject.getString("moveName"));
        assertEquals(20, jsonObject.getInt("damage"));
    }
}

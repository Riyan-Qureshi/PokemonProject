package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerTest {
    private Trainer testTrainer;

    @BeforeEach
    void runBefore(){
        testTrainer = new Trainer("Bob");
    }

    @Test
    @DisplayName("Should create a trainer with a name and an empty party")
    void testConstructor(){
        assertEquals("Bob", testTrainer.getName());
        assertEquals(0, testTrainer.getParty().getPartySize());
    }
}

package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChallengerTest {
    private Challenger testChallenger1;
    private Challenger testChallenger2;

    @BeforeEach
    void runBefore(){
        testChallenger1 = new Challenger("Gary", 5);
        testChallenger2 = new Challenger("Bob", 6);
    }

    @Test
    @DisplayName("Should create a challenger with specified name and randomized Pokemon party of specified party size")
    void testConstructor(){
        assertEquals("Gary", testChallenger1.getName());
        assertEquals("Bob", testChallenger2.getName());

        Party garyParty = testChallenger1.getParty();
        Party bobParty = testChallenger2.getParty();

        assertEquals(testChallenger1.getPartySize(), garyParty.getPartySize());
        assertEquals(testChallenger2.getPartySize(), bobParty.getPartySize());
    }

}

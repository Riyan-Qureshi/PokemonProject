package persistence;

import model.Challenger;
import model.Pokemon;
import model.Trainer;
import model.Type;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest {

    @Test
    void testReaderMissingFile() {
        JsonReader reader = new JsonReader("./data/nothing.json");
        try {
            Trainer trainer = reader.readTrainer();
            fail("Expected IOException not thrown");
        } catch(IOException e) {
            // test passed
        }
    }

    @Test
    void testReaderTrainer() {
        JsonReader reader = new JsonReader("./data/testReaderOneTrainerAndChallenger.json");

        try {
            Trainer trainer = reader.readTrainer();
            assertEquals("Riyan", trainer.getName());
            assertEquals(1, trainer.getParty().getPartySize());

            Pokemon firstPokemon = trainer.getParty().getPartyMember(0);
            assertEquals("Charizard", firstPokemon.getName());
            assertEquals(156, firstPokemon.getHealthPoints());
            Type type = firstPokemon.getType();
            assertEquals(type.FIRE, type);
            assertEquals(4, firstPokemon.getMoves().size());
        } catch(IOException e) {
            fail("Expected file not found");
        }
    }

    @Test
    void testReaderChallenger() {
        JsonReader reader = new JsonReader("./data/testReaderOneTrainerAndChallenger.json");

        try {
            Challenger rival = reader.readChallenger();
            assertEquals("Gary", rival.getName());
            assertEquals(1, rival.getParty().getPartySize());

            Pokemon firstPokemon = rival.getParty().getPartyMember(0);
            assertEquals("Vaporeon", firstPokemon.getName());
            assertEquals(159, firstPokemon.getHealthPoints());
            Type type = firstPokemon.getType();
            assertEquals(type.WATER, type);
            assertEquals(4, firstPokemon.getMoves().size());

        } catch(IOException e) {
            fail("Expected file not found");
        }
    }

}

package persistence;

import model.Challenger;
import model.Trainer;
import model.Type;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest{

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
            checkTrainer("Riyan", 1, "Charizard", 156, Type.FIRE, trainer);
        } catch(IOException e) {
            fail("Expected file not found");
        }
    }

    @Test
    void testReaderChallenger() {
        JsonReader reader = new JsonReader("./data/testReaderOneTrainerAndChallenger.json");

        try {
            Challenger rival = reader.readChallenger();
            checkTrainer("Gary", 1, "Vaporeon", 159, Type.WATER, rival);
        } catch(IOException e) {
            fail("Expected file not found");
        }
    }

}

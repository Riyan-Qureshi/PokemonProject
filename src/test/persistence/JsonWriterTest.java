package persistence;

import model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest{

    @Test
    void testWriterBadFile() {
        JsonWriter writer = new JsonWriter("./data/somewhere/wrongfile.json");

        try {
            writer.open();
            fail("Expected IOException not thrown");
        } catch(IOException e) {
            // pass
        }
    }

    @Test
    void testWriteAll() {
        GeneratePokemonPC testStoragePC = new GeneratePokemonPC();
        Pokemon pokemon = testStoragePC.getPokemon(0);
        Trainer player = new Trainer("Riyan");
        Challenger rival = new Challenger("Gary");

        player.getParty().addMember(pokemon);
        rival.getParty().addMember(pokemon);

        try {
            JsonWriter writer = new JsonWriter("./data/testWriterOneTrainerAndChallenger.json");
            writer.open();
            writer.writeAll(player, rival);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterOneTrainerAndChallenger.json");
            player = reader.readTrainer();
            rival = reader.readChallenger();

           checkTrainer("Riyan", 1, "Charizard", pokemon.getHealthPoints(), Type.FIRE, player);
           checkTrainer("Gary", 1, "Charizard", pokemon.getHealthPoints(), Type.FIRE, rival);
        } catch(IOException e) {
            fail("Expected file not found");
        }
    }
}

package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TrainerTest {
    private Trainer testTrainer;

    @BeforeEach
    void runBefore() {
        testTrainer = new Trainer("Bob");
    }

    @Test
    @DisplayName("Should create a trainer with a name and an empty party")
    void testConstructor() {
        assertEquals("Bob", testTrainer.getName());
        assertEquals(0, testTrainer.getParty().getPartySize());
    }

    @Test
    @DisplayName("It should return a comma-separated list of all Pokemon in trainer's party as a string")
    void testViewParty(){
        List<String> allPokemonNames = new ArrayList<>();
        Party trainerParty = testTrainer.getParty();
        List<Pokemon> pokemonParty = trainerParty.getParty();

        for(Pokemon pokemon : pokemonParty){
            allPokemonNames.add(pokemon.getName());
        }

        String expectedString = String.join(", ", allPokemonNames);

        assertEquals(expectedString, testTrainer.viewParty());
    }

    @Test
    void testToJson(){
        JSONObject jsonObject = testTrainer.toJson();

        assertEquals("Bob", jsonObject.getString("name"));
        assertEquals("{\"party\":[]}", jsonObject.getJSONObject("myParty").toString());
    }
}

package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class PartyTest {
    private Party testParty;
    private GeneratePokemonPC testStoragePC;

    @BeforeEach
    void runBefore() {
        testParty = new Party();
        testStoragePC = new GeneratePokemonPC();
    }

    @Test
    @DisplayName("It should add the given Pokemon to the party list and increase size of party by 1 when executed")
    void testAddMember() {
        Pokemon pokemon = testStoragePC.getPokemon(0);
        testParty.addMember(pokemon);
        assertEquals(1, testParty.getPartySize());
    }

    @Test
    @DisplayName("It should return 6 for the size of the party when adding more than 6 Pokemon to party")
    void testMaxAddMember() {
        List<Pokemon> allPokemon = testStoragePC.getStorage();

        for (int i = 0; i < allPokemon.size(); i++) { //Adds 6 Pokemon to the party
            Pokemon pokemon = testStoragePC.getPokemon(i);
            testParty.addMember(pokemon);
        }

        Pokemon pokemon = testStoragePC.getPokemon(0);
        testParty.addMember(pokemon);
        assertEquals(testParty.getMaxPartySize(), testParty.getPartySize());
    }

    @Test
    @DisplayName("It should return the Pokemon in specified slot number when executed")
    void testGetPartyMember() {
        Pokemon pokemon = testStoragePC.getPokemon(0);
        Pokemon expectedPokemon = pokemon;

        testParty.addMember(pokemon);

        assertEquals(expectedPokemon, testParty.getPartyMember(0));
    }

    @Test
    void testToJson() {
        JSONObject party = testParty.toJson();

        assertEquals("{\"party\":[]}", party.toString());
    }

    @Test
    void testPartyToJson() {
        Pokemon pokemon = testStoragePC.getPokemon(0);
        int pokemonHP = pokemon.getHealthPoints();
        testParty.addMember(pokemon);

        JSONArray party = testParty.partyToJson();

        assertEquals("[{\"healthPoints\":" + pokemonHP + ",\"moves\":[{\"damage\":15,\"moveName\":\"Scratch\"},{\"damage\":20,\"moveName\":\"Punch\"},{\"damage\":20,\"moveName\":\"Kick\"},{\"damage\":15,\"moveName\":\"Bite\"}],\"name\":\"Charizard\",\"type\":\"FIRE\"}]", party.toString());
    }

    @Test
    void testGetParty() {
        assertEquals(0, testParty.getParty().size());

        Pokemon pokemon = testStoragePC.getPokemon(0);
        testParty.addMember(pokemon);
        assertEquals(1, testParty.getParty().size());
    }
}

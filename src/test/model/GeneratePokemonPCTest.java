package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GeneratePokemonPCTest {
    private GeneratePokemonPC testStoragePC;
    private List<Pokemon> allPokemon;

    @BeforeEach
    void runBefore(){
        testStoragePC = new GeneratePokemonPC();
        allPokemon = testStoragePC.getStorage();
    }

    @Test
    @DisplayName("It should return a comma-separated list of all created Pokemon as a string")
    void testDisplayAllPokemon(){
        List<String> allPokemonNames = new ArrayList<>();

        for(Pokemon pokemon : allPokemon){
            allPokemonNames.add(pokemon.getName());
        }

        String expectedString = String.join(", ", allPokemonNames);
        //String expectedString = "Charizard, Blaustoise, Venasaur, Raticate, Vaporeon, Flareon";

        assertEquals(expectedString, testStoragePC.displayAllPokemon());
    }

    @Test
    @DisplayName("It should return the Pokemon at the specified slot number when method is executed")
    void testGetPokemon(){
        int slotOne = 0;
        Pokemon expectedPokemon = allPokemon.get(slotOne);

        assertEquals(expectedPokemon, testStoragePC.getPokemon(slotOne));
    }
}

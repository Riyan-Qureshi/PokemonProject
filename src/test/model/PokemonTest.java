package model;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PokemonTest {
    private Pokemon testPokemon;
    private Move move1;
    private Move move2;

    @BeforeEach
    void runBefore(){
        testPokemon = new Pokemon("Treecko", Type.GRASS);
        move1 = new Move("Scratch", 20);
        move2 = new Move("Punch", 15);
    }

    @Test
    @DisplayName("Test correct Pokemon construction")
    void testConstructor(){
        assertEquals("Treecko", testPokemon.getName());
        assertEquals(Type.GRASS, testPokemon.getType());
        assertEquals(0, testPokemon.getMoves().size());
        int healthPts = testPokemon.getHealthPoints();
        boolean validHealth = healthPts >= 140 && healthPts <= 160;
        assertTrue(validHealth);
    }

    @Test
    @DisplayName("Test preview all moves successfully")
    void testPreviewMoves(){
        testPokemon.addMove(move1);
        testPokemon.addMove(move2);
        assertEquals("Scratch, Punch", testPokemon.previewMoves());
    }

    @Test
    @DisplayName("Test preview empty moves successfully")
    void testEmptyPreviewMoves(){
        assertEquals("", testPokemon.previewMoves());
    }

    @Test
    @DisplayName("Test retrieve specified move from non-empty moves list successfully")
    void testGetMove(){
        testPokemon.addMove(move1);
        assertEquals(move1, testPokemon.getMove(0));
    }

    @Test
    @DisplayName("It should return 4 moves when adding more than 4 moves")
    void testMaxAddMove(){
        testPokemon.addMove(move1);
        testPokemon.addMove(move2);
        testPokemon.addMove(move1);
        testPokemon.addMove(move2);
        testPokemon.addMove(move1);
        List<Move> expectedResult = new ArrayList<>();
        Collections.addAll(expectedResult, move1, move2, move1, move2);

        assertEquals(4, testPokemon.getMoves().size());
        assertEquals(expectedResult, testPokemon.getMoves());
    }

    @Test
    void testSetHealthPoints(){
        int healthPts = testPokemon.getHealthPoints();
        boolean validHealth = healthPts >= 140 && healthPts <= 160;
        assertTrue(validHealth);

        testPokemon.setHealthPoints(150);
        assertEquals(150, testPokemon.getHealthPoints());
    }

    @Test
    void testToJson() {
        JSONObject pokemon = testPokemon.toJson();
        int pokemonHP = pokemon.getInt("healthPoints");

        assertTrue(pokemonHP >= 140 && pokemonHP <= 160);
        assertEquals("Treecko", pokemon.getString("name"));
        Type type = pokemon.getEnum(Type.class,"type");
        assertEquals(type.GRASS, type);

        assertEquals("[]", pokemon.getJSONArray("moves").toString());
    }

    @Test
    void testMovesToJson() {
        testPokemon.addMove(move1);
        JSONArray moves = testPokemon.movesToJson();

        assertEquals("[{\"damage\":20,\"moveName\":\"Scratch\"}]", moves.toString());
    }
}
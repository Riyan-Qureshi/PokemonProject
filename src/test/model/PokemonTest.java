package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

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
    void testConstructor(){
        assertEquals("Treecko", testPokemon.getName());
        assertEquals(Type.GRASS, testPokemon.getType());
        assertEquals(0, testPokemon.getMoves().size());
        int healthPts = testPokemon.getHealthPoints();
        boolean validHealth = healthPts >= 140 && healthPts <= 160;
        assertTrue(validHealth);
    }

    @Test
    void testPreviewMoves(){
        testPokemon.addMove(move1);
        testPokemon.addMove(move2);
        assertEquals("Scratch, Punch", testPokemon.previewMoves());
    }

    @Test
    void testGetMove(){
        testPokemon.addMove(move1);
        assertEquals(move1, testPokemon.getMove(0));
    }

    @Test
    void testMaxAddMove(){
        testPokemon.addMove(move1);
        testPokemon.addMove(move2);
        testPokemon.addMove(move1);
        testPokemon.addMove(move2);
        testPokemon.addMove(move1);

        assertEquals("Scratch, Punch, Scratch, Punch", testPokemon.previewMoves());

    }
}
package persistence;

import model.Pokemon;
import model.Trainer;
import model.Type;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {
    protected void checkTrainer(String name, int partySize, String pokemonName, int hp, Type realType,Trainer player) {
        assertEquals(name, player.getName());
        assertEquals(partySize, player.getParty().getPartySize());

        Pokemon firstPokemon = player.getParty().getPartyMember(0);
        assertEquals(pokemonName, firstPokemon.getName());
        assertEquals(hp, firstPokemon.getHealthPoints());
        Type type = firstPokemon.getType();
        assertEquals(realType, type);
        assertEquals(4, firstPokemon.getMoves().size());
    }
}

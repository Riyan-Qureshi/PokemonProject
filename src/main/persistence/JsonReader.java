package persistence;

import model.Challenger;
import model.Pokemon;
import model.Trainer;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import model.Type;
import org.json.*;

// Represents a reader that reads either trainer or challenger from JSON data stored in file
public class JsonReader {
    private final String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads trainer from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Trainer readTrainer() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        JSONObject playerState = jsonArray.getJSONObject(0);
        return parseTrainer(playerState);
    }

    // EFFECTS: reads challenger from file and returns it;
    // throws IOException if an error occurs reading data from file
    public Challenger readChallenger() throws IOException {
        String jsonData = readFile(source);
        JSONArray jsonArray = new JSONArray(jsonData);
        JSONObject challengerState = jsonArray.getJSONObject(1);
        return parseChallenger(challengerState);
    }

    // EFFECTS: reads source file as string and returns it
    // CITATION: Paul Carter:
    // https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo/blob/master/src/main/persistence/JsonReader.java
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses trainer from JSON object and returns it
    private Trainer parseTrainer(JSONObject playerState) {
//        String name = jsonObject.getString("name");
//        Trainer player = new Trainer(name);
//        JSONObject party = jsonObject.getJSONObject("myParty");
//        addParty(player, party);
//        return player;
        String playerName = playerState.getString("name");
        Trainer player = new Trainer(playerName);
        JSONObject party = playerState.getJSONObject("myParty");
        addParty(player, party);

        return player;
    }

    // EFFECTS: parses challenger from JSON object and returns it
    private Challenger parseChallenger(JSONObject challengerState) {
        String challengerName = challengerState.getString("name");
        Challenger challenger = new Challenger(challengerName);
        JSONObject party = challengerState.getJSONObject("myParty");
        addParty(challenger, party);

        return challenger;
    }

    // MODIFIES: player, challenger
    // EFFECTS: parses party from JSON object and adds them to player
    private void addParty(Trainer player, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("party");
        for (Object json : jsonArray) {
            JSONObject nextPokemon = (JSONObject) json;
            addPokemon(player, nextPokemon);
        }
    }

    // MODIFIES: player, challenger
    // EFFECTS: parses pokemon from JSON object and adds it to workroom
    private void addPokemon(Trainer player, JSONObject jsonObject) {
        int healthPoints = jsonObject.getInt("healthPoints");
        String name = jsonObject.getString("name");
        Type type = Type.valueOf(jsonObject.getString("type"));
        Pokemon pokemon = new Pokemon(name, type);
        pokemon.setHealthPoints(healthPoints);
        player.getParty().addMember(pokemon);
    }
}

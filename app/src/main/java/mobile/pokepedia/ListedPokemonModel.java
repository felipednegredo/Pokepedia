package mobile.pokepedia;

public class ListedPokemonModel {
    String pokemonNumber;
    String pokemonType1;
    String pokemonType2;
    String pokemonName;
    int pokemonRank;

    public ListedPokemonModel(String pokemonNumber, String pokemonType1, String pokemonType2, String pokemonName, int pokemonRank) {
        this.pokemonNumber = pokemonNumber;
        this.pokemonType1 = pokemonType1;
        this.pokemonType2 = pokemonType2;
        this.pokemonName = pokemonName;
        this.pokemonRank = pokemonRank;
    }

    public String getPokemonNumber() {
        return pokemonNumber;
    }

    public String getPokemonType1() {
        return pokemonType1;
    }

    public String getPokemonType2() {
        return pokemonType2;
    }

    public String getPokemonName() {
        return pokemonName;
    }

    public int getPokemonRank() {
        return pokemonRank;
    }
}



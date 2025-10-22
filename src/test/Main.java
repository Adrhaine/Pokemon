package test;
import pokemons.Pokemon0;
import pokemons.PokemonEau;
import pokemons.PokemonFeu;
import pokemons.PokemonPlante;


public class Main {

	public static void main(String[] args) {
		// Lance le test de la Partie 1
		System.out.println("\nTEST POKEMONO \n"); 
		testPokemon0(); 
				
		// Lance le test de la Partie 2
		System.out.println("\nTEST POKEMON TYPES \n"); 
		testPokemonTypes();
	}


	/**
	 * Scénario de test pour la Partie 1 : Pokemon0
	 */
	private static void testPokemon0() {
		// test Pokemono
		Pokemon0 p1 = new Pokemon0("Rhinolove");
		Pokemon0 p2 = new Pokemon0("Chovsourir");
		
		p1.log(p1.toString());
		p2.log(p2.toString());
		
		p1.attaquer(p2);
		p2.log(p2.toString());
	}

	/**
	 * Scénario de test pour la Partie 2 : Pokemon Types
	 */
	private static void testPokemonTypes() {
		// Crée un Pokemon Eau et un Pokemon Feu
		PokemonEau eau = new PokemonEau("PokéEau");
		PokemonFeu feu = new PokemonFeu("PokéFeu");
		
		// Affiche leurs stats de base
		eau.log(eau.toString());
		feu.log(feu.toString());
		
		// Combat !
		eau.attaquer(feu); // Attaque Eau vs Feu (devrait être "très efficace")
		feu.attaquer(eau); // Attaque Feu vs Eau (devrait être "peu efficace")
		
		// Affiche leurs nouvelles stats
		eau.log(eau.toString());
		feu.log(feu.toString());
	}
}

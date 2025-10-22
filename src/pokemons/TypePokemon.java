package pokemons;

public enum TypePokemon {
	FEU("Feu"),
	PLANTE("Plante"),
	EAU("Eau");
		
	private String type;
		
		
	private TypePokemon(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}

		 
}

package pokemons;

public enum TypePokemon {
	FEU("Type Feu"),
	PLANTE("Type Plante"),
	EAU("Type Eau");
		
	private String type;
		
		
	private TypePokemon(String type) {
		this.type = type;
	}
	
	@Override
	public String toString() {
		return this.type;
	}

		 
}

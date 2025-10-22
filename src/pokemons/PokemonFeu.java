package pokemons;

public class PokemonFeu extends Pokemon {
	
	public PokemonFeu(String nom) {
		super(nom, TypePokemon.FEU);
	}

	@Override
	public void attaquer(Pokemon p) {
		this.log("J'attaque " + p.getNom());
		p.subir(this);
	}

	@Override
	public void subir(Pokemon p) {
		TypePokemon typeAttaquant = p.getType();
		int atkAttaquant = p.getAtk();
		double degats;

		// On utilise un switch pour gérer les efficacités
		switch (typeAttaquant) {
			case EAU:
				// Eau est SUPER EFFICACE contre Feu (2*atk) 
				degats = atkAttaquant * 2.0;
				this.log("C'est très efficace !"); 
				break;
			
			case PLANTE:
				// Plante est PEU EFFICACE contre Feu (0.5*atk) 
				degats = atkAttaquant * 0.5;
				this.log("C'est peu efficace..."); 
				break;

			case FEU:
				// Feu est PEU EFFICACE contre Feu (0.5*atk) 
				degats = atkAttaquant * 0.5;
				this.log("C'est peu efficace...");
				break;

			default:
				// Cas normal (ne devrait pas arriver ici)
				degats = atkAttaquant;
				break;
		}

		// Applique les dégâts (on utilise 'hp' qui est 'protected')
		// On reconvertit les dégâts en 'int'
		this.hp = this.hp - (int)degats;
		
		// On s'assure que les HP ne tombent pas sous 0
		if (this.hp < 0) {
			this.hp = 0;
		}
	}
	

}

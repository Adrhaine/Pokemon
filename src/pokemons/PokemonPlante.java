package pokemons;

public class PokemonPlante extends Pokemon {

	public PokemonPlante(String nom) {
		super(nom, TypePokemon.PLANTE);
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
						// Eau est PEU EFFICACE contre Plante (0.5*atk) 
						degats = atkAttaquant * 0.5;
						this.log("C'est peu efficace..."); 
						break;
					
					case PLANTE:
						// Plante est PEU EFFICACE contre Plante (0.5*atk) 
						degats = atkAttaquant * 0.5;
						this.log("C'est peu efficace..."); 
						break;

					case FEU:
						// Feu est SUPER EFFICACE contre PLANTE (2*atk) 
						degats = atkAttaquant * 2;
						this.log("C'est très efficace !");
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

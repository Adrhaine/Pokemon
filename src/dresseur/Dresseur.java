package dresseur;

// Imports nécessaires
import pokemons.*; // Accès à Pokemon, PokemonFeu, etc.
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Modélise un Dresseur de Pokémon.
 * Cette version implémente toute la logique directement,
 * sans déléguer à d'autres classes.
 */
public class Dresseur {

	// --- Attributs ---
	private String nom; // [cite: 225]
	private int argent; // [cite: 227]
	private List<Pokemon> sacADos; // [cite: 228]
	private Equipe equipe; // (Nécessaire pour les logs de test) [cite: 259]

	// --- Constantes et Statiques ---
	private static final int ARGENT_DEPART = 150; // (Basé sur les logs) [cite: 260, 273]
	private static final int VOL_ARGENT = 10; // [cite: 237]
	private static final int MAX_POKEMONS = 3; // [cite: 228]
	private static Random random = new Random();
	
	// (Attributs statiques de la partie 4, mais nécessaires si on suit les logs)
	private static int nbDresseurs = 0;
	private static int[] nbParEquipe = new int[Equipe.values().length];

	/**
	 * Constructeur du Dresseur.
	 * [cite: 229]
	 * @param nom Le nom du dresseur.
	 */
	public Dresseur(String nom) {
		this.nom = nom;
		this.argent = ARGENT_DEPART;
		this.sacADos = new ArrayList<>(); // Initialise le sac
		
		// Logique d'équipe (Partie 4, mais visible dans les logs Part 3)
		this.choixEquipe();
		nbDresseurs++;
		nbParEquipe[this.equipe.ordinal()]++;
	}
	
	/**
	 * Attribue aléatoirement une équipe au dresseur.
	 * [cite: 303]
	 */
	private void choixEquipe() {
		this.equipe = Equipe.choixEquipeAleatoire();
	}

	// --- Getters ---

	/**
	 * @return Le nom du dresseur.
	 * [cite: 230]
	 */
	public String getNom() {
		return this.nom;
	}

	/**
	 * @return Le nombre de Pokémons dans le sac.
	 * [cite: 230]
	 */
	public int getNbPokemons() {
		return this.sacADos.size();
	}

	// --- Méthodes principales ---

	/**
	 * Capture un nombre aléatoire de Pokémons (1 à 3)
	 * d'un type aléatoire.
	 * [cite: 231-233]
	 */
	public void capturerPokemons() {
		int nbACapturer = random.nextInt(1, MAX_POKEMONS + 1);
		
		for (int i = 0; i < nbACapturer; i++) {
			// Vérifie si le sac est plein
			if (this.sacADos.size() < MAX_POKEMONS) {
				
				// Génère un nom ("Red:Pokemon0", "Red:Pokemon1", etc.)
				String nomPokemon = this.nom + ":Pokemon" + this.getNbPokemons();
				
				// Tire le type au hasard
				int typeAleatoire = random.nextInt(3); // 0, 1, ou 2
				
				Pokemon nouveauPokemon;
				
				switch (typeAleatoire) {
					case 0:
						nouveauPokemon = new PokemonFeu(nomPokemon);
						break;
					case 1:
						nouveauPokemon = new PokemonEau(nomPokemon);
						break;
					case 2:
						nouveauPokemon = new PokemonPlante(nomPokemon);
						break;
					default: 
						// Sécurité
						nouveauPokemon = new PokemonFeu(nomPokemon); 
						break;
				}
				
				this.sacADos.add(nouveauPokemon);
				this.log("Capture du pokémon " + nouveauPokemon);
			} else {
				this.log("Le sac à dos est plein ! Impossible de capturer plus de Pokémon.");
				break; // Sort de la boucle
			}
		}
	}

	/**
	 * Vérifie si le dresseur peut combattre.
	 * [cite: 234-235]
	 * @return true s'il a de l'argent et au moins 1 Pokémon non KO.
	 */
	public boolean peutCombattre() {
		if (this.argent <= 0) {
			return false;
		}
		
		// Cherche un Pokémon non KO
		for (Pokemon p : this.sacADos) {
			if (!p.isKO()) {
				return true; // Trouvé ! [cite: 235]
			}
		}
		
		return false; // Aucun Pokémon valide
	}

	/**
	 * Retourne le premier Pokémon non KO du sac.
	 * [cite: 236]
	 * @return Le prochain Pokémon valide, ou null si aucun.
	 */
	public Pokemon prochainPokemon() {
		for (Pokemon p : this.sacADos) {
			if (!p.isKO()) {
				return p; // Retourne le premier non KO [cite: 236]
			}
		}
		return null; // Tous les Pokémons sont KO
	}

	/**
	 * Vole 10 Pokedollars à un adversaire.
	 * [cite: 237]
	 * @param adversaire Le dresseur qui perd de l'argent.
	 */
	public void prendreArgent(Dresseur adversaire) {
		int montantVole = Math.min(adversaire.argent, VOL_ARGENT);
		adversaire.argent -= montantVole;
		this.argent += montantVole;
	}

	/**
	 * Gère un combat complet contre un autre dresseur.
	 * [cite: 238-242]
	 * @param adversaire Le Dresseur à combattre.
	 */
	public void combatre(Dresseur adversaire) {
		this.log("C'est une belle journée. " + adversaire.getNom() + " je te défie en duel!");
		
		// Boucle principale du combat [cite: 239]
		while (this.peutCombattre() && adversaire.peutCombattre()) {
			
			Pokemon p1 = this.prochainPokemon(); // [cite: 240]
			Pokemon p2 = adversaire.prochainPokemon(); // [cite: 240]
			
			// L'initiateur attaque en premier [cite: 241]
			p1.attaquer(p2);
			
			// Boucle de combat 1v1 [cite: 242]
			while (!p1.isKO() && !p2.isKO()) {
				
				p2.attaquer(p1); // L'adversaire réplique
				
				if (p1.isKO()) {
					break; // p1 est KO
				}
				
				p1.attaquer(p2); // p1 attaque à nouveau
			}
			
			// Annonce le KO
			if (p1.isKO()) {
				adversaire.log(p1.getNom() + " est KO!");
			} else if (p2.isKO()) {
				this.log(p2.getNom() + " est KO!");
			}
		}
		
		// Fin du combat: déterminer le vainqueur [cite: 242]
		if (!this.peutCombattre()) {
			adversaire.log("Hé hé! Je t'ai battu!");
			adversaire.prendreArgent(this); // L'adversaire prend l'argent
		} else {
			this.log("Hé hé! Je t'ai battu!");
			this.prendreArgent(adversaire); // Le dresseur 1 prend l'argent
		}
	}

	/**
	 * Soigne tous les Pokémons du sac.
	 * [cite: 243]
	 */
	public void pokeCenter() {
		for (Pokemon p : this.sacADos) {
			p.soigner();
		}
		this.log("Tous les Pokémons ont été soignés !");
	}

	// --- Affichage ---

	/**
	 * Crée le préfixe pour les logs (basé sur les logs de test)
	 * [cite: 257, 261]
	 */
	private String prefixe() {
		return "[Dresseur " + this.nom + "]";
	}

	/**
	 * Affiche un message dans la console avec le préfixe.
	 * [cite: 257, 261]
	 */
	public void log(String msg) {
		System.out.println(this.prefixe() + ": " + msg);
	}
	
	@Override
	public String toString() {
		// Format basé sur l'exemple [cite: 257-260]
		return "Je m'appelle " + this.nom + "\n" +
			   "j'ai " + this.getNbPokemons() + " Pokemons !\n" +
			   "j'appartiens à l'équipe " + this.equipe + "\n" +
			   "je possède " + this.argent + " $";
	}
	
	// --- Getters Statiques (Partie 4) ---
	
	public static int getNbDresseurs() {
		return nbDresseurs;
	}
	
	public static int getNbDresseursEquipe(Equipe e) {
		return nbParEquipe[e.ordinal()];
	}
}
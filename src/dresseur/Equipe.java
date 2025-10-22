package dresseur;

import java.util.Random;

/**
 * Enum modélisant les 3 grandes équipes de dresseurs.
 * [cite: 302]
 */
public enum Equipe {
	SAGESSE("Sagesse"),
	BRAVOURE("Bravoure"),
	INTUITION("Intuition");
	
	private String nom;
	private static Random random = new Random();
	
	/**
	 * Constructeur privé pour l'enum.
	 * @param nom Le nom à afficher.
	 */
	private Equipe(String nom) {
		this.nom = nom;
	}
	
	@Override
	public String toString() {
		return this.nom;
	}
	
	/**
	 * Tire une équipe au hasard.
	 * [cite: 303]
	 * @return Une Equipe (SAGESSE, BRAVOURE ou INTUITION).
	 */
	public static Equipe choixEquipeAleatoire() {
		int choix = random.nextInt(Equipe.values().length);
		return Equipe.values()[choix];
	}
}
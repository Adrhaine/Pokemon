package pokemons;

import java.util.Random;

public abstract class Pokemon {
	private String nom;
	private int niveau;
	protected int hp;
	private int atk;
	private TypePokemon type;
	private static final int NIVEAU_MAX = 10;
	
	private static Random random = new Random();
	
	public Pokemon(String nom, TypePokemon type) {
		this.nom = nom;
		this.type = type;
		
		this.niveau = random.nextInt(1, NIVEAU_MAX + 1);
		this.hp = 2 * this.niveau;
		this.atk = (this.niveau / 2) + 1;
	}
	
	public String getNom() {
		return this.nom;
	}
	
	public int getNiveau() {
		return this.niveau;
	}
	
	public int getHp() {
		return this.hp;
	}
	
	public int getAtk() {
		return this.atk;
	}
	
	public TypePokemon getType() {
		return this.type;
	}
	
	public boolean isKO() {
		return this.hp == 0;
	}
	
	public void soigner() {
		this.hp = 2 * this.niveau;
	}
	
	public abstract void attaquer(Pokemon p);
	
	public abstract void subir(Pokemon p);
	
	@Override
	public String toString() {
		return "Je m'appelle " + this.nom + " !\n" +
			   "je suis de niveau " + this.niveau + "\n" +
			   "j'ai " + this.hp + " points de vie\n" +
			   "mon attaque de base est de " + this.atk + "\n" +
			   "je suis de type " + this.type;
	}
	
	private String prefixe() {
		return "[Pokemon "+ this.nom + "]: ";
	}
	
	public void log(String msg) {
		System.out.println(this.prefixe() + msg);
	}
}
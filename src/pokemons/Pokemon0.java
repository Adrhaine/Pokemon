package pokemons;

import java.util.Random;

public class Pokemon0 {
	private String nom;
	private int niveau;
	private int hp;
	private int atk;
	
	private static Random random = new Random();
	
	public Pokemon0(String nom) {
		this.nom = nom;
		
		this.niveau = random.nextInt(1, 11);
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
	
	public boolean isKO() {
		return this.hp == 0;
	}
	
	public void soigner() {
		this.hp = 2 * this.niveau;
	}
	
	public void attaquer(Pokemon0 p) {
		this.log("J'attaque " + p.getNom());
		
		p.hp = p.hp - this.atk;
		
		if (p.hp < 0) {
			p.hp = 0;
		}
	}
	
	@Override
	public String toString() {
		return "Je m'appelle " + this.nom + " !\n" +
			   "je suis de niveau " + this.niveau + "\n" +
			   "j'ai " + this.hp + " points de vie\n" +
			   "mon attaque de base est de " + this.atk;
	}
	
	private String prefixe() {
		return "[Pokemon "+ this.nom + "]: ";
	}
	
	public void log(String msg) {
		System.out.println(this.prefixe() + msg);
	}
}

package it.polito.tdp.PremierLeague.model;

public class Avversari implements Comparable<Avversari>{
	
	private Player a1;
	private Integer  peso;
	
	public Avversari(Player a1, Integer peso) {
		this.a1 = a1;
		this.peso = peso;
	}

	public Player getA1() {
		return a1;
	}

	public void setA1(Player a1) {
		this.a1 = a1;
	}

	public Integer getPeso() {
		return peso;
	}

	public void setPeso(Integer peso) {
		this.peso = peso;
	}
	
	public String toString() {
		return this.a1+":"+ this.peso+"\n";
	}

	public int compareTo(Avversari other) {
		
		return (other.getPeso().compareTo(this.peso));
	}

	
	
	

}

package it.polito.tdp.PremierLeague.model;

public class Adiacenze {
	
	private Player p1;
	private Player p2;
	private int sum1;
	private int sum2;
	
	public Adiacenze(Player p1, Player p2, int sum1, int sum2) {
		super();
		this.p1 = p1;
		this.p2 = p2;
		this.sum1 = sum1;
		this.sum2 = sum2;
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public int getSum1() {
		return sum1;
	}

	public void setSum1(int sum1) {
		this.sum1 = sum1;
	}

	public int getSum2() {
		return sum2;
	}

	public void setSum2(int sum2) {
		this.sum2 = sum2;
	}
	
	

}

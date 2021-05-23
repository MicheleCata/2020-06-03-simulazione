package it.polito.tdp.PremierLeague.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jgrapht.Graphs;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import it.polito.tdp.PremierLeague.db.PremierLeagueDAO;

public class Model {
	
	private SimpleDirectedWeightedGraph<Player, DefaultWeightedEdge> grafo;
	private PremierLeagueDAO dao;
	private Map<Integer, Player> idMap;
	
	public Model() {
		dao = new PremierLeagueDAO();
		idMap= new HashMap <Integer, Player>();
		dao.listAllPlayers(idMap);
	}
	
	public void creaGrafo (double x) {
		grafo = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);
		// Aggiungo i vertici
		Graphs.addAllVertices(grafo, dao.getVertici(x, idMap));
		
		// Aggiungo gli archi
		for (Adiacenze a: dao.getAdiacenze( idMap)) {
			if (this.grafo.containsVertex(a.getP1()) && this.grafo.containsVertex(a.getP2())) {
				if (a.getSum1()>a.getSum2() && (a.getSum1()-a.getSum2())>0)
					Graphs.addEdgeWithVertices(grafo, a.getP1(), a.getP2(), a.getSum1()-a.getSum2());
				else if (a.getSum2()>a.getSum1() && a.getSum2()-a.getSum1()>0)
					Graphs.addEdgeWithVertices(grafo, a.getP2(), a.getP1(), a.getSum1()-a.getSum2());
			}
		}
		System.out.format("Grafo creato con %d vertici e %d archi\n",
 				this.grafo.vertexSet().size(), this.grafo.edgeSet().size()); 
	}
	
	public int getNumVertici() {
		return this.grafo.vertexSet().size();
	}
	
	public int getNArchi() {
		return this.grafo.edgeSet().size();
	}
	
	public TopPlayer getTopPlayer() {
		Integer max=0;
		Player best =null;
		for (Player p: grafo.vertexSet()) {
			if (grafo.outDegreeOf(p)>max) {
				max=grafo.outDegreeOf(p);
				best=p;
			}
		}
		TopPlayer top = new TopPlayer();
		top.setP(best);
		List<Avversari> avversari = new ArrayList<>();
		for (DefaultWeightedEdge e: grafo.outgoingEdgesOf(top.getP())) {
				avversari.add(new Avversari (grafo.getEdgeTarget(e),(int) grafo.getEdgeWeight(e)));
		}
		Collections.sort(avversari);
		top.setAvversari(avversari);
		
		return top;
		
	}

}

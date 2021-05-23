package it.polito.tdp.PremierLeague.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import it.polito.tdp.PremierLeague.model.Action;
import it.polito.tdp.PremierLeague.model.Adiacenze;
import it.polito.tdp.PremierLeague.model.Player;

public class PremierLeagueDAO {
	
	public List<Player> listAllPlayers(Map<Integer, Player> idMap){
		String sql = "SELECT * FROM Players";
		List<Player> result = new ArrayList<Player>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Player player = new Player(res.getInt("PlayerID"), res.getString("Name"));
				
				idMap.put(player.getPlayerID(), player);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Action> listAllActions(){
		String sql = "SELECT * FROM Actions";
		List<Action> result = new ArrayList<Action>();
		Connection conn = DBConnect.getConnection();

		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {

				Action action = new Action(res.getInt("PlayerID"),res.getInt("MatchID"),res.getInt("TeamID"),res.getInt("Starts"),res.getInt("Goals"),
						res.getInt("TimePlayed"),res.getInt("RedCards"),res.getInt("YellowCards"),res.getInt("TotalSuccessfulPassesAll"),res.getInt("totalUnsuccessfulPassesAll"),
						res.getInt("Assists"),res.getInt("TotalFoulsConceded"),res.getInt("Offsides"));
				
				result.add(action);
			}
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Player> getVertici (double x, Map<Integer, Player> idMap) {
		String sql = "SELECT PlayerID "
				+ "FROM actions "
				+ "group by PlayerID "
				+ "having AVG(Goals) > ?";
		
		
		List<Player> result = new ArrayList<>();
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setDouble(1, x);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				result.add(idMap.get(res.getInt("PlayerID")));
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Adiacenze> getAdiacenze(Map<Integer, Player> idMap ) {
		String sql = "SELECT a1.PlayerID as p1, a2.PlayerID as p2, SUM(a1.`TimePlayed`) as sum1, SUM(a2.`TimePlayed`) as sum2 "
				+ "From Actions a1, Actions a2 "
				+ "where a1.`MatchID`=a2.`MatchID` AND a1.`TeamID`<>a2.`TeamID` AND a1.starts=1 AND a2.starts=1 AND a1.playerId > a2.playerId "
				+ "group by a1.PlayerID, a2.PlayerID ";
				

		
		List<Adiacenze> result = new ArrayList<>();
		
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet res = st.executeQuery();
			while (res.next()) {
				Player p1= idMap.get(res.getInt("p1"));
				Player p2= idMap.get(res.getInt("p2"));
				if (p1!=null && p2!=null)
				result.add(new Adiacenze(p1,p2, res.getInt("sum1"), res.getInt("sum2")));
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}

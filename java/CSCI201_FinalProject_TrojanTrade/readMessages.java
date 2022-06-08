package CSCI201_FinalProject_TrojanTrade;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class readMessages {
	private int from_id; 
	private int to_id; 
	private String text;
	private Date time;
	
	public readMessages(int from_id, int to_id, String text, Date time) {
		this.from_id = from_id; 
		this.to_id = to_id;
		this.text = text;
		this.time = time;
	}
	
	public readMessages(ResultSet rs) throws SQLException {
		this.from_id = rs.getInt("from_id"); 
		this.to_id = rs.getInt("to_id"); 
		this.text = rs.getString("text"); 
		this.time = rs.getDate("time"); 
	}
	
	private User getUser(int id) {
		String sql = "SELECT * FROM user WHERE id=?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1,  id);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					return new User(rs);
				}
			}
		catch(Exception e) {	}
		return null;	
	}
	
	
	public User getUserFrom() { return getUser(from_id);}
	public User getUserTo() { return getUser(to_id);}
	public String getText() {return this.text;}
	public Date getTime() {return this.time;}
	
}



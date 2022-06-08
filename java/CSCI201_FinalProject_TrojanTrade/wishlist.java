package CSCI201_FinalProject_TrojanTrade;

import java.sql.ResultSet;
import java.sql.SQLException;

public class wishlist {
	private int id;
	private int user_id;
	private int item_id;
	
	public wishlist(int id, int user_id, int item_id) {
		this.id = id;
		this.user_id = user_id;
		this.item_id = item_id;
	}
	
	public wishlist(ResultSet rs) throws SQLException {
		this.id = rs.getInt("id");
		this.user_id = rs.getInt("user_id");
		this.item_id = rs.getInt("item_id");
	}
	
	public int getId() {return this.id;}
	public int getUserId() {return this.user_id;}
	public int getItemId() {return this.item_id;}
	
}

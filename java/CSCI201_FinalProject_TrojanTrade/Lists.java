package CSCI201_FinalProject_TrojanTrade;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Lists {
	public static boolean checkWishList(int user_id, int item_id) {
		boolean status = false;
		String sql = "SELECT * FROM wishlist WHERE wishlist.user_id = ? and wishlist.item_id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  user_id);
			ps.setInt(2, item_id);
			ResultSet rs = ps.executeQuery();
			status = rs.next();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static Item getItemById(int item_id) {
		Item item = null;
		String sql = "SELECT * FROM item WHERE id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,  item_id);
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				item = new Item(rs);
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return item;
	}
	public static void main(String[] args) {
	}

}

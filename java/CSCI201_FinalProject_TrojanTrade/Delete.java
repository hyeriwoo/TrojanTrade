package CSCI201_FinalProject_TrojanTrade;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/Delete")
public class Delete extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static void deleteItem(int item_id) {
		String sql = "DELETE FROM item WHERE id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, item_id);
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	private static void deleteFav(int item_id) {
		String sql = "DELETE FROM wishlist WHERE item_id = ?";
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(mySQL.db, mySQL.user, mySQL.pwd);
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, item_id);
			ps.executeUpdate();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
		      throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("loggedUser");
		if(user == null) {
			out.println("<script>");
			out.println("alert('Time Out!');");
			out.println("location='home.jsp';");
			out.println("</script>"); 
			request.getRequestDispatcher("/home.jsp").forward(request, response);
			return;
		}
		int id =  Integer.parseInt(request.getParameter("index"));
		deleteItem(id);
		deleteFav(id);
		
		out.println("<script>");
		out.println("alert('The item has been removed!');");
		out.println("location='history.jsp';");
		out.println("</script>"); 
		session.setAttribute("postlist", Login.loadPostlist(user.getId()));
		session.setAttribute("wishlist", Favorite.getWishlist(user.getId()));
		request.getRequestDispatcher("/history.jsp").forward(request, response);
	}
}

package CSCI201_FinalProject_TrojanTrade;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/history")
public class history extends HttpServlet {

   /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

   public void init() throws ServletException {
      // Do required initialization
   }

   public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	   	response.setContentType("text/html");

		PrintWriter out = response.getWriter();
		
		HttpSession session = request.getSession();
		
		String name = request.getParameter("user");
		
		User user = (User) session.getAttribute("loggedUser");
		
		if(user == null) {
			String output = "<!DOCTYPE html>\r\n"
					+ "<html lang=\"en\">\r\n"
					+ "<head>\r\n"
					+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n"
					+ "\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Lobster&display=swap\" rel=\"stylesheet\">\r\n"
					+ "\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
					+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
					+ "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap\" rel=\"stylesheet\"> \r\n"
					+ "\r\n"
					+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"page.css\">\r\n"
					+ "    \r\n"
					+ "    <meta charset=\"UTF-8\">\r\n"
					+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
					+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
					+ "    <title>Document</title>\r\n"
					+ "</head>\r\n"
					+ "<body>\r\n"
					+ "    <div class=\"header\">\r\n"
					+ "        <div>\r\n"
					+ "            <a class=\"header-logo\" href=\"home.jsp\">Trojan Trade</a>\r\n"
					+ "        </div>\r\n"
					+ "        <div class=\"header-info\">\r\n"
					+ "            <div class=\"header-info-1\">\r\n"
					+ "                <a href=\"home.jsp\">Home</a>\r\n"
					+ "            </div>\r\n"
					+ "            <div class = \"header-info-2\">\r\n"
					+ "                <a href = \"messages\">Messages</a>\r\n"
					+ "            </div>\r\n"
					+ "        </div>\r\n"
					+ "    </div>\r\n"
					+ "    \r\n"
					+ "    <div class=\"main\">\r\n"
					+ "       <div class=\"main-content\">\r\n"
					+ "            <div class = \"message-title\"> <hr>"
					+ "                <p>You don't seem to be logged in, please try again.</p> </div> </div> </div </body> </html>";
			
			out.println(output);
			return;
		}
		
		int ID = user.getId();
		int otherID = Integer.parseInt(request.getParameter("id"));
		
		String sql = "SELECT * FROM readMessages WHERE from_id = ? AND to_id = ? \n"
				+ "UNION\n"
				+ "SELECT * FROM readMessages WHERE from_id = ? AND to_id = ? \n"
				+ "UNION\n"
				+ "SELECT * FROM unreadMessages WHERE from_id = ? AND to_id = ? \n"
				+ "UNION\n"
				+ "SELECT * FROM unreadMessages WHERE from_id = ? AND to_id = ?\n"
				+ "ORDER BY time ASC";

		ArrayList<String> messages = new ArrayList<String>();
		ArrayList<Boolean> yours = new ArrayList<Boolean>();
		
		try {
			Class.forName("com.mysql.jdbc.Driver"); 
		}
		catch(ClassNotFoundException e) {
			System.out.println("There was a problem with the driver.");
		}
		
		try {
			Connection conn = DriverManager.getConnection(mySQL.db,mySQL.user,mySQL.pwd);
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, ID);
			st.setInt(2, otherID);
			st.setInt(3, otherID);
			st.setInt(4, ID);
			st.setInt(5, ID);
			st.setInt(6, otherID);
			st.setInt(7, otherID);
			st.setInt(8, ID);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String toPrint;
				int from_id = rs.getInt(1);
				String text = rs.getString(3);
				String time = rs.getString(4);
				if(from_id == ID) {
					toPrint = "[" + time + "] You said: " + text + "\r\n";
					yours.add(true);
				}
				else {
					toPrint = "<b>[" + time + "] " + name + " said: " + text + "</b>\r\n";
					yours.add(false);
				}
				messages.add(toPrint);
			}
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
		// Display results
		String output = "<!DOCTYPE html>\r\n"
				+ "<html lang=\"en\">\r\n"
				+ "<head>\r\n"
				+ "    <link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\" integrity=\"sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3\" crossorigin=\"anonymous\">\r\n"
				+ "\r\n"
				+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
				+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
				+ "    <link href=\"https://fonts.googleapis.com/css2?family=Lobster&display=swap\" rel=\"stylesheet\">\r\n"
				+ "\r\n"
				+ "    <link rel=\"preconnect\" href=\"https://fonts.googleapis.com\">\r\n"
				+ "    <link rel=\"preconnect\" href=\"https://fonts.gstatic.com\" crossorigin>\r\n"
				+ "    <link href=\"https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap\" rel=\"stylesheet\"> \r\n"
				+ "\r\n"
				+ "    <link rel=\"stylesheet\" type=\"text/css\" href=\"page.css\">\r\n"
				+ "    \r\n"
				+ "    <meta charset=\"UTF-8\">\r\n"
				+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
				+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n"
				+ "    <title>Document</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "    <div class=\"header\">\r\n"
				+ "        <div>\r\n"
				+ "            <a class=\"header-logo\" href=\"home.jsp\">Trojan Trade</a>\r\n"
				+ "        </div>\r\n"
				+ "        <div class=\"header-info\">\r\n"
				+ "            <div class=\"header-info-1\">\r\n"
				+ "                <a href=\"home.jsp\">Home</a>\r\n"
				+ "            </div>\r\n"
				+ "            <div class = \"header-info-2\">\r\n"
				+ "                <a href = \"messages\">Messages</a>\r\n"
				+ "            </div>\r\n"
				+ "        </div>\r\n"
				+ "    </div>\r\n"
				+ "    \r\n"
				+ "    <div class=\"main\">\r\n"
				+ "       <div class=\"main-content\">\r\n"
				+ "            <div class = \"message-title\"> <hr>";
		
		output = output + "                <p  style=\"padding-left:100px\"? Messages with " + name + ": </p> \n";
		output = output + "            </div>\r\n"
						+ "            <div class=\"message-history\">";
		for(int i=0;i<messages.size();i++) {
			if(yours.get(i)) {
				output = output + "                <p style=\"background-color:Bisque;padding-left:100px\">" + messages.get(i) + "</p>";
			}
			else {
				output = output + "                <p style=\"background-color:Azure;padding-left:100px\">" + messages.get(i) + "</p>";
			}
		}
		output = output + "<form name=\"messageForm\" method=\"post\" style = \"padding-left:100px\" action=\"send?from=" + ID + "&to=" + otherID + "\">\r\n"
				+ "                    Reply: <input type=\"text\" name=\"message\" maxlength = \"500\"/> <br/>\r\n"
				+ "                    <input type=\"image\" src = \"send.png\" />\r\n"
				+ "                </form>\r\n"
				+ "            </div>\r\n"
				+ "       </div>\r\n"
				+ "    </div>\r\n"
				+ "\r\n"
				+ "    \r\n"
				+ "    <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\" integrity=\"sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p\" crossorigin=\"anonymous\"></script>\r\n"
				+ "</body>\r\n"
				+ "</html>";
		
		out.println(output);
	}

   public void destroy() {
      // do nothing.
   }
}

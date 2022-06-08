package CSCI201_FinalProject_TrojanTrade;

import java.util.*;
import java.io.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


@WebServlet("/messages")
public class messages extends HttpServlet {

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
		
		User user = (User) request.getSession().getAttribute("loggedUser");
		
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
		
		String sql = "SELECT nickname, id FROM user WHERE id IN (SELECT from_id FROM readMessages WHERE to_id = ? UNION SELECT from_id FROM unreadMessages WHERE to_id = ?)";
	
		ArrayList<Integer> ids = new ArrayList<Integer>();
		ArrayList<String> names = new ArrayList<String>();
		
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
			st.setInt(2, ID);
		    ResultSet rs = st.executeQuery();
			while (rs.next()) {
				String name = rs.getString(1);
				int id = rs.getInt(2);
				names.add(name);
				ids.add(id);
			}
		} catch (SQLException sqle) {
			System.out.println ("SQLException: " + sqle.getMessage());
		}
		
        String check = "";
        String name = "";
        int id = 0;
     	 if(user == null) { 
     		 check = "0";
     		 name = "";
     		 id = 0;
        }
     	 else { 
     		 check = "1";
     		 name = user.getNickname();
     		 id = user.getId();
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
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://www.w3schools.com/w3css/4/w3.css\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\r\n"
				+ "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css\" integrity=\"sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk\" crossorigin=\"anonymous\">\r\n"
				+ "<script src=\"//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js\"></script>\r\n"
				+ "<script src=\"//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js\"></script>\r\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\" href=\"//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css\">\r\n	"
				+ "    <title>Message</title>\r\n"
				+ "</head>\r\n"
				+ "<body>\r\n"
				+ "<div id='nav'>\n"
				+ "<nav>"
				+ "<div class='saleat'> <a href='home.jsp' id='banner'><img src='usc_logo.png' width='50px'></a> </div>\r\n"
				+ "<span id= 'logCheck' data-prodnumber=" + check + "></span>\r\n"
			    + "<div class='dropdown home' id='dropdown'>\r\n"
			    + "<button class='btn btn-secondary dropdown-toggle' type='button' id='dropdownMenuButton' data-toggle='dropdown' aria-haspopup='true' aria-expanded='false'>\r\n"
			    + "Welcome, " + user.getNickname() + "<i class='fa fa-user'></i></button>\r\n"
			    + "<div class='dropdown-menu' aria-labelledby='dropdownMenuButton'>\r\n"
			    + "<a class='dropdown-item' href='home.jsp'>Home</a>\r\n"
				+ "<a class='dropdown-item' href='favorite.jsp'>Favorite</a>\r\n"
				+ "<a class='dropdown-item' href='history.jsp'>History</a>\r\n"
				+ "<a class='dropdown-item' href='Setting?index="+ user.getId() + "'>Setting</a>\r\n"
				+ "<a class='dropdown-item' href='javascript: logout()'>Log out</a>\r\n"
				+ "<form method='POST' id='logout-form' action='Logout'></form>\r\n"
				+ "</div>\r\n"
				+ "</div>\r\n"
				+ "<div class='home'> <a href='home.jsp' id='home'> Home  </a>\r\n"
				+ "<a href='login.jsp' id='login'> Login / Register</a> </div>\r\n"
			    + "</nav>\r\n"
			    + "</div>\r\n"
				+ "    <div class=\"main\">\r\n"
				+ "       <div class=\"main-content\">\r\n"
				+ "            <div class=\"message history\">\r\n"
				+ "<script src='https://code.jquery.com/jquery-3.5.1.min.js'\r\n"
				+ "integrity='sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0='\r\n"
				+ "crossorigin='anonymous'></script>\r\n"
				+ "<script type='text/javascript'>\r\n"
				+ "function logout() {\r\n"
				+ "document.getElementById('logout-form').submit();\r\n"
				+ 		"alert('Successfully logged out!');\r\n"
				+ "}\r\n"
				+ "</script>\r\n"
				+ "<script>\r\n"
				+ "$(window).on('load', function() {\r\n"
				+ "$('#filter_form').css('display', 'none');\r\n"
				+ "if ($('#logCheck').attr('data-prodnumber') == '0') {\r\n"
				+ "$('#login').css('display', 'inline');\r\n"
				+ "$('#home').css('display', 'inline');\r\n"
				+ "$('#dropdown').css('display', 'none');\r\n"
				+ "}\r\n"
				+ "else if($('#logCheck').attr('data-prodnumber') == '1') {\r\n"
				+ "$('#login').css('display', 'none');\r\n"
				+ "$('#home').css('display', 'none');\r\n"
				+ "$('#dropdown').css('display', 'inline');\r\n"
				+  "}\r\n"
				+  "});\r\n"
				+ "$('#filter_btn').click(function() {\r\n"
				+    "$('#filter_form').slideToggle('slow');\r\n"
				+    "});\r\n"
				+  "</script>\r\n";
		for(int i=0;i<names.size();i++) {
			output = output + "                <br></br>\r\n";
			output = output + "                <a href = \"history?user=" + names.get(i) + "&id=" + ids.get(i) + "\">Messages with " + names.get(i) + "</a>\r\n";
		}
		output = output + "</div> <br/> \r\n"
				+ "          <form name=\"messageForm\" method=\"post\" action=\"new?from=" + Integer.toString(ID) + "\">\r\n"
				+ "              <input type=\"submit\" value=\"Message a new user\" /> <br/> \r\n"
				+ "          </form>\r\n"
				+ "       </div>\r\n"
				+ "    </div>\r\n"
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

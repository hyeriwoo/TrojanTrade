<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList"%>
<%@page import="CSCI201_FinalProject_TrojanTrade.Item"%>
<%@page import="CSCI201_FinalProject_TrojanTrade.User"%>
<%@page import="CSCI201_FinalProject_TrojanTrade.Lists"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css" integrity="sha384-9aIt2nRpC12Uk9gS9baDl411NQApFmC26EwAOH8WgZl5MYYxFfc+NcPb1dKGj7Sk" crossorigin="anonymous">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" type="text/css" href="//netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">	<title>Trojan Trade</title>
	 <style>
    @font-face {
      font-family: AlfaSlabOne;
	    src: url("AlfaSlabOne-Regular.ttf");
    }

    body, html {
      text-align: center;
      font-family: AlfaSlabOne;
      font-size: 15px;
      color: #626262;
    }

    /* navigation */
    #nav {
      position: relative;
      width: auto;
      height: 59px;
      list-style-type: none;
      margin: 10px;
      padding: 10px;
      margin-bottom: 20px;
    }

    nav a {
      text-decoration: none;
    }

    div.saleat {
      position: absolute;
      left: 20%;
    }

    div.home{
      padding-top: 15px;
      position: absolute;
      right: 20%;
    }
    #home {
      padding-right:10px;
      color: #626262;
    }
    #home:hover {
      color: #000000;
    }

    #login {
      color: #626262;
    }

    #login:hover {
      color: #000000;
    }

    #banner {
      position: relative;
      left: 1%;
    }
    /* navigation end */

    /* banner start */
    #logo {
 	  padding: 80px;
      font-family: AlfaSlabOne;
      font-size: 80px;
      color: #B31818;
      text-shadow: 3px 3px #660000;
    }

    #image-div {
      padding: 0px;
      width: 100%;
      display: block;
      margin-left: auto;
      margin-right: auto;
      background-image: url("trojan2.jpg");
      background-size: cover;
      pardding: 50px;
    }
    /* banner end */
    #addPost {
      position:fixed;
      top: 60%;
      right: 0;
      margin: 140px;
      color: #FFC72C;
    }
    h2 {
    	color: #B31818;
    	font-size: 50px;
      	text-shadow: 1px 1px #660000;
    	padding: 30px;
    	margin-top: 30px;
    	margin-bottom: 30px;
    	background-image: url("trojan2.jpg");
      	background-size: cover;
    }
    
    .res{
      border-top: 1px solid #9c9c9c;
      width: 60%;
      margin-left: auto;
      margin-right: auto;
      display: flex;
      padding: 30px;
      text-decoration: none;
    }
    
    .res #main_image {
      border-radius: 20px;
      width: 300px;
      height: 300px;
      object-fit: fill;
    }
    
    .detail #user {
    	color: #3A3A3A;
    	font-size: 20px;
    }
    
    .detail #fav img {
    	width: 40px;
    	height: 40px;
    }
    
    .detail {
    	padding-left: 20%;
    	padding-top: 30px;
    	text-align: left;
    }
    
    #chat {
    	display: flex;
    	padding-top: 10px;
    }
    
    
    #chat form button {
      border: none;
      border-radius: 3px;
      background-color: #B31818;
      height: 40px;
      width: 40px;
      cursor: pointer;
      color: white;
      margin-left: 20px;
    }
	/* Modal Content */
	#modal-content {
		background-color: #FFC72C;
		margin: auto;
		padding: 40px;
		border: 1px solid #888;
		width: 40%;
		font-size: 150%;
		color: #990000;
	}
	/* The Close Button */
	.close {
		color: #aaaaaa;
		float: right;
		font-size: 28px;
		font-weight: bold;
	}
	.close:hover, .close:focus {
		color: #000;
		text-decoration: none;
		cursor: pointer;
	}
    
    </style>
</head>
<body>
<!-- navigation bar -->
  <div id="nav">
    <nav>
      <div class="saleat"> 	<a href="home.jsp" id="banner"><img src="usc_logo.png" width="50px"></a> </div>
      <% User user = (User)session.getAttribute("loggedUser");
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
      	 %>
      <span id="logCheck" data-prodnumber="<%=check%>"></span>
      <div class="dropdown home" id="dropdown">
  		<button class="btn btn-secondary dropdown-toggle" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false"> 
  		Welcome, <%=name%> <i class="fa fa-user"></i></button>
		  <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
		  	<a class="dropdown-item" href="home.jsp">Home</a>
		    <a class="dropdown-item" href="favorite.jsp">Favorite</a>
		    <a class="dropdown-item" href="history.jsp">History</a>
		    <a class="dropdown-item" href="Setting?index=<%=id%>">Setting</a>
		    <a class="dropdown-item" href="javascript: logout()">Log out</a>
		    <form method="POST" id="logout-form" action="Logout"></form>
		  </div>
	  </div>
	  <div class="home"> <a href="home.jsp" id="home"> Home  </a>
	  <a href="login.jsp" id="login"> Login / Register</a> </div>
    </nav>
  </div>
  
  <% Item item = (Item)session.getAttribute("detail"); 
  	 String heart = "heart1.png";
  	 if(item != null) {
  		if(user != null) {
	  	  	boolean checkWish = Lists.checkWishList(user.getId(), item.getId());
	  	  	if(checkWish == true) {
	  	  		heart = "heart2.png";
	  	  	}
	  	  	else {
	  	  		heart = "heart1.png";
	  	  	}
  	  	}%>
   <h2><%=item.getTitle()%></h2>
   <div class="res">
    <img id="main_image" src="<%=item.getImage()%>">
    <div class="detail">
	  <a id="user" href="Setting?index=<%=item.getUserId()%>"><i class="fa fa-user-circle-o fa-lg"></i> <%=User.getNameById(item.getUserId())%></a>
      <h4>Status: <%=item.getStatus()%></h4>
      <h4>Price: <%=item.getPrice()%></h4>
      <h4>Location: <%=item.getLocation()%></h4>
      <h4><%=item.getDescription()%></h4>
      <h4><a href ="https://twitter.com/intent/tweet?text=Look%20what%20I%20found%20on%20TrojanTrade!">Tweet About It!</a></h4>
      <div id="chat">
      <a id="fav" href="Favorite?index=<%=item.getId()%>"><img src="<%=heart%>"></a>
      <form action="messages" method="GET"><button type="submit"><i class="fa fa-comments fa-2x"></i></button></form>
      </div>
    </div> 
  </div>
  <%}%>
   <a href="post.jsp"><i id="addPost" class="fa fa-plus-square" style="font-size:120px;"></i></a>
  	  <%if(user!= null) { %>
  <script>
  		var webSocketUri =  "ws://localhost:8080/CSCI201_FinalProject_TrojanTrade/ws";
		var socket = new WebSocket(webSocketUri);
		socket.onopen = function(event) {
			console.log("Connected!");
		};
			
		socket.onmessage = function(event){
				//alert(event.data);
			document.getElementById("modal-text").innerHTML = event.data;
			document.getElementById("myModal").style.display = "block";
			console.log(event.data);
		};
			
		socket.onclose = function(event) {
			console.log("Disconnected!");
		};
</script>	
<%} %>	
<!-- The Modal -->
<div id="myModal" class="modal">
  <!-- Modal content -->
  <div id="modal-content">
    <span class="close">&times;</span>
    <p><strong id="modal-text"></strong></p>
  </div>
</div>	
<script>
// Get the modal
var modal = document.getElementById("myModal");
// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];
// When the user clicks on <span> (x), close the modal
span.onclick = function() {
  modal.style.display = "none";
}
</script>
    <script src="https://code.jquery.com/jquery-3.5.1.min.js"
  integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
  crossorigin="anonymous"></script>
   <script type="text/javascript">
  function logout() {
	  	document.getElementById("logout-form").submit();
		alert("Successfully logged out!");
	}
  </script>
  <script>
  $(window).on('load', function() {
      $("#filter_form").css("display", "none");
      if ($("#logCheck").attr("data-prodnumber") == "0") {
    	  $("#login").css("display", "inline");
    	  $("#home").css("display", "inline");
    	  $("#dropdown").css("display", "none");
    	  $("#addPost").css("display", "none");
      }
      else if($("#logCheck").attr("data-prodnumber") == "1") {
    	  $("#login").css("display", "none");
    	  $("#home").css("display", "none");
    	  $("#dropdown").css("display", "inline");
    	  $("#addPost").css("display", "inline");
      }
    });

    // slide down and up the input
    $("#filter_btn").click(function() {
      $("#filter_form").slideToggle("slow");
    });
  </script>
</body>
</html>
</body>
</html>
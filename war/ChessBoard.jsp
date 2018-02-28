<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import="com.scrumchess.ajaxservlet.ChessBoardHTMLBuilder"
     %>

   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js">
</script>
<script type="text/javascript" src="${pageContext.request.contextPath}/ChessBoard.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/engine.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/engine_wrapper.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath}/scrumchessajax.js"></script>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/chess_board.css" />

<title>Scrum-Chess</title>
</head>
<body>



<div id="navbar">

</div>


<div id="gameSection"> 
<%
	out.write(ChessBoardHTMLBuilder.getDivBoardBottomUp());
%>

<!-- insert game controls  -->

<div id="statusBox">

	

</div>


</div>
<button id="button">Start Animation</button>
<div id="debugOutput">
</div>



</body>
</html>
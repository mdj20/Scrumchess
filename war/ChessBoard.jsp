<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
     import="com.scrumchess.mdj20.ChessBoardHTMLBuilder"
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
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/chess_board.css" />

<title>Chess</title>
</head>
<body>

<p>
HELLO WORLD!
</p>

<button id="button">Start Animation</button>
<%

	out.write(ChessBoardHTMLBuilder.getDivBoardBottomUp());

%>

<div id="debugOutput">
</div>



</body>
</html>
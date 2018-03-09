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
	<div id="chatBox">
		<div id="chatOutputDiv"></div>
 		<div id="chatInputDiv"> 
 			<textarea id="chatTextArea"></textarea>
 		</div>
	</div>

	<%
		out.write(ChessBoardHTMLBuilder.getDivBoardBottomUp());
	%>



	<div id="statusBox">
		<div>WHITE:</div>
		<div id="whiteusernamedisplay"></div>
		<input type="text" name="userName">
	</div>



</div>
<button id="button">  Button 1 </button>
<button id="button2"> Button 2 </button>
<button id="button3"> Button 3 </button>
<button id="button4"> Button 4 </button>
<button id="button5"> Button 5 </button>
<br>
<button id="button6">  Button 6 </button>
<button id="button7"> Button 7 </button>
<button id="button8"> Button 8 </button>
<button id="button9"> Button 9 </button>
<button id="button10"> Button 10 </button>
<br>
<input  type="text" id="fromInput">
<br>
<input type="text" id="toInput">
<div id="debugOutput">
</div>




</body>
</html>
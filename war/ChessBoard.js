/**
 *  // much of this is done with jquerry
 */
var p = ['\u9812','\u9823'];

var _BoardInfo_proto = {} // boardInfo prototype!

var divPiecePrefix = "dp";
var highlightColor = "yellow";

// denotes the number of pieces per type
var pieceCount = {'K':1,'Q':1,'R':2,'B':2,'N':2,'P':8,'k':1,'q':1,'r':2,'b':2,'n':2,'p':8};

// object that contains info about the pieces on the board
function DivPiece(type, id, image){
	this.promoted = false;
	this.index = id ; // index OF array
	this.type = type;
	this.image = image;
	this.loc = -1;
	this.d = $("<div/>", { "id":(divPiecePrefix+""+id) , "class":'trans square'} );
	$(this.d).html(image);
}
var _DivPiece_proto = {}; // DivPiece prototype. 

_DivPiece_proto.showPieceDiv = function(){
	$(this.d).show();
}

_DivPiece_proto.appendPDtoBodyAndHide = function(){
	this.d.appendTo('body');
	$(this.d).hide();
}

DivPiece.prototype = _DivPiece_proto;

//an aggregate class that will describe the view board and it's contents 
function BoardInfo(fenString,pieceCount,pieceImageMap){
	this.pieceImageMap = pieceImageMap;
	this.squares = new Array(64);
	// Loop is replacing this.squares.fill(-1), it's intermittently  causing rendering issues; 
	for (var i = 0; i< this.squares.length ; i++ ){
		this.squares[i] = -1;
	}
	this.squareOffsets = this.getSquareOffsets(new Array(64));  // array for each sqauares offset
	this.pieces = makePieceDivArray(fenString,pieceCount,pieceImageMap);  // array of DivPieces
	this.appendAndHideArray(this.pieces);
}

var _BoardInfo_proto = {} // boardInfo prototype.
// start of the board info object prototypes 
_BoardInfo_proto.findFreePiece = function(inType) {
	var ret = -1;
	for (var i = 0 ; i < this.pieces.length ; i++){
		if ( this.pieces[i].type == inType && this.pieces[i].loc == -1 ){
			ret = this.pieces[i];
			break;
		}
	}
	return ret;
}

//assigns offsets to pos
_BoardInfo_proto.getSquareOffsets = function(pos){
	for (var i = 0 ; i < 64 ; i++){
		pos[i]= $("#"+i).offset();
	}
	return pos;
}

_BoardInfo_proto.appendAndHideArray = function(dPArray){
	for(var i = 0 ; i < dPArray.length ; i++){
		dPArray[i].appendPDtoBodyAndHide();
	}	
}

// sets visual board from a 8x8 grid as array 
_BoardInfo_proto.setBoardFromState = function(grid){
	var squareCont = -1;
	var divPiece = -1;

	// clear board pieces	
	this.unassignAndHideAllDivPiece();
	
	for( var i = 0 ; i < grid.length ; i++ ){
		if (( squareCont = grid[i]) != -1){
			divPiece = this.findFreePiece(squareCont);
			this.setDivPieceToPosAndShow(divPiece,i);
		}
	}
}

_BoardInfo_proto.unassignAndHideAllDivPiece = function(){
	for (var i = 0 ; i < this.pieces.length ; i++){
		this.hideDivPieceAtIndex(i);
	}
}
_BoardInfo_proto.hideDivPieceAtIndex = function(index){
		if (this.pieces[index].loc != -1){
			this.squares[this.pieces[index].loc] = -1;
			this.pieces[index].loc = -1;
		}
		$(this.pieces[index].d).hide();
}
_BoardInfo_proto.hideDivPieceAtSquare = function(sqr){
	var ret = 0;
	var dp = this.getPieceAtSquare(sqr);
	if (dp != -1){
		this.hideDivPieceAtIndex(dp.index);
		ret = 1;
	}
	return ret;
}

_BoardInfo_proto.getPieceAtSquare =  function(index){
	var ret = -1;
	var p = -1;
	if (index>=0 && index < 64 && (p = this.squares[index]) != -1){
		ret = this.squares[index];
	}
	return ret;
}

_BoardInfo_proto.promoteBySquare = function(square, type){
	var ret = 0;
	var newType = this.PieceImageMap[type+""];
	var p = this.getPieceAtdSquare(square);
	if (p != 1  && newType != undefined){
		p.type = type;
		$(p.d).html(newType);
	}	
}

_BoardInfo_proto.setDivPieceToPosAndShow = function(pieceDiv,squareIndex){
	pieceDiv.showPieceDiv();
	this.setPieceDivToPos(pieceDiv,squareIndex);
}

_BoardInfo_proto.setPieceDivToPos = function(pieceDiv, squareIndex){
	var dest = this.squareOffsets[squareIndex];
	pieceDiv.loc = squareIndex;
	this.squares[squareIndex] = pieceDiv;
	//$(pieceDiv.d).offset({left:0,top:0}); // 
	$(pieceDiv.d).offset({left: dest.left, top: dest.top});
}

_BoardInfo_proto.moveDivPieceToSquare = function(pieceDiv, squareIndex){
	var dest = this.squareOffsets[squareIndex];  // find offset
	if (pieceDiv.loc != -1){
		this.squares[pieceDiv.loc] = -1;	// remove PD from current square
	}
	this.squares[squareIndex] = pieceDiv;	// set new square value 
	pieceDiv.loc = squareIndex; // set new PD location
	$(pieceDiv.d).animate({left: dest.left, top: dest.top},"fast");
}

_BoardInfo_proto.moveDPAtIndex = function( index, toSquare){
	if (index < this.pieces.length){
		this.moveDivPieceToSquare(this.pieces[index],toSquare);
	}
}

_BoardInfo_proto.moveDPAtSquare= function(atSquare, toSquare){
	var ret = 0 ; 
	var val = this.getPieceAtSquare(atSquare);
	if (val != -1){
		this.moveDivPieceToSquare(val,toSquare);
		ret = 1;
	}
	return ret;
}


_BoardInfo_proto.getSquareOfDiv = function(arg){
	var ret = -1;
	var id = $(arg).attr("id");
	if(id.substring(0,2) === divPiecePrefix){
		var pindex = parseInt(id.substring(2,id.length));
		ret = this.pieces[pindex].loc;
	}
	else {
		ret = parseInt(id);
	}
	return ret;
}

// return divPiece by the ID of its html id;
_BoardInfo_proto.getDPByID = function(id){
	ret = -1;
	if (id.subString(0,2) === divPiecePrefix ){
		var idInt = parseInt(id.subString(2,id.length));
		if( idInt < this.pieces.length){
			ret = this.pieces[idInt];
		}
	}
	return ret;
}

BoardInfo.prototype = _BoardInfo_proto;

// array the holds that offsets for each square on the board, used for animation info
var squareOffsets = new Array(64);

// string that contains all symbols for piece type
var fenNotationString = "KQRBNPkqrbnp";

// inner html codes for the divPieces. Currently just. 
var pieceImageList = [
	"&#9812",	
	"&#9813",
	"&#9814",
	"&#9815",
	"&#9816",
	"&#9817",
	"&#9818",
	"&#9819",
	"&#9820",
	"&#9821",
	"&#9822",
	"&#9823"
	];

// creates map for image/ fonts (K,V) = (
function initPieceMap( fenstring, p ){
	var tempMap = {};
	for (var i = 0 ; i < fenstring.length ; i++  ){
	tempMap[fenstring.charAt(i)] = p[i];
	}
	return tempMap;
}

//
var pieceMap = new initPieceMap(fenNotationString,pieceImageList);

// set square at index to val
function setSquareHTML(index,val ){
	$('#'+index).html(val);
}

//
var square = "&#9823";

// peices
// variables to store move data
var selectedSquare = ""; // stores curently selectedSquareed 
var selectedSquareColor = ""; // stores currently selectedSquareed color.

//
function test( squareID ){
	alert(square);	
	square = squareID;
	alert(peices[1]);
}//

// adds user click to specific square
function addUserClickToSquare(d){
	$(d).click( function(){
		userClick($(this).attr("id"));
	});
}

// adds user click to trans
function addUserClickToTrans(){
	$(".trans").click(function(){
		userClick($(this).attr("id"));
	});
}

// defining and
$(document).ready( function() {
	
	var boardInfo = new BoardInfo(fenNotationString,pieceCount,pieceMap);
	var engineProxy = new EngineProxy();
	var control = new Control(boardInfo,engineProxy);
	
	engine_js_test(control);
	
	$(".square").click( function(){
		control.squareClick(this);
	} );
	
	$("#button").click( function(){
		control.debugButton(this);
	} );
	
});

// this will serve as a inital test for the engine (will replace with a separate proxy)
function engine_js_test(control){
	
	control.engineProxy.newGame();
	var translated = control.engineProxy.getTranslatedState();
	control.boardInfo.setBoardFromState(translated);
	
	
	//printMatrix(translated,8);
}


// gets the first key that maps to the value (denoted by value).
function getKeyByValue(object, value) {
	var ret = -1;
	for ( var key in object ){
		if (object[key] == value){
			ret = key;
			break;
		}
	}
	return ret;
}

// returns an array of 32 divPieces representing all of the pieces required 
function makePieceDivArray(fenString,pieceCount,pieceMap_a){
	var arr = []; 
	var j = 0;
	for (var i = 0 ; i < fenString.length ; i++){
		for (var k = 0 ; k < pieceCount[fenString.charAt(i)] ; k++){
			arr[j] = new DivPiece(fenString.charAt(i)+"",j,pieceMap_a[fenString.charAt(i)]+"");
			j++;
		}
	} 
	return arr;
}

function Control(boardInfo, engineProxy){
	this.players = new Array(2);
	this.players[0] = new Player("User",0,false);
	this.players[1] = new Player("Jose",1,true);
	this.currentPlayer = 0;
	this.depth = 2; // this will be the difficulty for now....	
	this.boardInfo = boardInfo;
	this.engineProxy = engineProxy;
	this.check = [0,0];
	this.mate = [0,0];
	this.s1 = "";  // used for stored selection, in a two click action
	this.selected = false;
	this.storedColor = "";
	return this;
}

var _Control_proto = {};  // Control prototype!

_Control_proto.squareClick = function( clicked ){
	
	
	
	// if this is the first click
	if ( this.s1 != square && !this.selected ){
		this.s1 = clicked;
		this.selected = true;
		this.highlight(clicked);
	} 
	// if this is 2nd click on diffrent square than first
	else if (this.s1 != square && this.selected){
		if (this.moveByClick(this.s1,clicked)){
			this.endTurn();
		}
		else{
			this.unHighlight(this.s1);
			this.s1 = "";
			this.selected = false;
		}
	}
	// same square, cancels click 
	else {
		this.unHighlight(this.s1);
		this.s1 = "";
		this.selected = false;	
	}
	
	this.userCycle();
}

_Control_proto.endTurn = function(){
	this.currentPlayer = (this.currentPlayer == 0 )? 1 : 0;	
}

_Control_proto.turnAI = function(){
	if(this.players[this.currentPlayer].ai == true){
		// find player move from AI
		var mov = this.engineProxy.getMoveFS(this.players[this.currentPlayer].aiDepth);
		this.engineProxy.move(mov[0],mov[1]);
		this.executeMove(mov[0],mov[1]);
		this.endTurn();
	}
}

_Control_proto.userCycle =  function(){
	var loop = true;
	while (loop){
		if (this.players[this.currentPlayer].ai == true){
			this.turnAI();
		}
		else {
			loop = false;
		}
	}
}

_Control_proto.highlight = function(lightThis){
	this.storedColor = $(lightThis).css("background");
	$(lightThis).css("background",highlightColor);
}
_Control_proto.unHighlight = function(unLightThis){
	$(unLightThis).css("background",this.storedColor);
	this.storedColor = "";  // not required to empty this variable. 
}
_Control_proto.moveByClick = function(from, to){
	
	var ret = false;  // denotes success or failure on return 
	var fromSquare = this.boardInfo.getSquareOfDiv(from);
	var toSquare = this.boardInfo.getSquareOfDiv(to);
	var ok = this.engineProxy.move(fromSquare,toSquare);
	
	// if the move is valid
	if (ok){
		this.unHighlight(from)
		this.executeMove(fromSquare,toSquare,this.engineProxy.capture);
		this.s1= "";
		this.selected = false;
		ret = true;
	}
	return ok;
}

_Control_proto.findAndMakeMove = function(){
	var mov = this.engineProxy.getMoveFS(this.depth);
	var ok = this.engineProxy.move(mov[0],mov[1]);
	
	if (ok){
		this.executeMove(mov[0],mov[1]);
	}
	
}

// this function will parse the flags from the engine and process the board changes
_Control_proto.executeMove = function(fromSquare, toSquare){
	
	if (this.engineProxy.capture){
		this.boardInfo.hideDivPieceAtSquare(toSquare);
	}	
	
	this.boardInfo.moveDPAtSquare(fromSquare,toSquare);
	
	// move rooks during a castle
	if(this.engineProxy.castleKing){
		this.boardInfo.moveDPAtSquare(fromSquare+3,fromSquare+1);
	}
	else if (this.engineProxy.castleQueen){
		this.boardInfo.moveDPAtSquare(fromSquare-4,fromSquare-1);
	}
	
	// check and carryout pawn promotion
	if(this.promotionCheck(this.engineProxy.moveString)){
		this.boardInfo.promoteBySquare(toSquare,this.getPromotionType(this.engineProxy.moveString));
	}
	
	
	/*
	if (this.engineProxy.check){
		this.check[this.currentPlayer] = 1;
	}
	
	if (this.engineProxy.mate){
		this.mate[this.currentPlayer] = 1;
	}
	
	*/
	
}

//reads a move string (from engine) and determines if the move resulted in a promotion
_Control_proto.promotionCheck = function(str){
	var ret = 0;
	if(str.charAt(str.length-1) == "Q"  || str.charAt(str.length-1) =="q"){
		ret = 1;
	}
	return ret;
}

_Control_proto.getPromotionType = function(str){
	var ret = -1;
	if (str.charAt(str.length-1) == "Q"  || str.charAt(str.length-1) =="q"){
		ret = str.charAt(str.length-1);
	}
	return ret;
}

_Control_proto.debugButton = function(button){
	// this will be a debug fun.
	var move = this.engineProxy.getMoveFS();
	var f = this.engineProxy.getSmallCoord(move[0]);
}

// Player stored info about Human/AI Color and name
function Player(name, color, ai){
	this.name = name;
	this.color = color;
	this.ai = ai;
	this.aiDepth = 2;
	return this;
}

Control.prototype = _Control_proto;

// debugMethods

function pDebug(str){
	$("#debugOutput").append(str);
}

function zeroBoard(){
	return new Array(120);
}



//copies Large 10x12 to 8x8,
// fills matrix (mat) starting with number (start) with an interval(inteval)
function fillMatrix(mat, start, interval){
	for (var i = 0 ; i < mat.length ; i++){
		mat[i]=start;
		start+=interval;
	}
}

function printMatrix(mat, br){
	for (var i = 0 ; i < mat.length; i++){
		if(i > 0 && i%br==0){
			pDebug("<br>");
		}
		pDebug(mat[i]+" ");
	}
}

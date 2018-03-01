
/*	
 *  Acts as a wrapper for the engine.js will proxy to the XML engine in future iterations.
 * 
 *  Contains utilities for translating game data between engine.ja and ChessBoard.js
 */

// engine proxy 

function EngineProxy(){
	this.state = "";
	this.ok = 0;
	this.check = 0;
	this.mate  = 0;
	this.capture = 0;
	this.castleKing = 0;
	this.castleQueen = 0;
	this.draw = 0;
	this.moveString = "";
	return this;
}

_EngineProxy_proto = {};

_EngineProxy_proto.newGame = function(){
	this.state = p4_new_game();
}
_EngineProxy_proto.gameFromState = function(fen){
	this.state = p4_fen2state(fen);
}
_EngineProxy_proto.getTranslatedState = function(){
	var temp = new Array(64);
	var type = new Array(64);
	copyLargeToSmall(this.state.board,temp);
	this.translateNumToType(temp,type);
	return type;
}
_EngineProxy_proto.translateNumToType = function(num,type){
	for (var i = 0 ; i < num.length ; i++){
		type[i] = this.getKeyByValue(P4_PIECE_LUT,num[i]);
	}
}
_EngineProxy_proto.parseFlags = function(flags){
	this.ok = flags & 1;
	this.check = flags & 2;
	this.mate  = flags & 4;
	this.capture = flags & 8;
	this.castleKing = flags & 16;
	this.castleQueen = flags & 32;
	this.draw = flags & 64;
}

_EngineProxy_proto.move = function(f,t){
	
	// these equations translate the 8x8 coordinates to 10x12 equivalents.
	var nf = f+21+((Math.floor(f/8))*2);  
	var nt = t+21+((Math.floor(t/8))*2);
	
	var moveReturn = this.state.move(nf,nt);
	var ok = moveReturn.ok;
	this.moveString = moveReturn.string;
	var flags = moveReturn.flags;
	this.parseFlags(flags);
	return ok;
}

_EngineProxy_proto.getMoveFS = function(depth){
	var moveFound = this.state.findmove(depth);
	moveFound[0] = this.getSmallCoord(moveFound[0]);
	moveFound[1] = this.getSmallCoord(moveFound[1]);
	return moveFound;
}

_EngineProxy_proto.getKeyByValue = function(object, value) {
	var ret = -1;
	for ( var key in object ){
		if (object[key] == value){
			ret = key;
			break;
		}
	}
	return ret;
}

// changes 10x2 board coordinate to 8x8 coordinate.
_EngineProxy_proto.getSmallCoord = function(large){
	var temp = large - 21;
	return  temp - (Math.floor(temp/10)*2);
}

EngineProxy.prototype = _EngineProxy_proto;


// copies small 8x8 to 10x12 grid
function copySmallToLarge(small, large){ 
	var lIndex = 22;
	var x = 0;
	for(var i = 0 ; i < 64 ; i++){
		if ( x  == 8 ){
			lIndex+=2;
			x=0;
		}
		large[lIndex] = small[i];
		x++;
		lIndex++;
	}
}

// copies 10x12 to 8x8
function copyLargeToSmall(large,small){
	var lIndex = 21;
	var x =0;
	for(var i = 0 ; i < 64 ; i++){
		if ( x  == 8 ){
			lIndex+=2;
			x=0;
		}
		small[i] = large[lIndex];
		lIndex++;
		x++;
	}
}
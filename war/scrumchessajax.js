// contains all of the ajax call objects



//_ScrumchessAjax_proto_ 

var userTokenString = "userToken";
var gameIdString = "gameId";
var algebraicNotationString = "algebraicNotation";
var authenticationTypeString = "authenticationType";
var newGameConfigString = "newGameConfig";
var opponentString = "opponent";
var returnObjectQueue = [];
var gameConfigutationType = ["WHITE","BLACK","WHITE2","BLACK2"];
var authenticationType = ["GOOGLE","DEBUG","NONE"];

GameRequestProto = {
		type : "POST"
};


function NewGameAjaxObject(userToken,authenticationType,userConfig,other){
		this.url = "/newgamerequest";
		this.data = {
			[userTokenString] : userToken ,
			[authenticationTypeString] : authenticationType,
			[newGameConfigString] : userConfig,
			[opponentString] : other
		};
};
NewGameAjaxObject.prototype = GameRequestProto;

function GameInfoRequestAjaxObject(userToken,authenticationType,gameId){
	this.url = "/gameinforequest";
	this.data = {
		[userTokenString] : userToken ,
		[authenticationTypeString] : authenticationType,
		[gameIdString] : gameId
	};
}
GameInfoRequestAjaxObject.prototype = GameRequestProto;


function MoveRequestAjaxObject(userToken,authenticationType,gameId,algebraicNotiontion){
			this.url = "/moverequest";
			this.data = {
				[userTokenString] : userToken,
				[authenticationTypeString] : authenticationType,
				[gameIdString] : gameId,
				[algebraicNotationString]: algebraicNotiontion
			};
};
MoveRequestAjaxObject.prototype = GameRequestProto;

function tryMoveRequest(userToken,gameId,algebraicNotiontion){
	var moveRequestResponse
	var moveRequest = new MoveRequestObject(userToken,gameId,algebraicNotiontion);
	moveRequest.success = function(result){returnObjectQueue.push(result)};
	$.ajax(moveRequest);
}

function tryGameInfoRequest(userToken,authenticationType,gameId){
	var gameInfoResponse;
	var gameInfo = new GameInfoRequestAjaxObject(userToken,authenticationType,gameId);
	moveRequest.success = function(result){returnObjectQueue.push(result)};
	$.ajax(newMoveGameRequest);
}

function tryNewGameRequest(userToken,authenticationType,userConfig,other){
	var newGameResponse;
	var newGame = new NewGameAjaxObject(userToken,authenticationType,userConfig,other);
	newGame.success = function(result){returnObjectQueue.push(result)};
	$.ajax(newGame);
}


var getNewGameObject = function(userToken,authenticationType,userConfig){
	var ret = {
			type: "POST",
			url: "/newgamerequest",
			data: {
				[userTokenString] : userToken,
				[authenticationTypeString] : authenticationType,
				[newGameConfigString] : userConfig
			}
	}
	return ret;
}


var getSignInAjaxObject = function(userToken,url){
	var ret = {
			type: "POST",
			url: "/usertest",
			data: { [userTokenString] : userToken}
	}
	return ret;
}



var getGameInfoRequestObject  = function(userToken,authenticationType,gameId){
	var ret = {
			type: "POST",
			url: "/usertest",
			data : {
				[userTokenString] : userToken,
				[authenticationTypeString] : authenticationType,
				[gameIdString] : gameId
			}
	}
	return ret;
}


var getMoveRequestObject = function(userToken,gameId, algebraicNotiontion){
	var ret = {
			type: "POST",
			url: "/usertest",
			data: {
				[userTokenString] : userToken,
				[gameIdString] : gameId,
				[algebraicNotationString]: algebraicNotiontion
			}
	}

	return ret;
}

var getSignInAjax = function(gid){
	var ret = {
			type: "POST",
			url: "/usertest",
			data: {"tokenid" : gid},			
	}
}
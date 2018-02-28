// contains all of the ajax call objects



//_ScrumchessAjax_proto_ 

var userTokenString = "userToken";
var gameIdString = "gameId";
var algebraicNotationString = "algebraicNotation";
var authenticationTypeString = "authenticationType";
var newGameConfigString = "newGameConfig";



var getNewGameObject = function(userToken,authenticationType,userConfig){
	var ret = {
			type: "POST",
			url: "/newgamerequest",
			data: { [userTokenString] : userToken ,
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
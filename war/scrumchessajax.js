// contains all of the ajax call objects



_ScrumchessAjax_proto_ 

var userTokenString = "userToken";
var gameIdString = "gameId";
var algebraicNotationString = "algebraicNotation";
var authenticationTypeString = "authenticationType";


var getSignInAjaxObject = function(userToken,url){
	var ret = {
			type: "POST",
			url: "/usertest",
			data: {userTokenString : userToken}
	}
}

var getMoveRequestObject  = function(userToken,authenticationType,gameId){
	var ret = {
			type: "POST",
			url: "/usertest",
			data : {
				userTokenString : userToken,
				authenticationTypeString : authenticationType,
				gameIdString : gameId
			}
	}
}


var getMoveRequestObject = function(userToken,gameId, algebraicNotiontion){
	var ret = {
			type: "POST",
			url: "/usertest",
			data: {
				userTokenString : userToken,
				gameIdString : gameId,
				algebraicNotationString : algebraicNotiontion
			}
	}
}

var getSignInAjax = function(gid){
	var ret = {
			type: "POST",
			url: "/usertest",
			data: {"tokenid" : gid},			
	}
}
// contains all of the ajax call objects


var getSignInAjaxObject = function(userToken,url){
	var ret = {
			type: "POST",
			url: "/usertest",
	}
}


var getSignInAjax = function(gid){
	var ret = {
			type: "POST",
			url: "/usertest",
			data: {"tokenid" : gid},			
	}
}
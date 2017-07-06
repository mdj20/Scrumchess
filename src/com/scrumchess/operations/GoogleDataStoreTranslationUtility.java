package com.scrumchess.operations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.scrumchess.ajaxendpoint.AuthenticatedUserMoveInfo;
import com.scrumchess.ajaxendpoint.UserMoveInfo;
import com.scrumchess.data.Game;
import com.scrumchess.data.GameMovelistComposite;
import com.scrumchess.data.Move;
import com.scrumchess.transit.game.CompleteGameInfo;
import com.scrumchess.transit.game.CompleteGameInfoBuilder;
import com.scrumchess.transit.game.playerconfiguration.PlayerConfiguration;
import com.scrumchess.transit.move.MoveAlgebraic;
import com.scrumchess.transit.move.SimpleMoveAlgebraic;

public class GoogleDataStoreTranslationUtility {
	
	protected static ArrayList<MoveAlgebraic> translateMove(List<Move> move){
		ArrayList<MoveAlgebraic> ret = new ArrayList<MoveAlgebraic>();
		for(Move m:move){
			ret.add(translateMove(m));
		}
		return ret;
	}
	protected static MoveAlgebraic translateMove(Move move){
		return new SimpleMoveAlgebraic(move.getMoveString(),move.getNumber());
	}
	
	protected static CompleteGameInfo buildCompleteGameInfo(GameMovelistComposite gmlc){
		CompleteGameInfo ret = null;
		CompleteGameInfoBuilder builder = new CompleteGameInfoBuilder(); 
		builder.setFen(gmlc.getGame().getFen());  // set fen
		builder.setGameId(gmlc.getGame().getId()); 
		List<Move> moves = gmlc.getMoves();  // get and sortMoves
		Collections.sort(moves);
		builder.addMove(translateMove(moves)); // add moves
		builder.setPlayerConfiguration(translatePlayerConfiguration(gmlc.getGame())); // add player configuration
		ret = builder.build();
		return ret;
	}
	
	protected static PlayerConfiguration.Config translatePlayerConfiguration(Game game){
		PlayerConfiguration.Config ret;
		if(game.isWhite() && game.isBlack()){
			ret = PlayerConfiguration.Config.BOTH;
		}
		else if(game.isWhite()){
			ret = PlayerConfiguration.Config.WHITE;
		}
		else if(game.isBlack()){
			ret = PlayerConfiguration.Config.BLACK;
		}
		else{
			ret = PlayerConfiguration.Config.NONE;
		}
		return ret;
	}
	
	// this will create an AuthenticatedUserMoveInfo bypassing the actual authentication since authentication is done before any steps are processed
	protected static AuthenticatedUserMoveInfo buildAuthenticatedUserMoveInfo(MoveAlgebraic move, long gameId, String userId){
		AuthenticatedUserMoveInfo ret = AuthenticatedUserMoveInfo.overrideToken(new UserMoveInfo(userId,move.getMoveAlgebraic(),gameId), userId);
		return ret;
	}
	
	
}

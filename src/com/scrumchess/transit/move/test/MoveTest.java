package com.scrumchess.transit.move.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

import com.scrumchess.data.Move;
import com.scrumchess.transit.move.FenUtility;
import com.scrumchess.transit.move.MoveAlgebraic;
import com.scrumchess.transit.move.SimpleMoveAlgebraic;

public class MoveTest {
	public static void main(String args[]){
		smokeTest();
	}
	
	private static void smokeTest(){
		List<MoveAlgebraic> moves = createRandom(20);

		System.out.println(testComparable(moves));
		Collections.sort(moves);	

		System.out.println(testComparable(moves));
		for(MoveAlgebraic ma:moves){
			System.out.println(ma.getHalfMoveNumber());
		}
	}
	
	private static boolean testComparable(List<MoveAlgebraic> moves){	
	
		boolean ret = true;
		for (MoveAlgebraic ma:moves){
			if (moves.indexOf(ma)>0){
				ret = ret && (ma.compareTo(moves.get(moves.indexOf(ma)-1))>=0);
			}
		}
		return ret;
	}
	
	private static List<MoveAlgebraic> createRandom(int n){
		Random r = new Random();
		SimpleMoveAlgebraic tMove;
		ArrayList<MoveAlgebraic> moves = new ArrayList<MoveAlgebraic>();
		for (int i = 0 ; i < n; i++){
			String fen = FenUtility.move(r.nextInt(8), r.nextInt(8), r.nextInt(8), r.nextInt(8));
			tMove = new SimpleMoveAlgebraic(fen,i);	
			moves.add(tMove);
		}
		Collections.shuffle(moves);
		return moves;
	}
	

}

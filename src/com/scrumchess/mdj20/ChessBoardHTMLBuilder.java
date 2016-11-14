package com.scrumchess.mdj20;

public class ChessBoardHTMLBuilder {
	
	
	private static String divOpen = "<div ";
	private static String tableOpen = "<table ";
	private static String trOpen = "<tr ";
	private static String tdOpen = "<td ";
	private static String idString = " id='";
	private static String classString = " class='";
	private static String closeSingleQuote = "' ";
	private static String attributeClose = " > ";
	
	public static final String boardClass = "chessBoard";
	public static final String whiteClass = "white square";
	public static final String blackClass = "black square";
	public static final String rankClass = "rank";
	public static final String squareClass = "square"; 
	
	public static String getBoard(){
		
		StringBuilder sb = new StringBuilder();
		sb.append(openTableIDClass(boardClass,boardClass));
		
		int colorToggle = 0;
		
		for (int rank = 8 ; rank > 0 ;  rank--){

			String rankString = Integer.toString(rank);
			String rankID = rankClass+"_"+rankString;
			sb.append(openTrIDClass(rankID,rankClass));
			
			for (int file = 0 ; file <8 ; file++){
				String fileString = Character.toString((char)(file+65));
				String squareID = rankString + fileString;
				String tempSquareClass = (colorToggle%2 == 0)? whiteClass : blackClass; 
				sb.append(openTdIDClass(squareID,tempSquareClass));
				colorToggle++;
				sb.append("</td>\n");
			}
			colorToggle++;
			sb.append("</tr>\n"); // close row
		}
		sb.append("</table>");
		
		return sb.toString();
	}
	
	// this class will make the bvoard 
		public static String getDivBoardBottomUp(){
			StringBuilder sb = new StringBuilder();
			sb.append(openDivIdClass(boardClass,boardClass));
			
			int colorToggle = 0;
			int squareID = 0;
			
			for (int rank = 8 ; rank > 0 ;  rank--){

				String rankString = Integer.toString(rank);
				String rankID = rankClass+"_"+rankString;
				sb.append(openDivIdClass(rankID,rankClass));
				
				for (int file = 0 ; file <8 ; file++){
					String fileString = Character.toString((char)(file+65));
					String tempSquareClass = (colorToggle%2 == 0)? whiteClass : blackClass; 
					sb.append(openDivIdClass(Integer.toString(((rank-1)*8)+file),tempSquareClass));
					colorToggle++;
					sb.append("</div>\n");
				}
				colorToggle++;
				sb.append("</div>\n"); // close row
			}
			sb.append("</div>");
			
			return sb.toString();
		}
	
	// this class will make the bvoard 
	public static String getDivBoardIntSquareId(){
		StringBuilder sb = new StringBuilder();
		sb.append(openDivIdClass(boardClass,boardClass));
		
		int colorToggle = 0;
		int squareID = 0;
		
		for (int rank = 8 ; rank > 0 ;  rank--){

			String rankString = Integer.toString(rank);
			String rankID = rankClass+"_"+rankString;
			sb.append(openDivIdClass(rankID,rankClass));
			
			for (int file = 0 ; file <8 ; file++){
				String fileString = Character.toString((char)(file+65));
				String tempSquareClass = (colorToggle%2 == 0)? whiteClass : blackClass; 
				sb.append(openDivIdClass(Integer.toString(squareID++),tempSquareClass));
				colorToggle++;
				sb.append("</div>\n");
			}
			colorToggle++;
			sb.append("</div>\n"); // close row
		}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static String getDivBoard(){
		
		StringBuilder sb = new StringBuilder();
		sb.append(openDivIdClass(boardClass,boardClass));
		
		int colorToggle = 0;
		
		for (int rank = 8 ; rank > 0 ;  rank--){

			String rankString = Integer.toString(rank);
			String rankID = rankClass+"_"+rankString;
			sb.append(openDivIdClass(rankID,rankClass));
			
			for (int file = 0 ; file <8 ; file++){
				String fileString = Character.toString((char)(file+65));
				String squareID =   fileString + rankString;
				String tempSquareClass = (colorToggle%2 == 0)? whiteClass : blackClass; 
				sb.append(openDivIdClass(squareID,tempSquareClass));
				colorToggle++;
				sb.append("</div>\n");
			}
			colorToggle++;
			sb.append("</div>\n"); // close row
		}
		sb.append("</div>");
		
		return sb.toString();
	}
	
	public static String openDivIdClass(String id, String className){
		return openAttributeWithIDClass(divOpen,id,className);
	}
	
	public static String openTrIDClass(String id , String className){
		return openAttributeWithIDClass(trOpen,id,className);
	}
	
	public static String openTdIDClass(String id , String className){
		return openAttributeWithIDClass(tdOpen,id,className);
	}
	
	public static String openTableIDClass(String id , String className){
		return openAttributeWithIDClass(tableOpen,id,className);
	}

	public static String openAttributeWithIDClass(String attr, String id, String className){
		return  attr+ idString + id + closeSingleQuote + classString + className + closeSingleQuote + attributeClose;
	}
	
	
	public static void main(String args[]){
		
		test();
		
	}
	private static void test(){
		System.out.print(getBoard());
		System.out.print(getDivBoard());
		System.out.print(getDivBoardIntSquareId());
		System.out.print(getDivBoardBottomUp());
	}
	
	
	
}

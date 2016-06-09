package bingo.data;

import java.io.Serializable;

public class GameInfo implements Serializable {

	// 본인이 선택하거나, 상대방이 선택한 빙고 단어
	private String keyword;
	// 입력한 25개의 빙고 단어 저장
	// private String bingoKeywords [][] = new String[5][5];
	private String[][] bingoKeywords = { 
			{ "a", "a", "3", "4", "5" }, 
			{ "a", "a", "8", "9", "10" },
			{ "a", "a", "a", "14", "15" }, 
			{ "a", "a", "18", "a", "20" }, 
			{ "a", "a", "23", "24", "a" } };// 유닛테스트용

	// 빙고 현황(결과)를 담는 배열, 내가 선택하거나 상태방이 선택한 빙고 단어와 일치되는 위치의 값을 1로 초기화 한다.
	private int bingoResult[][] = new int[5][5];
	// 상대방이 선택한 빙고 단어가 내가 입력한 빙고 단어와 일치하는 것이 발견될 경우 해당 단어의 배열(5X5)상의 위치 값
	private int x, y;
	// 자기자신에 대한 정보를 갖는 User
	private User user;

	public static void main(String[] args) {
		
		
		GameInfo g = new GameInfo();
		g.markBingoResult("a");

		int num = g.checkBingo();
		System.out.println(num);
	}
	

	/**
	 * 인자로 전달된 빙고 단어를 가지고 빙고 결과를 담는 배열을 1로 초기화 한다. 사용자가 입력한 빙고 단어와 일치하는 단어가 있으면
	 * bingoResult 배열의 해당 위치값을 1로 초기화 하고, 해당 배열의 위치 값으로 x,y 변수를 초기화 한다. 매개변수로
	 * 전달된 단어와 일치하는 단어가 있으면 true를, 그렇지 않으면 false를 반환한다.
	 */
	public boolean markBingoResult(String keyword) {
		boolean result = false;

		// 사용자가 입력한 빙고 단어와 일치하는 단어가 있으면
		// 빙고배열 담는 배열을 일단 1로 초기화

		int k = 0;
		for (int i = 0; i < bingoKeywords.length; i++) {
			for (int j = 0; j < bingoKeywords.length; j++) {
				String str = bingoKeywords[i][j];
				if (str.equals(keyword)) {
					bingoResult[i][j] = 1;
					x = i;
					y = j;
					result = true;// 일치하는 경우 true
					k++;
				}

			}
		}
		System.out.println(k + "개의 일치단어 찾음");

		return result;
	}

	/**
	 * 빙고 결과를 담는 배열로부터 빙고 개수(가로,세로,대각선 연속하여 5개의 빙고단어를 맞춘 개수) 계산하여 반환한다.
	 */
	public int checkBingo() {
	      int bingoNum = 0;
	      int dcount = 0;
	      int rdcount = 0;

	       
	      for (int i = 0; i < bingoResult.length; i++) {
	         int wcount = 0;
	         int hcount = 0;
	         for (int j = 0; j < bingoKeywords[i].length; j++) {
	            if (bingoResult[i][j] == 1) {
	               wcount++;
	               if (wcount == 5)
	                  bingoNum++;
	            }
	            

	            if (bingoResult[j][i] == 1) {
	               hcount++;
	               if (hcount == 5)
	                  bingoNum++;
	            }
	            
	            
	            if (bingoResult[i][j] == 1 && i == j) {
	               dcount++;
	               if (dcount == 5)
	                  bingoNum++;
	            }
	            
	            
	            if (bingoResult[i][j] == 1 && i + j == 4) {
	               rdcount++;
	               if (rdcount == 5){
	                  bingoNum++;
	               }
	            }
	         }
	      }
	      return bingoNum;
	   }
	
	

	public String[][] getBingoKeyword() {
		return bingoKeywords;
	}

	public void setBingoKeywords(String[][] bingoKeyword) {
		this.bingoKeywords = bingoKeyword;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getKeyword() {
		return keyword;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int[][] getBingoResult() {
		return bingoResult;
	}

}

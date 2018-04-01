package code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;

public class Board {
	private ArrayList<String> words;
	private String selectedwords[][];
	private String key[][];
	private ArrayList<String> twentyfive;// assasin Bystander Blue agents Red Agents
	private int turn;
	

	public Board(String file) {
		turn = 25;
		words = new ArrayList<String>();
		ArrayList<String> temp = readCSVFile(file);
		Collections.shuffle(temp);
		words = temp;
		selectedwords = new String[5][5];
		boardwords();
		twentyfive = new ArrayList<String>();
		settwentyfivecards();
		key = new String[5][5];
		setgamekey();

	}
 //Reads filename and set words in arraylist words
	public ArrayList<String> readCSVFile(String filename) {
		ArrayList<String> answer = new ArrayList<String>();
		try {
			for (String word : Files.readAllLines(Paths.get(filename))) {
				answer.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return answer;
	}

	//set the 5x5 board with random words
	public void boardwords() {
		int count4 = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				selectedwords[i][j] = words.get(count4);
				count4++;
			}
		}
	}
 
	public void settwentyfivecards() {
		for (int i = 0; i < 25; i++) // fills an array list "twenty-five" with the 4 different types of cards
		{
			if (i == 0)
				twentyfive.add("assasin");
			else if (i > 0 && i < 8)
				twentyfive.add("Bystanders");
			else if (i >= 8 && i < 16)
				twentyfive.add("Blue");
			else if (i >= 15 && i < 25)
				twentyfive.add("Red");
		}
		Collections.shuffle(twentyfive);

	}
	//sets key arraylist
	public void setgamekey(){
		int count3=0;
		for (int i=0;i<5;i++){
			for (int j=0;j<5;j++){
				if (count3<25)
					key[i][j]=twentyfive.get(count3);
					count3++;
			}
		}	
	}
	
	public boolean checkguess(String guess) {
		// checks if a clue is legal or not
		for(int i=0;i < selectedwords.length; i++) {
			for(int j=0; j < selectedwords[0].length; j++) {
				if(selectedwords[i][j].equals(guess)) {
					return false;
				}
			}
		}
		return true;
	}
	
	public String updateLocation(String x) //Updates the counts on how many class cards are left
											//returns the class name that replaced the board
	{
		String result="no more cards";
		
		for (int i=0;i<key.length;i++){
			for(int j=0;j<key[0].length;j++){
				if ((selectedwords[i][j].toLowerCase()).equals(x.toLowerCase()))
						{
					if (twentyfive.contains(key[i][j])){
					result=key[i][j];
					selectedwords[i][j]=key[i][j];
					twentyfive.remove(key[i][j]);
					turn = turn - 1;
					}
						}
			}
		}
		return result;
	}
	public boolean gameWon() {
		int blue = 0;
		int red = 0;
		for(int i = 0; i < 5;i++) {
			for(int j = 0;j < 5;j++) {
				if(selectedwords[i][j].equals("Blue")) {
					blue++;
					//keeps track of how many blue team words were found.
				}
				else if(selectedwords[i][j].equals("Red")) {
					red++;
					//keeps track of how many red team words were found.
				}
			}
		}
		if(blue == 8) {
			System.out.println("BLUE TEAM WINS");
			return true;
			//if max cards 
		}
		else if(red == 9) {
			System.out.println("Red TEAM WINS");
			return true;
		}
		return false;
	}
	public String assasinFound(String obj) {
		String result = updateLocation(obj);
		if(result.toLowerCase().equals("assasin")) {
			if(turn%2==0) {
				System.out.println("Blue team wins");
				return "Blue";
			}
			else {
				System.out.println("Red team wins");
				return "Red";
			}
			
		}
		else return "Assasin not found";
	}


	public ArrayList<String> getWords() {
		return words;
	}

	public void setWords(ArrayList<String> words) {
		this.words = words;
	}

	public String[][] getSelectedwords() {
		return selectedwords;
	}

	public void setSelectedwords(String[][] selectedwords) {
		this.selectedwords = selectedwords;
	}

	public String[][] getKey() {
		return key;
	}

	public void setKey(String[][] key) {
		this.key = key;
	}

	public ArrayList<String> getTwentyfive() {
		return twentyfive;
	}

	public void setTwentyfive(ArrayList<String> twentyfive) {
		this.twentyfive = twentyfive;
	}
	
	public int getTurn() {
		return turn;
	}

}

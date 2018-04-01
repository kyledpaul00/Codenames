package JunitTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import code.Board;


public class tests {
	
	Board _big;
	Board _small;
	
	@Before
	public void createInstances() {
		_big = new Board("GamewordsBig.csv");
		_small = new Board("GamewordsSmall.csv");
	}
	
	@Test
	public void readCSVFileTest() {
		ArrayList<String> test1 = new ArrayList<String>();
		test1 = _small.readCSVFile("GamewordsSmall.csv");
		assertEquals("Size of ArrayList Should be 50",50,test1.size());
		assertFalse("ArrayList should contain no Null entries",test1.contains(null));
		ArrayList<String> test2 = new ArrayList<String>();
		test2 = _big.readCSVFile("GamewordsBig.csv");
		assertEquals("Size of ArrayList should be 400",400,test2.size());
		assertFalse("ArrayList should contain no Null entries",test2.contains(null));
	}
	
	@Test
	public void boardWordsTest() {
		_big.boardwords();
		_small.boardwords();
		ArrayList<String> duplicateTest1 = new ArrayList<String>();
		ArrayList<String> duplicateTest2 = new ArrayList<String>();
		for (String[] s: _small.getSelectedwords()) {
			for(String t: s) {
				if (duplicateTest1.contains(t) == false) {
					duplicateTest1.add(t);
				}
			}
		}
		assertEquals("All words in Selected Words should be different",25,duplicateTest1.size());
		assertFalse("Selected Words should contain no Null entries",duplicateTest1.contains(null));
		for (String[] u: _big.getSelectedwords()) {
			for(String v: u) {
				if (duplicateTest2.contains(v) == false) {
					duplicateTest2.add(v);
				}
			}
		}
		assertEquals("All words in Selected Words should be different",25,duplicateTest2.size());
		assertFalse("Selected Words should contain no Null entries",duplicateTest2.contains(null));
	}
	
	@Test
	public void setTwentyFiveCardsTest() {
		ArrayList<String> a = new ArrayList<String>();
		ArrayList<String> b = new ArrayList<String>();
		a = _small.getTwentyfive();
		b = _big.getTwentyfive();
		assertEquals("Size of 25 ArrayList must be 25",25,a.size());
		assertEquals("Size of 25 ArrayList must be 25",25,b.size());
		assertFalse("25 ArrayList should not Equal null",a == null);
		assertFalse("25 ArrayList should not Equal null",b == null);
		int countAssasinA = 0;
		int countBystanderA = 0;
		int countRedA = 0;
		int countBlueA = 0;
		int countAssasinB = 0;
		int countBystanderB = 0;
		int countRedB = 0;
		int countBlueB = 0;
		for (int i = 0; i < a.size();i++) {
			String s = a.get(i);
			if (s == "assasin") {
				countAssasinA++;
			}
			if (s == "Bystanders") {
				countBystanderA++;
			}
			if (s == "Red") {
				countRedA++;
			}
			if (s == "Blue") {
				countBlueA++;
			}
		}
		assertEquals("There must be 1 assassin in 25 ArrayList",1,countAssasinA);
		assertEquals("There must be 7 Innocent Bystanders in 25 ArrayList",7,countBystanderA);
		assertEquals("There must be 9 Red troops in 25 ArrayList",9,countRedA);
		assertEquals("There must be 8 Blue troops in 25 ArrayList",8,countBlueA);
		for (int i = 0; i < b.size();i++) {
			String s = b.get(i);
			if (s == "assasin") {
				countAssasinB++;
			}
			if (s == "Bystanders") {
				countBystanderB++;
			}
			if (s == "Red") {
				countRedB++;
			}
			if (s == "Blue") {
				countBlueB++;
			}
		}
		assertEquals("There must be 1 assassin in 25 ArrayList",1,countAssasinB);
		assertEquals("There must be 7 Innocent Bystanders in 25 ArrayList",7,countBystanderB);
		assertEquals("There must be 9 Red troops in 25 ArrayList",9,countRedB);
		assertEquals("There must be 8 Blue troops in 25 ArrayList",8,countBlueB);
		// Checking for differences to ensure proper shuffling
		int countDifferences = 0;
		for(int i = 0; i < 25;i++) {
			String s = a.get(i);
			String t = b.get(i);
			if (s != t) {
				countDifferences++;
			}
		}
		assertTrue("There must be at least 2 differences between the shuffled 25 ArrayLists",countDifferences >= 2);

	}
	
	@Test
	public void setGameKeyTest() {
		_big.setgamekey();
		_small.setgamekey();
		String [][] big = _big.getKey();
		String [][] small = _small.getKey();
		int countEmptyString = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 1; j< 5; j++) {
				if (big [i][j] == "") {
					countEmptyString++;
				}
				if (small [i][j] ==  "") {
					countEmptyString++;
				}
			}
		}
		assertEquals("There should be no empty strings in the key",0,countEmptyString);
		assertFalse("The key should not be null",big == null);
		assertFalse("The key should not be null",small == null);
	}
	
	@Test
	public void checkGuessTest() {
		String [][] bigKey = _big.getSelectedwords();
		String [][] smallKey = _small.getSelectedwords();
		String illegal1 = smallKey[0][0];
		String illegal2 = bigKey[4][4];
		String illegal3 = smallKey[2][3];
		assertFalse("Check Guess should return false when string exists in key",_small.checkguess(illegal1));
		assertFalse("Check Guess should return false when string exists in key",_big.checkguess(illegal2));
		assertFalse("Check Guess should return false when string exists in key",_small.checkguess(illegal3));
		assertTrue("Check Guess should return true when string does not exist in key",_small.checkguess(""));
		assertTrue("Check Guesss should return true when string does not exist in key",_big.checkguess("bruv"));
		assertTrue("Check Guess should return true when null is input",_big.checkguess(null));
	}
	
	@Test
	public void updateLocationTest() {
		String [][] bigKey = _big.getKey();
		String [][] smallKey = _small.getKey();
		String [][] big = _big.getSelectedwords();
		String [][] small = _small.getSelectedwords();
		_big.updateLocation(big[2][0]);
		_big.updateLocation(big[3][4]);
		_big.updateLocation("asljdal");
		_small.updateLocation(small[0][0]);
		_small.updateLocation(small[2][3]);
		_small.updateLocation("alsdkjalfja");
		String [][] updatedBig = _big.getSelectedwords();
		String [][] updatedSmall = _small.getSelectedwords();
		assertEquals("The turn instance variable should decrease after every valid updateLocation",23,_small.getTurn());
		assertEquals("The turn instance variable should decrease after every valid updateLocation",23,_big.getTurn());
		assertEquals("The updateLocation Method should reveal which card is hidden in the guessed tile",bigKey[2][0],updatedBig[2][0]);
		assertEquals("The updateLocation Method should reveal which card is hidden in the guessed tile",bigKey[3][4],updatedBig[3][4]);
		assertEquals("The updateLocation Method should reveal which card is hidden in the guessed tile",smallKey[0][0],updatedSmall[0][0]);
		assertEquals("The updateLocation Method should reveal which card is hidden in the guessed tile",smallKey[2][3],updatedSmall[2][3]);
		assertEquals("The updateLocation Method should return the value of the card revealed",bigKey[0][0],_big.updateLocation(updatedBig[0][0]));
		assertEquals("The updateLocation Method should return the value of the card revealed",bigKey[1][4],_big.updateLocation(updatedBig[1][4]));
		assertEquals("The updateLocation Method should return the value of the card revealed",smallKey[1][1],_small.updateLocation(updatedSmall[1][1]));
		assertEquals("The updateLocation Method should return the value of the card revealed",smallKey[2][2],_small.updateLocation(updatedSmall[2][2]));
	}
	
	@Test
	public void gameWonTest() {
		String [][] bigKey = _big.getKey();
		String [][] bigSelectedWords = _big.getSelectedwords();
		String [][] smallKey = _small.getKey();
		String [][] smallSelectedWords = _small.getSelectedwords();
		assertFalse("GameWon should return false at the beginning of a game",_big.gameWon());
		assertFalse("GameWon should return false at the beginning of a game",_small.gameWon());
		//Simulating a blue and red victory
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (bigKey[i][j] == "Blue") {
					_big.updateLocation(bigSelectedWords[i][j]);
				}
				if (smallKey[i][j] == "Red") {
					_small.updateLocation(smallSelectedWords[i][j]);
				}
			}
		}
		assertEquals("GameWon should return true after a blue victory",true,_big.gameWon());
		assertEquals("GameWon should return true after a red victory",true,_small.gameWon());
	}
	
	@Test
	public void assasinFoundTest() {
		String [][] bigKey = _big.getKey();
		String [][] bigSelectedWords = _big.getSelectedwords();
		int bigX = 6; int bigY = 6;
		String [][] smallKey = _small.getKey();
		String [][] smallSelectedWords = _small.getSelectedwords();
		int smallX = 6; int smallY = 6;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (bigKey[i][j] == "assasin") {
					bigX = i;
					bigY = j;
				}
				if (smallKey[i][j] == "assasin") {
					smallX = i;
					smallY = j;
				}
			}
		}
		assertEquals("Blue should win if assasin is found on first turn","Blue",_big.assasinFound(bigSelectedWords[bigX][bigY]));
		if (smallX == 0 && smallY == 0) {
			_small.updateLocation(smallSelectedWords[1][1]);
		}else {
			_small.updateLocation(smallSelectedWords[0][0]);
		}
		assertEquals("Red should win if assasin is found on second turn","Red",_small.assasinFound(smallSelectedWords[smallX][smallY]));
		assertEquals("assasinFound should say if the guess does not return assasin","Assasin not found",_small.assasinFound("asldjfa;"));
	}

	
}

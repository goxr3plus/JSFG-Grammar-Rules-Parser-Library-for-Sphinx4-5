package tester;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import library.JSGFGrammarParser;

public class JSGFGrammarParserTester {
	
	public static void main(String[] args) {
		
		////////////////////////////------------------EXAMPLES--------------///////////////////////////////////////
		List<String> givenWords;
		List<String> results;
		
		//----------------------!! IF GRAMMAR IS INSIDE A `.JAR FILE !!!----------------------
		
		//Example 1 -- The grammar is in the same resource package
		givenWords = Arrays.asList("how are you", "say hello");
		results = JSGFGrammarParser.getRulesContainingWords(JSGFGrammarParserTester.class.getResourceAsStream("grammar1.gram"), givenWords, false);
		printResults(givenWords, results);
		
		//Example 2 -- The grammar is in the folder RESOURCES/grammars/grammar2.gram	
		givenWords = Arrays.asList("six");
		results = JSGFGrammarParser.getRulesContainingWords(JSGFGrammarParserTester.class.getResourceAsStream("/grammars/grammar2.gram"), givenWords, true);
		printResults(givenWords, results);
		
		//----------------------If the grammar is outside the jar file-----------------------
		//-----------------------THE BELOW CODE WILL NOT WORK FOR GRAMMAR FILES INSIDE THE JAR FILE----------------------
		
		//-----------------------------Test 1-----------------------------			
		//Get all the rules that contain any of these words 
		givenWords = Arrays.asList("say amazing", "what day is today");
		results = JSGFGrammarParser.getRulesContainingWords(new File("grammar.gram"), Arrays.asList("say amazing", "what day is today"), false);
		printResults(givenWords, results);
		
		//-----------------------------Test 2-----------------------------
		//Get all the rules that contain all of these words 
		givenWords = Arrays.asList("say amazing", "what day is today");
		results = JSGFGrammarParser.getRulesContainingWords(new File("grammar.gram"), givenWords, true);
		printResults(givenWords, results);
		
		//-----------------------------Test 3-----------------------------	
		//----WARNING ON TEST 3---The third parameter can be true or false here , it doesn't matter cause it is one word
		//Get all the rules that contain the given word	
		givenWords = Arrays.asList("six");
		results = JSGFGrammarParser.getRulesContainingWords(new File("grammar.gram"), givenWords, true);
		printResults(givenWords, results);
		
		////////////////////////////------------------EXAMPLES--------------///////////////////////////////////////
	}
	
	/**
	 * Print the results
	 * 
	 * @param givenWords
	 * @param results
	 */
	private static void printResults(List<String> givenWords , List<String> results) {
		System.out.println("\nGiven words : \"" + givenWords + "\"\nGrammar Rules containing them :-> " + results);
	}
}

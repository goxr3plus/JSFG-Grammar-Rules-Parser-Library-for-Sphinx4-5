package tester;

import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import library.JSGFGrammarParser;

public class JSGFGrammarParserTester {
	
	public static void main(String[] args) {
		
		////////////////////////////------------------EXAMPLES--------------///////////////////////////////////////
		
		//-----------------------------Test 1-----------------------------			
		//Get all the rules that contain any of these words 
		List<String> givenWords = Arrays.asList("say amazing", "what day is today");
		List<String> results = JSGFGrammarParser.getRulesContainingWords("grammar.gram", Arrays.asList("say amazing", "what day is today"), false);
		printResults(givenWords, results);
		
		//-----------------------------Test 2-----------------------------
		//Get all the rules that contain all of these words 
		givenWords = Arrays.asList("say amazing", "what day is today");
		results = JSGFGrammarParser.getRulesContainingWords("grammar.gram", givenWords, true);
		printResults(givenWords, results);
		
		//-----------------------------Test 3-----------------------------	
		//----WARNING ON TEST 3---The third parameter can be true or false here , it doesn't matter cause it is one word
		//Get all the rules that contain the given word	
		givenWords = Arrays.asList("six");
		results = JSGFGrammarParser.getRulesContainingWords("grammar.gram", givenWords, true);
		printResults(givenWords, results);
		
		//----------------------If the grammar is a resource inside the project-----------------------
		
		//Example 1 -- The grammar is in the same resource package
		//Get all the rules that contain any of these words 	
		try {
			givenWords = Arrays.asList("how are you", "say hello");
			results = JSGFGrammarParser.getRulesContainingWords(Paths.get(JSGFGrammarParserTester.class.getResource("grammar1.gram").toURI()).toAbsolutePath().toString(), givenWords,
					false);
			printResults(givenWords, results);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		//Example 2 -- The grammar is in the folder RESOURCES/grammars/grammar2.gram
		//Get all the rules that contain any of these words 	
		try {
			givenWords = Arrays.asList("six");
			results = JSGFGrammarParser.getRulesContainingWords(Paths.get(JSGFGrammarParserTester.class.getResource("/grammars/grammar2.gram").toURI()).toAbsolutePath().toString(),
					givenWords, true);
			printResults(givenWords, results);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
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

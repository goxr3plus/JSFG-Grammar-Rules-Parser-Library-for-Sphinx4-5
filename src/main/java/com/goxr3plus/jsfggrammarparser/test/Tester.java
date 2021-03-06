package main.java.com.goxr3plus.jsfggrammarparser.test;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import main.java.com.goxr3plus.jsfggrammarparser.parser.JSGFGrammarParser;

public class Tester {
	
	public static void main(String[] args) {
		
		////////////////////////////------------------EXAMPLES Part 1--------------///////////////////////////////////////
		List<String> rules;
		
		rules = JSGFGrammarParser.getAllGrammarRules(Tester.class.getResourceAsStream("grammar1.gram"), false);
		System.out.println("Grammar Rules , without definitions: " + rules + "\n");
		
		rules = JSGFGrammarParser.getAllGrammarRules(Tester.class.getResourceAsStream("grammar1.gram"), true);
		System.out.println("Grammar Rules , with definitions: " + rules + "\n");
		
		////////////////////////////------------------EXAMPLES Part 2--------------///////////////////////////////////////
		List<String> givenWords;
		List<String> results;
		
		//----------------------!! IF GRAMMAR IS INSIDE A `.JAR FILE !!!----------------------
		
		//Example 1 -- The grammar is in the same resource package
		givenWords = Arrays.asList("how are you", "say hello");
		results = JSGFGrammarParser.getRulesContainingWords(Tester.class.getResourceAsStream("grammar1.gram"), givenWords, false);
		printResults(givenWords, results);
		
		//Example 2 -- The grammar is in the folder RESOURCES/grammars/grammar2.gram	
		givenWords = Arrays.asList("six");
		results = JSGFGrammarParser.getRulesContainingWords(Tester.class.getResourceAsStream("/grammars/grammar2.gram"), givenWords, true);
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
	 * @param givenWords The words Given
	 * @param results The Results
	 */
	private static void printResults(List<String> givenWords , List<String> results) {
		System.out.println("\nGiven words : \"" + givenWords + "\"\nGrammar Rules containing them :-> " + results);
	}
}

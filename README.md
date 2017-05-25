### A user in youtube asked me

I have got a question. Is that possible to get known, which grammar rule was recognized? Some variable which returns "feelings" , "syntax" etc. ??

For example we have the grammar:

```
  #JSGF V1.0;

  /**
  * JSGF Grammar 
  */

  grammar grammar;

  public <feelings>  = ( how are you | say hello);
  public <voices>  = ( change to voice one  | change to voice two | change to voice three );
  public <amazing>  = ( say amazing | what day is today );
  public <nervous>  = (who is your daddy | obey monster | hey boss);
  public <number> = ( zero | one | two | three | four | five | six | seven | nine | ten
                   | eleven | twelve | thirteen | fourteen | fifteen | sixteen | seventeen | eighteen | nineteen | twenty 
                   | thirty | forty | fifty | sixty | seventy | eighty | ninety 
		           | hundred | thousand | million | billion)+;                   
  public <syntax> = <number>{1} (plus | minus | multiply | division){1} <number>{1}; 
```
---

### My answer

Yes you can do it using the simple library i have written here , just one class :)

### Warning

I have to fix some things like for example if you search for the word `zero` it will return the rule `number` but not the rule `syntax` which obviously contains
the word `zero` but it has it like `<number>{1}` , so i must add more code to detect this also.

### Here i will add the Youtube tutorial

//here

### Example Test Code


```
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

```
package library;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * The grammar file must be correctly formated in order for this class to work.
 * 
 * <br>
 * Please see: <a href="https://www.w3.org/TR/jsgf/"> Link </a>
 * 
 * @author GOXR3PLUS
 *         [[SuppressWarningsSpartan]]
 */
public class JSGFGrammarParser {
	
	/**
	 * This class must not be initialised
	 */
	private JSGFGrammarParser() {
	}
	
	/**
	 * Opens the specified .gram file and finds the rules that contain the given
	 * words , based on the below parameters :
	 * 
	 * <hr>
	 * If for example we have [<b> public `feelings` = ( how are you | say
	 * hello); </b>] . The rule is <b>`feelings`</b><br>
	 * 
	 * <br>
	 * Big Problem is that ->it doesn't check if the rules contains other rules
	 * that contain the
	 * given
	 * words , so this rule contains the given words , for example :
	 * 
	 * <pre>
	 *  <code>
	 *   public {@code <number> }= ( zero | one | two | three | four | five | six | seven | nine | ten
	 *        | eleven | twelve | thirteen | fourteen | fifteen | sixteen | seventeen | eighteen | nineteen | twenty 
	 *        | thirty | forty | fifty | sixty | seventy | eighty | ninety 
	 *        | hundred | thousand | million | billion)+;                   
	 *   public {@code <syntax> } = {@code <number>}{1} (plus | minus | multiply | division){1} {@code <number>}{1}; 
	 *  </code>
	 * </pre>
	 * 
	 * @param absoluteFilePath
	 *        <br>
	 *        The absolute path of the grammar file that you want to read
	 * @param words
	 *        <br>
	 *        A list of words that you want to check and get the
	 *        rules containing them
	 * @param containAllWords
	 *        <br>
	 *        <b>True</b> if you want the rule to contain all the given words
	 *        <br>
	 *        <b>False</b> if you want the rule to contain any of the given
	 *        words
	 * @return ArrayList
	 *         <br>
	 *         of the rules that are containing [all or some ]
	 *         of the words given based on the third parameter [
	 *         ruleMustContainAllWords] of the method
	 */
	public static List<String> getRulesContainingWords(File file , List<String> words , boolean containAllWords) {
		
		//Read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(file.getAbsolutePath()))) {
			
			//Collect all the lines to one
			String oneLine = stream.map(String::trim).collect(Collectors.joining());
			
			return parseGrammarLines(oneLine, words, containAllWords);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	/**
	 * Opens the specified .gram file and finds the rules that contain the given
	 * words , based on the below parameters :
	 * 
	 * <hr>
	 * If for example we have [<b> public `feelings` = ( how are you | say
	 * hello); </b>] . The rule is <b>`feelings`</b><br>
	 * 
	 * <br>
	 * Big Problem is that ->it doesn't check if the rules contains other rules
	 * that contain the
	 * given
	 * words , so this rule contains the given words , for example :
	 * 
	 * <pre>
	 *  <code>
	 *   public {@code <number> }= ( zero | one | two | three | four | five | six | seven | nine | ten
	 *        | eleven | twelve | thirteen | fourteen | fifteen | sixteen | seventeen | eighteen | nineteen | twenty 
	 *        | thirty | forty | fifty | sixty | seventy | eighty | ninety 
	 *        | hundred | thousand | million | billion)+;                   
	 *   public {@code <syntax> } = {@code <number>}{1} (plus | minus | multiply | division){1} {@code <number>}{1}; 
	 *  </code>
	 * </pre>
	 * 
	 * @param absoluteFilePath
	 *        <br>
	 *        The grammar file as input stream ( in case it is inside a jar file
	 *        )
	 * @param words
	 *        <br>
	 *        A list of words that you want to check and get the
	 *        rules containing them
	 * @param containAllWords
	 *        <br>
	 *        <b>True</b> if you want the rule to contain all the given words
	 *        <br>
	 *        <b>False</b> if you want the rule to contain any of the given
	 *        words
	 * @return ArrayList
	 *         <br>
	 *         of the rules that are containing [all or some ]
	 *         of the words given based on the third parameter [
	 *         ruleMustContainAllWords] of the method
	 */
	public static List<String> getRulesContainingWords(InputStream inputStream , List<String> words , boolean containAllWords) {
		
		//Read file into stream, try-with-resources
		try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			
			//Collect all the lines to one
			StringBuilder oneLine = new StringBuilder(1500);
			String line;
			while ( ( line = reader.readLine() ) != null)
				oneLine.append(line.trim());
			
			return parseGrammarLines(oneLine.toString(), words, containAllWords);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return new ArrayList<>();
	}
	
	/*
	 * @param words
	 * <br>
	 * A list of words that you want to check and get the
	 * rules containing them
	 * @param containAllWords
	 * <br>
	 * <b>True</b> if you want the rule to contain all the given words
	 * <br>
	 * <b>False</b> if you want the rule to contain any of the given
	 * words
	 * @return ArrayList
	 * <br>
	 * of the rules that are containing [all or some ]
	 * of the words given based on the third parameter [
	 * ruleMustContainAllWords] of the method
	 */
	private static List<String> parseGrammarLines(String oneLine , List<String> words , boolean containAllWords) {
		
		//-------------Print the line - For testing----------------------
		//System.out.println(oneLine);
		
		//Store the rules containing this word inside an ArrayList		
		ArrayList<String> rules = new ArrayList<>();
		
		//Split by ; and go
		Arrays.asList(oneLine.split(";")).stream().forEach(line -> {
			
			//Check if line is a rule 
			if (!line.contains("<") || !line.contains(">") || !line.contains("="))
				return;
			
			//System.out.println("Line is a rule->" + line + "\n"); //TESTING CODE IGNORE IT
			
			//-------------------If the rule must [contain all] the given words
			if (containAllWords) {
				if (words.stream().allMatch(line.split("=")[1]::contains))
					rules.add(line.split("\\>")[0].split("\\<")[1]); //Get the rule		
					
				//---------------If the rule contains can [contain any] of the given words
			} else if (words.stream().anyMatch(line.split("=")[1]::contains))
				
				rules.add(line.split("\\>")[0].split("\\<")[1]); //Get the rule		
		});
		
		return rules;
	}
	
}

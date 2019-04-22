/****
 *
 * This is a simple stand-alone testing program for the baseGrammar lexer defined in
 * basGrammarLexer.JFLex.  The main method accepts an input file as its first
 * command-line argument.  It then calls the lexer's next_token method with an
 * input reader for that file.  The value of each Symbol returned by next_token
 * is printed to stanard output.
 *                                                                      <p> 
 * The following command-line invocation will read in the test program in the
 * file "lexer-test.p" and print out each token found in that file:
 *
 *     java baseGrammarLexerTest lexer-test.p
 *
 */

import java.io.*;
import java_cup.runtime.*;

public class BaseGrammarLexerTest {

  public static void main(String [] args) throws BaseGrammarException {
    Reader reader = null;
    
    if (args.length == 1) {
      File input = new File(args[0]);
      if (!input.canRead()) {
        System.out.println("Error: could not read ["+input+"]");
      }
      try {
        reader = new FileReader(input);
      }
      catch(Exception ex)
      {
        System.out.println(ex);
      }
    }
    else {  
     reader = new InputStreamReader(System.in);
    }

    BaseGrammarLexer scanner = new BaseGrammarLexer(reader);   // create scanner

    parser parser = new parser(scanner); // create parser
    Program program = null;

    try { 
      program = (Program) parser.parse().value;  // parse
    }    
    catch (Exception e) {
      e.printStackTrace(); 
    }  
    System.out.print(program.toString(0));
    boolean genCode  false;
    try
    {
    program.analysis();
    System.out.println("Analysis success");
    genCode  true;
    }
    catch(BaseGrammarException ex)
    {
      System.out.println(ex.toString());
    }
    catch(Exception ex)
    {
      System.out.println("ERROR:");
      System.out.println(ex);
    }
    try{
	program.gen();
	System.out.println("Code generated!");
    }   
    catch (Exception e) {
      e.printStackTrace(); 
    } 
  }

  public static void error(String s) { 
    System.out.println(s); 
  }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;

/**
 *
 * @author Mohamed Almukhtar
 */
public class ErrorException extends Exception{
    
    String message;
    int errorToken = -1;
    
    public ErrorException(int errorType, int lookAhead, String expected, ArrayList<String> tokens){
        
        this.message = errorMessage(errorType, lookAhead, expected, tokens);
    
    }
    
    @Override
    public String toString(){
        return message;
    }
    
    private String errorMessage(int errorType, int lookAhead, String expected, ArrayList<String> tokens){
        
        if(lookAhead<tokens.size())
         this.errorToken = lookAhead;
        
        switch(errorType){
            
            case 1 : return "\n\nToken : others : illegal use of expression\n\nSyntax Analysis :-\nSyntax Error : Error in Token : others, cant start if statement with others";
            
            case 2 : return "\nToken : '" + tokens.get(lookAhead) + "' : illegal use of expression, not an if statement\n\n"
                    + "Syntax Analysis :-\nSyntax Error : Error in Token : '" + tokens.get(lookAhead) + "'\nno if statement was found\nterminal number " + (++lookAhead) + " in statement";       
            
            case 3 : return "\nError in statement : stetement is incomplete\n\nSyntax Analysis :-\nSyntax Error : empty else statement, No commands in else expression.\n";
            
            case 4 : return "\nError in statement : stetement is incomplete\n\nSyntax Analysis :-\nSyntax Error : empty if statement.\n";
            
            case 5 : return "\nToken : '" + tokens.get(lookAhead) + "' illegal use of expression\n\n"
                    + "Syntax Analysis :-\nSyntax Error : Error in Token : '" + tokens.get(lookAhead) + "'\n'Relop operator' expected to complete expression.\nterminal number " + (++lookAhead) + " in statement";
            
            case 6 : return "\nError in statement : stetement is incomplete\n\nSyntax Analysis :-\nSyntax Error : missing terminal, 'Relop operator' expected to complete expression.\n";
            
            case 7 : return "\nToken : '" + tokens.get(lookAhead) + "' : illegal use of expression\n\n"
                    + "Syntax Analysis :-\nSyntax Error : Error in Token : '" + tokens.get(lookAhead) + "'\n'Identifier or constant' expected to complete expression\nterminal number " + (++lookAhead) + " in statement";
            
            case 8 : return "\nError in statement : stetement is incomplete\n\nSyntax Analysis :-\nSyntax Error : missing terminal, 'Identifier or constant' expected to complete expression.\n";
            
            case 9 : return "\nError in statement : stetement is incomplete\n\nSyntax Analysis :-\nSyntax Error : missing terminal, '" + expected + "' expected.\n";
            
            case 10 : return "\nToken : '" + tokens.get(lookAhead) + "' : illegal use of expression\n\n"
                    + "Syntax Analysis :-\nSyntax Error : Error in Token : '" + tokens.get(lookAhead) + "'\nterminal number " + (++lookAhead) + " in statement, '" + expected + "' expected";
            
            default : return "";
        }
        
    }
}

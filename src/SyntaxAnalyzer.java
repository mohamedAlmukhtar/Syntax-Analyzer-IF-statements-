

import java.awt.Color;
import java.util.ArrayList;
import javax.swing.text.BadLocationException;

/**
 *
 * @author Mohamed Almukhtar
 */
public class SyntaxAnalyzer {
    
    private int match = 0;
    private int lookAhead = 0;      //pointer used in parsing operation
    
    final ArrayList<String> tokens = new ArrayList<>();
    
    public int getSize(){
        return tokens.size();
    }
    
    public int getLookAhead(){
        return lookAhead;
    }
    
    
    
    public SyntaxAnalyzer(String statement){
        
        for(String token : statement.split("\\s+")){
          
            tokens.add(token);
          
        }
        
    }
    
    
    
    public void ifStatement() throws ErrorException, BadLocationException{
        
        match = 0;
        
        for(String token : tokens){
          
            if(token.equals("if"))
                match++;
            else if(token.equals("else"))
                match--;
          
        }
        
        if(match==0){
            matched(true);
        }    
        else{
            unmatched();
        }
        
    }
    
    
    
    private void matched(boolean firstToken) throws ErrorException, BadLocationException{
        
        boolean flag = !(lookAhead >= tokens.size());
        
        if(flag){
            
            switch(tokens.get(lookAhead)){

                case "if" : 
                    parse("if"); parse("("); expression(); parse("then");
                    matched(false); parse("else");
                    matched(false);
                    break;

                case "statement" : 
                    if(firstToken){
                        throw new ErrorException(1,lookAhead,null,tokens);
                    }
                    else{
                        parse("statement"); parse(";");
                    }
                    break;

                default : throw new ErrorException(2,lookAhead,null,tokens);

            }
            
        }
        else{
            throw new ErrorException(3,lookAhead,null,tokens);
        }
    }
    
    
    
    private void unmatched() throws ErrorException, BadLocationException{
        
        parse("if"); parse("("); expression(); 
        parse("then"); unmatchedDash();
        
    }
    
    
    
    private void unmatchedDash() throws ErrorException, BadLocationException{
        
        boolean flag = !(lookAhead >= tokens.size());
        
        if(flag){
            
            int startingToken = lookAhead;
            match = 0;
            int listSize;
            boolean over = false;

            if(tokens.get(lookAhead).equals("statement")){
                listSize = lookAhead + 2;
                if(listSize>tokens.size())
                    listSize--;
            }
            else
                listSize = tokens.size();

            for(int i = lookAhead; i < listSize; i++){

                if(tokens.get(i).equals("if")){
                    match++;
                }    
                else if(tokens.get(i).equals("else")){
                    match--;
                }
                if(match==0){
                    for(int j=(i+1); j < tokens.size(); j++){
                        if(!over){
                            if(tokens.get(j).equals("if"))
                                break;
                            else if(tokens.get(j).equals("else")){
                                lookAhead = startingToken;
                                matched(false); parse("else"); 
                                if(tokens.get(lookAhead).equals("statement"))
                                    matched(false);
                                else
                                    unmatched();
                                over = true;
                            }
                        }
                    }
                }

            }

            if(!over){
                if(match==0)
                    matched(false);
                else
                    unmatched();
            }
            
        }
        else{
            throw new ErrorException(4,lookAhead,null,tokens);
        }
    }
    
    
    
    private void expression() throws ErrorException, BadLocationException{
        
        factor();
        
        if(!(tokens.get(lookAhead).equals(")")))
            expressionDash();
        
        parse(")");
    }
    
    
    
    private void expressionDash() throws ErrorException, BadLocationException{
        
        boolean flag = !(lookAhead >= tokens.size());
        
        if(flag){
            switch(tokens.get(lookAhead)){

                case "<" :
                    parse("<"); factor();
                    break;

                case ">" : 
                    parse(">"); factor(); 
                    break;

                case "<>" :  
                    parse("<>"); factor(); 
                    break;

                case "==" :  
                    parse("=="); factor(); 
                    break;

                case ">=" :  
                    parse(">="); factor(); 
                    break;

                case "<=" :  
                    parse("<="); factor(); 
                    break;

                default : throw new ErrorException(5,lookAhead,null,tokens);
            }
        }
        else{
            throw new ErrorException(6,lookAhead,null,tokens);
        }
    }
    
    
    
    private void factor() throws ErrorException, BadLocationException{
        
        boolean flag = !(lookAhead >= tokens.size());
        
        if(flag){
            switch(tokens.get(lookAhead)){

                case "id" : 
                    parse("id");
                    break;

                case "num" :
                    parse("num");
                    break;

                case "!id" :
                    parse("!id");
                    break;

                default : throw new ErrorException(7,lookAhead,null,tokens);
            }
        }
        else{
            throw new ErrorException(8,lookAhead,null,tokens);
        }
    }
    
    
    
    private void parse(String terminal) throws ErrorException, BadLocationException{
        
        if(lookAhead >= tokens.size()){
            throw new ErrorException(9,lookAhead,terminal,tokens);
        }
        
        String currentToken = tokens.get(lookAhead);
        
        boolean equals = terminal.equals(currentToken);
        
        if(equals){
            AnalysisSystem.appendToPane(AnalysisSystem.output,"\nToken : '" + currentToken + "' : Successful use of terminal\n",Color.black);
            lookAhead++;
        }
        else{
            throw new ErrorException(10,lookAhead,terminal,tokens);
        }
    }
    
    
    
}//End of Class

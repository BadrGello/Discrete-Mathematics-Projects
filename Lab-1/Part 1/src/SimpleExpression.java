import java.util.Stack;

public class SimpleExpression implements Expression {
    String expression;

    @Override
    public String getRepresentation() {
        return this.expression;
    }
    
    private  boolean checkRepresentation(String expression){
        Stack<Character> parentheses=new Stack<>();
        char before;
        char after;
        String operations="^v>";
        String notAfterOperations=")^v>";
        String notBeforeOperations="(^v>";
        for (int i = 0; i < expression.length(); i++) {
            if(i==0){
                if(inTheString(expression.charAt(i), operations)){
                    return false; 
                }
                else if(expression.charAt(i)==')'){
                    return false; 
                }
                else if(expression.charAt(i)=='('){
                    parentheses.push('(');
                }
                else if(expression.charAt(i)=='~'){
                    if(expression.charAt(i+1)==')'||inTheString(expression.charAt(i+1), operations)){
                        return false; 
                    }
                }
                else{
                }
            }
            else if(i==expression.length()-1){
                if(inTheString(expression.charAt(i), operations)||expression.charAt(i)=='~'){
                    return false; 
                }
                else if(expression.charAt(i)=='('){
                    return false; 
                }
                else if(expression.charAt(i)=='~'){
                    return false;
                }
                else if(expression.charAt(i)==')'){
                    if(parentheses.isEmpty()){
                        return false; 
                        }
                    else{
                            parentheses.pop();
                        }
                    if(inTheString(expression.charAt(i-1), notBeforeOperations)){
                        return false; 
                    }
                }
                else{}
            }
            else{
                before=expression.charAt(i-1);
                after =expression.charAt(i+1);
                if(inTheString(expression.charAt(i), operations)){
                    if(inTheString(after, notAfterOperations)){
                        return false; 
                    }
                    else if(inTheString(before, notBeforeOperations)){
                        return false; 
                    }
                }
                else if(expression.charAt(i)=='('){
                    parentheses.push('(');
                    if(before=='~'||before==')'){
                        return false; 
                    }
                }
                else if(expression.charAt(i)==')'){
                    if(parentheses.isEmpty()){
                        return false; 
                    }
                    else{
                        parentheses.pop();
                    }
                    if(inTheString(before, notBeforeOperations)){
                        return false; 
                    }
                }
                else if(expression.charAt(i)=='~'){
                    if(inTheString(after, notAfterOperations)||before==')'){
                        return false; 
                    }
                }
                else{
                    if(inTheString(after, notAfterOperations)&&(inTheString(before, notBeforeOperations)||before=='~')){}
                    else {
                        return false; 
                    }
                }
            }
        }
        
        return true;
        
    }

    @Override
    public void setRepresentation(String representation) {
        representation=representation.replace(" ", "");
        if(!checkRepresentation(representation)){
            this.expression="Wrong expression";
        }
        else{
            this.expression=representation;
            
        }
    }
    private boolean inTheString(char a,String s){
        for(int i=0;i<s.length();i++){
            if(a==s.charAt(i)){
                return true;
            }
        }
        return false;
    }
    
}

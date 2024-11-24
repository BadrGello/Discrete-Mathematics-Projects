
import java.util.HashMap;
import java.util.Scanner;
import java.util.Stack;



public class SimpleLogicalExpressionSolver implements LogicalExpressionSolver{
    HashMap<Character, Boolean> variables;
    HashMap<Character, Integer> pairoty;

    public SimpleLogicalExpressionSolver(){
        this.variables=new HashMap<>();
        this.pairoty=new HashMap<>();
        pairoty.put('(', 4);
        pairoty.put('~', 3);
        pairoty.put('^', 2);
        pairoty.put('v', 1);
        pairoty.put('>', 0);
        variables.put('1',true);
        variables.put('0',false);
    }


    @Override
    public boolean evaluateExpression(Expression expression) {
        Stack<Character> expressionAfter=new Stack<>();
        Stack<Character> expressionque=new Stack<>();
        Scanner scan=new Scanner(System.in);
        String notVariables="()~^v>";
        String s=expression.getRepresentation();
        for(int i=0;i<s.length();i++){
            if(inTheString(s.charAt(i), notVariables)){}
            else if(!this.variables.containsKey(s.charAt(i))){
                System.out.print(s.charAt(i)+" =(true/false) ");
                String bool=scan.nextLine();
                if (bool.equalsIgnoreCase("true") || bool.equalsIgnoreCase("t")) {
                    this.variables.put(s.charAt(i), true);
                } else if (bool.equalsIgnoreCase("false") || bool.equalsIgnoreCase("f")) {
                    this.variables.put(s.charAt(i), false);
                } else {
                    System.out.println("Please enter (true or false)");
                    i--;
                }
            }
        }
        for(int i=0;i<s.length();i++){
            if(s.charAt(i)=='~'&&s.charAt(i+1)=='~'){
                i++;
            }
            else if(!inTheString(s.charAt(i), notVariables)&&s.charAt(i)!='~'){
                expressionAfter.push(s.charAt(i));
                
            }
            else if(s.charAt(i)==')'){
                char c =expressionque.peek();
                while (c!='(') {
                    expressionAfter.push(c);
                    expressionque.pop();
                    c=expressionque.peek();
                }
                expressionque.pop();
            }
            else{
                if(expressionque.isEmpty()||(expressionque.peek()=='(')){
                    expressionque.push(s.charAt(i));
                }
                else{
                    char c =expressionque.peek();
                    if (this.pairoty.get(s.charAt(i))>this.pairoty.get(c)){
                        expressionque.push(s.charAt(i));
                    }
                    else{
                        expressionAfter.push(c);
                        expressionque.pop();
                        expressionque.push(s.charAt(i));
                    }
                }
            }
        }
        while (!expressionque.isEmpty()) {
            char c=expressionque.peek();
            expressionAfter.push(c);
            expressionque.pop();
        }
        String operations="~^v>";
        boolean result=true;
        while (!expressionAfter.isEmpty()) {
            
                char c=expressionAfter.peek();
            if(inTheString(c, operations)){
                    expressionque.push(c);
                    expressionAfter.pop();
            }
                
            else{
                if(!expressionque.isEmpty()){
                    if(expressionque.peek()=='~'){
                        result=!variables.get(c);
                        expressionAfter.pop();
                        expressionque.pop();
                        if (result){
                            expressionAfter.push('1');
                        }
                        else{
                            expressionAfter.push('0');
                        }
                        
                    }
                    else{
                        char c1=expressionque.peek();
                        expressionque.pop();
                        if(inTheString(c1, operations)){
                            expressionque.push(c1);
                            expressionque.push(c);
                            expressionAfter.pop();
                        }
                        else {
                            char c2=expressionque.peek();
                            expressionque.pop();
                            switch (c2) {
                                case '^':
                                    result=variables.get(c1)&&variables.get(c);    
                                    break;
                                case  'v':
                                    result=variables.get(c1)||variables.get(c);
                                break;

                                case  '>':
                                    if(!variables.get(c)) result=true;
                                    else result = variables.get(c1);
                                break;

                                
                            }
                            if(!expressionque.isEmpty()&&expressionque.peek()=='~'){
                                result  =!  result;
                            }
                            if (result){
                                expressionAfter.pop();
                                expressionAfter.push('1');
                            }
                            else{
                                expressionAfter.pop();
                                expressionAfter.push('0');
                            }
                    }
                }
                }
                else{
                    expressionque.push(expressionAfter.peek());
                    expressionAfter.pop();
                }
            }
        }

        scan.close();
        return this.variables.get(expressionque.peek());
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
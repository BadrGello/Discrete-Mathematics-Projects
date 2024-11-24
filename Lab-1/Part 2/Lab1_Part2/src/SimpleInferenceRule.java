import java.util.ArrayList;
import java.util.HashMap;

public class SimpleInferenceRule implements InferenceRule{

    Expression premise1;
    Expression premise2;
    Expression inferredExpression;
    String ruleName;
    HashMap<String, String> relation;
    Boolean equivalent;

    // Define a new rule on the instance of the class (constructor)
    public SimpleInferenceRule(Expression exp1, Expression exp2, Expression output, String name) {
        this.premise1 = new SimpleExpression();
        this.premise1.setRepresentation((minNots(exp1.getRepresentation())));
        this.premise2 = new SimpleExpression();
        this.premise2.setRepresentation((minNots(exp2.getRepresentation())));
        this.inferredExpression = new SimpleExpression();
        this.inferredExpression.setRepresentation(minNots(output.getRepresentation()));
        this.ruleName = name;
        this.relation = new HashMap<>();
        this.equivalent = true;

    }

    // Returns a list of variables and operators "~A^B" --> ["~A", "^", "B"]
    public ArrayList<String> stringToList(String exp){
        ArrayList<String> vars = new ArrayList<>();
        for (int i=0; i<exp.length(); i++){
            if (exp.charAt(i) == '~'){
                vars.add(exp.substring(i, i+2));
                i++;
            }
            else {
                vars.add(exp.substring(i, i+1));
            }
        }
        return vars;
    }

    public Boolean isOperator (String c) {
        String ops = "~^v>";
        return ops.contains(c);
    }


    // Minimize the number of nots
    public String minNots(String str) {
        
        int numNots = 0;
        Boolean prevWasNot = false;

        StringBuilder result = new StringBuilder();

        for (int i=0; i<str.length(); i++){
            
            if (str.charAt(i) == '~'){
                numNots++;
                prevWasNot = true;
            }
            else {
                if (prevWasNot){
                    //Only append if not count is odd
                    if (numNots%2!=0){
                        result.append('~');
                    }
                }
                numNots = 0;
                prevWasNot = false;

                result.append(str.charAt(i));
            }
        }
        
        return result.toString();
    }

    // Validate inputs (expressions) with the rule's premises
    private int checkInputs(String exp1, String exp2){
        

        ArrayList<String> e1 = stringToList(exp1);
        ArrayList<String> e2 = stringToList(exp2);
        ArrayList<String> p1 = stringToList(premise1.getRepresentation());
        ArrayList<String> p2 = stringToList(premise2.getRepresentation());
        
        // First, check if e1 matches p1 and e2 matches p2
        if (checkLengthAndOperator(e1, p1) && checkLengthAndOperator(e2, p2)) {
            return 0; 
        }

        // If the original order doesn't work, try swapping e1 and e2
        if (checkLengthAndOperator(e1, p2) && checkLengthAndOperator(e2, p1)) {
            return 1;
        }

        return -1;

    }

    // Helper function for "checkInputs"
    private boolean checkLengthAndOperator(ArrayList<String> expList, ArrayList<String> premiseList) {
        // Check lengths first
        if (expList.size() != premiseList.size()) {
            return false;
        }

        // Check operators
        if (expList.size() == 3) {
            String expOperator = expList.get(1);
            String premiseOperator = premiseList.get(1);
            
            if (!expOperator.equals(premiseOperator)) {
                return false;
            }
        }
        return true;
    }
    

    private boolean containsOrAnd(String exp){
        return exp.contains("v") || exp.contains("^");
    }

    @Override
    // Check if the 2 expressions matches the inference rule
    public boolean matches(Expression exp1In, Expression exp2In) {

        ArrayList<String> e1List = stringToList(exp1In.getRepresentation());
        ArrayList<String> e2List = stringToList(exp2In.getRepresentation());
        ArrayList<Expression> tries = new ArrayList<>();
    
        tries.add(exp1In);
        tries.add(exp2In);
    
        if (containsOrAnd(exp1In.getRepresentation())) {
            Expression permutedExp1 = new SimpleExpression();
            permutedExp1.setRepresentation("" + e1List.get(2) + e1List.get(1) + e1List.get(0));
            tries.add(permutedExp1);
            tries.add(exp2In);
        } 
    
        if (containsOrAnd(exp2In.getRepresentation())) {
            Expression permutedExp2 = new SimpleExpression();
            permutedExp2.setRepresentation("" + e2List.get(2) + e2List.get(1) + e2List.get(0));
            tries.add(exp1In);
            tries.add(permutedExp2);
        }
    
        if (containsOrAnd(exp1In.getRepresentation()) && containsOrAnd(exp2In.getRepresentation())) {
            Expression permutedExp1 = new SimpleExpression();
            permutedExp1.setRepresentation("" + e1List.get(2) + e1List.get(1) + e1List.get(0));
    
            Expression permutedExp2 = new SimpleExpression();
            permutedExp2.setRepresentation("" + e2List.get(2) + e2List.get(1) + e2List.get(0));
    
            tries.add(permutedExp1);
            tries.add(permutedExp2);
        }
    
        for (int i=0; i<tries.size(); i+=2) {
            Expression exp1 = tries.get(i);
            Expression exp2 = tries.get(i + 1);
    
            int state = checkInputs(exp1.getRepresentation(), exp2.getRepresentation());
            if (state == -1) {
                continue;  
            }
    
            
            if (state == 1) {
                Expression swap = exp1;
                exp1 = exp2;
                exp2 = swap;
            }
    
            if (matchesOneWay(exp1, exp2)) {
                return this.equivalent = true; 
            }
    
            state = checkInputs(exp2.getRepresentation(), exp1.getRepresentation());
            if (state != -1) {

                if (state == 1) {
                    Expression swap = exp1;
                    exp1 = exp2;
                    exp2 = swap;
                }

                if (matchesOneWay(exp2, exp1)) {
                    return this.equivalent = true; 
                }
            }
        }
    
        return this.equivalent = false;
    }
    
    
    private boolean matchesOneWay(Expression exp1, Expression exp2){

        String preIn1 = (minNots(exp1.getRepresentation()));
        String preIn2 = (minNots(exp2.getRepresentation()));
        this.relation = new HashMap<>();

        ArrayList<String> vars = stringToList(preIn1);
        ArrayList<String> varsToCheck = stringToList(premise1.getRepresentation());
                
        for (int numExp=1; numExp<=2; numExp++){

            if (numExp==2){
                vars = stringToList(preIn2);
                varsToCheck = stringToList(premise2.getRepresentation());
            }

            for (int i=0; i<vars.size(); i++){
                
                if (isOperator(vars.get(i)) && isOperator(varsToCheck.get(i))){
                    if (!vars.get(i).equals(varsToCheck.get(i))){
                        return false;
                    }
                    this.relation.put(vars.get(i), varsToCheck.get(i));
                }
                else {
                   
                    if (this.relation.get(varsToCheck.get(i)) == null && this.relation.get(minNots("~"+varsToCheck.get(i))) == null ){
                            this.relation.put(varsToCheck.get(i), vars.get(i)); 
                            this.relation.put(minNots("~"+varsToCheck.get(i)), (minNots("~"+vars.get(i))));                       
                    }
                    else {
                        if (!this.relation.get(varsToCheck.get(i)).equals(vars.get(i))){
                            return false;
                        }
                    }
                }
            }
        }
        
        return true;
    }

    @Override
    // Outputs the inferred expression
    public Expression apply(Expression exp1, Expression exp2) {
        Expression result = new SimpleExpression();

        if (this.equivalent){
            StringBuilder out = new StringBuilder();
            ArrayList<String> varsConclusion = stringToList(inferredExpression.getRepresentation());
            for (String var : varsConclusion){
                String mappedValue = this.relation.get(var);
                if (var != null) {
                    if (var.charAt(0)=='~' && this.relation.get(var.substring(1)) != null){
                        out.append("~");
                        out.append(this.relation.get(var.substring(1)));
                    }
                    else if (mappedValue == null && this.relation.get("~"+var) != null){
                        out.append("~");
                        out.append(this.relation.get("~"+var));
                    }
                    else if (mappedValue != null) {
                        out.append(mappedValue);
                    } 
                    else {
                        out.append(var);
                    }
                }
            }
            String outString = minNots(out.toString());
            System.out.println("(" + this.ruleName + ")");
            result.setRepresentation(outString);
        }
        else{
            result.setRepresentation("Not A Match");
        }

        return result;
    }

    
}

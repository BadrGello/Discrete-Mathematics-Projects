import java.util.ArrayList;

public class SimpleInferenceEngine implements InferenceEngine {
    
    ArrayList<InferenceRule> rules;
    ArrayList<Expression> expressions;

    public SimpleInferenceEngine() {
        this.rules = new ArrayList<>();
        this.expressions = new ArrayList<>();
    }

    @Override
    public void addRule(InferenceRule rule) {
        
        rules.add(rule);
    }

    @Override
    public void addExpression(Expression exp) {
        
        if (expressions.size() < 2) {  //Allow only up to 2 expressions
            expressions.add(exp);
        } 
        else {
            System.out.println("Cannot add more than 2 expressions.");
            
        }
    }

    @Override
    public Expression applyRules() {
        //Looping over each rule and trying to apply it to the current expressions
        for (InferenceRule rule : rules) {
            if (expressions.size() == 2) {
                Expression exp1 = expressions.get(0);
                Expression exp2 = expressions.get(1);

                if (rule.matches(exp1, exp2)) {
                    return rule.apply(exp1, exp2);
                }
            } 
            else {
                System.out.println("Can only infer 2 expressions.");
            }
            
        }

        return null;
    }
}

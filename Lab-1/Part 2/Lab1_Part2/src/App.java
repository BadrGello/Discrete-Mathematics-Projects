import java.util.Scanner;

public class App {
    public static void main(String[] args){

        Expression exp1 = new SimpleExpression();
        Expression exp2 = new SimpleExpression();
        Expression expOut = new SimpleExpression();

        InferenceEngine Iengine = new SimpleInferenceEngine();

        // Modus Ponens: P > Q, P ⟹ Q
        exp1.setRepresentation("P > Q");
        exp2.setRepresentation("P");
        expOut.setRepresentation("Q");
        InferenceRule modusPonens = new SimpleInferenceRule(exp1, exp2, expOut, "Modus Ponens");
        Iengine.addRule(modusPonens);

        // Modus Tollens: P > Q, ~Q ⟹ ~P
        exp1.setRepresentation("P > Q");
        exp2.setRepresentation("~Q");
        expOut.setRepresentation("~P");
        InferenceRule modusTollens = new SimpleInferenceRule(exp1, exp2, expOut, "Modus Tollens");
        Iengine.addRule(modusTollens);

        // Hypothetical Syllogism: P > Q, Q > R ⟹ P > R
        exp1.setRepresentation("P > Q");
        exp2.setRepresentation("Q > R");
        expOut.setRepresentation("P > R");
        InferenceRule hypotheticalSyllogism = new SimpleInferenceRule(exp1, exp2, expOut, "Hypothetical Syllogism");
        Iengine.addRule(hypotheticalSyllogism);

        // Disjunctive Syllogism: P v Q, ~P ⟹ Q
        exp1.setRepresentation("P v Q");
        exp2.setRepresentation("~P");
        expOut.setRepresentation("Q");
        InferenceRule disjunctiveSyllogism = new SimpleInferenceRule(exp1, exp2, expOut, "Disjunctive Syllogism");
        Iengine.addRule(disjunctiveSyllogism);

        // Resolution: P v Q, ~P v R ⟹ Q v R
        exp1.setRepresentation("P v Q");
        exp2.setRepresentation("~P v R");
        expOut.setRepresentation("Q v R");
        InferenceRule resolution = new SimpleInferenceRule(exp1, exp2, expOut, "Resolution");
        Iengine.addRule(resolution);

        // MadeUp rule for testing:
        // exp1.setRepresentation("A");
        // exp2.setRepresentation("B");
        // expOut.setRepresentation("A>B>1");
        // InferenceRule madeUp = new SimpleInferenceRule(exp1, exp2, expOut, "MadeUp");
        // Iengine.addRule(madeUp);

        // MadeUp2 rule for testing:
        // exp1.setRepresentation("a");
        // exp2.setRepresentation("b");
        // expOut.setRepresentation("a ^ b");
        // InferenceRule madeUp2 = new SimpleInferenceRule(exp1, exp2, expOut, "Conjuctive Addition");
        // Iengine.addRule(madeUp2);

        // MadeUp3 rule for testing:
        // exp1.setRepresentation("a^b");
        // exp2.setRepresentation("");
        // expOut.setRepresentation("a");
        // InferenceRule madeUp3 = new SimpleInferenceRule(exp1, exp2, expOut, "Conjuctive Simplification");
        // Iengine.addRule(madeUp3);


        Expression test1 = new SimpleExpression();
        Expression test2 = new SimpleExpression();

        System.out.println("Enter 2 Expressions");
        Scanner scanner = new Scanner(System.in);

        Boolean testMode = false;
        // testMode = true;
        String in;

        if (!testMode){
            in = scanner.nextLine();
            test1.setRepresentation(in);
            if (test1.getRepresentation().equals("Wrong expression")){
                System.out.println("Wrong expression");
                scanner.close();
                return;
            }

            in = scanner.nextLine();
            test2.setRepresentation(in);
            if (test2.getRepresentation().equals("Wrong expression")){
                System.out.println("Wrong expression");
                scanner.close();
                return;
            }
        }

        // test1.setRepresentation("~b>a");
        // test2.setRepresentation("~b");

        // test1.setRepresentation("L>~G");
        // test2.setRepresentation("G");

        // test1.setRepresentation("G>H");
        // test2.setRepresentation("H > L");
        
        // test1.setRepresentation("GvL");
        // test2.setRepresentation("~L");

        // test1.setRepresentation("~Lv~G");
        // test2.setRepresentation("Lv~B");
        
        Iengine.addExpression(test1);
        Iengine.addExpression(test2);

        Expression result = Iengine.applyRules();
        if (result==null){
            System.out.println("The input expression cannot be inferred");
        }
        else {
            System.out.println(result.getRepresentation());
        }

        scanner.close();

    }
}

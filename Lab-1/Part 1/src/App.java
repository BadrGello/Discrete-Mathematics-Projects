import java.util.Scanner;

    public class App {
        public static void main(String[] args) {
            System.out.println("Enter Expression");
            Scanner scan=new Scanner(System.in);
            Expression e =new SimpleExpression();
            String s=scan.nextLine();
            e.setRepresentation(s);
            if("Wrong expression".equals(e.getRepresentation())){
                System.out.println("Wrong expression");
                scan.close();
            }
            else{
                LogicalExpressionSolver s1= new SimpleLogicalExpressionSolver();
                System.out.println(s1.evaluateExpression(e));
                scan.close();
            }
    }
}

public class App {
    public static int computeGCD(int a ,int b){
        if(b==0) return a;
        else{
        return computeGCD(b, a%b);
        }
     }
     public static int computeLCM(int a ,int b){
         int gcd=computeGCD(a, b);
         int lcm=gcd*(a/gcd)*(b/gcd);
         return lcm;
      }
 
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}

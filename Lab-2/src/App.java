import java.util.Map;

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
    
    public static int computeGCDByPrime(int a ,int b){
        if(a==0||b==0){
            return a+b;
        }
        else {
            int gcd=1;
            Primes x=new Primes();
            Map<Integer,Integer> ap=x.getPrimeFactors(a);
            Map<Integer,Integer> bp=x.getPrimeFactors(b);
            for(Integer i:ap.keySet()){
                if (bp.containsKey(i)){
                int power=Math.min(ap.get(i), bp.get(i));
                gcd *=Math.pow(i, power);

            }
        }
        return gcd;
        }
    }
    public static int computeLCMByPrime(int a ,int b){
        if(a==0||b==0){
            return 0;
        }
        else {
        int lcm=1;
            Primes x=new Primes();
            Map<Integer,Integer> ap=x.getPrimeFactors(a);
            Map<Integer,Integer> bp=x.getPrimeFactors(b);
            for(Integer i:ap.keySet()){
                if (bp.containsKey(i)){
                int power=Math.max(ap.get(i), bp.get(i));
                lcm *=Math.pow(i, power);}
                else{
                    lcm *=Math.pow(i, ap.get(i));
                }
                bp.put(i, 0);    
            }
        for(Integer i:bp.keySet()){
            lcm *=Math.pow(i, bp.get(i));
        }
        return lcm;
    }
    }
    public static void test(int A,int B) {
        System.out.println("n1: "+A+"    n2: "+B+"   lcmByprimefactors: "+computeLCMByPrime(A, B)+"  gcdByprimefactors: "+computeGCDByPrime(A, B)+"  lcmByeuclidean: "+computeLCM(A, B)+"    gcdByeuclidean: "+computeGCD(A, B));
    } 

    public static void primeTest(int A){
        Primes x=new Primes();
        Map<Integer,Integer> Ap=x.getPrimeFactors(A);
        String s="";
        for(Integer i:Ap.keySet()){
            int n=Ap.get(i);
            if(n!=1) s+=(i+"^"+Ap.get(i)+"*");
            else s+=(i+"*");
        }
        s=s.substring(0, s.length() - 1);
        System.out.println("n: "+A+"    prime factorization: "+s);
    }
    public static void primeCheckerTest(int A) {
        Primes x=new Primes();
        System.out.println("n: "+A+"    is prime: "+x.isPrime(A));
    }
    public static void main(String[] args) throws Exception {
        System.out.println("is prime test cases:");
        System.out.println();
        primeCheckerTest(2);
        primeCheckerTest(4);
        primeCheckerTest(97);
        primeCheckerTest(100);
        primeCheckerTest(1);
        primeCheckerTest(13);
        primeCheckerTest(9);
        primeCheckerTest(143);
        primeCheckerTest(6);
        System.out.println();
        System.out.println("prime factorization test cases:");
        System.out.println();
        primeTest(12);
        primeTest(6);
        primeTest(13);
        primeTest(8);
        primeTest(30);
        primeTest(100);
        primeTest(97);
        primeTest(120);
        primeTest(143);
        System.out.println();
        System.out.println("GCD and LCM test cases:");
        System.out.println();
        test(12, 0);
        test(12, 18);
        test(5, 20);
        test(7, 13);
        test(0, 25);
        test(8, 8);
        test(450, 600);
        test(14, 15);

        


        // Boolean arr[] = primes.getPrimeArray();
        // for (int i=0; i<arr.length; i++){
        //     if (arr[i]){
        //         System.out.println(i + "");
        //     }
        // }
    }
}

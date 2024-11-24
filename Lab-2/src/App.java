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
    public static void main(String[] args) throws Exception {
        System.out.println(computeLCMByPrime(12, 15));
        int n = 7561;
        Primes primes = new Primes();
        System.out.println("is " + n + " prime? " + primes.isPrime(n));
        Map<Integer, Integer> map = primes.getPrimeFactors(n);
        for (Integer key : map.keySet()) {
            System.out.println("Key: " + key + " Power: " + map.get(key));
        }


        // Boolean arr[] = primes.getPrimeArray();
        // for (int i=0; i<arr.length; i++){
        //     if (arr[i]){
        //         System.out.println(i + "");
        //     }
        // }
    }
}

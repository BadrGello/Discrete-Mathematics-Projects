import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

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

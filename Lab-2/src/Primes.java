import java.util.HashMap;
import java.util.Map;

public class Primes {

    private Boolean primeArray[];
    private int maxN;

    public Primes(){
        this.maxN=0;
    }

    private void evaluatePrimeArray(int n){
        if (n<=maxN && n!=0){
            return;
        }

        this.maxN = n;
        this.primeArray = new Boolean[n+1];

        for (int i=0; i<=n; i++){
            if (i==0 || i==1){
                this.primeArray[i] = false;
                continue;
            }
            
            this.primeArray[i] = true;
        }

        // We iterate over each number and if it's prime, we uncheck its multiples, 
        // we stop at p*p > n, ex: n=100, 11*11 > 100 so before p=11 we guarantee we checked every number <= 100 (up to p=10). 
        for (int p=2; p*p<=n; p++){
            if (this.primeArray[p]){
                // When we start unchecking its multiples, we start at the number squared (p*p) since all numbers before it have been checked
                for (int i=p*p; i<=n; i+=p){
                    this.primeArray[i] = false;
                }
            }
        }
    }

    // Returns a boolean array of numbers up to n where each number is true if prime or false if composite
    public Boolean[] getPrimeArray(int n){
        if(n!=maxN){
            this.evaluatePrimeArray(n);
        }
        

        if (this.primeArray.length != 0){
            return this.primeArray;
        }

        return null;
    }
    
    public Boolean isPrime(int p){
        if (primeArray == null || p>maxN){
            this.evaluatePrimeArray(p);
        }

        return primeArray[p];
    
        
    }

    // Returns a map of all prime factors of a number in form <p, power>
    public Map<Integer, Integer> getPrimeFactors(int n){
        if (n>maxN){
            this.evaluatePrimeArray(n);
        }
        Map<Integer, Integer> primeFactorsMap =new HashMap<Integer, Integer>();

        for (int i=2; i<=n; i++){
            if (primeArray[i]){
                if (n%i == 0){
                    if (primeFactorsMap.containsKey(i)){
                        primeFactorsMap.put(i, primeFactorsMap.get(i) + 1);
                    }
                    else {
                        primeFactorsMap.put(i, 1);
                    }
                    n = n / i;
                    i--;
                }
            }
        }

        return primeFactorsMap;
    }
}

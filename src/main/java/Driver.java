import com.stantonj.primesieve.PrimeSieve;

public class Driver {

    public static void main(String[] args) {
        long[] primes = PrimeSieve.SINGLETON.generatePrimes(1000, 50000000L);
        for (long p : primes) {
            System.out.println(p);
        }
        String version = PrimeSieve.SINGLETON.version();
        System.out.println(version);
        //System.out.println(PrimeSieve.SINGLETON.test());

        PrimeSieve.SINGLETON.callbackPrimes(1, 20, System.out::println);
    }
}

import com.stantonj.primesieve.PrimeSieve;
import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;
import static java.lang.Math.toIntExact;


/**
 * Created by jstanton on 2/4/16.
 */
public class Driver {

    public static void main(String[] args){
        PrimeSieve prime = (PrimeSieve) Native.loadLibrary("primesieve_x64", PrimeSieve.class);

        Pointer size = new Memory(Long.SIZE);
        Pointer tmp = prime.primesieve_generate_primes(1000, 50000000L, size, PrimeSieve.INT64_PRIMES);
        long[] primes = tmp.getLongArray(0, toIntExact(size.getLong(0)));
        for(long p : primes){
            System.out.println(p);
        }
        String version = prime.primesieve_version();
        System.out.println(version);
        System.out.println(prime.primesieve_test());
    }
}

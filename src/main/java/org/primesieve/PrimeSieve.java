package org.primesieve;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.NativeLibrary;
import com.sun.jna.Pointer;
import cz.adamh.utils.NativeUtils;

import java.util.Locale;
import java.util.Properties;

import static java.lang.Math.toIntExact;

public class PrimeSieve {

    static final int LONGSIZE = Long.SIZE;
    public static final PrimeSieveRaw INSTANCE;
    public static final PrimeSieve SINGLETON = new PrimeSieve();


    private static Object LoadNativePath(String path, String name, Class clz){
       //String tmp = System.getProperty("java.library.path");
        //System.setProperty("java.library.path", path);
        //Object ret = Native.loadLibrary(name, clz);
        //System.setProperty("java.library.path", tmp);
        NativeLibrary.addSearchPath(path, name);
        return Native.loadLibrary(name, clz);
    }

    static {
        Properties props = System.getProperties();
        PrimeSieveRaw inst = null;
        if(!props.containsKey("org.primesieve.library")) {

            int archSize = LONGSIZE;

            String arch = "primesieve_x" + archSize;

            try {
                inst = (PrimeSieveRaw) Native.loadLibrary(arch, PrimeSieveRaw.class);
            }
            catch(java.lang.UnsatisfiedLinkError e){
                String path;
                String name;

                String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
                System.out.println(OS);
                if ((OS.contains("mac")) || (OS.contains("darwin"))) {
                    path = String.format("/libprimesieve_x%s.dylib", archSize);
                } else if (OS.contains("nux")) {
                    path = String.format("/libprimesieve_x%s.so", archSize);
                } else {
                    throw new UnsatisfiedLinkError("Library for platform unsupported");
                }
                try {
                    //inst = (PrimeSieveRaw) NativeUtils.loadLibraryFromJar(path, (String p) -> LoadNativePath(p, "libprimesieve", PrimeSieveRaw.class));
                    NativeUtils.loadLibraryFromJar(path);
                    inst = (PrimeSieveRaw) Native.loadLibrary(PrimeSieveRaw.class);
                } catch(Exception e2){
                    throw new UnsatisfiedLinkError("Error loading library: " + e2.getMessage());
                }
            }



        }
        else{
            inst = (PrimeSieveRaw) LoadNativePath(props.getProperty("org.primesieve.library"), "primesieve", PrimeSieveRaw.class);
        }

        INSTANCE = inst;
    }

    private PrimeSieve() {
    }


    public long[] generatePrimes(long start, long stop) {
        Pointer size = new Memory(LONGSIZE);
        Pointer tmp = INSTANCE.primesieve_generate_primes(start, stop, size, PrimeSieveRaw.INT64_PRIMES);
        return tmp.getLongArray(0, toIntExact(size.getLong(0)));
    }

    public long[] generateNPrimes(long n, long start) {
        Pointer tmp = INSTANCE.primesieve_generate_n_primes(n, start, PrimeSieveRaw.INT64_PRIMES);
        return tmp.getLongArray(0, toIntExact(n));
    }

    public long nthPrime(long n, long start) {
        return INSTANCE.primesieve_nth_prime(n, start);
    }

    public long parallelNthPrime(long n, long start) {
        return INSTANCE.primesieve_parallel_nth_prime(n, start);
    }

    public long countPrimes(long start, long stop) {
        return INSTANCE.primesieve_count_primes(start, stop);
    }

    public long countTwins(long start, long stop) {
        return INSTANCE.primesieve_count_twins(start, stop);
    }

    public long countTriplets(long start, long stop) {
        return INSTANCE.primesieve_count_triplets(start, stop);
    }

    public long countQuadruplets(long start, long stop) {
        return INSTANCE.primesieve_count_quadruplets(start, stop);
    }

    public long countQuintuplets(long start, long stop) {
        return INSTANCE.primesieve_count_quintuplets(start, stop);
    }

    public long countSextuplets(long start, long stop) {
        return INSTANCE.primesieve_count_sextuplets(start, stop);
    }

    public long parallelCountPrimes(long start, long stop) {
        return INSTANCE.primesieve_parallel_count_primes(start, stop);
    }

    public long parallelCountTwins(long start, long stop) {
        return INSTANCE.primesieve_parallel_count_twins(start, stop);
    }

    public long parallelCountTriplets(long start, long stop) {
        return INSTANCE.primesieve_parallel_count_triplets(start, stop);
    }

    public long parallelCountQuadruplets(long start, long stop) {
        return INSTANCE.primesieve_parallel_count_quadruplets(start, stop);
    }

    public long parallelCountQuintuplets(long start, long stop) {
        return INSTANCE.primesieve_parallel_count_quintuplets(start, stop);
    }

    public long parallelCountSextuplets(long start, long stop) {
        return INSTANCE.primesieve_parallel_count_sextuplets(start, stop);
    }

    public void printPrimes(long start, long stop) {
        INSTANCE.primesieve_print_primes(start, stop);
    }

    public void printTwins(long start, long stop) {
        INSTANCE.primesieve_print_twins(start, stop);
    }

    public void printTriplets(long start, long stop) {
        INSTANCE.primesieve_print_triplets(start, stop);
    }

    public void printQuadruplets(long start, long stop) {
        INSTANCE.primesieve_print_quadruplets(start, stop);
    }

    public void printQuintuplets(long start, long stop) {
        INSTANCE.primesieve_print_quintuplets(start, stop);
    }

    public void printSextuplets(long start, long stop) {
        INSTANCE.primesieve_print_sextuplets(start, stop);
    }

    int getSieveSize() {
        return INSTANCE.primesieve_get_sieve_size();
    }

    int getNumThreads() {
        return INSTANCE.primesieve_get_num_threads();
    }

    public long getMaxStop() {
        return INSTANCE.primesieve_get_max_stop();
    }

    public void setSieveSize(int sieve_size) {
        INSTANCE.primesieve_set_sieve_size(sieve_size);
    }

    public void setNumThreads(int num_threads) {
        INSTANCE.primesieve_set_num_threads(num_threads);
    }

    public int test() {
        return INSTANCE.primesieve_test();
    }

    public String version() {
        return INSTANCE.primesieve_version();
    }

    public void  callbackPrimes (long start, long stop, PrimeSieveRaw.PrimeCallback cb){
        INSTANCE.primesieve_callback_primes(start, stop, cb);
    }

    //void  free (Pointer primes);
}

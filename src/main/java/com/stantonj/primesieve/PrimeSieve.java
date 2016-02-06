package com.stantonj.primesieve;

import com.sun.jna.Memory;
import com.sun.jna.Native;
import com.sun.jna.Pointer;

import static java.lang.Math.toIntExact;

public class PrimeSieve {

    static final int LONGSIZE = Long.SIZE;
    public static final PrimeSieveRaw INSTANCE;
    public static final PrimeSieve SINGLETON = new PrimeSieve();

    static {
        int archSize = LONGSIZE;

        String arch = "primesieve_x" + archSize;

        INSTANCE = (PrimeSieveRaw) Native.loadLibrary(arch, PrimeSieveRaw.class);
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

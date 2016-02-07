package org.primesieve;

import com.sun.jna.Callback;
import com.sun.jna.Library;
import com.sun.jna.Pointer;

public interface PrimeSieveRaw extends Library{

    interface PrimeCallback extends Callback {
        void invoke(long prime);
    }

    /** Use all CPU cores for prime sieving. */
    int MAX_THREADS = - 1;
    /** Generate primes of short type. */
    int SHORT_PRIMES = 0;
    /** Generate primes of unsigned short type. */
    int USHORT_PRIMES = 1;
    /** Generate primes of int type. */
    int INT_PRIMES = 2;
    /** Generate primes of unsigned int type. */
    int UINT_PRIMES = 3;
    /** Generate primes of long type. */
    int LONG_PRIMES = 4;
    /** Generate primes of unsigned long type. */
    int ULONG_PRIMES = 5;
    /** Generate primes of long long type. */
    int LONGLONG_PRIMES = 6;
    /** Generate primes of unsigned long long type. */
    int ULONGLONG_PRIMES = 7;
    /** Generate primes of int16_t type. */
    int INT16_PRIMES = 8;
    /** Generate primes of uint16_t type. */
    int UINT16_PRIMES = 9;
    /** Generate primes of int32_t type. */
    int INT32_PRIMES = 10;
    /** Generate primes of uint32_t type. */
    int UINT32_PRIMES = 11;
    /** Generate primes of int64_t type. */
    int INT64_PRIMES = 12;
    /** Generate primes of long type. */
    int UINT64_PRIMES = 13;


    Pointer primesieve_generate_primes(long start, long stop, Pointer size, int type);

    Pointer 	primesieve_generate_n_primes (long n, long start, int type);
    long 	primesieve_nth_prime (long n, long start);
    long 	primesieve_parallel_nth_prime (long n, long start);
    long 	primesieve_count_primes (long start, long stop);
    long 	primesieve_count_twins (long start, long stop);
    long 	primesieve_count_triplets (long start, long stop);
    long 	primesieve_count_quadruplets (long start, long stop);
    long 	primesieve_count_quintuplets (long start, long stop);
    long 	primesieve_count_sextuplets (long start, long stop);
    long 	primesieve_parallel_count_primes (long start, long stop);
    long 	primesieve_parallel_count_twins (long start, long stop);
    long 	primesieve_parallel_count_triplets (long start, long stop);
    long 	primesieve_parallel_count_quadruplets (long start, long stop);
    long 	primesieve_parallel_count_quintuplets (long start, long stop);
    long 	primesieve_parallel_count_sextuplets (long start, long stop);
    void 	primesieve_print_primes (long start, long stop);
    void 	primesieve_print_twins (long start, long stop);
    void 	primesieve_print_triplets (long start, long stop);
    void 	primesieve_print_quadruplets (long start, long stop);
    void 	primesieve_print_quintuplets (long start, long stop);
    void 	primesieve_print_sextuplets (long start, long stop);
    void 	primesieve_callback_primes (long start, long stop, PrimeCallback cb);
    int 	primesieve_get_sieve_size ();
    int 	primesieve_get_num_threads ();
    long 	primesieve_get_max_stop ();
    void 	primesieve_set_sieve_size (int sieve_size);
    void 	primesieve_set_num_threads (int num_threads);
    void 	primesieve_free (Pointer primes);
    int 	primesieve_test ();
    String 	primesieve_version ();
}

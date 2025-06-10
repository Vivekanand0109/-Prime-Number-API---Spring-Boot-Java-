package com.example.primeapi;

import org.springframework.web.bind.annotation.*;

@RestController
public class PrimeController {

    @GetMapping("/is_prime")
    public PrimeResponse checkPrime(@RequestParam(required = false) String number) {
        try {
            int num = Integer.parseInt(number);
            boolean isPrime = isPrime(num);
            return new PrimeResponse(num, isPrime);
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("Invalid or missing 'number' parameter. Please provide a valid integer.");
        }
    }

    private boolean isPrime(int n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (int i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    record PrimeResponse(int number, boolean isPrime) {}
}

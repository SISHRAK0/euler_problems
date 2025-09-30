#include <iostream>
#include <cmath>

bool is_prime(long n) {
    if (n < 2) return false;
    if (n == 2) return true;
    if (n % 2 == 0) return false;
    long limit = std::sqrt(n);
    for (long i = 3; i <= limit; i += 2) {
        if (n % i == 0) return false;
    }
    return true;
}

long nth_prime(int n) {
    int count = 0;
    long candidate = 1;
    while (count < n) {
        candidate++;
        if (is_prime(candidate)) {
            count++;
        }
    }
    return candidate;
}

int main() {
    std::cout << nth_prime(10001) << std::endl;
    return 0;
}

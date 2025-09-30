#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
#include <numeric>

long factorial(int n) {
    long res = 1;
    for (int i = 2; i <= n; i++) res *= i;
    return res;
}

std::string nth_permutation(std::vector<int> digits, long n) {
    n -= 1; // 0-based
    std::string result;
    while (!digits.empty()) {
        long f = factorial(digits.size() - 1);
        int idx = n / f;
        result += std::to_string(digits[idx]);
        digits.erase(digits.begin() + idx);
        n %= f;
    }
    return result;
}

int main() {
    std::vector<int> digits(10);
    std::iota(digits.begin(), digits.end(), 0);
    std::cout << nth_permutation(digits, 1000000) << std::endl;
    return 0;
}

(ns euler.problem7-test
  (:require [clojure.test :refer :all]
            [problem7 :as p7]
            [problem7-alt :as p7alt]))

(deftest basics
  (is (= 13 (nth p7/primes-lazy 5)))
  (is (= 104743 (p7/solve-7-lazy)))
  (is (= 104743 (p7/solve-7-map)))
  (is (= 104743 (p7/solve-7-recursive)))
  (is (= 104743 (p7/solve-7-tail)))
  (is (= 104743 (p7alt/solve-7-sieve))))

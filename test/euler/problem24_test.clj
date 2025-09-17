(ns euler.problem24-test
  (:require [clojure.test :refer :all]
            [problem24 :as p24]
            [problem24-alt :as p24alt]))

(deftest permutations-test
  (is (= "2783915460" (p24/solve-24)))
  (is (= "2783915460" (p24alt/nth-perm-tail (map str (range 0 10)) 1000000))))

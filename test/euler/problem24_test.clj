(ns euler.problem24-test
  (:require [clojure.test :refer :all]
            [problem24 :as p24]
            [problem24-alt :as p24alt]))

(deftest factorial-test
  (is (= 1 (p24/factorial 0)))
  (is (= 1 (p24/factorial 1)))
  (is (= 120 (p24/factorial 5))))

(deftest small-permutations
  (testing "Малые множества (сравнение с известными перестановками)"
    ;; Для [0 1 2] все перестановки:
    ;; 012, 021, 102, 120, 201, 210
    (is (= ["0" "1" "2"]
           (p24/nth-lexicographic-permutation ["0" "1" "2"] 1)))
    (is (= ["2" "1" "0"]
           (p24/nth-lexicographic-permutation ["0" "1" "2"] 6)))))

(deftest solve-24-all-implementations
  (testing "Все реализации задачи 24 должны вернуть одинаковый результат"
    (is (= "2783915460" (p24/solve-24)))
    (is (= "2783915460" (p24/solve-24-tail)))
    (is (= "2783915460" (p24alt/millionth-permutation)))))

(deftest consistency-check
  (testing "Сравнение всех реализаций задачи 24"
    (let [results [(p24/solve-24)
                   (p24/solve-24-tail)
                   (p24alt/millionth-permutation)]]
      (is (apply = results)))))

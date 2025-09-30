(ns euler.problem24-test
  (:require
    [clojure.java.shell :as shell]
    [clojure.string :as str]
    [clojure.test :refer [deftest is testing]]
    [problem24 :as p24]
    [problem24-alt :as p24alt]))

(defn run-python-script [script-path]
  (let [result (shell/sh "python3" script-path)]
    (if (zero? (:exit result))
      (str/trim (:out result))
      (throw (Exception. (str "Python error: " (:err result)))))))

(defn run-cpp-program [src-path]
  (let [exe (str src-path ".out")]
    (shell/sh "g++" src-path "-o" exe)
    (let [result (shell/sh (str "./" exe))]
      (if (zero? (:exit result))
        (str/trim (:out result))
        (throw (Exception. (str "C++ error: " (:err result))))))))

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



(deftest euler24-correctness
  (testing "Сравнение всех реализаций задачи 24 (миллионная перестановка)"
    (let [expected "2783915460"
          clojure-results [(p24/solve-24)
                           (p24/solve-24-tail)
                           (p24alt/millionth-permutation)]
          python-result (run-python-script "./src/python_euler24.py")
          cpp-result (run-cpp-program "./src/cpp_euler24.cpp")]
      (is (every? #(= expected %) clojure-results))
      (is (= expected python-result))
      (is (= expected cpp-result)))))

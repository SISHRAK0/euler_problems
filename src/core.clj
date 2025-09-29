(ns core
  (:gen-class)
  (:require
    [clojure.string :as str]
    [problem7 :as p7]
    [problem7-alt :as p7alt]
    [problem24 :as p24]
    [problem24-alt :as p24alt]))

(defn -main
  "Печатает результаты всех реализаций задачи 7."
  [& _]
  (println "Решение задачи 7 (найти 10_001-ое простое число)")
  (println "tail-recursive:  " (p7/solve-7-tail))
  (println "recursive:       " (p7/solve-7-recursive))
  (println "modular (filter/reduce idea):" (p7/solve-7-modular))
  (println "map-based:       " (p7/solve-7-map))
  (println "loop/recur:      " (p7/solve-7-loop))
  (println "lazy filter:     " (p7/solve-7-lazy))
  (println "sieve (lazy):    " (p7alt/solve-7-sieve))
  (println "\nВсе реализации должны давать одинаковый ответ: 104743")

  "Печатает результаты всех реализаций задачи 24."
  (println "Project Euler #24 — Millionth lexicographic permutation")
  (println "analytical (nth-lexicographic-permutation):" (p24/solve-24))
  (println "tail variant (nth-perm-tail):" (p24/solve-24-tail))
  (println "alt compact (millionth-permutation):" (p24alt/millionth-permutation))
  (println "bruteforce (lazy):" (p24/solve-24-bruteforce))
  (System/exit 0))

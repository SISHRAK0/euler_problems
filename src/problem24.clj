(ns problem24
  (:require [clojure.string :as str]))

;; Быстрый factorial (возвращает BigInt при больших n)
(defn factorial [n]
  (reduce *' 1 (range 1 (inc n))))

;; ----- аналитический алгоритм: nth lexicographic permutation -----
;; items - последовательность (вектор или список) элементов
;; n - 1-based индекс искомой перестановки
(defn nth-lexicographic-permutation
  "Возвращает последовательность элементов (в том же типе, что и items) —
   n-ю (1-based) лексикографическую перестановку."
  [items n]
  (let [items (vec items)
        n (dec n)]
    (loop [avail items
           k n
           result []]
      (if (empty? avail)
        result
        (let [m (count avail)
              f (factorial (dec m))
              idx (quot k f)
              chosen (nth avail idx)
              new-avail (vec (concat (subvec avail 0 idx) (subvec avail (inc idx))))]
          (recur new-avail (rem k f) (conj result chosen)))))))

(defn solve-24 []
  ;; входные цифры — символы '0'..'9', возвращаем строку
  (apply str (nth-lexicographic-permutation (map str (range 0 10)) 1000000)))

;; ----- хвостовой вариант (альтернативная стилистика) -----
(defn nth-perm-tail
  "Хвостово-рекурсивная реализация того же алгоритма, возвращает строку."
  [digits k]
  (let [k (dec k)]
    (loop [elems (vec digits)
           kk k
           acc []]
      (if (empty? elems)
        (apply str acc)
        (let [m (count elems)
              f (factorial (dec m))
              idx (quot kk f)
              chosen (nth elems idx)
              new-elems (vec (concat (subvec elems 0 idx) (subvec elems (inc idx))))]
          (recur new-elems (rem kk f) (conj acc chosen)))))))

(defn solve-24-tail []
  (nth-perm-tail (map str (range 0 10)) 1000000))

;; ----- ленивый брутфорс (для демонстрации) -----
(defn permutations
  "Ленивая последовательность перестановок coll (каждая перестановка — список).
   Работает лениво, но генерирует много элементов — для 10! ~ 3.6M."
  [coll]
  (if (empty? coll)
    (list '())
    (apply concat
           (for [x coll]
             (map #(cons x %) (permutations (remove #{x} coll)))))))

(defn solve-24-bruteforce []
  ;; ВНИМАНИЕ: генерация всех перестановок может быть медленной и потреблять память,
  ;; но реализация ленивaя — nth возьмёт только нужный элемент, однако глубина рекурсии может быть значительной.
  (let [perms (permutations (map str (range 0 10)))]
    (apply str (nth (seq perms) (dec 1000000)))))

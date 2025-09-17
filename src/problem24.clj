(ns problem24
  (:require [clojure.string :as str]))

(defn factorial [n]
  (reduce * 1 (range 1 (inc n))))

(defn nth-lexicographic-permutation [items n]
  (loop [items (vec items)
         n (dec n)
         result []]
    (if (empty? items)
      result
      (let [f (factorial (dec (count items)))
            index (quot n f)
            item (items index)
            new-items (vec (concat (subvec items 0 index) (subvec items (inc index))))]
        (recur new-items (rem n f) (conj result item))))))

(defn solve-24 []
  (apply str (nth-lexicographic-permutation (map str (range 0 10)) 1000000)))

(defn permutations
  "returns lazy sequence of permutations of coll"
  [coll]
  (if (empty? coll)
    (list '())
    (apply concat
           (for [x coll]
             (map #(cons x %) (permutations (remove #{x} coll)))))))

(defn solve-24-bruteforce []
  (let [perms (permutations (map str (range 0 10)))
        p (nth (seq perms) (dec 1000000))]
    (apply str p)))

(ns problem24-alt)

;; Компактный позиционный алгоритм для миллио́нной перестановки.
(defn factorial [n]
  (reduce *' 1 (range 1 (inc n))))

(defn millionth-permutation []
  (let [digits (vec (map str (range 0 10)))
        target 1000000
        build (fn build [elems k acc]
                (if (empty? elems)
                  acc
                  (let [m (count elems)
                        f (factorial (dec m))
                        idx (quot k f)
                        chosen (nth elems idx)
                        new-elems (vec (concat (subvec elems 0 idx) (subvec elems (inc idx))))]
                    (recur new-elems (rem k f) (conj acc chosen)))))]
    ;; запускаем build с k = target-1 (0-based)
    (apply str (build digits (dec target) []))))

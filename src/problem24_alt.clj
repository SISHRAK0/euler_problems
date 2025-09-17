(ns problem24-alt)


(defn nth-perm-tail [digits k]
  (let [n (count digits)
        k (dec k)]
    (loop [k k
           elems (vec digits)
           i (dec n)
           acc []]
      (if (empty? elems)
        (apply str acc)
        (let [f (reduce * (range 1 (inc (dec (count elems)))))
              idx (quot k f)]
          (recur (mod k f)
                 (vec (concat (subvec elems 0 idx) (subvec elems (inc idx))))
                 (dec i)
                 (conj acc (nth elems idx))))))))

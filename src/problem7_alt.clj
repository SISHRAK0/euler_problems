(ns problem7-alt)

(defn sieve []
  (letfn [(sieve-inner [s]
            (lazy-seq
              (let [p (first s)]
                (cons p (sieve-inner (remove #(zero? (mod % p)) (rest s)))))))]
    (sieve-inner (iterate inc 2))))

(defn solve-7-sieve []
  (nth (sieve) 10000))

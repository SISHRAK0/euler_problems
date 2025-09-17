(ns problem7)

(defn prime? [n]
  (cond
    (< n 2) false
    (= n 2) true
    (even? n) false
    :else (let [lim (long (Math/sqrt n))]
            (not-any? #(zero? (mod n %)) (range 3 (inc lim) 2)))))

(defn nth-prime-tail [n]
  (loop [count 0
         x 2]
    (if (= count n)
      (dec x)
      (if (prime? x)
        (recur (inc count) (inc x))
        (recur count (inc x))))))


(defn solve-7-tail []
  (nth-prime-tail 10001))

(defn nth-prime-recursive
  ([n] (nth-prime-recursive n 2 0))
  ([n x count]
   (if (= count n)
     (dec x)
     (if (prime? x)
       (recur n (inc x) (inc count))
       (recur n (inc x) count)))))

(defn solve-7-recursive []
  (nth-prime-recursive 10001))

(def primes-lazy
  (filter prime? (iterate inc 2)))

(defn solve-7-lazy []
  (nth primes-lazy (dec 10001)))

(defn naturals [] (iterate inc 2))

(defn primes-by-map []
  (filter prime? (naturals)))

(defn solve-7-map []
  (nth (primes-by-map) (dec 10001)))


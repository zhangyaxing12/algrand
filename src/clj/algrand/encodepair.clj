:; Functions to encode a pair of values in a single string
(ns algrand.encodepair
    [clojure.math.numeric-tower :as nt])

;; Example usage:
;; (run! fmt-encoding (algrand.core/drop-take 1000000 40 encoded-pairs))

(defn encode-pair
  "Single-number encoding of pairs from Nies' *Computability and Randomness*
  (almost identical to the one in Li and Vitanyis' *An Introduction to 
  Kolmogorov Complexity and Its Applications)."
  [m n]
  (+ m 
     (/ (* (+ m n) (+ m n 1))
        2))) ; use div rather than 1/2 to avoid coerce to bigint

(defn calc-w 
  "Helper function for extract-* functions.
  (w from https://en.wikipedia.org/wiki/Pairing_function.)"
  [encoded-pair] ; z in wikipedia
  (long (nt/floor 
          (/ (dec (nt/sqrt (inc (* 8 encoded-pair))))
             2))))

(defn calc-t
  "Helper function for extract-* functions. Second argument should be result 
  from calc-w. (t from https://en.wikipedia.org/wiki/Pairing_function.)"
  ([encoded-pair] (calc-t encoded-pair (calc-w encoded-pair)))
  ([encoded-pair w] (/ (* w (inc w)) 2))) ; div not 1/2 avoid coerce to bigint

(defn extract-first
  "Return first element encoded in argument.  
  (From https://en.wikipedia.org/wiki/Pairing_function.)"
  [encoded-pair]
  (- encoded-pair (calc-t encoded-pair)))

(defn extract-second
  "Return second element encoded in argument.
  (From https://en.wikipedia.org/wiki/Pairing_function.)
  https://en.wikipedia.org/wiki/Pairing_function."
  [encoded-pair] ; z in wikepedia
  (let [w (calc-w encoded-pair)
        t (calc-t encoded-pair w)]
    (+ t (- w encoded-pair))))

(def encoded-pair-triples
  "Lazy list of triples [encoded pair, first element, second element]
  in encoded-pair order beginning from 0."
  (map (fn [n] [n (extract-first n) (extract-second n)])
       (range)))

(defn fmt-encoding 
  "Given a sequence containing an encoding followed by its two elements,
  in order, print binary reps of each to stdout."
  [[e x y]]
  (pp/cl-format true "~20b ~20b ~20b~%" e x y))

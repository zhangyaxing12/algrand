(ns algrand.core
    (:require [clojure.pprint :as pp]
              [clojure.math.numeric-tower :as nt]))

;; Example usage:
;; (run! fmt-encoding (drop-take 1000000 40 encoded-pairs))

(defn drop-take
  "Drop, then take."
  [to-drop to-take coll]
  (take to-take (drop to-drop coll)))

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

(defn natural-number?
  "True iff n is a non-negative integer."
  [n] 
  (and (integer? n) (>= n 0)))

;; bit-counting done "right"; helper function
(defn count-bits-once
  "Count the number of bits in non-negative integer n."
  [n]
  (when (natural-number? n)
    (cond (zero? n) 1              ; special case
          :else (loop [x n         ; positive integers
                       bits 0] 
                      (if (< x 1) 
                        bits 
                        (recur (* x 0.5) (inc bits)))))))
                               ;; floats faster, space-efficent vs rationals

;; bit-counting done "right"
(defn count-bits
  "Count the number of bits in non-negative integers ns."
  [& ns]
  (reduce (fn [bits n] (+ bits (count-bits-once n)))
          0 ns))


;; simple hacky version--a little slower than "right" version
;; note toBinaryString can't handle bigints, so use cl-format
(defn cheezy-count-bits
  "Count the number of bits in non-negative integers ns."
  [& ns]
  (count 
    (apply str 
           (map (fn [n] (pp/cl-format nil "~b" n)) ; (Integer/toBinaryString n)
                ns))))
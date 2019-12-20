(ns algrand.core
    (:require [clojure.pprint :as pp]))

;; Example usage:
;; (run! fmt-encoding (sort (drop-take 1000000 40 encodings)))

(defn drop-take
  "Drop, then take."
  [to-drop to-take coll]
  (take to-take (drop to-drop coll)))

(defn encode-pair
  "Single-number encoding of pairs from Nies' *Computability and Randomness*
  (almost identical to the one in Li and Vitanyis' *An Introduction to Kolmogorov 
  Complexity and Its Applications)."
  [m n]
  (+ m 
     (/ (* (+ m n) (+ m n 1)) 2)))

(def encodings (for [x (range 10000) y (range 1000)]
                    [(encode-pair x y) x y]))

(defn fmt-encoding 
  "Given a sequence containing an encoding followed by its two elements,
  in order, print binary reps of each to stdout."
  [[e x y]]
  (pp/cl-format true "~16b ~16b ~16b~%" e x y))

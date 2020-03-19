(ns algrand.hopcraftpairing
    (:require [clojure.math.numeric-tower :as nt]))

;; Pairing function from Hopcraft and Ullman 1979 p. 169 per
;; https://mathworld.wolfram.com/PairingFunction.html

;; 'long's (64bit) added just so results are easier to read

(defn delta
  [x]
  (* 1/2 x (inc x)))

(defn pairing
  "Return an integer encoding integers i and j."
  [i j]
  (long
    (+ (delta (+ i j -2)) i)))

(defn c-of-pair
  [h]
  (nt/floor
    (- (nt/sqrt (* 2 h))
       1/2)))

(defn first-elt
  [h]
  (long
    (- h 
       (delta (c-of-pair h)))))

(defn second-elt
  [h]
  (long 
    (- (c-of-pair h)
       (first-elt h)
       -2)))

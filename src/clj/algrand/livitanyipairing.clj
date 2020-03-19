(ns algrand.livitanyipairing
    (:require [clojure.math.numeric-tower :as nt]))

;; To encode a pair of integers in an integer, use a pairing function <m,n>.
;; 
;; Li & Vitanyi 3d, p.7:
;;         <m,n> = n + (m+n)(m + n + 1)/2
;;
;; Nies p. 418 is the same but for the order of the arguments:
;;         <m,n> = m + (m + n)(m + n + 1)/2
;;               = m + [(m + n)^2 + (m + n)]/2
;; 
;; They don't show how to extract the number.
;; Wikipedia does:
;; 
;; https://en.wikipedia.org/wiki/Pairing_function
;;         calls it the Cantor pairing function

;; 'long's (64bit) added just so results are easier to read

(defn pairing
  "Return an integer encoding integers m and n."
  [m n]
  (long
    (let [s (+ m n)]
      (+ m 
         (* s (inc s) 1/2)))))

(defn w-of-pair
  [z]
  (nt/floor
    (* 1/2
       (dec (nt/sqrt (inc (* 8 z)))))))

(defn t-of-pair
  [z]
  (let [w (w-of-pair z)]
    (* w (inc w) 1/2)))

(defn first-elt
  [z]
  (long
    (- z (t-of-pair z))))

(defn second-elt
  [z]
  (long
    (- (w-of-pair z)
       (first-elt z))))

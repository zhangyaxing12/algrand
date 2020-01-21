(ns algrand.kraftchaitin
    (:require [clojure.pprint :as pp]))

;; Nie's proof of the Machine Existence (Kraft-Chaitin) theorem:

(defn max-shorter
  "Given a sequence of strings Rn-1 sorted by length, and a string size rn,
  returns the largest string with size greater than or equal to rn."
  [Rn-1 rn]
  (loop [rs Rn-1]
    (when rs  ; if this is nil, bad args--we went too far
      (let [next-rs (next rs)
            r-size (count (first next-rs))]
        (if (> r-size rn)
          (first rs)   ; too big--go back to preceding one
          (recur next-rs)))))) ; keep looking

;; assume Rn sorted by length
;(defn blah
;  [Rn-1 rn wn]
;  (



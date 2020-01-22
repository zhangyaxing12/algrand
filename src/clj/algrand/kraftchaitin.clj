(ns algrand.kraftchaitin
    (:require [clojure.pprint :as pp]))

;; Nie's proof of the Machine Existence (Kraft-Chaitin) theorem:

;; Note that when r = length of z, that's the z we want.  This
;; function will skip past it and pull it back on the next iteration.
;; This very slight inefficiency makes the code simpler.
(defn max-shorter
  "Given a sequence of strings Rn-1 sorted by length, and a string size r,
  returns the largest string with size greater than or equal to r."
  [Rn-1 r]
  (loop [zs Rn-1]
    (when zs  ; if this is nil, bad args--we went too far
      (let [next-zs (next zs)
            z-size (count (first next-zs))]
        (if (> z-size r)
          (first zs)  ; too big--go back to preceding one
          (recur next-zs))))))

;; assume Rn sorted by length
;(defn blah
;  [Rn-1 rn wn]
;  (



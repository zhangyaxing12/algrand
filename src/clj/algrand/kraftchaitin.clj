(ns algrand.kraftchaitin
    (:require [clojure.pprint :as pp]))

;; Nie's proof of the Machine Existence (Kraft-Chaitin) theorem:

;; Note that when r = length of z, that's the z we want.  This
;; function will skip past it and pull it back on the next iteration.
;; This very slight inefficiency makes the code simpler.
(defn max-shorter
  "Given a sequence of strings Rn-1 sorted by length, and a string size r,
  returns the largest string with size greater than or equal to r."
  [r Rn-1]
  (loop [zs Rn-1]
    (when zs  ; if this is nil, bad args--we went too far
      (let [next-zs (next zs)
            z-size (count (first next-zs))]
        (if (> z-size r)
          (first zs)  ; too big--go back to preceding one
          (recur next-zs))))))

;  (let [z (max-shorter Rn-1 r)])
        ;pad-len (- r (count z))]

(defn make-w
  [old-z pad-len]
  (apply str 
         old-z 
         (repeat pad-len \0))) ; returns z if pad-len is zero

(defn make-new-z
  [old-z pad-len]
  (if (< pad-len 1)
    nil ; this shouldn't happen
    (str
      (apply str 
             old-z
             (repeat (dec pad-len) \0))
      \1)))

(defn make-zs
  [old-z max-pad-len]
  (map (fn [pad-len] (make-new-z old-z pad-len))
       (range 1 (inc max-pad-len))))

;; TODO  remove old z from Rn-1 (maybe do that in max-shorter?)
;;       and append the new-zs to it,
;;       then return it with w. or something
(defn next-R-stage
  [Rn-1 r]
  (let [z (max-shorter r Rn-1)
        pad-len (- r (count z))
        w (make-w z pad-len) 
        new-zs (make-zs z pad-len)]
    ))

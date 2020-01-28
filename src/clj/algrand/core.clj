;; Miscellaneous utility functions
(ns algrand.core
    (:require [clojure.pprint :as pp]
              [clojure.math.numeric-tower :as m]))

(defn set-pprint-width 
  "Sets width for pretty-printing with pprint and pp."
  [cols] 
  (alter-var-root 
    #'clojure.pprint/*print-right-margin* 
    (constantly cols)))

(defn drop-take
  "Drop, then take."
  [to-drop to-take coll]
  (take to-take (drop to-drop coll)))

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

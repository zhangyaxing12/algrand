;; Code to explore Martin-Löf test ideas

(ns algrand.martinlof
    [clojure.math.numeric-tower :as nt])

;; re Li & Vitanyi's (3rd ed) proof of lemma 2.4.1, "We can effectively 
;; enumate all P-tests."  A P-test is a Martin-Löf test wrt (computable)
;; probability distribution P.

(defn generate-tests
  "psi is a function from positive integers to [natural number, natural number] pairs."
  [psi]
  (loop [deltas (repeat 0)
         i 1]
     (let [[m x] (psi i)]
       (if (nth deltas) 
;; no not right delta shold be a function of strings.
))))


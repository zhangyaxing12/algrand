Using algrand.kraftchaitin.clj
===

kraftchaitin.clj contains an implementation of the algorithm in proof of
the Machine Existence theorem 2.2.17, pp. 88f in André Nies'
*Comutability and Randomness*, Oxford University Press 2009.  This
theorem is more commonly known as the Kraft-Chaitin theorem, as in e.g.
Calude's *Information and Randomness*.  Downey and Hirschfeldt call it
the KC theorem.

## Examples of suggested usage:

You might find that it's convenient to use these functions in an
exploratory manner at a Clojure REPL.

(1) The following displays a list of prefix-free input strings (the *w*'s) and a
sequence of *R*<sub>0</sub> , *R*<sub>1</sub>, etc.  These are lists of
strings representing available intervals for subsequent *w*'s.  Note
that the list of *w*'s and the list of *R*<sub>n</sub>'s are each in
revers order: the most recently generated value is first, and the oldest
value is last.

```clojure
(clojure.pprint/pprint
  (ws-and-Rns [3 3 5 2 3 6 9 10 9 9 10 11]))
```

The sequence of numbers above is a set of requested input string
lengths.  `pprint` isn't essential, but formats the output for easier
reading.

(2) To see the intervals to which each string corresponds, use
`interval-of`.  For example, you might want to look at the intervals
corresponding to the generated *w*'s and the intervals represented by the
strings in the last *R*<sub>n</sub>.  This will allow you to verify
that all of the intervals together partition *[0,1)$.  For example:
```clojure
(use 'clojure.pprint)

(let [[ws Rns] (ws-and-Rns [3 3 5 2 3 6 9 10 9 9 10 11])]
  (pprint (sort (map interval-of ws)))
  (pprint (sort (map interval-of (first Rns)))))
```

## Output from examples of suggested usage:

(1)
```clojure
user=> (pprint (ws-and-Rns [3 3 5 2 3 6 9 10 9 9 10 11]))
Weight: 0.68017578125
[("11000110110" "1100011010" "110001100" "110001010" "1100010010" "110001000" "110000" "011" "10" "01000" "001" "000")
 (("0101" "01001" "111" "1101" "11001" "1100010011" "110001011" "11000111" "11000110111")
  ("0101" "01001" "111" "1101" "11001" "1100010011" "110001011" "11000111" "1100011011")
  ("0101" "01001" "111" "1101" "11001" "1100010011" "110001011" "11000111" "110001101")
  ("0101" "01001" "111" "1101" "11001" "1100011" "1100010011" "110001011")
  ("0101" "01001" "111" "1101" "11001" "1100011" "11000101" "1100010011")
  ("0101" "01001" "111" "1101" "11001" "1100011" "11000101" "110001001")
  ("0101" "01001" "111" "1101" "11001" "110001")
  ("0101" "01001" "11")
  ("011" "0101" "01001" "11")
  ("1" "011" "0101" "01001")
  ("1" "01")
  ("1" "01" "001"))]
```

(2) The first list below contains the intervals corresponding to input
code strings.  The second list contains the intervals representing
still-available intervals in the last *R*<sub>n</sub>.

```clojure
user=> (let [[ws Rns] (ws-and-Rns [3 3 5 2 3 6 9 10 9 9 10 11])]
	 (println)
         (pprint (sort (map interval-of ws)))
	 (println)
         (pprint (sort (map interval-of (first Rns)))))
Weight: 0.68017578125

([0.0 0.125]
 [0.125 0.25]
 [0.25 0.28125]
 [0.375 0.5]
 [0.5 0.75]
 [0.75 0.765625]
 [0.765625 0.767578125]
 [0.767578125 0.7685546875]
 [0.76953125 0.771484375]
 [0.7734375 0.775390625]
 [0.775390625 0.7763671875]
 [0.7763671875 0.77685546875])

([0.28125 0.3125]
 [0.3125 0.375]
 [0.7685546875 0.76953125]
 [0.771484375 0.7734375] 
 [0.77685546875 0.77734375]
 [0.77734375 0.78125]
 [0.78125 0.8125]
 [0.8125 0.875]
 [0.875 1.0])

```




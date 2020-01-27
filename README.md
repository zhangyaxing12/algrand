# algrand
Code for thinking about algorithmic randomness

Most of this is in Clojure.  (I use Sean Luke's Java Mersenne Twister
for pseudorandom number generation.) The clojure source is under
*src/clj*`, with the fun stuff in *src/clj/algrand*.

There are also a few functions written for the Racket dialect of Scheme
in *src/racket*.  Racket is useful here because it makes it easy to
enter and print out fractional numbers in human-readable binary form,
and because one can easily make plots using numbers in such forms.

See files in doc/ directory or comments in source files for additional
information.

(ns furry-bear.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(fn d [l]
  (reduce
   #(if ((set %) %2)
      %
      (conj % %2))
   []
   l))

(fn f[l]
  (reduce
   #(assoc %1 %2 (inc (or (%1 %2) 0)))
   {}
   l))
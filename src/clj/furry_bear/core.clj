(ns furry-bear.core)

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(fn f [l]
  (reduce
   #(if ((set %) %2)
      %
      (conj % %2))
   []
   l))
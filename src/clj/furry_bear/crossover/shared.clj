(ns furry-bear.crossover.shared
  (:require;*CLJSBUILD-REMOVE*;-macros
    [furry-bear.crossover.macros :as macros]))

(defn make-furry-bear-text []
  (macros/reverse-eval
   ("shared"  "crossover" "furry-bear" str)))
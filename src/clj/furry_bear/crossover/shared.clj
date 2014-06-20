(ns furry-bear.crossover.shared
  (:require;*CLJSBUILD-REMOVE*;-macros
    [furry-bear.crossover.macros :as macros]))

(defn make-site-text []
  (fn []
    "furry-bear"))

(defn make-example-text []
  (fn []
    "furry-bear"))

(defn make-furry-bear-text []
  (macros/reverse-eval
   ("shared"  "crossover" "furry-bear" str)))
(ns furry-bear.hello
  (:require
    [furry-bear.crossover.shared :as shared]))

(defn ^:export say-hello []
  (js/alert (shared/make-furry-bear-text)))

(defn add-some-numbers [& numbers]
  (apply + numbers))


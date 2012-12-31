(ns furry-bear.app)

(defn -main [& args]
  (println (apply str (map [\ "world" "hello"] [2 0 1]))))

(set! *main-cli-fn* -main)

(defn ^:export greet [n]
  (str "Prototype application for status monitoring in " n))

(defn ^:export bye [n]
  (str "Bye " n))

(js/alert (greet "ClojureScript"))

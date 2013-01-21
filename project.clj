(defproject furry-bear "0.1.0-SNAPSHOT"
  :description "ClojureScript and Heroku"
  :url "http://wal.sh/#furry-bear"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/clj"]
  :dependencies [[org.clojure/clojure "1.4.0"]
                 [ring/ring-jetty-adapter "1.1.0"]
                 [compojure "1.0.4"]
                 [hiccup "1.0.0"]
                 [clojure-complete "0.2.2"]
                 [cascalog "1.10.0"]]
  :plugins [[lein-cljsbuild "0.2.9"]
            [lein-ring "0.8.2"]]
  :hooks [leiningen.cljsbuild]
  :cljsbuild {
              :builds [
                       {:source-path "src/cljs"
                        :compiler {:output-to "resources/public/js/main.js"
                                   :optimizations :whitespace
                                   :pretty-print true}}
                       {:source-path "src/cljs"
                        :compiler {:output-to "resources/public/js/main.min.js"
                                   :pretty-print true}}]}
  :ring {:handler furry-bear.routes/app}
  :min-lein-version "2.0.0")
               

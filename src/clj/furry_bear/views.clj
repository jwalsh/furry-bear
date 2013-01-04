(ns furry-bear.views
  (:use [c2.core :only [unify]])
  (:require
   [furry-bear.crossover.shared :as shared]
   [c2.scale :as scale]
   [clj-json.core :as json]
   [clj-http.client :as client]
   [hiccup
    [page :refer [html5]]
    [element :refer [javascript-tag]]
    [page :refer [include-js]]]))

(defn- run-clojurescript [path init]
  (list
   (javascript-tag "var CLOSURE_NO_DEPS = true;")
   (include-js path)
   (javascript-tag init)))

(defn prelude-and-coda [title & content]
  (html5
   [:head
    [:link {:rel "stylesheet" :href "http://wal.sh/static/resume.css"}]
    [:meta {:http-equiv "Content-type"
            :content "text/html; charset=utf-8"}]
    [:title (str shared/make-example-text ": " title)]]
   (include-js "http://ajax.googleapis.com/ajax/libs/angularjs/1.0.3/angular.min.js")
   [:body content]))



(defn index-page []
  (prelude-and-coda
   (str shared/make-furry-bear-text)
   (str (json/parse-string (:body (client/get "https://graph.facebook.com/jwalsh.sandbox"))))
   ;; (comment (:trace-redirects (client/get "http://wal.sh/")))
   [:div
    [:h1 "Features"]
    [:div#content.text
     [:ul
      [:li (:trace-redirects (client/get "http://wal.sh/"))]
      [:li (client/get "https://graph.facebook.com/btaylor")]
      [:li "Login"]
      [:li "Task entry"]
      [:li "Shared events"]]]
    [:h1 "Angular"]
    [:div
     [:label "Name"]
     [:input {:type "text" :ng-model "yourName" :placeholder "Enter a name here"}]
     [:h1 "Hello {{yourName}}!"]]
    [:h1 (shared/make-furry-bear-text)]
    [:p     (let [data {"E", 5}]
      [:p (str data "...")])]
    (let [width 500, bar-height 20
          data {"A" 10, "B" 1, "C" 11, "D" 20}
          s (scale/linear :domain [0 (apply max (vals data))]
                          :range [0 width])]
      [:div#bars
       (unify data
              (fn [[label val]]
                [:div.bars-row {:style (str "background-color: gray; width: " (s val) "px")}
                 [:span {:style {:color "white"}}  label]]))])
    [:div]]))

(defn view-input []
  (let [title "add numbers"]
    (prelude-and-coda
     title
     [:h2 title]
     [:form {:method "post" :action ""}
      [:input.math {:type "text" :name "a"}] [:span.math " + "]
      [:input.math {:type "text" :name "b"}] [:br]
      [:input.action {:type "submit" :value "add"}]])))

(defn view-output
  ([]
     (let [title "No data"]
       (prelude-and-coda
        title
        [:h2 "No data seen"])))
  ([a b]
     (let [title "Added"]
       (prelude-and-coda
        title
        [:h2 "Added"]
        [:p
         (str a " + " b " = "
              (+ (Integer/parseInt a) (Integer/parseInt b)))]))))

;; TODO: GET for the initial s param; POST for site
;; Purpose: Provide a JSON representation of the HTML of a site
(defn get-site []
  (prelude-and-coda
   "Site"
   [:form {:method "POST"}
    [:label "Site"]
    [:input {:type "text" :ng-model "site" :name "s" :placeholder "Enter a site here"}]
    [:input {:type "submit"}]]))


(defn post-site [s]
  (prelude-and-coda
   (:body 
    (client/get s))))

(defn repl-demo-page []
  (html5
   [:head
    [:title "REPL Demo"]]
   [:body
    [:h1 "REPL Demo"]
    [:hr]
    "This page is meant to be accessed by running this in one terminal:"
    [:pre "lein ring server-headless 3000"]
    "And then this in a different terminal:"
    [:pre "lein trampoline cljsbuild repl-launch firefox http://localhost:3000/repl-demo"]
    [:hr]
    "Alternately, you can run:"
    [:pre "lein ring server-headless 3000 &
lein trampoline cljsbuild repl-listen"]
    "And then browse to this page manually."]
   [:hr]
   [:h2 {:id "fun"} "Try some fun REPL commands!"]
   [:pre "> (js/alert \"Hello!\")
> (load-namespace 'goog.date.Date)
> (js/alert (goog.date.Date.))
> (.log js/console (reduce + [1 2 3 4 5]))
> (load-namespace 'goog.dom)
> (goog.dom.setTextContent (goog.dom.getElement \"fun\") \"I changed something....\") "]
   (run-clojurescript
    "/js/main-debug.js"
    "furry-bear.repl.connect()")))

;; Proxying requests
;; [clj-http "0.6.3"]


(defn json-response [data & status]
  {:status (or status 200)
   :headers {"Content-Type" "application/json"}
   :body (json/generate-string data)})
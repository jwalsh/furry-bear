(ns furry-bear.routes
  (:use compojure.core
        furry-bear.views
        [hiccup.middleware :only (wrap-base-url)])
  (:require [compojure.route :as route]
            [compojure.handler :as handler]
            [compojure.response :as response]))

(defroutes main-routes
  (GET "/" [] (index-page))
  (GET "/repl-demo" [] (repl-demo-page))
  (GET "/add-nums" [] (view-input))
  (POST "/add-nums" [a b]
        (let [a b]
          (view-output a b)))
  (route/resources "/")
  (route/not-found "Page not found"))

(def app
  (-> (handler/site main-routes)
      (wrap-base-url)))

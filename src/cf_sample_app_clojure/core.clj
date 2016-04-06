(ns cf-sample-app-clojure.core
   (:gen-class)
   (:require [compojure.core :refer :all]
             [compojure.route :as route]
             [ring.adapter.jetty :as jetty]
             [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
             [cf-sample-app-clojure.mongodb :refer :all])
   (:use     [hiccup.core]
             [hiccup.page]
             [hiccup.form]
             [hiccup.element]))

(defn saveToDo [todo]
  (saveToMongo todo)
)

(defn getToDo []
  (getAllFromMongo)
)

(defn commonLayout [& content]
 (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:title "ListOfTasks"]
    (include-css "/css/style.css")
    ]
   [:body content]))

(defn toDoPageInput []
  (commonLayout 
    [:h2 "Enter a new ToDo"]
    [:form {:method "post" :action "/todo"}
      [:input.text {:type "text" :name "todo"}]
      [:br]
      [:br]
      [:input.action {:type "submit" :value "New"}]]))

(defn toDoPageOutput [todo]
  (saveToDo todo)
  (commonLayout
    [:h2 "Todo List"]
    (ordered-list (getToDo))))

(defroutes app-routes
  (GET "/" [] "Welcome to the Swisscom Application Cloud!")
  (GET "/todo" [] (toDoPageInput))
  (POST "/todo" [todo] (toDoPageOutput todo))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)))

(defn -main
  [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT") 3000))]
    (jetty/run-jetty #'app {:port  port
                            :join? false})))

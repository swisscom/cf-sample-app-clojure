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

(defn commonLayout [& content]
  (html5
    [:head
      [:meta {:charset "utf-8"}]
      [:title "ListOfTasks"]
      (include-css "/css/style.css")]
    [:body content]))

(defn createToDo [todo]
  (saveToDB todo "todos"))

(defn findToDos []
  (getAllFromDB "todos"))

(defn toDoPage [& [todo]]
  (when todo
    (createToDo todo))
  (commonLayout
    [:h2 "Todo List"]
    (ordered-list (findToDos))
    [:h2 "Enter a new ToDo"]
    [:form {:method "post" :action "/todos"}
      [:input.text {:type "text" :name "todo"}]
      [:br]
      [:br]
      [:input.action {:type "submit" :value "New"}]]))

(defroutes app-routes
  (GET "/" [] "I am awesome!")
  (GET "/todos" [] (toDoPage))
  (POST "/todos" [todo] (toDoPage todo))
  (route/resources "/")
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes (assoc-in site-defaults [:security :anti-forgery] false)))

(defn -main
  [& [port]]
  (let [port (Integer. (or port (System/getenv "PORT") 3000))]
    (jetty/run-jetty #'app {:port  port
                            :join? false})))

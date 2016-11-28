(ns cf-sample-app-clojure.mongodb
  (:require [monger.core :as mg]
            [monger.collection :as mc]
            [cf-sample-app-clojure.parse :refer :all])
  (:import  [org.bson.types ObjectId]
            [com.mongodb DB WriteConcern]))

(defn- connectToMongo []
  (let [credentials (getCredentials "mongodb")
        uri (get credentials :uri "mongodb://127.0.0.1:27017/monger-test")]
    (mg/connect-via-uri uri)))

(defn saveToDB [document coll]
  (try
    (let [{:keys [conn db]} (connectToMongo)]
      (println "Inserting document")
      (mc/insert-and-return db coll {:todo document})
      (println "Document inserted")
      (println "Closing database")
      (mg/disconnect conn))
  (catch Exception e (println (str "caught exception: " (.getMessage e))))))

(defn getAllFromDB [coll]
  (try
    (let [{:keys [conn db]} (connectToMongo)
          all-documents-in-map (mc/find-maps db coll)
          documents (doall (map #(get % :todo) all-documents-in-map))]
      (mg/disconnect conn)
      documents)
    (catch Exception e (println (str "caught exception: " (.getMessage e))))))

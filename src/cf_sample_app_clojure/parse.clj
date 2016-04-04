(ns cf-sample-app-clojure.parse
    (require [environ.core :refer [env]]
             [cheshire.core :refer :all]))

(defn getCredentials [service]
  (let [vcap_services (env :vcap-services)
        json_services (parse-string vcap_services true)
        db (get json_services (keyword service))]
    (get (get db 0) :credentials)))

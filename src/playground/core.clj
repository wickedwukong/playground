(ns playground.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]))

(defn log-value
  [msg value]
  (println msg value)
  value)

(defn wrap-logging
  [msg handler]
  (fn [request]
    (log-value msg (handler request))))

(defn wrap-content-type
  [handler content-type]
  (fn [request]
    (assoc-in (handler request) [:header "ContentType"] content-type)))

(defn handler [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello from your web application!"})

(def app
  (wrap-logging "Response:"
                (wrap-content-type handler "text/html")))

(defn -main []
  (jetty/run-jetty app {:port 8080}))

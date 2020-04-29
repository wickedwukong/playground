(ns playground.core
  (:gen-class)
  (:require [ring.adapter.jetty :as jetty]))

(defn handler [request]
  {:status  200
   :headers {"Content-Type" "text/html"}
   :body    "Hello from your web application!"})

(defn -main []
  (jetty/run-jetty handler {:port 8080}))

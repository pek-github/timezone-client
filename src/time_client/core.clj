(ns time-client.core
  (:require [clj-http.client :as client]
            [clojure.data.json :as json])
  (:gen-class))


; ----------
; Input Validation
; ----------

(defn- validate-number-of-arguments [args]
  (= 1 (count args)))

(defn- validate-first-argument
  "verify that the first argument is indeed a valid ZoneId
   according to the semantics of java.time.ZoneId, described at
   https://docs.oracle.com/javase/8/docs/api/java/util/TimeZone.html"
  [arg]
  (try (let [zone-id (java.time.ZoneId/of arg)]
         (not (nil? zone-id)))
       (catch Exception e false)
  ))

(defn- validate-input [args]
  (and (validate-number-of-arguments args)
       (validate-first-argument (first args))))

(defn- show-usage
  "it shows the proper usage of the program, when used via Leiningen"
  []
  (println "--------")
  (println "Proper usage via Leiningen, so as to ask the server")
  (println "for the current time in a specific timezone is:")
  (println "> lein run 'time-zone' ")
  (println "  'time-zone' is the given timezone according to")
  (println "  https://docs.oracle.com/javase/8/docs/api/java/util/TimeZone.html")
  (println "--------"))


; ----------
; Core Logic
; ----------

; server URL
(defonce server "http://localhost:4444/time")

(defn- create-request
  "creates the request HTTP message, for a given HTTP Body"
  [request-body]
  { :content-type :json
    :accept :json
    :body request-body })

(defn- send-request
  "sends a request to the time-convertion server, for the given time-zone"
  [time-zone]
  (let [request-body (json/write-str {:timezone time-zone})]
    (client/post server (create-request request-body))))


; ----------
; Main
; ----------

(defn -main [& args]
  (if (validate-input args)
    (let [response (send-request (first args))]
      (println response))
    (show-usage)
  ))

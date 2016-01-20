(defproject time-client "0.1.0-SNAPSHOT"
  :description "A client for WS of time conversion"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [clj-http "2.0.0"]
                 [org.clojure/data.json "0.2.6"]]
  :main  time-client.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

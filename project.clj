(defproject tech.gojek/newrelic-clj "0.0.1"
  :description "A thin wrapper around New Relic Agent Java API"
  :url "https://github.com/gojektech/newrelic-clj"
  :license {:name "Apache License, Version 2.0"
            :url  "https://www.apache.org/licenses/LICENSE-2.0"}
  :repl-options {:host "0.0.0.0"
                 :port 1337}
  :local-repo "vendor"
  :source-paths ["src/clojure"]
  :java-source-paths ["src/java"]
  :test-paths ["test/clojure"]
  :resource-paths ["src/resources"]
  :target-path "target/%s/"
  :dependencies [[org.clojure/clojure "1.8.0"]]
  :plugins [[jonase/eastwood "0.2.5"
             :exclusions [org.clojure/clojure]]
            [lein-ancient "0.6.15"]
            [lein-cljfmt "0.5.7"]
            [lein-cloverage "1.0.10"]]
  :profiles {:test {:resource-paths ["test/resources"]}
             :dev  {:dependencies [[cider/cider-nrepl "0.8.2"]
                                   [com.newrelic.agent.java/newrelic-api "3.45.0"]
                                   [fipp "0.6.12"]
                                   [gui-diff "0.6.7"
                                    :exclusions [fipp]]
                                   [org.clojure/tools.trace "0.7.9"]
                                   [ring/ring-core "1.6.3"]
                                   [ring-mock "0.1.5"]]}}
  :jvm-opts ["-server"
             "-Dfile.encoding=UTF8"
             "-XX:+UseG1GC"
             "-Xms768m"
             "-Xmx1024m"
             "-XX:+UnlockCommercialFeatures"
             "-XX:+PrintGCDetails"
             "-XX:+PrintGCDateStamps"
             "-XX:+PrintTenuringDistribution"
             "-XX:+FlightRecorder"
             "-XX:ErrorFile=.jvm-errors.log"
             "-Xloggc:.jvm-gc.log"])

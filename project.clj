(defproject tech.gojek/clj-newrelic "0.0.1"
  :description "Clojure newrelic java api wrapper"
  :url "https://github.com/gojektech/clj-newrelic"
  :license {:name "Apache License, Version 2.0"
            :url  "https://www.apache.org/licenses/LICENSE-2.0"}
  :local-repo "vendor"
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [com.newrelic.agent.java/newrelic-api "3.45.0"]]
  :repl-options {:host "0.0.0.0" :port 1337}
  :profiles {:dev {:dependencies [[ring/ring-core "1.6.3"]
                                  [ring-mock "0.1.5"]
                                  [org.clojure/tools.trace "0.7.9"]]}}
  :plugins [[lein-ancient "0.6.15"]])

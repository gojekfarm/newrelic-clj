(ns newrelic-clj.new-core-test
  (:require [newrelic-clj.new-core :as newrelic]
            [newrelic-clj.test-helper :as helper]
            [clojure.test :refer :all]))

(deftest with-tracing-test
  (testing "GIVEN a valid Tracer
            WITH category, transaction AND criterion
            IT SHOULD set Tracer's spec"
    (helper/with-test-tracer
      [tracer]
      (let [category    "API"
            transaction "taser"
            criterion   {:api-type "external"
                         :txn-type "heavyweight"}]
        (newrelic/with-tracing
          [tracer
           {:category    category
            :transaction transaction}
           criterion]
          (helper/noop))
        (is (= [["API" "taser"]
                [["api-type" "external"]
                 ["txn-type" "heavyweight"]]]
               (newrelic/entry tracer)))))))

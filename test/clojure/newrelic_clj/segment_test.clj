(ns newrelic-clj.segment-test
  (:require [clojure.test :refer :all]
            [newrelic-clj.segment :refer :all]))

(deftest ^:unit expands-with-segment-with-given-args-and-body
  (let [expansion (macroexpand-1 `(with-segment "sample-segment" true))
        segment (:segment (meta expansion))
        expected-expansion `(clojure.core/let [~segment (newrelic-clj.segment/start-segment "sample-segment")]
                             (try true
                                  (finally
                                    (newrelic-clj.segment/end-segment ~segment))))
        ]
    (is (= expansion expected-expansion))))

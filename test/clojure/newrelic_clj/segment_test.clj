(ns newrelic-clj.segment-test
  (:require [clojure.test :refer :all]
            [newrelic-clj.segment :refer :all]))

(deftest ^:unit executes-with-segment-when-body-doesnt-throw
  (let [expected-segment-name "my-segment"]
    (with-redefs [start-segment (fn [segment-name]
                                (do (is (= segment-name expected-segment-name))
                                    {:segment-name segment-name}))
                  end-segment (fn [segment]
                                (is (= (:segment-name segment) expected-segment-name)))]
      (is (with-segment expected-segment-name true)))))

(deftest ^:unit executes-with-segment-when-body-throws
  (let [expected-segment-name "my-segment"]
    (with-redefs [start-segment (fn [segment-name]
                                (do (is (= segment-name expected-segment-name))
                                    {:segment-name segment-name}))
                  end-segment (fn [segment]
                                (is (= (:segment-name segment) expected-segment-name)))]
      (is (thrown? ArithmeticException (with-segment expected-segment-name (/ 1 0)))))))

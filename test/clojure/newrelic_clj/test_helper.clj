(ns newrelic-clj.test-helper
  (:require [newrelic-clj.new-core :as newrelic]))

(defn noop
  []
  (constantly nil))

(defn- ->str
  [value]
  (if (keyword? value)
    (name value)
    (str value)))

(defn- ->pair
  [[map-key map-val]]
  [(->str map-key) (->str map-val)])

(defn- seriate-a-map
  [a-map]
  (map ->pair a-map))

(defn make-test-tracer
  []
  (let [entry (atom nil)]
    (reify newrelic/Tracer
      (entry [_]
        @entry)
      (trace! [_ category transaction criterion]
        (reset! entry
                [[category transaction]
                 (seriate-a-map criterion)])))))

(defmacro with-test-tracer
  [[tracer] & body]
  (let [tracer (or tracer 'tracer)]
    `(let [~tracer (make-test-tracer)]
       ~@body)))

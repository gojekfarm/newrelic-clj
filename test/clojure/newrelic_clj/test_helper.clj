(ns newrelic-clj.test-helper
  (:require [newrelic-clj.new-core :as newrelic]))

(defn noop
  []
  (constantly nil))

(defn- map->vec
  [-map]
  (let [->pair (fn [[map-key map-val]]
                 [(name map-key)
                  map-val])]
    (map ->pair -map)))

(defn make-test-tracer
  []
  (let [entry (atom nil)]
    (reify newrelic/Tracer
      (entry [_]
        @entry)
      (trace! [_ category transaction criterion]
        (reset! entry
                [[category transaction]
                 (map->vec criterion)])))))

(defmacro with-test-tracer
  [[tracer] & body]
  (let [tracer (or tracer 'tracer)]
    `(let [~tracer (make-test-tracer)]
       ~@body)))

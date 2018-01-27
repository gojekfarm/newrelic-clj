(ns newrelic-clj.new-core)

(defprotocol Tracer
  (entry [this])
  (trace! [this category transaction criterion]))

(defmacro with-tracing
  [[tracer {:keys [category transaction]} criterion] & body]
  `(let [~category    (or ~category "Clojure")
         ~transaction (or ~transaction ~(str *ns*))
         ~criterion   (or ~criterion {})]
     (trace! ~tracer ~category ~transaction ~criterion)
     ~@body))

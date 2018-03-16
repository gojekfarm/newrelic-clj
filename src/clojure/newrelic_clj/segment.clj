(ns newrelic-clj.segment
  (:import  [com.newrelic.api.agent  NewRelic]))

(defn start-segment
  [segment-name]
  (-> (NewRelic/getAgent) .getTransaction (.startSegment segment-name)))

(defn end-segment
  [segment]
  (.end segment))

(defmacro with-segment
  [segment-name body]
  (let [segment (gensym)]
    (with-meta
      `(let [~segment (start-segment ~segment-name)]
         (try
           ~body
           (finally (end-segment ~segment))))
      {:segment segment})))

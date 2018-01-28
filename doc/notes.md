# New Relic Clojure - Notes

## 2018-JAN-28
### Super Epics + Design Notes
#### by [Ranjeet Singh](http://github.com/raeoks)

- New Relic's Java API are designed around idiomatic annotations.
- Both annotations `@Trace` and `@TraceMethod` do a lot of magic behind the scene.
- To directly port Java API to Clojure will only need `with-tracing` function;
  with some way to pass tracer + tracing spec.
- But `with-tracing` function will again have to do lots of magic.
- `[yleisradio/new-reliquary]` already does it. 

---

- Time to look at other tracing (specially APM) protocols.
- OpenZipkin or OpenTracing seems likely candidates.
- Also to mention `newrelic-clj` does lots of side effect operations;
  some design pattern + scoping from `[org.clojure/java.jdbc]` are favoured here.
- Maybe explicitly passing tracer to `with-tracer`, seems more idiomatic Clojure.
- Try to avoid as much as magic as possible. A `tracer` can be created using `deftracer` macro.
- A function can be marked for tracing by defining it with `deftraced` and `tracer` passed as metadata.
- `with-tracer` can be flexible too by accepting `criterion` as optional arguments.

---

- Here's a rough Clojure API design + example  

```clojure
(deftracer name spec)

(deftraced name doc-string? attr-map? [params*] prepost-map? body)
(deftraced name doc-string? attr-map? ([params*] prepost-map? body) + attr-map?)

(with-tracer
  [tracer criterion-map?]
  body)
```

PS: These are really rough notes, only intended to keep a reminder for [authors](/AUTHORS.md)

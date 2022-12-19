# Kaocha watch mode not reporting load errors

When there are 2 (or more) entries in `:kaocha/tests` and you
activate the second one in watch mode (here `./bin/kaocha :checker --watch`),
if reloading a file fails, the stacktrace is not printed.

## Reproduction

1. Run `./bin/kaocha :checker --watch`.
2. After tests pass, save `src/loads_once.clj` so it reloads.
3. Observe the following unhelpful message:
```
[watch] Reloading #{loads-once-test loads-once}
WARNING: All 2 tests were skipped. Check for misspelled settings in your Kaocha test configuration or incorrect focus or skip filters.
[watch] Fatal error in test run #error {
 :cause throw+: #:kaocha{:early-exit 0}
 :data #:kaocha{:early-exit 0}
 :via
 [{:type clojure.lang.ExceptionInfo
   :message throw+: #:kaocha{:early-exit 0}
   :data #:kaocha{:early-exit 0}
   :at [slingshot.support$stack_trace invoke support.clj 201]}]
 :trace
 [[slingshot.support$stack_trace invoke support.clj 201]
  [kaocha.api$run$fn__3232 invoke api.clj 113]
  [clojure.lang.AFn applyToHelper AFn.java 152]
  [clojure.lang.AFn applyTo AFn.java 144]
  [clojure.core$apply invokeStatic core.clj 667]
  [clojure.core$with_bindings_STAR_ invokeStatic core.clj 1990]
  [clojure.core$with_bindings_STAR_ doInvoke core.clj 1990]
  [clojure.lang.RestFn invoke RestFn.java 425]
  [kaocha.api$run invokeStatic api.clj 99]
  [kaocha.api$run invoke api.clj 86]
  [kaocha.watch$try_run$fn__4902 invoke watch.clj 54]
  [kaocha.watch$try_run invokeStatic watch.clj 53]
  [kaocha.watch$try_run invoke watch.clj 45]
  [kaocha.watch$run_loop invokeStatic watch.clj 195]
  [kaocha.watch$run_loop invoke watch.clj 189]
  [kaocha.watch$run_STAR_ invokeStatic watch.clj 319]
  [kaocha.watch$run_STAR_ invoke watch.clj 283]
  [kaocha.watch$run$fn__5007 invoke watch.clj 329]
  [clojure.lang.AFn applyToHelper AFn.java 152]
  [clojure.lang.AFn applyTo AFn.java 144]
  [clojure.core$apply invokeStatic core.clj 667]
  [clojure.core$with_bindings_STAR_ invokeStatic core.clj 1990]
  [clojure.core$with_bindings_STAR_ doInvoke core.clj 1990]
  [clojure.lang.RestFn invoke RestFn.java 425]
  [clojure.lang.AFn applyToHelper AFn.java 156]
  [clojure.lang.RestFn applyTo RestFn.java 132]
  [clojure.core$apply invokeStatic core.clj 671]
  [clojure.core$bound_fn_STAR_$fn__5818 doInvoke core.clj 2020]
  [clojure.lang.RestFn invoke RestFn.java 397]
  [kaocha.watch$run$fn__5009 invoke watch.clj 336]
  [clojure.core$binding_conveyor_fn$fn__5823 invoke core.clj 2047]
  [clojure.lang.AFn call AFn.java 18]
  [java.util.concurrent.FutureTask run FutureTask.java 317]
  [java.util.concurrent.ThreadPoolExecutor runWorker ThreadPoolExecutor.java 1144]
  [java.util.concurrent.ThreadPoolExecutor$Worker run ThreadPoolExecutor.java 642]
  [java.lang.Thread run Thread.java 1589]]}
```

(ns loads-once)

(when (System/getProperty "loads-once")
  (throw (Exception. "loads-once")))

(System/setProperty "loads-once" "true")

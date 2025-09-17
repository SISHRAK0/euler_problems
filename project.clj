(defproject euler "0.1.0-SNAPSHOT"
  :description "Project Euler solutions for lab — problems 7 and 24"
  :url "https://example.org/your-repo"
  :license {:name "MIT"}

  :min-lein-version "2.9.0"

  ;; Clojure version requirement
  :dependencies [[org.clojure/clojure "1.11.3"]
                 ;; numeric-tower optional if you want extra math funcs
                 [org.clojure/math.numeric-tower "0.0.4"]]

  ;; Plugins: cljfmt for formatting, eastwood for linting, midje for facts
  :plugins [[lein-cljfmt "0.8.4"]
            [jonase/eastwood "1.4.0"]
            [lein-midje "3.2.1"]]

  ;; For `lein run` convenience (optional)
  :main ^:skip-aot euler.core

  :profiles
  {:dev {:dependencies [[midje "1.9.10"]]
         :source-paths ["src" "dev"]
         :test-paths ["test"]}}

  ;; cljfmt configuration via lein plugin (can also be .cljfmt)
  :cljfmt {:indents {nth-prime-tail [[:block 0]]}} ;; example if needed
  )

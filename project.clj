(defproject prezel "0.1.0-SNAPSHOT"
  :description ""
  :url "https://github.com/plexus/prezel"
  :license {:name "Mozilla Public License 2.0"
            :url "https://www.mozilla.org/en-US/MPL/2.0/"}
  :dependencies [[org.clojure/clojure "1.9.0-alpha14"]
                 [org.clojure/clojurescript "1.9.495" :scope "provided"]
                 [com.cognitect/transit-clj "0.8.297" :exclusions [[com.fasterxml.jackson.core/jackson-core]]]

                 ;; config
                 [environ "1.1.0"]

                 ;; ring
                 [ring "1.5.1"]
                 [ring/ring-defaults "0.2.3"]
                 [ring-middleware-format "0.7.2"]
                 [compojure "1.5.2"]

                 ;; component
                 [com.stuartsierra/component "0.3.2"]
                 [org.danielsz/system "0.4.0"]
                 [org.clojure/tools.namespace "0.2.11"]

                 ;; Frontend
                 [reagent "0.6.0"]
                 [re-frame "0.9.2"]
                 [cljs-ajax "0.5.8"]
                 [day8.re-frame/http-fx "0.1.3"]
                 [re-frisk "0.3.2"]
                 [cljs-ajax "0.5.8"]]

  :plugins [[lein-environ "1.1.0"]
            [lein-cljsbuild "1.1.5"]]

  :source-paths ["src/clj" "src/cljc"]

  :test-paths ["test/clj" "src/cljc"]

  :clean-targets ^{:protect false} [:target-path :compile-path "resources/public/js"]

  :uberjar-name "prezel.jar"

  :cljsbuild {:builds
              [{:id "app"
                :source-paths ["src/cljs" "src/cljc"]

                :figwheel {:on-jsload "prezel.core/mount-root"}

                :compiler {:main prezel.core

                           :asset-path "js/compiled/out"
                           :output-to "resources/public/js/compiled/prezel.js"
                           :output-dir "resources/public/js/compiled/out"
                           :source-map-timestamp true}}

               {:id "test"
                :source-paths ["src/cljs" "test/cljs" "src/cljc" "test/cljc"]
                :compiler {:output-to "resources/public/js/compiled/testable.js"
                           :main prezel.test-runner
                           :optimizations :none}}

               {:id "min"
                :source-paths ["src/cljs" "src/cljc"]
                :jar true
                :compiler {:main prezel.core
                           :output-to "resources/public/js/compiled/prezel.js"
                           :output-dir "target"
                           :source-map-timestamp true
                           :optimizations :advanced
                           :closure-defines {goog.DEBUG false}
                           :pretty-print false}}]}

  :figwheel {:css-dirs ["resources/public/css"]
             :server-logfile "log/figwheel.log"}

  :profiles
  {:dev
   {:dependencies [[figwheel "0.5.9"]
                   [figwheel-sidecar "0.5.9"]
                   [lambdaisland/garden-watcher "0.3.1"]
                   [com.cemerick/piggieback "0.2.1"]
                   [reloaded.repl "0.2.3"]
                   [binaryage/devtools "0.9.2"]]}})

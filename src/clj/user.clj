(ns user
  (:require [clojure.tools.namespace.repl :refer [set-refresh-dirs]]
            [figwheel-sidecar.config :as fw-config]
            [figwheel-sidecar.repl-api :as fw-repl-api]
            [figwheel-sidecar.system :as fw-sys]
            [garden-watcher.core :refer [new-garden-watcher]]
            [prezel.system :refer [prod-system]]
            [reloaded.repl :refer [system]]
            [ring.middleware.reload :refer [wrap-reload]]))

(defn dev-system []
  (-> (prod-system)
      (update-in [:middleware :middleware :middleware] conj wrap-reload)
      (assoc
       :figwheel-system (fw-sys/figwheel-system (fw-config/fetch-config))
       :css-watcher (fw-sys/css-watcher {:watch-paths ["resources/public/css"]})
       :garden-watcher (new-garden-watcher ['prezel.styles]))))

(defn cljs-repl
  ([]
   (cljs-repl nil))
  ([id]
   (when (get-in reloaded.repl/system [:figwheel-system :system-running] false)
     (fw-sys/cljs-repl (:figwheel-system reloaded.repl/system) id)
     (fw-repl-api/cljs-repl))))

(set-refresh-dirs "src" "dev")
(reloaded.repl/set-init! dev-system)

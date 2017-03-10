(ns prezel.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            ^:keep [day8.re-frame.http-fx]
            [prezel.events]
            [prezel.subs]
            [prezel.views :as views]
            [prezel.config :as config]
            [re-frisk.core :refer [enable-re-frisk!]]
            [devtools.core :as devtools]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (devtools/install!)))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (mount-root))

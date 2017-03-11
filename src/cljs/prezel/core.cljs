(ns prezel.core
  (:require [reagent.core :as reagent]
            [re-frame.core :as re-frame]
            ^:keep [day8.re-frame.http-fx]
            [prezel.events]
            [prezel.subs]
            [prezel.views :as views]
            [prezel.config :as config]
            [re-frisk.core :refer [enable-re-frisk!]]
            [devtools.core :as devtools]
            [goog.events :as events]
            [goog.events.EventType :as EventType]))

(defn dev-setup []
  (when config/debug?
    (enable-console-print!)
    (enable-re-frisk!)
    (devtools/install!)))

(defn mount-root []
  (re-frame/clear-subscription-cache!)
  (reagent/render [views/main-panel]
                  (.getElementById js/document "app")))

(defn register-global-events []
  (events/listen js/document
                 EventType/KEYDOWN
                 #(re-frame/dispatch [:evt/keypress %]))

  (events/listen js/window
                 EventType/RESIZE
                 #(re-frame/dispatch [:evt/resize %])))

(defn ^:export init []
  (re-frame/dispatch-sync [:initialize-db])
  (dev-setup)
  (register-global-events)
  (mount-root))

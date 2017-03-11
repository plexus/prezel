(ns prezel.events
  (:require [prezel.db :as db]
            [re-frame.cofx :refer [inject-cofx]]
            [re-frame.core :as re-frame :refer [debug reg-event-db reg-event-fx]]
            [prezel.slides :as slides]))

(defn slide-left [selected]
  (if (< selected 1) selected (dec selected)))

(defn slide-right [selected]
  (if (>= (inc selected) (count (slides/all-slides)))
    selected
    (inc selected)))

(defn handle-keypress [db [_ evt]]
  (case (.. evt getBrowserEvent -key)
    "ArrowLeft" (update db :slides/selected slide-left)
    "ArrowRight" (update db :slides/selected slide-right)
    "ArrowUp" (update db :code/style dec)
    "ArrowDown" (update db :code/style inc)
    db))

(reg-event-db :evt/keypress [debug] handle-keypress)

(defn viewport-size []
  {:width  js/window.innerWidth
   :height js/window.innerHeight})

(defn handle-resize [db [_ evt]]
  (assoc db :viewport/size (viewport-size)))

(reg-event-db :evt/resize [debug] handle-resize)

(reg-event-db :initialize-db
              (fn [_]
                (assoc db/default-db
                       :viewport/size (viewport-size))))

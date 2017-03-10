(ns prezel.views
  (:require [re-frame.core :as re-frame :refer [subscribe dispatch]]
            [reagent.core :as r]))

(def <sub (comp deref subscribe))
(def >evt dispatch)

(defn main-panel []
  [:div.app (<sub [:name])])

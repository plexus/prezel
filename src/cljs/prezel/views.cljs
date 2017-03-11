(ns prezel.views
  (:require [prezel.logos :as logos]
            [prezel.slides :as slides]
            [prezel.helpers :refer [<sub >evt]]))


(defn slide [& children]
  (into [:section.slide] children))

(defn select-slide [children]
  (nth children (<sub [:slides/selected])))



(defn main-panel []

  [:div.w-100.h-100.bg-black.avenir.dark-gray
   [:div.fixed-1920x1080.bg-white {:style {:transform (str "scale(" (<sub [:slides/scale]) ")")}}
    [select-slide (slides/all-slides)]]
   [:link {:rel "stylesheet" :href (<sub [:hljs/stylesheet])}]])

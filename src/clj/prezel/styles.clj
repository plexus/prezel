(ns prezel.styles
  (:refer-clojure :exclude [complement])
  (:require [garden-watcher.def :refer [defstyles]]
            [garden.color :refer :all]))

(defstyles style
  [:a {:color "#4271ae"}]
  )

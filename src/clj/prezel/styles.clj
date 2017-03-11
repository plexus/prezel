(ns prezel.styles
  (:refer-clojure :exclude [complement])
  (:require [garden-watcher.def :refer [defstyles]]
            [garden.color :refer :all]))

(defstyles style
  [:a {:color "#4271ae"}]
  [:.fixed-1920x1080 {:width "1920px"
                      :height "1080px"
                      :top "50%"
                      :left "50%"
                      :margin "-540px 0 0 -960px"
                      :position "absolute"}]
  [:.top-left {:position "absolute"
               :top "0"
               :left "0"}]
  [:.fxl {:font-size "5rem"}]
  [:.fxxl {:font-size "6rem"}]
  [:.slide {:font-size "45px"}]
  [:body
   [:.hljs {:background-color "inherit"
            :overflow "hidden"}]
   [:.hljs-builtin-name {:color #_"#F5007D"
                         "#f50004"}]])

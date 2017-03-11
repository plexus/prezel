(ns prezel.subs
  (:require-macros [reagent.ratom :refer [reaction]])
  (:require [re-frame.core :as re-frame]))

(defn scale-factor [{viewport-width :width viewport-height :height}
                    {slide-width :width slide-height :height}]
  (min
   (/ viewport-width slide-width)
   (/ viewport-height slide-height)))

(re-frame/reg-sub :name (fn [db] (:name db)))
(re-frame/reg-sub :slides/selected (fn [db] (:slides/selected db)))
(re-frame/reg-sub :slides/scale (fn [db]
                                  (scale-factor (:viewport/size db) (:slides/size db))))

(def hljs-styles
  ["atom-one-light.css" "atelier-cave-light.css" "atelier-forest-light.css" "github.css" "github-gist.css" "gruvbox-light.css"]
  #_["agate.css" "androidstudio.css" "arduino-light.css" "arta.css" "ascetic.css" "atelier-cave-dark.css" "atelier-cave-light.css" "atelier-dune-dark.css" "atelier-dune-light.css" "atelier-estuary-dark.css" "atelier-estuary-light.css" "atelier-forest-dark.css" "atelier-forest-light.css" "atelier-heath-dark.css" "atelier-heath-light.css" "atelier-lakeside-dark.css" "atelier-lakeside-light.css" "atelier-plateau-dark.css" "atelier-plateau-light.css" "atelier-savanna-dark.css" "atelier-savanna-light.css" "atelier-seaside-dark.css" "atelier-seaside-light.css" "atelier-sulphurpool-dark.css" "atelier-sulphurpool-light.css" "atom-one-dark.css" "atom-one-light.css" "brown-paper.css" "codepen-embed.css" "color-brewer.css" "darcula.css" "dark.css" "darkula.css" "default.css" "docco.css" "dracula.css" "far.css" "foundation.css" "github.css" "github-gist.css" "googlecode.css" "grayscale.css" "gruvbox-dark.css" "gruvbox-light.css" "hopscotch.css" "hybrid.css" "idea.css" "ir-black.css" "kimbie.dark.css" "kimbie.light.css" "magula.css" "mono-blue.css" "monokai.css" "monokai-sublime.css" "obsidian.css" "ocean.css" "paraiso-dark.css" "paraiso-light.css" "pojoaque.css" "purebasic.css" "qtcreator_dark.css" "qtcreator_light.css" "railscasts.css" "rainbow.css" "school-book.css" "solarized-dark.css" "solarized-light.css" "sunburst.css" "tomorrow.css" "tomorrow-night-blue.css" "tomorrow-night-bright.css" "tomorrow-night.css" "tomorrow-night-eighties.css" "vs.css" "xcode.css" "xt256.css" "zenburn.css"])

(re-frame/reg-sub :hljs/stylesheet (fn [{n :code/style}]
                                     (str "css/hljs-styles/" (get hljs-styles n))))

(comment
  )

;; var scale = Math.min(
;;                      availableWidth / contentWidth,
;;                      availableHeight / contentHeight
;;                      );

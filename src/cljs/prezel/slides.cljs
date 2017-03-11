(ns prezel.slides
  (:require [prezel.helpers :refer [<sub]]
            [prezel.logos :as logos]
            [clojure.pprint :as pprint]
            [reagent.core :as reagent]))

(pprint/set-pprint-dispatch pprint/code-dispatch)

(defn h [s]
  [:h1.fxl.gray s])

(defn center-title [s]
  [:h1.fxxl.tc.w-100.gray s])

(defn page-number []
  [:div.absolute.bottom-2.right-2.f2 (inc (<sub [:slides/selected]))])

(defn twitter-handle []
  [:div.absolute.bottom-2.left-2.f2 (update logos/twitter 1 assoc :class "h3 w3 nb3 pr3") "@plexus"])

(defn slide [& content]
  `[:div.slide.top-left.ph6.pt5.w-100.h-100.relative.overflow-hidden
    ~@content
    ~(page-number)
    ~(twitter-handle)])

(defn split [left right]
  [:div.slide.top-left.w-100.h-100.relative.overflow-hidden
   [:div.fl.w-50.h-100.ph5.pt5 left]
   [:div.fl.w-50.h-100.bg-washed-yellow.ph5.pt5 right]
   (page-number)
   (twitter-handle)])

(defn cljs [form]
  (reagent/create-class {:component-did-mount
                         (fn [this]
                           (js/hljs.highlightBlock (.-firstChild (reagent/dom-node this))))
                         :reagent-render
                         (fn [form] [:pre
                                     [:code.clojure
                                      (with-out-str
                                        (pprint/pprint form))]])}))



(defn title-page []
  [slide
   [:div.mt6.pt6
    [center-title "ClojureScript and React.js"]
    [:p.tc.f1 "by " [:a.dark-gray {:href "https://twitter.com/plexus"} "Arne Brasseur"]]
    [:p.tc.f1 "Berlin React.js, 20 March 2017"]]])



(defn all-slides []
  [[title-page]
   [split
    [cljs '(defn dev-setup []
             (when config/debug?
               (enable-console-print!)
               (enable-re-frisk!)
               (devtools/install!)))]
    [cljs '(defn title-page []
             [slide
              [:div.mt6.pt6
               [center-title "ClojureScript and React.js"]
               [:p.tc.f1 "by " [:a.dark-gray {:href "https://twitter.com/plexus"} "Arne Brasseur"]]
               [:p.tc.f1 "Berlin React.js, 20 March 2017"]]])
     ]]
   [split
    [:p "There's a road that doesn't interfere with the land
it's part of it, it was born rather than made
not an inch by man's hand paved, not a tile that was laid
it twists across acres, it ricochets off lakes
it is a wayward way
it's the only way I am willing to take.
See I was born on a full moon with nomad's blood in my veins
I can grow fond of a place the urge to move still remains.
You might have seen me on sandals or met me on trains"]
    [:p "I'm that stranger in town who was gone the next day
     there's no telling where I'm heading
     if only I knew
     but each time the sun rises the road starts anew
     sometimes a track or a trail, just a line in the sand
     concrete slabs, bars of iron that cut through the land.
     I need to move, to still my mind
     to practice the art of leaving behind
     to find what I need before I knew that I did
     each day learning to trust on intuition and wit."]]])

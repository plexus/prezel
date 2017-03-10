(ns prezel.routes
  (:require [clojure.java.io :as io]
            [compojure.core :refer [GET POST routes]]
            [compojure.route :refer [resources]]
            [ring.middleware.multipart-params :refer [wrap-multipart-params]]
            [ring.util.response :as res]
            ))

(defn app-routes [_]
  (routes
   (GET "/" _
     (some-> (res/resource-response "public/index.html")
             (res/content-type "html")))
   (resources "/")))

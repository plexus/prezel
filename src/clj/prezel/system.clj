(ns prezel.system
  (:gen-class)
  (:require [com.stuartsierra.component :as component]
            [environ.core :refer [env]]

            [prezel.routes :refer [app-routes]]

            [ring.middleware.defaults :refer [api-defaults wrap-defaults]]
            [ring.middleware.format :refer [wrap-restful-format]]

            [system.components.endpoint :refer [new-endpoint]]
            [system.components.handler :refer [new-handler]]
            [system.components.http-kit :refer [new-web-server]]
            [system.components.middleware :refer [new-middleware]]))

(defn prod-system []
  (component/system-map
   :routes (new-endpoint app-routes)

   :middleware (new-middleware  {:middleware [[wrap-defaults :defaults]
                                              wrap-restful-format]
                                 :defaults api-defaults})

   :handler (-> (new-handler)
                (component/using [:routes :middleware]))

   :http (-> (new-web-server (Integer. (or (env :port) 10666)))
             (component/using [:handler]))))

(defn -main [& _]
  (component/start (prod-system)))

(ns prezel.events
  (:require [prezel.db :as db]
            [re-frame.cofx :refer [inject-cofx]]
            [re-frame.core :as re-frame
             :refer [debug reg-event-db reg-event-fx]]))

(reg-event-db :initialize-db
              (fn [_]
                db/default-db))

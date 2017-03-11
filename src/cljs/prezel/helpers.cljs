(ns prezel.helpers
  (:require [re-frame.core :refer [dispatch subscribe]]))

(def <sub (comp deref subscribe))
(def >evt dispatch)

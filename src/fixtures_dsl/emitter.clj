(ns fixtures-dsl.emitter
  (:require
   [clostache.parser :as clostache]
   )
)

(defn extract-predicates [parse-tree]
  (group-by #(:meta %) (rest parse-tree) )
  )



(defn render-phone [props]
    (render-resource "templates/phone.mustache" props)
  )

(render-phone {})

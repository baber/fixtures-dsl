(ns fixtures-dsl.emitter
  (:require
   [clostache.parser :as clostache]
   )
)




(defn render-phone [props]
    (clostache/render "Hello, {{name}}!" {:name "Felix"})
  )

(render-phone {})

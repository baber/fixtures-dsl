(ns fixtures-dsl.core
  (:use
   [clojure.string :only [join split]]
   [fixtures-dsl.parser :only [parse-tree]]
   )
)


(defn extract-predicates [parse-tree]
  (group-by #(:meta %) (rest parse-tree) )
  )


(def predicates (extract-predicates parse-tree))


(filter #(= "product-definition" (first %))  predicates)



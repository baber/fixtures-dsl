(ns fixtures-dsl.parser
  (:use
    [clojure.string :only [join split]]
   )
  (:require
   [instaparse.core :as insta]
   [clojure.walk :as walk]
   )
)


(def fixtures-parse
  (insta/parser (clojure.java.io/resource "./fixtures_dsl/grammar.txt") :auto-whitespace :standard )
)

(def data (fixtures-parse (slurp (clojure.java.io/resource "./fixtures_dsl/fixtures.txt"))) )


(defn transform-definition [type name _]
      {:meta "product-definition" :type type :name name })


(defn transform-relationship [product _ targets]
    {:meta "relationship" :product product :targets targets}
  )

(defn transform-property-assignment [product  properties]
    {:meta "property-assignment" :product product :properties properties}
  )


(defn transform-name [& args]
  (join "" args))



(defn transform-name-list [& args]
  (read-string (join " " args))
  )


(def transform-funcs {
                      :letters str
                      :name str
                      :digits str
                      :name-list transform-name-list
                      :product transform-definition
                      :relationship transform-relationship
                      :property-assignment transform-property-assignment
                      :type str
                      }
  )


(def parse-tree (insta/transform transform-funcs data))








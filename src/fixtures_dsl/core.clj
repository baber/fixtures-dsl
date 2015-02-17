(ns fixtures-dsl.core
  (:use
    [clojure.string :only [join]]
   )
  (:require
   [instaparse.core :as insta]
   [clojure.walk :as walk]
   )
)


(def whitespace
  (insta/parser
    "whitespace = #'\\s+'"))



(def fixtures-parse
  (insta/parser (clojure.java.io/resource "./fixtures_dsl/grammar.txt") :auto-whitespace whitespace )
)


(def data (fixtures-parse (slurp (clojure.java.io/resource "./fixtures_dsl/fixtures.txt"))) )

(defn transform-definition [_ type name _]
  {:meta "product-definition" :type type :name name}
  )

(defn transform-relationship [product _ targets]
    {:meta "relationship" :product product :targets targets}
  )

(defn transform-product-list [& args]
  (read-string (join " " args))
  )


(def transform-funcs {
                      :letter str
                      :name str
                      :digit str
                      :product-list transform-product-list
                      :product transform-definition
                      :relationship transform-relationship
                      :type str
                      }
  )


(def parse-tree (insta/transform transform-funcs data))







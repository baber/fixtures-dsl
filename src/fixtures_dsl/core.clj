(ns fixtures-dsl.core
  (:require [instaparse.core :as insta])
)


(def whitespace
  (insta/parser
    "whitespace = #'\\s+'"))





(def fixtures-parse
  (insta/parser (clojure.java.io/resource "./fixtures_dsl/grammar.txt") :auto-whitespace whitespace )
)


(def data (fixtures-parse (slurp (clojure.java.io/resource "./fixtures_dsl/fixtures.txt"))) )




(:fixture-definition data)

(map #(.toUpperCase (str %1)) parse-tree)

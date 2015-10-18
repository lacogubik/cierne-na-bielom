(ns cierne-na-bielom.core
  (:gen-class)
  (:require [reaver :refer [parse extract-from text attr]]))


(def url "http://www.ciernenabielom.sk/uvod/strana-2/")


(defn get-book
  []
  (-> url
      slurp
      parse
      (extract-from ".foot-line" [:name :url :img] "h2 a.nazov" text "h2 a.nazov" (attr :href) ".foot-img-cont a" (attr :href))))


(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

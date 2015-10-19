(ns cierne-na-bielom.core
  (:gen-class)
  (:require [reaver :refer [parse extract-from text attr edn]]
            [clojure.string :as string]))


(def url "http://www.ciernenabielom.sk/uvod/strana-")


(def wanted-authors #{"sloboda" "mitana" "hojč" "vilikovský" "dostojevskij"})





(defn get-book-data
  [n]
  (-> (str url n "/")
      slurp
      parse
      (extract-from ".foot-line" [:name :author :url :img] "h2 a.nazov" text "h2 a" text "h2 a.nazov" (attr :href) ".foot-img-cont a" (attr :href))))

(defn get-wanted-books
  [n]
  (let [data (get-book-data n)]
    (->>
      data
      (map #(assoc % :author (second (:author %)))))))


(defn get-latest
  []
  (flatten (for [n [1 2 3 4 5]]
             (get-wanted-books n))))


(defn find-wanted
  []
  (->> (get-latest)
       (filter #(contains? wanted-authors (-> (:author %)
                                              (string/split #" ")
                                              first
                                              string/lower-case)))))

;TODO
; only check the ones not changed from last time - we will need to keep state somewhere???
; parse authors but also book names??
; email result
;

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))

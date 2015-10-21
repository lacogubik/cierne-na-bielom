(ns cierne-na-bielom.core
  (:gen-class)
  (:require [reaver :refer [parse extract-from text attr edn]]
            [clojure.string :as string]))


(def url "http://www.ciernenabielom.sk/uvod/strana-")


(def wanted-authors #{"andruchovyč" "andrejev"
                      "alighieri"                           ;bozska komedia
                      "belyj"                               ;peterburg
                      "beckett"                             ;molloy
                      "bernanos"                            ;dennik vidieckeho knaza
                      "bondy"
                      "borges"                              ;spisy, cela kniznica
                      "canetti"                             ;zaslepeni
                      "céline"                              ;cesta do hlbin noci
                      "celine"
                      "carpentier"                          ;stratene kroky
                      "cohen"                               ;mila pane (her lover)
                      "dostojevskij" "dušek"
                      "földvári"
                      "gombrowicz"                          ;kosmos
                      "grossman"                            ;david, viz laska
                      "jančar"                              ;Drago, Kateřina, páv a jezuita
                      "karous"
                      "kiš"
                      "lasica"
                      "márai"
                      "mann"                                ;magicka hora
                      "mitana"
                      "morante"                             ;pribeh v dejinach
                      "musil"                               ;muz bez  vlastnosti
                      "nabokov"                             ;pozvanie na popravu, prednasky o ruskej literature, lolita
                      "pištánek"
                      "plat"
                      "platonov"                            ;cevengur
                      "rakús"
                      "satinský"
                      "sabato"                              ;tunel samoty
                      "saramago"                            ;mesto slepych
                      "sartre"                              ;hnus a ine
                      "singer"                              ;kejklir z lubliny
                      "sloboda"
                      "solženicyn"                          ;prvy kruh, polostrovie gulag
                      "staviarsky" "štrpka" "tatarka"
                      "tournier"                            ;kral tmy
                      "ulická"                              ;medea a jej deti
                      "vaculík" "vilikovský"

                      })





(defn get-book-data
  [n]
  (-> (str url (inc n) "/")
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
  (flatten (for [n (range 10)]
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

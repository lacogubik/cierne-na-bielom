(defproject cierne-na-bielom "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]
                 [reaver "0.1.2"]
                 [amazonica "0.3.36"]]
  :main ^:skip-aot cierne-na-bielom.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})

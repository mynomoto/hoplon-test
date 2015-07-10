(set-env!
  :dependencies '[[adzerk/boot-cljs          "0.0-3308-0"   :scope "test"]
                  [adzerk/boot-reload        "0.3.0"        :scope "test"]
                  [cljsjs/jquery             "2.1.4-0"      :scope "test"]
                  [im.chit/purnam.test       "0.5.2"        :scope "test"]
                  [org.clojure/clojure       "1.7.0"]
                  [org.clojure/clojurescript "0.0-3308"     :scope "test"]
                  [prismatic/dommy           "1.1.0"        :scope "test"]
                  [tailrecursion/boot-hoplon "0.1.0"        :scope "test"]
                  [tailrecursion/hoplon      "6.0.0-alpha1" :scope "test"]
                  [tailrecursion/javelin     "3.8.0"        :scope "test"]]
  :target-path  "resources/public"
  :source-paths #{"page" "src"})

(require
  '[adzerk.boot-cljs          :refer [cljs]]
  '[adzerk.boot-reload        :refer [reload]]
  '[tailrecursion.boot-hoplon :refer [hoplon prerender]])

(deftask test-profile
  []
  (with-pre-wrap fileset
    (set-env! :source-paths #{"test" "src"})
    (set-env! :target-path "target")
    fileset))

(deftask dev
  "Build hoplon-minimal for local development."
  []
  (comp
    (watch)
    (speak)
    (hoplon)
    (reload)
    (cljs)))

(deftask dev-test
  "Build hoplon-minimal for development with auto test."
  []
  (comp
    (test-profile)
    (watch)
    (hoplon)
    (cljs :optimizations :whitespace)))

(deftask prod
  "Build hoplon-minimal for production deployment."
  []
  (comp
    (hoplon)
    (cljs :optimizations :advanced)
    (prerender)))

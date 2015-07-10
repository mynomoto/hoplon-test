(ns hoplon-test.core-test
  (:require-macros
    [purnam.test :refer [describe it is is-not runs waits-for]]
    [tailrecursion.javelin :refer [defc]])
  (:require
    [dommy.core :as dommy :refer-macros [sel sel1]]
    [dommy.utils :as utils]
    [hoplon-test.core :as c]
    [purnam.test]
    [tailrecursion.javelin :refer [cell]]))

(defc c (cell nil))
(def word-item (c/word-item c))

(describe
  {:doc "testing word-item"
   :globals [el word-item]}
  (it "Should show a word"
    (reset! c "a-word")
    (waits-for "Just a cycle" 0 (= @c "a-word"))
    (runs (is (dommy/text el) "a-word")))
  (it "Should show another word"
    (reset! c "another-word")
    (waits-for "Just a cycle" 0 (= @c "another-word"))
    (runs
      (is (dommy/text el) "another-word"))))

(defc state ["abcdef"])
(def tops-component (c/tops-component state))

(describe
  {:doc "Test whole component"
   :globals [elp tops-component]}
  (it "Should add new word to the list on button click"
    (runs (dommy/append! js/document.body elp))
    (waits-for "Rendering" 10 (do
                                (.dir js/console (sel1 :li))
                                (when-let [w (sel1 :li)]
                                  (= (dommy/text w) "abcdef"))))
    (runs
      (let [elc (.-firstChild elp)
            h (aget (.-childNodes elc) 0)
            i (js/jQuery (sel1 :input))
            wl (sel1 :ul)]
        (is (.-childElementCount elp) 1)
        (is (.-childElementCount wl) 1)
        (is (dommy/text (.-firstChild wl)) "abcdef")
        (is (.val i) "")
        (.focus i)
        (.val i "blabla")
        (is (.val i) "blabla")
        (.change i)
        (is (dommy/text (sel1 :button)) "Submit")
        (.click (aget  (js/jQuery "button") 0))
        (is (.-childElementCount wl) 2)
        (is (dommy/text (.-firstChild wl)) "blabla")))))

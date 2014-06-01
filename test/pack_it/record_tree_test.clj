(ns pack-it.record-tree-test
  (:require [clojure.test :refer :all]
            [clojure.zip :as zip]
            [pack-it.record-tree :refer :all]))

(deftest mk-bin-test
  (testing "Simple bin tree creation"
    (let [t (mk-bin 50 75)]
      (is (= (:length (zip/node t)) 50))
      (is (= (:width (zip/node t)) 75)))))

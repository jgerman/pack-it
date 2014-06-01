(ns pack-it.record-tree-test
  (:require [clojure.test :refer :all]
            [clojure.zip :as zip]
            [pack-it.record-tree :refer :all]))

(deftest mk-bin-test
  (testing "Simple bin tree creation"
    (let [t (mk-bin 50 75)]
      (is (= (:length (zip/node t)) 50))
      (is (= (:width (zip/node t)) 75)))))

(deftest simple-split
  (testing "splitting a single node"
    (let [t (-> (mk-bin 50 75) (split-node 5 5))
          t-left (:left t)
          t-right (:right t)]
      (is (= (:length t) 5))
      (is (= (:width t) 5))
      (is (= (:length t-left) 50))
      (is (= (:width t-left) 70))
      (is (= (:length t-right) 45))
      (is (= (:width t-right) 5))
      )))

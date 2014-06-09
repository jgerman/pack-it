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


; test find on a single node,
; test a recurse left,
; test a recurse right,
; test a no match,
; and we should be solid
(deftest find-no-match-root
  (testing "Finding a node root"
    (let [t (mk-bin 50 50)
          n (->> (gen-fit 51 1)
                 (find-node t)
                 )]
      (is (= n nil))
      )))


(deftest find-root
  (testing "Finding a node root"
    (let [t (mk-bin 50 50)
          n (->> (gen-fit 1 1)
                 (find-node t)
                 zip/node)]
      (is (= (:length n) 50))
      (is (= (:width n) 50)))))

(deftest find-left
  (testing "Finding a node left"
    (let [t (-> (mk-bin 50 50) (split-node 5 5) tree-zip)
          n (->> (gen-fit 1 1)
                 (find-node t)
                 zip/node)]
      (is (= (:length n) 50))
      (is (= (:width n) 45)))))

(deftest find-right
  (testing "Finding a node right"
    (let [t (-> (mk-bin 50 50) (split-node 25 40) tree-zip)
          n (->> (gen-fit 20 35)
                 (find-node t)
                 zip/node)]
      (is (= (:length n) 25))
      (is (= (:width n) 40)))))

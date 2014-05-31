(ns pack-it.record-tree
  (:require [clojure.zip :as zip]))

; based on the bin tree from records in Joy of Clojure

(defprotocol TreeNode
  (branch? [node])
  (node-children [node])
  (make-node [node children]))

; this is an interesting trick, not sure if I like it yet
(extend-type Object TreeNode
             (branch? [node] false)
             (make-node [node children] node))


; the above trick lets us do this
(defn tree-zip
  "Make a zipper out of a tree."
  [root]
  (zip/zipper branch? node-children make-node root))

; do I want two BinNode types? one for the root and one for interior?
(defrecord BinNode [length width left right]
  TreeNode
  (branch? [node] true)
  (node-children [node] (seq (remove nil? [left right])))
  (make-node [node children] (BinNode. (:length node) (:width node) (first children) (second children))))

(defrecord ComponentNode [length width left right]
  TreeNode
  (branch? [node] true)
  (node-children [node] (seq (remove nil? [left right])))
  (make-node [node children] (ComponentNode. (:length node) (:width node) (first children) (second children))))

(defn find-node [root fit-fn]
  (loop [loc root]
    (cond (zip/end? loc) nil
          (fit-fn (-> loc zip/node)) loc
          :else (recur (zip/next loc)))))

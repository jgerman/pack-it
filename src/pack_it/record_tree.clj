(ns pack-it.record-tree
  (:require [clojure.zip :as zip]))

; based on the bin tree from records in Joy of Clojure

(defprotocol TreeNode
  (branch? [node])
  (node-children [node])
  (make-node [node children])
  (container? [node]))

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
  (make-node [node children] (BinNode. (:length node) (:width node) (first children) (second children)))
  (container? [node] true))

(defrecord ComponentNode [length width left right]
  TreeNode
  (branch? [node] true)
  (node-children [node] (seq (remove nil? [left right])))
  (make-node [node children] (ComponentNode. (:length node) (:width node) (first children) (second children)))
  (container? [node] false))

(defn gen-fit [w l]
  (fn [x]
    (and (container? x)
         (<= l (:length x))
         (<= w (:width x))
         )))


; todo ADD CODE TO FIND NODE AND ADD TO PERFORM THE INSERT
(defn find-node [root fit-fn]
  (loop [loc root]
    (cond (zip/end? loc) nil
          (fit-fn (-> loc zip/node)) loc
          :else (recur (zip/next loc)))))

; there won't be any children; should add check though...
; TODO go through the functions and make sure it's clear when they take and return zippers vs records
(defn split-node [node comp-w comp-l]
  (let [node-w (:width (-> node zip/node))
        node-l (:length (->  node zip/node))
        left   (BinNode. (- node-w comp-w) node-l nil nil)
        right  (BinNode. (- node-w comp-l) (- node-l comp-l) nil nil)]
             (ComponentNode. comp-w comp-l left right)))


#_(defn insert [tree w l]
  (let [loc (find-node (gen-fit w l))]
    (if loc
    )))

(defn print-tree [original]
    (loop [loc (zip/seq-zip (seq original))]
          (if (zip/end? loc)
                  (zip/root loc)
                        (recur (zip/next
                           (do (println (zip/node loc))
                                                                        loc))))))

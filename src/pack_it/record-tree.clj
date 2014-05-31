(ns pack-it.record-tree)

; based on the bin tree from records in Joy of Clojure

(defrecord BinNode [length width left right])

(defn bin-insert [t length width]
  (cond
   (nil? t) (BinNode. length width nil nil)
   ))

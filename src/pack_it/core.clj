(ns pack-it.core
  (:require [quil.core :refer :all
             pack-it.tree :refer all]))

; scratch pad code for now, will

; key functions for sorting








(defn produce-rect [node origin-x orgin-y]
  (println "Origin: " origin-x "," origin-y " Diag: " (+ origin-x (.getLength node)) "," (+ origin-y (.getWidth node))))




(defn draw []
  (stroke 2)
  (stroke-weight 2)
  (fill 255)

  (rect 10 10 40 40))

(defn draw-rect [node]
  (rect (:x node) (:y node) (:length node) (:width node)))

(def shapes (atom  [{:x 0 :y 0 :length 500 :width 500}
                    {:x 100 :y 0 :length 400 :width 50}
                    {:x 0 :y 50 :length 100 :width 450}
                    ]))

(defn draw-l []
  (stroke 1)
  (stroke-weight 1)
  (doseq [x @shapes] (draw-rect x))
  )

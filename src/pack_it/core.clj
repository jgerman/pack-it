(ns pack-it.core
  (:require [quil.core :refer :all]))


; key functions for sorting

(defn max-dimension [component]
  (max (:length component) (:width component)))

(defn length [component]
  (:length component))



(defn fits [node component]
  (let [nl (:length node)
        nw (:width node)
        cl (:length component)
        cw (:width component)]
    (and (<= cl nl)
         (<= cw nw))))

(defn mk-node [component]
  {:height (:height component)
   :width (:width component)
   :used true})

; given a node and a component return a tree containing
(defn split [node component]
  (let [node-length (:length node)
        node-width (:width node)
        component-length (:length component)
        component-width (:width component)
        new-a {:length (- node-length component-length)
               :width component-width
               :used false
               :x component-length
               :y 0}
        new-b {:length component-length
               :width (- node-width component-width)
               :used false
               :x 0
               :y component-width}]
    {:length component-length
     :width component-width
     :used true
     :left new-a
     :right new-b
     :x 0
     :y 0}))



(defn setup []
  (smooth)
  (frame-rate 1)
  (background 200))

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

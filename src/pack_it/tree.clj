(ns pack-it.tree)

(definterface IBinNode
  (getLeft [])
  (getRight [])
  (getLength [])
  (getWidth [])
  (setLeft [n])
  (setRight [n])
  (setWidth [x])
  (setLength [y])
  (pp [])
  (fit [w l])
  (preOrder [f])
  (leaf [])
  (insert [w l])
  )

(deftype BinNode
    [^:volatile-mutable ^IBinNode left
     ^:volatile-mutable ^IBinNode right
     ^:volatile-mutable ^IBinNode length
     ^:volatile-mutable ^IBinNode width]

  IBinNode
  (getLeft [_] left)
  (getRight [_] right)
  (getLength [_] length)
  (getWidth [_] width)
  (setLeft [this n] (set! left n) this)
  (setRight [this n] (set! right n) this)
  (setWidth [this x] (set! width x) this)
  (setLength [this y] (set! length y) this)
  (pp [this] (println "Node:"  (.getWidth this) "x" (.getLength this)))
  ; in this pass I'm keeping w and l oriented consistently, since my purpose is
  ; layout on hardwood boards abstract this later

  (fit [this w l]
    (if (and
             (<= l (.getLength this))
             (<= w (.getWidth this)))
      true
      false))

  (preOrder [this f]
    "visit the tree preOrder and apply f to the node"
    (or  (f this)
         ; change these to recur?
         (cond (.getLeft this) (.preOrder (.getLeft this) f))
         (cond (.getRight this) (.preOrder (.getRight this) f)))
    )

  (leaf [this]
    (every? nil?
          (conj []
            (.getLeft this)
            (.getRight this))))

  ; node insertion
  ; base case is leaf node, check for fit
  ; otherwise if (not leaf) call with left then with right
  (insert [this w l]
    "Insert a node when a node with space is found"
    (cond  (nil? this) false) ; base case 2 we hit nil
    (cond  (.leaf this)
               (cond (.fit this w l) ; base case 3 this is a leaf and we have a fit
                        (let [left  (BinNode. nil nil (- (.getWidth this) w) (.getLength this))
                              right (BinNode. nil nil (- (.getWidth this) w) (- (.getLength this) l))]
                          (.setLeft this left)
                          (.setRight this right)
                          (.setWidth this w)
                          (.setLength this l)
                          true
                          )
                     :else false)

           )
    )

  )

; convenience for adding a component
(defn add-component [root w l]
  (.preOrder root #(.insert % w l)))

; pretty print convenience
(defn pretty-print [root]
  (.preOrder root #(.pp %)))

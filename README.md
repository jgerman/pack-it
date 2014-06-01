# pack-it

A clojure library to handle bin packing. The motivation was for laying out parts on hardwood. Still in the early stages. 

A mutable just make things easy to work with.

Note: Immutable version with zippers is started in pack-it.record-tree. It's much cleaner. The structure is there, need to implement the algorithm over the zipper which should be as straightforward as finding the right place to add and using the zipper tree manipulators to get a new tree.

Known Issues:
length and width maintain their orientation in searching. 


## Splitting a Node ##

When an empty node is found that is the appropriate size that node is replaces with a subtree consisting of a root representing the component being inserted who's
left and right children are two new empty nodes divided as shown by the image below.

![alt tag] (docs/binpacknodesplit.jpg)

## Examples (applicable only to the mutable version).

```
(require 'pack-it.tree :reload)
(in-ns 'pack-it.tree)
```

Very basic right now, pretty print just prints a list of nodes. Visualization coming.
```
(def root (BinNode. nil nil 10 10))
(add-component 4 4)
(pretty-print root)
(add-component 1 2)
(pretty-print root)
```

## License

Copyright © 2014 Jeremy German

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

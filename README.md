# pack-it

A clojure library to handle bin packing. The motivation was for laying out parts on hardwood. Still in the early stages. 

A mutable just make things easy to work with.

Known Issues:
length and width maintain their orientation in searching. 

## Examples

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

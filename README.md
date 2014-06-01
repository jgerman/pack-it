# pack-it

A clojure library to handle bin packing. The motivation was for laying out parts on hardwood. Still in the early stages. 


Known Issues:
length and width maintain their orientation in searching. This is currently intentional based on the intended usage.  


## Splitting a Node ##

When an empty node is found that is the appropriate size that node is replaces with a subtree consisting of a root representing the component being inserted who's
left and right children are two new empty nodes divided as shown by the image below.

![alt tag] (docs/binpacknodesplit.jpg)

## Examples 


## License

Copyright © 2014 Jeremy German

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

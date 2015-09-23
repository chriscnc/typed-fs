# Typed-FS

A Clojure library that provides core.typed annotations for the [fs](https://github.com/Raynes/fs) library.

Still in development

## Usage

Simply require the annotations into the modules that do logging.

```clojure
(ns example.fs
  (:require [me.raynes.fs :as fs]
            [typed-fs.annotations :refer :all]))

;; use the logging library as usual. The referred annotations
;; should satify the type-checker
```

### Installation


## License

Copyright © 2015 Chris Cornelison

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

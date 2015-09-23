(ns typed-fs.annotations
  (:require [me.raynes.fs :as fs]
            [clojure.core.typed :as t :refer [ann IFn U Option]])
  (:import [java.io File]))


(ann ^:no-check me.raynes.fs/temp-dir (IFn [String -> (Option File)]
                                           [String String -> (Option File)]
                                           [String String Integer -> (Option File)]))


(defn foo
  []
  (let [^File tmpdir (fs/temp-dir "pre")]
    (when tmpdir (.deleteOnExit tmpdir))))
  

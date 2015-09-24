(ns typed-fs.annotations
  (:require [me.raynes.fs :as fs]
            [clojure.java.io :as io]
            [clojure.core.typed :as t :refer [ann IFn U Option Bool]])
  (:import [java.io File]
           [java.net URI URL]))

(t/defalias ResourceNamish (U File URI URL String))

(ann ^:no-check me.raynes.fs/*cwd* File)
(ann ^:no-check me.raynes.fs/absolute [ResourceNamish -> File])
(ann ^:no-check me.raynes.fs/absolute? [ResourceNamish -> Bool])
(ann ^:no-check me.raynes.fs/base-name (IFn [ResourceNamish -> String]
                                            [ResourceNamish (U String Bool) -> String]))
;(ann chdir) skipping
(ann ^:no-check me.raynes.fs/chmod [String ResourceNamish -> String])
(ann ^:no-check me.raynes.fs/child-of? [ResourceNamish ResourceNamish -> Bool])
(ann ^:no-check me.raynes.fs/file (Fn [ResourceNamish String * -> File]))

; technically this function returen (Option File). nil is the function 
; after some number of tries, fails to create the directory. I'm intentionally
; ignoring this fact here as it causes too many headaches when passing the return
; of this function to any of the other function that expect File.
(ann ^:no-check me.raynes.fs/temp-dir (IFn [String -> File]
                                           [String String -> File]
                                           [String String Integer -> File]))


(defn foo
  []
  (let [^File tmpdir1 (fs/temp-dir "pre")
        _ (.deleteOnExit tmpdir1)
        ^File tmpdir2 (fs/temp-dir "pre" "suf")
        _ (.deleteOnExit tmpdir2)
        ^File tmpdir3 (fs/temp-dir "pre" "suf" 100)
        _ (.deleteOnExit tmpdir3)
        ^File f fs/*cwd*]

    (let [f (fs/file "foo")
          uri (URI. "file:///foo")
          url (URL. "file:///foo")]
      (fs/file f "foo")
      (fs/file uri)
      (fs/file url)

      (fs/absolute "foo")
      (fs/absolute f)
      (fs/absolute uri)
      (fs/absolute url)

      (fs/absolute? "foo")
      (fs/absolute? f)
      (fs/absolute? uri)
      (fs/absolute? url)

      (fs/base-name uri)
      (fs/base-name url)
      (fs/base-name "/root/foo.txt")
      (fs/base-name f)
      (fs/base-name f true)
      (fs/base-name f ".txt")

      (fs/child-of? f f)
      (fs/chmod "+x" tmpdir3)
      )
    ))
  

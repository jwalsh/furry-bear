#!/bin/sh
# Ensure that the ClojureScript is constantly rebuilt following
# changes and run a stand-alone server. 


lein deps
lein cljsbuild auto &
lein ring server-headless 3000



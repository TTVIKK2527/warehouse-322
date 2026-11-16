#!/bin/sh
set -eu
cd "$(dirname "$0")"
mkdir -p build
javac -Xlint:all -encoding UTF-8 -d build src/*.java
exec java -cp build Main

#!/bin/sh
clear
for f in *.as
do
    cp $f testCodeGen.as
    make run
    rm testCodeGen.as
    cd snes
    bash ./assemble.sh
    cd ..
done


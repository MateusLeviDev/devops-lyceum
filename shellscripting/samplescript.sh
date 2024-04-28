#!/bin/bash

###############
# Author: levi
# Date: 27/04/2024 
#
# This script outputs numbers divided by 3, 5 and not by 15
#
# Version: v1
###############

for i in {1..100}; do
    if { [ $(( i % 3 )) -eq 0 ] || [ $(( i % 5 )) -eq 0 ]; } && ! { [ $(( i % 15 )) -eq 0 ]; }; then
        echo $i
    fi
done 

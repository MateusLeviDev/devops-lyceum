#!/bin/bash

###############
# Author: levi
# Date: 27/04/2024 
#
# This script outputs numbers of "$s" in Mississipi 
#
# Version: v1
###############

x=mississipi

grep -o "s" <<<"$x" | wc -l


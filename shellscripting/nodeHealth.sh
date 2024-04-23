#!/bin/bash

##########################
# Author: levi
# Date: 23/04/2024
#
# This script outputs the node health 
#
# Version: v1
##########################

set -x # debug mode
set -e # exit the script when there is an error
set -o pipefail

df -h 

free -g

nproc

ps -ef | grep nvidia | awk -F" " '{print $2}' 

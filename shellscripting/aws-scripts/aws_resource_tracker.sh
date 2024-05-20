#!/bin/bash

######################
# Author: levi
# Date: May 2 
#
# Version: v1
#
# This scripts will report the AWS resource usage
######################

# list s3 buckets
echo "Print list of s3 buckets"
aws s3 ls

# list EC2 Instances
echo "Print list of ec2 buckets"
aws ec2 describe-instances | jq '.Reservations[].Instances[].InstancesId'

# list lambda
echo "Print list of lambda functions"
aws lambda list-functions 

# list IAM Users
echo "Print list of IAM Users"
aws iam list-users

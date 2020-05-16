#! /usr/bin/env bash 

read cesar

echo $cesar | tr 'abcdefghijklmnopqrstuvwxyz' 'bcdefghijklmnopqrstuvwxyza'
echo $cesar | tr 'abcdefghijklmnopqrstuvwxyz' 'zabcdefghijklmnopqrstuvwxy'
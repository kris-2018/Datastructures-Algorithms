#195. 第十行
#给定一个文本文件 file.txt，请只打印这个文件中的第十行。
#示例:
#假设 file.txt 有如下内容：
#Line 1
#Line 2
#Line 3
#Line 4
#Line 5
#Line 6
#Line 7
#Line 8
#Line 9
#Line 10
#你的脚本应当显示第十行：
#Line 10
#说明:
#1. 如果文件少于十行，你应当输出什么？
#2. 至少有三种不同的解法，请尝试尽可能多的方法来解题。
# https://leetcode.com/problems/tenth-line/

#!/usr/bin/env bash

# Solution 1
cnt=0
while read line && [ $cnt -le 10 ]; do
  let 'cnt = cnt + 1'
  if [ $cnt -eq 10 ]; then
    echo $line
    exit 0
  fi
done < file.txt

# Solution 2
awk 'FNR == 10 {print }'  file.txt
# OR
awk 'NR == 10' file.txt

# Solution 3
sed -n 10p file.txt

# Solution 4
tail -n+10 file.txt|head -1

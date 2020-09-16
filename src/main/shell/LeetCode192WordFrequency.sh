
#192. 统计词频
#写一个 bash 脚本以统计一个文本文件 words.txt 中每个单词出现的频率。
#为了简单起见，你可以假设：
#   words.txt只包括小写字母和 ' ' 。每个单词只由小写字母组成。单词间由一个或多个空格字符分隔。
#示例:
#   假设 words.txt 内容如下：
#   the day is sunny the the
#   the sunny is is
# 你的脚本应当输出（以词频降序排列）：
#   the 4
#   is 3
#   sunny 2
#   day 1
# 说明:
#不要担心词频相同的单词的排序问题，每个单词出现的频率都是唯一的。
#你可以使用一行 Unix pipes 实现吗？
# https://leetcode.com/problems/word-frequency

#!/usr/bin/env bash
# 方法一:
# tr -s: truncate the string with target string, but only remaining one instance (e.g. multiple whitespaces)
# sort: To make the same string successive so that uniq could count the same string fully and correctly.
# uniq -c: uniq is used to filter out the repeated lines which are successive, -c means counting
# sort -r: -r means sorting in descending order
# awk '{ print $2, $1 }': To format the output, see here.

cat words.txt | tr -s ' ' '\n' | sort | uniq -c | sort -r | awk '{ print $2, $1 }'

#方法二:
:<<!
1. I should count the words. So I chose the awk command.
  I use a dictionary in awk. For every line I count every word in the dictionary.
  After deal with all lines. At the END, use for (item in Dict) { #do someting# } to print every words and its frequency.
2. Now the printed words are unsorted. Then I use a | pipes and sort it by sort
  sort -n means "compare according to string numerical value".
  sort -r means "reverse the result of comparisons".
  sort -k 2 means "sort by the second word"

NF 代表一行有多少个域 （也就是一行有多少个单词）
!
awk '\
{ for (i=1; i<=NF; i++) { ++D[$i]; } }\
END { for (i in D) { print i, D[i] } }\
' word.txt | sort -nr -k 2


#方法三:
:<<!
AC solution(one pipe command)
$ man grep
...
Matcher Selection
-E, --extended-regexp
Interpret PATTERN as an extended regular expression (ERE, see below).
...
-o, --only-matching
Print only the matched (non-empty) parts of a matching line,
with each such part on a separate output line.
!
grep -oE '[a-z]+' words.txt | sort | uniq -c | sort -r | awk '{print $2" "$1}'
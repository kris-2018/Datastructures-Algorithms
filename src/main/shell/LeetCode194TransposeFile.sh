#194. 转置文件
#给定一个文件 file.txt，转置它的内容。
#你可以假设每行列数相同，并且每个字段由 ' ' 分隔.
#示例:
#假设 file.txt 文件内容如下：
#   name age
#   alice 21
#   ryan 30
# 应当输出：
#   name alice ryan
#   age 21 30
# https://leetcode.com/problems/transpose-file/

#!/usr/bin/env bash
# Just feel free to use for and if.
# You can append string easily, for example, s = s a to append a with s.

awk '
{
    for (i = 1; i <= NF; i++) {
        if(NR == 1) {
            s[i] = $i;
        } else {
            s[i] = s[i] " " $i;
        }
    }
}
END {
    for (i = 1; s[i] != ""; i++) {
        print s[i];
    }
}' file.txt


#My solution in BASH. It works fine on my computer and I think it's conceptually straightforward. OJ complains exceeding memory.

ncol=`head -n1 file.txt | wc -w`
for i in `seq 1 $ncol`
do
    echo `cut -d' ' -f$i file.txt`
done


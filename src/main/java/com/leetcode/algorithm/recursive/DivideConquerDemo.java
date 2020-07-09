package com.leetcode.algorithm.recursive;

/**
 * 分治法的基本步骤
 * 分治算法就是将原问题划分成n个规模较小，并且结构与原问题相似的子问题，递归地解决这些子问题，然后再合并其结果，就得到原问题的解。
 * 分治算法的递归实现中，每一层递归都会涉及这样三个操作：
 *      分解：将原问题分解成一系列子问题
 *      解决：递归地求解各个子问题，若子问题足够小，则直接求解
 *      合并：将子问题的结果合并成原问题
 *
 *
 *
 * 分治算法能解决的问题，一般需要满足下面这几个条件：
 *      原问题与分解成的小问题具有相同的模式
 *      原问题分解成的子问题可以独立求解，子问题之间没有相关性
 *      具有分解终止条件，也就是说，当问题足够小，可以直接求解
 *      可以将子问题合并成原问题，而这个合并操作的复杂度不能太高，否则就起不到减小算法总体复杂度的效果了
 *
 */
public class DivideConquerDemo {
/*
    public void devide_conquer(String problem, String param1, String param2,...){
        // 1. recursion terminator 终止条件 分解到最后的叶子，没有子问题了
        if (problem == null) {
            print_result;
            return;
        }
        //2. prepare data  处理当前逻辑
        String data = prepare_data(problem);
        String subproblems = split_problem(problem, data);
        //3. conquer subproblems 下探到下一层，解决更细节的子问题
        subresult1 = divide_conquer(subproblems[0], p1,...)
        subresult2 = divide_conquer(subproblems[1], p1,...)
        subresult3 = divide_conquer(subproblems[2], p1,...)
        ...
        //4. process and generate the final result
        result = process_result(subresult1, subresult2, subresult3,...)
        //5. revert the current level states
    }*/
}

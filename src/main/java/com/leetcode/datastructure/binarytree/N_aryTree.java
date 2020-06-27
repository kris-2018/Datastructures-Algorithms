package com.leetcode.datastructure.binarytree;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class N_aryTree {
    public int data;
    public List<N_aryTree> children;

    public N_aryTree() {
    }

    public N_aryTree(int data) {
        this.data = data;
    }

    public N_aryTree(int data, List<N_aryTree> children) {
        this.data = data;
        this.children = children;
    }
}

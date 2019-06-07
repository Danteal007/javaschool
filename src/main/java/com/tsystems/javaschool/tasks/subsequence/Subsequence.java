package com.tsystems.javaschool.tasks.subsequence;

import java.util.List;

public class Subsequence {

    /**
     * Checks if it is possible to get a sequence which is equal to the first
     * one by removing some elements from the second one.
     *
     * @param x first sequence
     * @param y second sequence
     * @return <code>true</code> if possible, otherwise <code>false</code>
     */
    @SuppressWarnings("rawtypes")
    public boolean find(List x, List y) {
        // TODO: Implement the logic here
        int c = -1;
        int k = 0;
        Object a,b;
        if (x == null || y == null){
            throw new IllegalArgumentException();
        }

        for (int i = 0; i < x.size(); i++) {
            a = x.get(i);
            for (int j = c + 1; j < y.size(); j++) {
                b = y.get(j);
                if (a == b) {
                    c = j;
                    k++;
                    break;
                }
            }
        }

        if(k == x.size())
            return true;
        else
            return false;
    }
}


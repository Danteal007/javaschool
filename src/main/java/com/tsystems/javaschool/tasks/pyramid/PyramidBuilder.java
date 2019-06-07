package com.tsystems.javaschool.tasks.pyramid;

import java.util.ArrayList;
import java.util.List;

public class PyramidBuilder {

    /**
     * Builds a pyramid with sorted values (with minumum value at the top line and maximum at the bottom,
     * from left to right). All vacant positions in the array are zeros.
     *
     * @param inputNumbers to be used in the pyramid
     * @return 2d array with pyramid inside
     * @throws {@link CannotBuildPyramidException} if the pyramid cannot be build with given input
     */
    public int[][] buildPyramid(List<Integer> inputNumbers) {
        // TODO : Implement your solution here
        int kol = 0;
        int rows;
        int [][] pyramid;
        List<Integer> result;

        double descriminant = Math.sqrt(1 + 8l * inputNumbers.size());

        if(descriminant % 1 == 0){
            rows = (int)(descriminant - 1)/2;
            result = Sort(inputNumbers);
            pyramid = GoBuild(result,rows);
        }
        else
            throw new CannotBuildPyramidException();

        return pyramid;
    }

    public int[][] GoBuild (List<Integer> inputNumbers, int rows){
        int cols = rows*2-1;
        int num = 0;
        int [][] pyramid = new int[rows][cols];
        for (int i = 0; i < rows; i++){

            for (int j = 0; j < cols; j++){

                if(((i+j)%2 != rows%2) && (j >= rows - i - 1) && (j <= rows + i - 1)){
                    pyramid[i][j] = inputNumbers.get(num);
                    num++;
                    System.out.print(pyramid[i][j] + " ");
                }
                else {
                    pyramid[i][j] = 0;
                    System.out.print(pyramid[i][j] + " ");
                }
            }
            System.out.println();
        }

        return pyramid;
    }

    public List<Integer> Sort (List<Integer> input){
        int temp,a,b;
        try {


            for (int j = input.size() - 1; j > 0; j--) {
                for (int i = 0; i < j; i++) {

                    a = input.get(i);
                    b = input.get(i + 1);
                    if (a > b) {
                        input.set(i, b);
                        input.set(i + 1, a);
                    }
                }
            }
        }
        catch (NullPointerException e){
            throw new CannotBuildPyramidException();
        }
        return input;
    }


}
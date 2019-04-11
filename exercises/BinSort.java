/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exercises;

import java.util.Arrays;

/**
 *
 * @author jpm8993
 */
public class BinSort {
    private int MAX = 19;
    
    public void sort(int[] array) {
        int[] bin = new int[MAX + 1];
        
        for(int i = 0; i < array.length; i++) {
            bin[array[i]]++; 
            System.out.println(Arrays.toString(bin));
        }
        
        int position = 0;
        
        for(int i = 0; i < bin.length; i++) {
            for(int j = 0; j < bin[i]; j++ ) {
                array[position++] = i;
            }
        }
    }

    public static void main(String[] args) {
        BinSort bin = new BinSort();
        
        int[] array = {5, 5, 5, 5, 10, 2, 1, 0, 19};

        System.out.println("Before sort: " + Arrays.toString(array));
        bin.sort(array);
        System.out.println("Before sort: " + Arrays.toString(array));

    }
}

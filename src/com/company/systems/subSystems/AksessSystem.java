package com.company.systems.subSystems;

import com.company.scenes.DrawScene;
import com.company.systems.MainSystem;

import java.util.ArrayList;


public class AksessSystem<E extends Comparable<E>> extends MainSystem {
    public AksessSystem(DrawScene drawScene) {
        super(drawScene);
    }

    public void noSort(ArrayList<Integer> array, String input) {
        if(checkIfInt(input)) {
            try{
                showInfoMessage("Method1: " + array.get(Integer.parseInt(input) - 1));
            } catch(IndexOutOfBoundsException e) {
                showErrorMessage("Input not valid!");
            }
        } else {
            showErrorMessage("Please input a number instead!");
        }
    }


    /**
     *
     * KILDE: https://favtutor.com/blogs/sorting-algorithms-java
     * @param array
     * @param venstrePos
     * @param midten
     * @param hoyrePos
     */
    void merge(int array[], int venstrePos, int midten, int hoyrePos){
        int storrelseVenstre = midten - venstrePos +1;
        int storrelseHoyre = hoyrePos - midten;
        int venstreArray[] = new int [storrelseVenstre];
        int hoyreArray[] = new int [storrelseHoyre];
        int i = 0, j = 0;

        for( i = 0; i < storrelseVenstre; i++){
            venstreArray[i] = array[venstrePos+i];
        }
        for(j = 0; j < storrelseHoyre; j++){
            hoyreArray[j] = array[midten + 1 + j];
        }

        int sorteringPos = venstrePos;
        i = 0;
        j = 0;
        while(i < storrelseVenstre && j < storrelseHoyre){
            if(venstreArray[i] <= hoyreArray[j]){
                array[sorteringPos] = venstreArray[i];
                i++;
            } else {
                array[sorteringPos] = hoyreArray[j];
                j++;
            }
            sorteringPos++;
        }
        while(i < storrelseVenstre){
            array[sorteringPos] = venstreArray[i];
            i++;
            sorteringPos++;
        }
        while(j < storrelseHoyre){
            array[sorteringPos] = hoyreArray[j];
            j++;
            sorteringPos++;
        }
    }

    /**
     *
     * KILDE: https://favtutor.com/blogs/sorting-algorithms-java
     * @param array
     * @param venstre
     * @param hoyre
     */
    public void mergeSort(int array[], int venstre, int hoyre){
        int midten;
        if(venstre < hoyre){
            midten = (venstre + hoyre) / 2;
            mergeSort(array, venstre, midten);
            mergeSort(array, midten+1, hoyre);
            merge(array, venstre,midten, hoyre);
        }
    }

    //For testing
    public void display(int arr[]){
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i] + " ");
        }
    }
}
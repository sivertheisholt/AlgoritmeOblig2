package com.company.systems.subSystems;

import com.company.scenes.DrawScene;
import com.company.systems.MainSystem;

import java.util.ArrayList;

/**
 * Denne klassen håndterer tre aksess ting
 */
public class AksessSystem extends MainSystem {
    public AksessSystem(DrawScene drawScene) {
        super(drawScene);
    }

    /**
     * Sjekker input og finner riktig tall utifra x minste
     * @param array
     * @param input
     */
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
     * Sjekker input og finner riktig tall utifra x minste
     * @param array
     * @param input
     */
    public void sort(int[] array, String input) {
        if(checkIfInt(input)) {
            try{
                showInfoMessage("Method2: " + array[Integer.parseInt(input) - 1]);
            } catch(IndexOutOfBoundsException e) {
                showErrorMessage("Input not valid!");
            }
        } else {
            showErrorMessage("Please input a number instead!");
        }
    }

    /**
     * Merger til 1 array
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
     * Sorterer dataen og kjører merge til slutt
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
}
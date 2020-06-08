package com.example.exparcialg2.Class;

public class InvertirArray {
    private static char[] invertir_int;
    public  char[] invertir(char[] array){
        char aux;
        for(int i = 0; i< array.length/2; i++){
            aux = array[i];
            array[i] = array[array.length -1 -i];
            array[array.length - 1 - i] = aux;
        }
        return array;
    }
}

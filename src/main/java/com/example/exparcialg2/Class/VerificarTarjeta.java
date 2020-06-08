package com.example.exparcialg2.Class;

import java.util.Arrays;

public class VerificarTarjeta {
    public boolean verifica;
    public static void tarjeta(String tarjeta) {
        // TODO code application logic here
        InvertirArray invertirArray = new InvertirArray();
        ;
        //pasar tarjeta aqui


        //char[] acaracteres;
        char[] acaracteres = tarjeta.toCharArray();

        String digitoverificador;

        digitoverificador = String.valueOf(acaracteres[15]);

        char[] nuevatarjeta = new char[acaracteres.length -1];
        System.arraycopy(acaracteres, 0, nuevatarjeta, 0, nuevatarjeta.length);
        //String nuevatarjetastr = nuevatarjeta.toString();

        try{
            invertirArray.invertir(nuevatarjeta);

        }catch(Exception e){
            System.out.println("Error: " + e.getMessage());
        }
        //numeros impares
        //convertir char array to int array
        int[] tarjetamulti = new int[nuevatarjeta.length];
        for(int i = 0; i<tarjetamulti.length; i++){
            tarjetamulti[i] = (int) nuevatarjeta[i]-48;
        }
        int[] tarjetamulti2 = new int[tarjetamulti.length];
        for(int i = 0; i<tarjetamulti2.length; i++){
            if((i+1)%2 != 0){
                tarjetamulti2[i] =(2 * tarjetamulti[i]);
            }else{
                tarjetamulti2[i] = tarjetamulti[i];
            }
        }
        //convertir int array to charr array

//        char[] tarjetamultistr = new char[tarjetamulti2.length];
//        for(int i = 0; i<tarjetamulti2.length; i++){
//
//            tarjetamultistr[i] =  (char) ( tarjetamulti2[i] +48);
//
//        }



        //restar 9 a los mayores a 9 xd
        int[] tarjetaresta = new int[tarjetamulti2.length];
        for(int i = 0; i<tarjetaresta.length; i++){
            if(tarjetamulti2[i]>9){
                tarjetaresta[i] = tarjetamulti2[i]-9;
            }else{
                tarjetaresta[i] = tarjetamulti2[i];
            }
        }

        //sumar todo
        int total = 0;
        for(int i = 0; i<tarjetaresta.length; i++){
            total += tarjetaresta[i];
        }

        //operacion final
        int verificacion = 0;
        verificacion = (10-(total%10))%10;



    }

    public boolean isVerifica() {
        return verifica;
    }
}

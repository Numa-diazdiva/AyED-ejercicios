package tp02.ejercicio1;

public class ImpresionInversa {

    public static void main(String[] args){
        
        ListaDeEnterosEnlazada listaE = new ListaDeEnterosEnlazada();
        ListaDeEnterosConArreglos listaA = new ListaDeEnterosConArreglos();
        
        listaE.agregarFinal(1);
        listaE.agregarFinal(2);
        listaE.agregarFinal(3);
        listaE.agregarFinal(4);

        listaA.agregarFinal(1);
        listaA.agregarFinal(2);
        listaA.agregarFinal(3);
        listaA.agregarFinal(4);

        int tam = listaE.tamanio();
        imprimirRecursivoDescendente(listaE, tam);
        tam = listaA.tamanio();
        imprimirRecursivoDescendente(listaA, tam);

    }

    public static void imprimirRecursivoDescendente(ListaDeEnteros l, int pos){
        if (pos > 0){
            System.out.print(" " + l.elemento(pos));
            pos--;
            imprimirRecursivoDescendente(l, pos);
        }
    }


}

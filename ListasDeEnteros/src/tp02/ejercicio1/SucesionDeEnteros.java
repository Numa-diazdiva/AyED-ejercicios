package tp02.ejercicio1;

public class SucesionDeEnteros {

    public ListaDeEnterosEnlazada calcularSucesion(int n) {
        ListaDeEnterosEnlazada lista = new ListaDeEnterosEnlazada();
        calcularNum(lista,n);
        return lista;
    }

    public void calcularNum(ListaDeEnteros l, int n) {
    	l.agregarFinal(n);
        if (n != 1){
        	if (n % 2 == 0) {
        		n = n / 2;
        	} else {
        		n = n * 3 + 1;
        	}
        	calcularNum(l,n);
        }
    }

}

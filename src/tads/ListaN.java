package tads;

import clases.*;

import java.time.LocalDate;

public class ListaN<T extends Comparable<?>> implements IListaSimple<T> {

    public Nodo inicio;
    private Nodo fin;
    private int cantidad;

    public ListaN() {
        inicio = null;
    }

    public Nodo getInicio() {
        return inicio;
    }

    public T obtenerElemento(int posicion) {
        if (posicion < 0 || posicion >= cantidad) {
            return null;  // fuera de los límites
        }

        Nodo aux = inicio;
        for (int i = 0; i < posicion; i++) {
            aux = aux.getSiguiente();
        }

        return (T) aux.getDato();
    }

    @Override
    public boolean esVacia() {
        return inicio == null;
    }

    @Override
    public void agregarInicio(T n) {

        Nodo nuevo = new Nodo(n);

        if (esVacia()) {
            inicio = nuevo;
            fin = nuevo;
        } else {
            nuevo.setSiguiente(inicio);
            inicio = nuevo;
        }
        cantidad++;
        /*  Nodo nuevo = new Nodo(n);  
        nuevo.setSiguiente(inicio);
        inicio = nuevo;
        cantidad++;
        if(cantidad == 1){
            fin=inicio;
        }
        
         */
    }

    @Override
    public void agregarFinal(T n) {

        if (esVacia()) {
            agregarInicio(n);
        } else {

            Nodo nuevo = new Nodo(n);
            Nodo aux = inicio;

            while (aux.getSiguiente() != null) {
                aux = aux.getSiguiente();
            }

            aux.setSiguiente(nuevo);
            fin = nuevo;
            cantidad++;

        }
    }

    @Override
    public void borrarInicio() {

        if (!esVacia()) {

            Nodo aBorrar = inicio;
            inicio = inicio.getSiguiente();
            aBorrar.setSiguiente(null);
            cantidad--;

        }
    }

    @Override
    public void borrarFin() {

        if (!esVacia()) {

            if (inicio.getSiguiente() == null) { //tiene un elemento solo
                borrarInicio();
            } else {

                Nodo aux = inicio;

                while (aux.getSiguiente().getSiguiente() != null) {
                    aux = aux.getSiguiente();

                }
                aux.setSiguiente(null);
                fin = aux;

            }
            cantidad--;
        }

    }

    @Override
    public void vaciar() {
        inicio = null;
        fin = null;
        cantidad = 0;
    }

    @Override
    public void mostrar() {

        Nodo aux = inicio;

        while (aux != null) {
            System.out.print(aux.getDato() + " ");
            aux = aux.getSiguiente();
        }

    }

    @Override
    public void borrarElemento(T n) {
        if (!esVacia()) {

        }

    }

    @Override
    public int cantElementos() {
        return cantidad;
    }

    @Override
    public void agregarOrdenado(T n) {
        if (esVacia() || inicio.getDato().compareTo(n) >= 0) {
            this.agregarInicio(n);
        } else {

            Nodo aux = inicio;

            while (aux.getSiguiente() != null && aux.getSiguiente().getDato().compareTo(n) < 0) {
                aux = aux.getSiguiente();
            }

            if (aux.getSiguiente() == null) {
                this.agregarFinal(n);
            } else {

                Nodo nuevo = new Nodo(n);
                nuevo.setSiguiente(aux.getSiguiente());
                aux.setSiguiente(nuevo);
                cantidad++;

            }

        }

    }

    @Override
    public boolean existeElemento(T n) {

        boolean existe = false;
        Nodo aux = inicio;

        while (aux != null && !existe) {
            if (aux.getDato().equals(n)) {
                existe = true;
            }
            aux = aux.getSiguiente();
        }
        return existe;
    }

    @Override
    public void eliminarElemento(T n) {

        if (!esVacia()) {
            if (inicio.getDato().equals(n)) {
                this.borrarInicio();
            } else {

                Nodo aux = inicio;

                while (aux.getSiguiente() != null && !aux.getSiguiente().getDato().equals(n)) {
                    aux = aux.getSiguiente();
                }

                if (aux.getSiguiente() != null) { //Encontre el elemento

                    Nodo aBorrar = aux.getSiguiente();
                    aux.setSiguiente(aBorrar.getSiguiente());
                    aBorrar.setSiguiente(null);
                    cantidad--;

                }

            }

        }

    }

}

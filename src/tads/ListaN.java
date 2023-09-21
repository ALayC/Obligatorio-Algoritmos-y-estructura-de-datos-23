package tads;

import clases.*;

public class ListaN<T> implements IListaSimple<T> {

    private Nodo inicio;
    private Nodo fin;
    private int cantidad;

    public ListaN() {
        inicio = null;
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
    public Nodo obtenerElemento(T n) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void agregarOrdenado(T n) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    

}

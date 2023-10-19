package tads;


public class Pila<T extends Comparable<T>> implements IPila<T> {

    private Nodo<T> tope;
    private int cant;

    public Pila() {
        this.tope = null;
        cant = 0;
    }

    public boolean estaVacia() {
        return tope == null;
    }

    @Override
    public void apilar(T dato) {
        Nodo<T> nuevoNodo = new Nodo(dato);
        
        nuevoNodo.setSiguiente(tope);
        tope = nuevoNodo;
        cant++;
    }

    @Override
    public T desapilar() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        T dato = tope.getDato();
        tope = tope.getSiguiente();
        cant--;
        return dato;
    }

    public T top() {
        if (estaVacia()) {
            throw new IllegalStateException("La pila está vacía");
        }
        return tope.getDato();
    }

    @Override
    public void vaciar() {
        this.tope = null;
        cant = 0;
    }

    @Override
    public int cantidadNodos() {
        return cant;
    }
    
    public void mostrar(){
        if (!estaVacia()) {
           
            Nodo<T> actual = tope;
            
            while(actual != null){
                System.out.println(actual.getDato());
                actual = actual.getSiguiente();
            }
             System.out.println("-----------------------");
        }
        
    }
}

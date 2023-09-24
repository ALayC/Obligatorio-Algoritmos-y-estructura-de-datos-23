package tads;


public class Nodo<T extends Comparable> {
    
    private T dato;
    private Nodo siguiente;

    public Nodo(T elDato){
        this.setDato(elDato);
        siguiente = null;
    }
    
    
    
    public T getDato() {
        return dato;
    }

    /**
     * @param dato the dato to set
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * @return the siguiente
     */
    public Nodo getSiguiente() {
        return siguiente;
    }

    /**
     * @param siguiente the siguiente to set
     */
    public void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
    
}

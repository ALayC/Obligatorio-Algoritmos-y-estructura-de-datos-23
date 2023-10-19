package tads;


public interface IListaSimple<T extends Comparable<?>> {
    
    public boolean esVacia(); 
    public void agregarInicio(T n); 
    public void agregarFinal(T n); 
    public void borrarInicio(); 
    public void borrarFin(); 
    public void vaciar(); 
    public void mostrar();
    public void borrarElemento(T n);
    public int cantElementos();
    public T obtenerElemento(int posicion);
    public void agregarOrdenado(T n);
    public boolean existeElemento(T n);
    public void eliminarElemento(T n);
    
    
    
    
   
}

package tads;
public interface ICola<T extends Comparable<T>>  {

    public void encolar(T dato);

    public T desencolar();

    public T front();

    public boolean isEmpty();

    public void vaciar();

    public int cantidadNodos();

}

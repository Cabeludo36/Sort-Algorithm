package algorithms;

/**
 * Interface base para todos os algoritmos de ordenação.
 */
public interface ISortAlgoritimo {
    public String getName();

    public long getDelay();

    public void setDelay(long delay);

    public void runSort(SortArray array);
}

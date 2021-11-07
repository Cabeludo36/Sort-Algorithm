package algorithms;

 public class GnomeSort implements ISortAlgorithm {

    private long stepDelay = 2;
    /**
     * Este método implementa o algoritmo de Gnome Sort, consulte
     * <a href="https://en.wikipedia.org/wiki/Gnome_sort">Gnome_sort</a> para entender mais.
     * 
     * Pega um objeto SortArray chamado array e classifica seus elementos de acordo com a teoria matemática
     * do pedido "menor que", consulte <a href="https://en.wikipedia.org/wiki/Order_theory"> Order_theory </a> para
     * entenda mais.
     *
     * @param array Array a ser ordenado
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        int index = 0;
        while (index < array.arraySize()) {
            if (index == 0) {
                index++;
            }
            if (array.getValue(index) >= array.getValue(index - 1)) {
                index++;
            } else {
                array.muda(index, index - 1, getDelay(), true);
                index--;
            }
        }
    }

    @Override
    public String getName() {
        return "Gnome sort";
    }

    @Override
    public long getDelay() {
        return stepDelay;
    }

    @Override
    public void setDelay(long delay) {
        this.stepDelay = delay;
    }
}

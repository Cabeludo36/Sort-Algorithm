package algorithms;

public class InsertionSort implements ISortAlgoritimo {

    private long passoDelay = 1;
    /**
     * Este método implementa o algoritmo de classificação por inserção,
     * consulte <a href="https://en.wikipedia.org/wiki/Insertion_sort">Insertion_sort</a> para
     * entender mais.
     * 
     * Pega um objeto SortArray chamado array e classifica seus elementos de acordo com
     * a teoria matemática da ordem "menor que", consulte <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a> 
     * para entenda mais.
     *
     * @param array Array a ser ordenado
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        for (int i = 0; i < array.arraySize(); i++) {
            int key = array.getValue(i);
            int j = i - 1;
            while (j >= 0 && array.getValue(j) > key) {
                array.updateSingle(j + 1, array.getValue(j), 5, true);
                j--;
            }
            array.updateSingle(j + 1, key, getDelay(), true);
        }
    }

    @Override
    public String getName() {
        return "Insertion Sort";
    }

    @Override
    public long getDelay() {
        return passoDelay;
    }

    @Override
    public void setDelay(long delay) {
        this.passoDelay = delay;
    }
}

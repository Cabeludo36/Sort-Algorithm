package algorithms;

public class SelectionSort implements ISortAlgorithm {

    private long stepDelay = 120;
    /**
     * Este método implementa o algoritmo de classificação de seleção, consulte
     * <a href="https://en.wikipedia.org/wiki/Selection_sort">Selection_sort</a> para entender mais.
     * 
     * Pega um objeto SortArray chamado array e classifica seus elementos de acordo 
     * com a teoria matemática da ordem "menor que", consulte <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a> 
     * para entender mais.
     *
     * @param array Array a ser ordenado
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        int len = array.arraySize();
        for (int i = 0; i < len - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < len; j++) {
                if (array.getValue(j) < array.getValue(minIndex)) {
                    minIndex = j;
                }
            }
            array.muda(i, minIndex, getDelay(), true);
        }
    }

    @Override
    public String getName() {
        return "Selection Sort";
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

package algorithms;

public class BubbleSort implements ISortAlgoritimo {

    private long passoDelay = 2;
    /**
     * Este método implementa o algoritmo de classificação de bolhas,
     * consulte <a href="https://en.wikipedia.org/wiki/Bubble_sort">Bubble_sort</a> 
     * para entender mais.
     * Pega um objeto SortArray chamado array e classifica seus elementos
     *  de acordo com a teoria matemática da ordem "menor que", consulte 
     * <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a> 
     * para entenda mais.
     *
     * @param array Array a ser ordenado
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        int len = array.arraySize();
        for (int i = 0; i < len - 1; i++) {
            for (int j = 0; j < len - i - 1; j++) {
                if (array.getValue(j) > array.getValue(j + 1)) {
                    array.muda(j, j + 1, getDelay(), true);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Bubble Sort";
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
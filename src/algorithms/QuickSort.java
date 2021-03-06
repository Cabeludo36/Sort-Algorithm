package algorithms;

public class QuickSort implements ISortAlgoritimo {

    private long passoDelay = 30;
    /**
     * É aqui que esta a magia do Quick Sort. 
     * Encontra o pivo do array
     *
     * @param array Array a ser cortado e mesclado
     * @param lowIndex Index a esquerda do array
     * @param highIndex Index a direita do array
     * @see SortArray
     */
    private int findPivotPoint(SortArray array, int lowIndex, int highIndex) {
        int pivoValor = array.getValue(highIndex);
        int i = lowIndex - 1;
        for (int j = lowIndex; j <= highIndex - 1; j++) {
            if (array.getValue(j) <= pivoValor) {
                i++;
                array.muda(i, j, getDelay(), true);
            }
        }
        array.muda(i + 1, highIndex, getDelay(), true);
        return i + 1;
    }

    /**
     * Esse é o ponto central do Quick Sort
     *
     * @param array Array a ser cortado e mesclado
     * @param lowIndex Index a esquerda do array 
     * @param highIndex  Index a direita do array
     * @see SortArray
     */
    private void quickSort(SortArray array, int lowIndex, int highIndex) {
        if (lowIndex < highIndex) {
            int pivotPoint = findPivotPoint(array, lowIndex, highIndex);
            quickSort(array, lowIndex, pivotPoint - 1);
            quickSort(array, pivotPoint + 1, highIndex);
        }
    }

    /**
     * Este é o método que chama a primeira instância do quickSort, consulte
     * <a href="https://en.wikipedia.org/wiki/Quicksort">Quicksort</a>
     * para entender mais.
     * 
     * O método pega um objeto SortArray chamado array e classifica seus 
     * elementos de acordo com a teoria matemática da ordem "menor que", consulte
     * <a href="https://en.wikipedia.org/wiki/Order_theory">Order_theory</a> 
     * para entender mais.
     * 
     * @param array Array a ser ordenado
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        quickSort(array, 0, array.arraySize() - 1);
    }

    @Override
    public String getName() {
        return "Quick Sort";
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

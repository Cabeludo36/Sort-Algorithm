package algorithms;

public class MergeSort implements ISortAlgorithm {

    private long stepDelay = 20;
    /**
     * Retorna uma subsequência da matriz retirada da entrada. 
     * A matriz original é cortada a partir da posição inicial 
     * indicada pelo parâmetro homônimo até a posição (início + tamanho).
     *
     * @param array Array a ser cortado
     * @param inicio Posição inicial da subsequência
     * @param size  Tamanho da subsequência
     * @return A subsequência do array
     * @see SortArray
     */
    private int[] getSubArray(SortArray array, int inicio, int size) {
        int arr[] = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = array.getValue(inicio + i);
        }
        return arr;
    }

    /**
     * Este é o núcleo do algoritmo de Merge Sort, 
     * pegue o array da entrada e faça as coisas de cortar e mesclar.
     *
     * @param array  Array a ser cortado e mesclado
     * @param esq   Index da esquerda do array
     * @param meio Index do meio do array 
     * @param dire  Index da direita do array
     * @see SortArray
     */
    private void merge(SortArray array, int esq, int meio, int dire) {
        int esqSize = meio - esq + 1;
        int direSize = dire - meio;

        int esqArray[] = getSubArray(array, esq, esqSize);
        int direArray[] = getSubArray(array, meio + 1, direSize);

        int i = 0, j = 0, k = esq;
        while (i < esqSize && j < direSize) {
            if (esqArray[i] <= direArray[j]) {
                array.updateSingle(k, esqArray[i], getDelay(), true);
                i++;
            } else {
                array.updateSingle(k, direArray[j], getDelay(), true);
                j++;
            }
            k++;
        }

        while (i < esqSize) {
            array.updateSingle(k, esqArray[i], getDelay(), true);
            i++;
            k++;
        }

        while (j < direSize) {
            array.updateSingle(k, direArray[j], getDelay(), true);
            j++;
            k++;
        }
    }

    /**
     * O Merge Sort é um algoritmo de "divisão e conquista" que funciona 
     * dividindo a matriz em pequenas seções, classificando-as individualmente e, finalmente, 
     * mesclando-as novamente, consulte <a href = "https://en.wikipedia.org/wiki/Merge_sort"> Merge_sort </a> 
     * para entender mais.
     * 
     * O método pega um objeto SortArray chamado array e classifica seus elementos de acordo com a teoria 
     * matemática da ordem "menor que", consulte <a href="https://en.wikipedia.org/wiki/Order_theory"> Order_theory </a>
     * para entender mais.
     * 
     * A recursão foi adotada para simplificar
     *
     * @param array Array a ser ordenado 
     * @param esq  Index a esquerda do array
     * @param dire Index a direita do array 
     * @see SortArray
     */
    private void mergeSort(SortArray array, int esq, int dire) {
        if (esq < dire) {
            int meioIndex = (esq + dire) / 2;

            mergeSort(array, esq, meioIndex);
            mergeSort(array, meioIndex + 1, dire);
            merge(array, esq, meioIndex, dire);
        }
    }

    /**
     * Este é o método que chama a primeira instância de mergeSort.
     * 
     * @param array Array a ser ordenado
     * @see SortArray
     */
    @Override
    public void runSort(SortArray array) {
        int esq = 0;
        int dire = array.arraySize() - 1;
        mergeSort(array, esq, dire);
    }

    @Override
    public String getName() {
        return "Merge Sort";
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

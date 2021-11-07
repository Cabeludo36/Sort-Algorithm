package algorithms;

import Util.Util;

public class PancakeSort implements ISortAlgorithm {
    private long passoDelay = 1;
    /**
     * O Pancake Sort é o problema matemático 
     * de classificar uma pilha desordenada de panquecas em 
     * ordem de tamanho quando uma espátula pode ser inserida 
     * em qualquer ponto da pilha e usada para virar todas as 
     * panquecas acima dela.
     * 
     * Veja mais em <a href="https://en.wikipedia.org/wiki/Pancake_sorting">Pancake Sort</a>
     * 
     * @param array Arrat a ser ordenado
     */
    private void flip(SortArray array, int i) {
        for (int j = 0; j < i; j++, i--) {
            array.muda(i, j, getDelay(), true);
        }
    }

    @Override
    public void runSort(SortArray array) {
        for (int i = array.arraySize(); i > 1; i--) {
            int maxValorIndex = Util.findMaxValueIndex(array, i);
            if (maxValorIndex != i - 1) {
                flip(array, maxValorIndex);
                flip(array, i - 1);
            }
        }
    }

    @Override
    public String getName() {
        return "Pancake sort";
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
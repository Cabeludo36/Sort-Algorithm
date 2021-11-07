package algorithms;

public class CycleSort implements ISortAlgorithm {

    private long passoDelay = 125;
    private class CycleResult {
        public int posicao;
        public boolean cont;

        public CycleResult(int retValor, boolean deveContinuar) {
            posicao = retValor;
            cont = deveContinuar;

        }
    }
    
    private CycleResult doCycle(int inicio, int posicao, int valor, SortArray array, boolean podeFinalizarAntes) {
        posicao = inicio;
        for (int i = inicio + 1; i < array.arraySize(); i++) {
            if (array.getValue(i) < valor) {
                posicao++;
            }
        }
        if ((posicao == inicio) && podeFinalizarAntes) return new CycleResult(0, true);

        while (valor == array.getValue(posicao)) {
            posicao++;
        }
        return new CycleResult(posicao, false);
    }

    @Override
    public void runSort(SortArray array) {
        int n = array.arraySize();
        for (int inicio = 0; inicio <= n - 2; inicio++) {
            int valor = array.getValue(inicio);
            int posicao = inicio;
            CycleResult result = doCycle(inicio, posicao, valor, array, true);
            if (result.cont) continue;
            posicao = result.posicao;
            if (posicao != inicio) {
                int t = valor;
                valor = array.getValue(posicao);
                array.updateSingle(posicao, t, getDelay(), true);
            }
            while (posicao != inicio) {
                posicao = doCycle(inicio, posicao, valor, array, false).posicao;
                if (valor != array.getValue(posicao)) {
                    int t = valor;
                    valor = array.getValue(posicao);
                    array.updateSingle(posicao, t, getDelay(), true);
                }
            }
        }
    }

    @Override
    public String getName() {
        return "Cycle sort";
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
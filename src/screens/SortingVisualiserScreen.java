package screens;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.SwingWorker;

import algorithms.ISortAlgoritimo;
import algorithms.SortArray;
import main.MainApp;

/**
 * Main class do sort visualiser GUI
 */
public final class SortingVisualiserScreen extends Screen {
    
    private final SortArray sortArray;
    private final ArrayList<ISortAlgoritimo> sortQueue;

    /**
     * Cria GUI
     * @param algoritimos Lista de algoritimos para visualização
     * @param playSounds Se vai ou não tocar sons
     * @param app Aplicação main
     */
    public SortingVisualiserScreen(ArrayList<ISortAlgoritimo> algoritimos, boolean playSounds, MainApp app) {
        super(app);
        setLayout(new BorderLayout());
        sortArray = new SortArray(playSounds);
        add(sortArray, BorderLayout.CENTER);
        sortQueue = algoritimos;
    }
    
    private void longSleep() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        } 
    }

    private void shuffleAndWait() {
        sortArray.shuffle();
        sortArray.resetColours();
        longSleep();
    }
    
    public void onOpen() {
        // isso iria bloquear o EventDispatchThread, então
        // deve rodar em uma thread
        SwingWorker<Void, Void> swingWorker = new SwingWorker<Void,Void>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    Thread.sleep(250);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                } 
                for (ISortAlgoritimo algoritimo : sortQueue) {
                    shuffleAndWait();
                    
                    sortArray.setName(algoritimo.getName());
                    sortArray.setAlgorithm(algoritimo);
        
                    algoritimo.runSort(sortArray);
                    sortArray.resetColours();
                    sortArray.highlightArray();
                    sortArray.resetColours();
                    longSleep();
                }
                return null;
            }
            
            @Override
            public void done() {
                app.popScreen(); 
            }
        };
        
        swingWorker.execute();
    }
}

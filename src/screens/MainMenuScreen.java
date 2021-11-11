package screens;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;

import algorithms.BubbleSort;
import algorithms.CountingSort;
import algorithms.CycleSort;
import algorithms.GnomeSort;
import algorithms.HeapSort;
import algorithms.ISortAlgoritimo;
import algorithms.InsertionSort;
import algorithms.IterativeMergeSort;
import algorithms.MergeSort;
import algorithms.PancakeSort;
import algorithms.QuickSort;
import algorithms.RadixSort;
import algorithms.SelectionSort;
import algorithms.StoogeSort;
import main.MainApp;

public final class MainMenuScreen extends Screen {
    
    private static final Color BACKGROUND_COLOUR = Color.DARK_GRAY;
    private final ArrayList<AlgorithmCheckBox> checkBoxes;
    
    public MainMenuScreen(MainApp app) {
        super(app);
        checkBoxes = new ArrayList<>();
        setUpGUI();
    }
    
    private void addCheckBox(ISortAlgoritimo algorithm, JPanel panel) {
        JCheckBox box = new JCheckBox("", true);
        box.setAlignmentX(Component.LEFT_ALIGNMENT);
        box.setBackground(BACKGROUND_COLOUR);
        box.setForeground(Color.WHITE);
        checkBoxes.add(new AlgorithmCheckBox(algorithm, box));
        panel.add(box);
    }
    
    private void initContainer(JPanel p) {
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBackground(BACKGROUND_COLOUR);
        //p.setBorder(BorderFactory.createLineBorder(Color.WHITE));
    }
    
    public void setUpGUI() {
        JPanel sortAlgorithmContainer = new JPanel();
        JPanel optionsContainer = new JPanel();
        JPanel outerContainer = new JPanel();
        initContainer(this);
        initContainer(optionsContainer);
        initContainer(sortAlgorithmContainer);
        
        outerContainer.setBackground(BACKGROUND_COLOUR);
        outerContainer.setLayout(new BoxLayout(outerContainer, BoxLayout.LINE_AXIS));
        
        sortAlgorithmContainer.setAlignmentX(Component.CENTER_ALIGNMENT);
        addCheckBox(new BubbleSort(),       sortAlgorithmContainer);
        addCheckBox(new SelectionSort(),    sortAlgorithmContainer);
        addCheckBox(new CycleSort(),        sortAlgorithmContainer);
        addCheckBox(new StoogeSort(),       sortAlgorithmContainer);
        addCheckBox(new QuickSort(),        sortAlgorithmContainer);
        addCheckBox(new PancakeSort(),      sortAlgorithmContainer);
        addCheckBox(new MergeSort(),        sortAlgorithmContainer);
        addCheckBox(new InsertionSort(),    sortAlgorithmContainer);
        addCheckBox(new HeapSort(),         sortAlgorithmContainer);
        addCheckBox(new GnomeSort(),        sortAlgorithmContainer);
        addCheckBox(new CountingSort(),     sortAlgorithmContainer);
        addCheckBox(new RadixSort(),        sortAlgorithmContainer);
        addCheckBox(new IterativeMergeSort(), sortAlgorithmContainer);
        
        JCheckBox soundCheckBox = new JCheckBox("Tocar Sons");
        soundCheckBox.setAlignmentX(Component.LEFT_ALIGNMENT);
        soundCheckBox.setBackground(BACKGROUND_COLOUR);
        soundCheckBox.setForeground(Color.WHITE);
        
        optionsContainer.add(soundCheckBox);
       
        JButton startButton = new JButton("Começar Ordenação Vizual");
        startButton.addActionListener((ActionEvent e) -> {
            ArrayList<ISortAlgoritimo> algorithms = new ArrayList<>();
            for (AlgorithmCheckBox cb : checkBoxes) {
                if (cb.isSelected()) {
                    algorithms.add(cb.getAlgorithm());
                }
            }
            app.pushScreen(
                new SortingVisualiserScreen(
                            algorithms, 
                            soundCheckBox.isSelected(), 
                            app
                        ));
        });
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        outerContainer.add(optionsContainer);
        outerContainer.add(Box.createRigidArea(new Dimension(5,0)));
        outerContainer.add(sortAlgorithmContainer);
        
        int gap = 15;
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(outerContainer);
        add(Box.createRigidArea(new Dimension(0, gap)));
        add(startButton);
    }

    @Override
    public void onOpen() {
        checkBoxes.forEach((box) -> {
            box.unselect();
            
        });

    }
    
    private class AlgorithmCheckBox {
        private final ISortAlgoritimo algorithm;
        private final JCheckBox box;
        
        public AlgorithmCheckBox(ISortAlgoritimo algorithm, JCheckBox box) {
            this.algorithm = algorithm;
            this.box = box;
            this.box.setText(algorithm.getName());
        }
        
        public void unselect() {
            box.setSelected(false);
        }
     
        
        public boolean isSelected() {
            return box.isSelected();
        }
        
        public ISortAlgoritimo getAlgorithm() {
            return algorithm;
        }
    }
    
}

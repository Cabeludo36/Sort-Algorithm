package algorithms;

import java.awt.AlphaComposite;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

import Util.LeitorImg;
import main.MidiSoundPlayer;

public class SortArray extends JPanel {
    
    public static final int DEFAULT_WIN_WIDTH = 1280;
    public static final int DEFAULT_WIN_HEIGHT = 720;
    // tse sem imagens
    //private static final int DEFAULT_BAR_WIDTH = 5;
    LeitorImg li = new LeitorImg("img");
    String[] imgNomes = li.caminhoDiretorio.list(li.filtradorJPG);
    /**
     * NOTE(BRENO): Esta é a porcentagem do painel que as barras irão consumir.
     * NOTE       | Com base nAs 256 barras originais, cada uma tendo 2x sua altura
     * NOTE       | e altura da janela de 720px ou 512/720
     */
    private static final double BAR_HEIGHT_PERCENT = 512.0/720.0;
    // para criação do array sem as imagens
    //private static final int NUM_BARS = DEFAULT_WIN_WIDTH / DEFAULT_BAR_WIDTH;
    private final int NUM_BARS = imgNomes.length;
    
    private final int[] array;
    private final int[] barCors;
    // private int spinnerValue = 0;
    private String algoritimoNome = "";
    private ISortAlgoritimo algoritimo;
    private long algoritimoDelay = 0;
    
    private MidiSoundPlayer player;
    private JSpinner spinner;
    private boolean playSounds;

    private int arrayMuda = 0; // * Número de mudanças no array que o algoritmo atual realizou até agora

    public SortArray(boolean playSounds) {
        
        setBackground(Color.DARK_GRAY);
        array = new int[NUM_BARS];
        barCors = new int[NUM_BARS];
        // Cria array
        for (int i = 0; i < NUM_BARS; i++) {
            array[i] = li.leVerde(imgNomes[i]);
            barCors[i] = 0;
        }
        
        // cria array sem as imagens
        /* for (int i = 0; i < NUM_BARS; i++) {
            array[i] = i;
            barCors[i] = 0;
        } */
        
        player = new MidiSoundPlayer(2147483647);
        this.playSounds = playSounds;
        spinner = new JSpinner(new SpinnerNumberModel(1, 1, 1000, 1));
        spinner.addChangeListener((event) -> {
            algoritimoDelay = (Integer) spinner.getValue();
            algoritimo.setDelay(algoritimoDelay);
        });
        add(spinner, BorderLayout.LINE_START);
    }

    public int arraySize() {
        return array.length;
    }

    public int getValue(int index) {
        return array[index];
    }

    /**
     * Obtém o valor máximo da array ou Integer.MIN_VALUE se não houver um.
     * @return Valor máximo ou Integer.MIN_VALUE.
     */
    public int getValorMax() {
    	return Arrays.stream(array).max().orElse(Integer.MIN_VALUE);
    }
    
    private void finaliseUpdate(int value, long millisecondDelay, boolean isStep) {
        repaint();
        try {
            Thread.sleep(millisecondDelay);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
        if (playSounds) {
            player.makeSound(value);
        }
        if (isStep) 
            arrayMuda++;
    }

    public void muda(int primeiroIndex, int segundoIndex, long milisegundoDelay, boolean ePasso) {
        int temp = array[primeiroIndex];
        array[primeiroIndex] = array[segundoIndex];
        array[segundoIndex] = temp;

        barCors[primeiroIndex] = 100;
        barCors[segundoIndex] = 100;

        finaliseUpdate((array[primeiroIndex] + array[segundoIndex]) / 2, milisegundoDelay, ePasso);
    }

    public void updateSingle(int index, int valor, long milisegundosDelay, boolean ePasso) {
        array[index] = valor;
        barCors[index] = 100;
       

        finaliseUpdate(valor, milisegundosDelay, ePasso);
        repaint();
    }

    public void shuffle() {
        arrayMuda = 0;
        Random rng = new Random();
        for (int i = 0; i < arraySize(); i++) {
            int mudaComIndex = rng.nextInt(arraySize() - 1);
            muda(i, mudaComIndex, 5, false);
        }
        arrayMuda = 0;
    }

    public void highlightArray() {
        for (int i = 0; i < arraySize(); i++) {
            updateSingle(i, getValue(i), 5, false);
        }
    }

    /**
     * Obtém o tamanho do canvas
     *
     * @return Tamanho
     */
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(DEFAULT_WIN_WIDTH, DEFAULT_WIN_HEIGHT);
    }

    public void resetColours() {
        for (int i = 0; i < NUM_BARS; i++) {
            barCors[i] = 0;
        }
        repaint();
    }

    /**
     * Desenha array
     *
     * @param g Dispositivo gráfico para desenho
     */
    @Override
    public void paintComponent(Graphics g) {
    	super.paintComponent(g);
        Graphics2D panelGrafico = (Graphics2D) g.create();

		try
		{
			Map<RenderingHints.Key, Object> renderingHints = new HashMap<>();
			renderingHints.put(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			panelGrafico.addRenderingHints(renderingHints);
			panelGrafico.setColor(Color.WHITE);
			panelGrafico.setFont(new Font("Monospaced", Font.BOLD, 20));
			panelGrafico.drawString("  Algoritimo Atual: " + algoritimoNome, 10, 30);
			panelGrafico.drawString("   Delay de Passos: " + algoritimoDelay + "ms", 10, 55);
			panelGrafico.drawString(" Total de mudanças: " + arrayMuda, 10, 80);

			drawBars(panelGrafico);
		} finally {
        	panelGrafico.dispose();
        }
    }

	private void drawBars(Graphics2D panelGraphics)
	{
		int barLargura = getWidth() / NUM_BARS;
		int bufferedImageLargura = barLargura * NUM_BARS;
		int bufferedImageAltura = getHeight();
        
		if(bufferedImageAltura > 0 && bufferedImageLargura > 0) {
			if(bufferedImageLargura < 256) {
				bufferedImageLargura = 256;
			}
			
			double maxValor = getValorMax();
		
			BufferedImage bufferedImagem = new BufferedImage(bufferedImageLargura, bufferedImageAltura, BufferedImage.TYPE_INT_ARGB);
			makeBufferedImageTransparent(bufferedImagem);
			Graphics2D bufferedGraficos = null;
			try
			{
				bufferedGraficos = bufferedImagem.createGraphics();
				
				for (int x = 0; x < NUM_BARS; x++) {
					double atualValue = getValue(x);
					double percentualDoMax = atualValue / maxValor;
					double alturaPercentualDoPanel = percentualDoMax * BAR_HEIGHT_PERCENT;
					int altura = (int) (alturaPercentualDoPanel * (double) getHeight());
					int xInicio = x + (barLargura - 1) * x;
					int yInicio = getHeight() - altura;
					
					int val = barCors[x] * 2;
					if (val > 190) {
						bufferedGraficos.setColor(new Color(255 - val, 255, 255 - val));
					}
					else {
						bufferedGraficos.setColor(new Color(255, 255 - val, 255 - val));
					}
					bufferedGraficos.fillRect(xInicio, yInicio, barLargura, altura);
					if (barCors[x] > 0) {
						barCors[x] -= 5;
					}
				}
			}
			finally
			{
				if(bufferedGraficos != null)
				{
					bufferedGraficos.dispose();
				}
			}
			
			panelGraphics.drawImage(bufferedImagem, 0, 0, getWidth(), getHeight(), 0, 0, bufferedImagem.getWidth(), bufferedImagem.getHeight(), null);
		}
	}
	
    private void makeBufferedImageTransparent(BufferedImage image)
    {
    	Graphics2D bufferedGraficos = null;
		try
		{
			bufferedGraficos = image.createGraphics();
			
			bufferedGraficos.setComposite(AlphaComposite.getInstance(AlphaComposite.CLEAR));
			bufferedGraficos.fillRect(0, 0, image.getWidth(), image.getHeight());
			bufferedGraficos.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));
		}
		finally
		{
			if(bufferedGraficos != null)
			{
				bufferedGraficos.dispose();
			}
		}
    }
    
    @Override
    public void setName(String algoritimoNome) {
        this.algoritimoNome = algoritimoNome;
    }
    
    public void setAlgorithm(ISortAlgoritimo algoritimo) {
        this.algoritimo = algoritimo;
        algoritimoDelay = algoritimo.getDelay();
        spinner.setValue((int) algoritimo.getDelay());
    }
    public long getAlgorithmDelay(){
        return algoritimoDelay;
    }
}

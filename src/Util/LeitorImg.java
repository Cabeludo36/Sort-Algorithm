package Util;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.Color;

public class LeitorImg {
    public File caminhoDiretorio;
    public FilenameFilter filtradorJPG;
    public FilenameFilter filtradorPNG;

    public LeitorImg(String diretorio) {
        caminhoDiretorio = new File(diretorio);
        //cria filtro para imgs .jpg
        filtradorJPG = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".jpg")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        
        //cria filtro para imgs .png
        filtradorPNG = new FilenameFilter() {
            public boolean accept(File dir, String name) {
                String lowercaseName = name.toLowerCase();
                if (lowercaseName.endsWith(".png")) {
                    return true;
                } else {
                    return false;
                }
            }
        };
    }

    public int leVerde(String nomeImg) {
        File f = new File(caminhoDiretorio+"\\"+nomeImg);
        BufferedImage img = null;
        try {
            img = ImageIO.read(f);
        } catch (IOException e) {
            e.printStackTrace();
        }
        int count = 0;
        for (int y = 0; y < img.getHeight(); y++) {
            for (int x = 0; x < img.getWidth(); x++) {
               //pega cor de cada pixel
               int pixel = img.getRGB(x,y);
               //cria um obj Color
               Color color = new Color(pixel, true);
               //separa em R G B
               int r = color.getRed();
               int g = color.getGreen();
               int b = color.getBlue();

               if (g - 20 > r && g - 20 > b) {
                   count++;
               }
            }
        }
        return count;
    }
}
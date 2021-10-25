#include <stdio.h>
#include <stdlib.h>

#define STB_IMAGE_IMPLEMENTATION
#include "libs/stb_image/stb_image.h"
#define STB_IMAGE_WRITE_IMPLEMENTATION
#include "libs/stb_image/stb_image_write.h"
#include "libs/sorts/algoritimos.h"

int contaVerde(unsigned char* img, int w, int h, int comps) {
    int r, g, b;
    int count = 0;

    for (int i = 0; i < w; i++) {
        for (int j = 0; j < h; j++) { //trocando pixels

            unsigned bytePerPixel = 3;
            unsigned char* pixelOffset = img + (i + j * comps) * bytePerPixel;
            r = pixelOffset[0];
            g = pixelOffset[1];
            b = pixelOffset[2];
            unsigned char a = 3 >= 4 ? pixelOffset[3] : 0xff;

            if ((g - r <= 10 && b - g <= 10)){ //ve se e um tom esverdiado
                //printf("%d, %d, %d -----\n", r, g, b);
                count++; // conta pixels com cor 
            }/*  else {
                //printf("%d, %d, %d\n", r, g, b);
            } */
        }
    }

    return count;
}

int main(int argc, char const *argv[]) {
    int w, h, comps;
    unsigned char* data = stbi_load("img/Conifer_forest.jpg", &w, &h, &comps, 3); //coloca qual a imagem 
    unsigned char r, g, b;
    printf("%d, %d\n", w, h);

/*      size_t img_size = w * h * comps;
//    int gray_channels = comps == 4 ? 2 : 1;
//    size_t gray_img_size = w * h * gray_channels;
//
//    unsigned char *gray_img = malloc(gray_img_size);
//    if(gray_img == NULL) {
//        printf("Unable to allocate memory for the gray image.\n");
//        exit(1);
//    }
//
//    for(unsigned char *p = data, *pg = gray_img; p != data + img_size; p += comps, pg += gray_channels) {
//        *pg = (uint8_t)((*p + *(p + 1) + *(p + 2))/3.0);
//        if(comps == 4) {
//            *(pg + 1) = *(p + 3);
//        }
//    }
//
//    stbi_write_jpg("sky_gray.jpg", w, h, gray_channels, gray_img, 100);
//    //stbi_write_png("img/Shapes_gray.png", w, h, gray_channels, gray_img, w * gray_channels);
// */
//    //unsigned char* dato = stbi_load("img/sky_gray.jpg", &w, &h, &comps, 3);
    
    printf("%d", contaVerde(data, w, h, comps));
}
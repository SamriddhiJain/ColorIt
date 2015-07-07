package com.example.samriddhi.colorit;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.widget.Toast;

/**
 * Created by samriddhi on 28/3/15.
 */


public class ColorC {

    private Bitmap image;
    public Bitmap Timage;
    private int width;
    private int height;
    private int key;
    private double[][] rgb2lms={{17.8824,43.5161,4.1193},
            {3.4557,27.1554,3.8671},
            {0.02996,0.18431,1.4670}
    };

    private double[][] lmsp={
            {0,2.02344,-2.52581},
            {0,1,0},
            {0,0,1}
    };

    private double[][] lmsd={
            {1.5,0,0},
            {0.7413105,0,1.872405},
            {0,0,1.5}
    };

    private double[][] lmst={
            {1,0,0},
            {0,1,0},
            {-0.395913,0.801109,0}
    };

    private double[][] lms2rgb={{0.0809,-0.1305,0.1167},
            {-0.0102,0.0540,-0.1136},
            {-0.0003,-0.0041,0.6935}
    };

    private double[][] error={{0,0,0},
            {0.7,1,0},
            {0.7,0,1}
    };

    public ColorC(Bitmap testImage,int x) {
        image=testImage;
        width=image.getWidth();
        height=image.getHeight();
        key=x;
    }

    public Bitmap colorCover(){
        Timage = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //Timage.setHasAlpha(true);
        for(int i=0;i<width;i++){
            for(int j=0;j<height;j++){

                int color=image.getPixel(i,j);

                int red=Color.red(color);
                int green=Color.green(color);
                int blue= Color.blue(color);

                double[][] rgb={{red},
                        {green},
                        {blue}
                };

                double[][] blindCover = null;

                if(key==0){
                    blindCover= lms2rgb(lmsp(rgb2lms(rgb)));
                }else if(key==1){
                    blindCover= lms2rgb(lmsd(rgb2lms(rgb)));
                }else if(key==2){
                    blindCover= lms2rgb(lmst(rgb2lms(rgb)));
                }

                double[][] err=solveError(blindCover,rgb);
                double[][] finalVec=add(rgb,3,1,err,3,1);

                int red2=(int)finalVec[0][0];
                int green2=(int)finalVec[1][0];
                int blue2=(int)finalVec[2][0];

                /*int red2=(int)blindCover[0][0];
                int green2=(int)blindCover[1][0];
                int blue2=(int)blindCover[2][0];*/


                int newColor=Color.rgb(red2, green2, blue2);
                //Toast.makeText(getApplicationContext(), red2 + green2 +blue2, Toast.LENGTH_LONG).show();

                Timage.setPixel(i, j, newColor);

            }
        }

        return Timage;

    }

    public double[][] rgb2lms(double[][] x){
        return multiply(rgb2lms,3,3,x,3,1);
    }

    public double[][] lmsp(double[][] x){
        return multiply(lmsp,3,3,x,3,1);
    }

    public double[][] lmsd(double[][] x){
        return multiply(lmsd,3,3,x,3,1);
    }

    public double[][] lmst(double[][] x){
        return multiply(lmst,3,3,x,3,1);
    }

    public double[][] lms2rgb(double[][] x){
        return multiply(lms2rgb,3,3,x,3,1);
    }

    public double[][] solveError(double[][] x,double[][] y){
        double[][] z=new double[3][1];
        z=multiply(error,3,3,subtract(x,3,1,y,3,1),3,1);

        return z;
    }

    public int convertRGB(double x){

        int value=0;

        if(x>=0 && x<=255 ){
            value=(int)x;
        }else if(x<0){
            value=0;
        }else{
            value=255;
        }

        return value;
    }

    public double[][] multiply(double[][] mat1,int row1,int col1,double[][] mat2,int row2,int col2){

        double[][] product=new double[row1][col2];

        for(int i=0;i<row1;i++){
            for(int j=0;j<col2;j++){
                for(int k=0;k<col1;k++){
                    product[i][j]+= mat1[i][k]*mat2[k][j];
                }
            }
        }
        return product;
    }

    public double[][] subtract(double[][] mat1,int row1,int col1,double[][] mat2,int row2,int col2){

        double[][] diff=new double[row1][col2];
        for(int i=0;i<row1;i++){
            for(int j=0;j<col2;j++){
                diff[i][j]=mat1[i][j]-mat2[i][j];
            }
        }
        return diff;
    }

    public double[][] add(double[][] mat1,int row1,int col1,double[][] mat2,int row2,int col2){

        double[][] diff=new double[row1][col2];
        for(int i=0;i<row1;i++){
            for(int j=0;j<col2;j++){
                diff[i][j]=mat1[i][j]+mat2[i][j];
            }
        }
        return diff;
    }

}


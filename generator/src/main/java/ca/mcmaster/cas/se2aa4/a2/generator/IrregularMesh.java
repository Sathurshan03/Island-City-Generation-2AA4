package ca.mcmaster.cas.se2aa4.a2.generator;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class IrregularMesh extends MeshADT {


    public IrregularMesh(int width, int height, int precision){
        super(width,height, precision);
        centroids=new ArrayList<>();
        vertices=new ArrayList<>();
        segments=new ArrayList<>();
        createCentroids();

        for (int i=0; i<centroids.size(); i++){
            CustomPolygon polygon=new CustomPolygon(i, precision);
            addPolygon(polygon.gePolygon());
        }


    }

    public void createCentroids(){
        Random rand=new Random();

        for (int i=0; i<500; i++){
            double random_x= rand.nextDouble(0,width);
            double random_y=rand.nextDouble(0,height);

            CustomVertex new_centroid=new CustomVertex(random_x,random_y,new Color(254,0,0,254), "2.0", precision);

            centroids.add(new_centroid);

        }

    }

}

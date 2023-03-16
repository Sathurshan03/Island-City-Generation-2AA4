package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.Biomes.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;

import java.awt.*;
import java.io.IOException;

public class Heatmaps extends Regular{


    public Heatmaps(String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType, BiomeTypes biome, int maxLakes, int maxRivers) throws IOException {
        super(inputMesh, outputMesh, shapeType, altitudeType, biome, maxLakes, maxRivers);
        super.generate();
        setProperties();
    }
    public void setProperties(){
        for (Tile t: tiles){
            if (t.isTileLand()){
                Double col=t.getAverageTemperature();
                Color new_color;
                if (col>=0){
                    new_color=new Color(255, 0,0,(int)Math.abs(col)*3);
                }else{
                    new_color=new Color(0, 0,255,(int)Math.abs(col));

                }
                t.setPolygonColor(new_color);
            }else{
                t.setPolygonColor(new Color(0,0,0));
            }
        }
    }


}

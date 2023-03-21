package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;

import java.awt.*;
import java.io.IOException;

public class Heatmaps extends Regular{


    public Heatmaps(String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType, BiomeTypes biome, int maxLakes, int maxRivers, SoilTypes soil) throws IOException {
        super(inputMesh, outputMesh, shapeType, altitudeType, biome, maxLakes, maxRivers, soil);
        super.generate();
        setProperties();
    }
    public void setProperties(){
        for (Tile t: tiles){
            if (t.isTileLand()){
                Color new_color;
                Double col=t.getAverageTemperature();

                if (col<=0){
                    if (col<-254){
                        col=-254.0;
                    }
                    new_color=new Color(0, 0,255,(int)Math.abs(col));
                }else{
                    if (col>254){
                        col=254.0;
                    }
                    new_color=new Color(255, 0,0,(int)Math.abs(3*col));
                }

                t.setPolygonColor(new_color);
            }else{
                t.setPolygonColor(new Color(0,0,0));
            }
        }
    }


}

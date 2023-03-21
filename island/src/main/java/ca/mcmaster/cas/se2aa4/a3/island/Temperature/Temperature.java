package ca.mcmaster.cas.se2aa4.a3.island.Temperature;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.ArrayList;
import java.util.List;

public class Temperature {

    List<Tile> all_tiles;
    Double base_temperature;

    public Temperature(){
        all_tiles=new ArrayList<>();
    }

    public void setTemperature(List<Tile> tiles, double baseTemperature, Double min_elevation){
        this.all_tiles=tiles;
        this.base_temperature= baseTemperature;


        for (Tile t: all_tiles){
            Double average=0.0;
            for (TileVertex v:t.getTileVertices()){
                v.setTemperature(base_temperature-0.03*(v.getElevation()));
                average+=v.getTemperature();
            }
            average/=t.getTileVertices().size();
            t.setAverageTemperature(average);
        }
    }
}
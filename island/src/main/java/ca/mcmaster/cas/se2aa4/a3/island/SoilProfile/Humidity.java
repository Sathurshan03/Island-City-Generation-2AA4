package ca.mcmaster.cas.se2aa4.a3.island.SoilProfile;

import ca.mcmaster.cas.se2aa4.a3.island.BodiesOfWater.BodiesWater;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.util.List;

public class Humidity {

    private Double coefficient;
    private SoilTypes soil;

    public Humidity(SoilTypes soil){
        this.soil=soil;
        this.coefficient=soil.getSoilCoefficient();
    }

    public Double calculateDistance(TileVertex v1, TileVertex v2){
        Double x1=v1.getX();
        Double x2=v2.getX();

        Double y1=v1.getY();
        Double y2=v2.getY();

        Double distance=Math.sqrt(Math.pow((x2-x1),2)+Math.pow((y2-y1),2));

        return distance;

    }

    public void SetHumidity(List<Tile> landTiles, List<BodiesWater> allWater){
        for (Tile tile:landTiles){
            Double averageHumidity=0.0;
            for (BodiesWater water:allWater){
                for (TileVertex v: water.getMidPoints()){
                    Double distance=calculateDistance(tile.getCentroid(),v);
                    Double humidity=(water.getHumidityLevel()*coefficient)/distance;
                    v.setHumidity(humidity);
                    averageHumidity+=humidity;
                }
            }
            averageHumidity/=allWater.size();
            tile.setAverageHumidity(averageHumidity);
        }

    }


}

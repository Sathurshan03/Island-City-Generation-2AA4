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

        return x1;

    }

    public void SetHumidity(List<Tile> landTiles, List<BodiesWater> allWater){
        for (Tile tile:landTiles){
            for (BodiesWater water:allWater){
                Double humidity=0.0;
                for (TileVertex v: water.getMidPoints()){
                    Double distance=calculateDistance(tile.getCentroid(),v);

                }

            }
        }

    }


}

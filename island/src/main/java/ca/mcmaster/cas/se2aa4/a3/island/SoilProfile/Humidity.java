package ca.mcmaster.cas.se2aa4.a3.island.SoilProfile;

import ca.mcmaster.cas.se2aa4.a3.island.BodiesOfWater.BodiesWater;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;

import java.awt.*;
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
                    Double humidity=(2*water.getHumidityLevel()*coefficient)/(distance+1);
                    averageHumidity+=humidity;
                }
            }
            tile.setAverageHumidity(averageHumidity);

            Color c_new=newColor(tile.getColor(), averageHumidity/2);

            tile.setPolygonColor(c_new);
        }

    }

    private Color newColor(Color c, Double averageHumidity){
        int new_red=(int)Math.round(c.getRed()-averageHumidity);

        if (new_red<0){
            new_red=0;
        }

        int new_blue=(int)Math.round(c.getBlue()-averageHumidity);
        if (new_blue<0){
            new_blue=0;
        }

        int new_green=(int)Math.round(c.getGreen()-averageHumidity);
        if (new_green<0){
            new_green=0;
        }
        return new Color(new_red,new_green,new_blue);

    }


}

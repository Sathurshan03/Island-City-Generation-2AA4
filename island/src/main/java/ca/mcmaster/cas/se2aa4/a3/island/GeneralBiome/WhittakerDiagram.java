package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import java.util.HashMap;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.awt.Shape;

public class WhittakerDiagram {
    protected final double scaleHumidity;
    protected HashMap<Shape, TileTypes> whittakerDiagram;
    protected final double minHumidity;

    public WhittakerDiagram(double maxHumidy, double minHumidity){
        this.scaleHumidity = 100 / maxHumidy; 
        this.minHumidity = minHumidity;
        this.whittakerDiagram = new HashMap<Shape, TileTypes>();
    }

    public void addToWhittakerDiagram(Shape shape, TileTypes biome){
        whittakerDiagram.put(shape, biome);
    }

    public TileTypes getBiome(double humidity, double temperature){
        //Get the biome(tileTypes) based on the whittaker diagram
        for(Shape area: whittakerDiagram.keySet()){
            if (area.contains((humidity - minHumidity)*scaleHumidity , temperature))
            {
                //Returns the TileTypes for that area
                return whittakerDiagram.get(area);
            }
        }

        return TileTypes.GRASSLAND;
    }


    
}

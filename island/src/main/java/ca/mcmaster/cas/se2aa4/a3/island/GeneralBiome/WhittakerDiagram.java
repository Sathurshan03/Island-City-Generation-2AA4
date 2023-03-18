package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import java.util.HashMap;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.awt.Shape;

public class WhittakerDiagram {
    protected final double scaleHumidity;
    protected final double scaleTemperature;
    protected HashMap<Shape, TileTypes> whittakerDiagram;

    public WhittakerDiagram(double maxHumidy, double maxTemperature){
        this.scaleHumidity = maxHumidy / 100;
        this.scaleTemperature = maxTemperature / 100;
        this.whittakerDiagram = new HashMap<Shape, TileTypes>();
    }

    public void addToWhittakerDiagram(Shape shape, TileTypes biome){
        whittakerDiagram.put(shape, biome);
    }

    public TileTypes getBiome(double humidity, double temperature){
        //Get the biome based on the whittaker diagram

        for(Shape area: whittakerDiagram.keySet()){
            if (area.contains(temperature/scaleTemperature, humidity/scaleHumidity))
            {
                return whittakerDiagram.get(area);
            }
        }
        return TileTypes.UNDETERMINEDLAND;
    }


    
}

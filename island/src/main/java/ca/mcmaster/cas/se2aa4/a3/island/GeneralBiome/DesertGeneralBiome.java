package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.awt.*;

public class DesertGeneralBiome extends GeneralBiome{
    WhittakerDiagram desertWhittakerDiagram;

    public DesertGeneralBiome(double getBaseTemperature){
        super(getBaseTemperature);
    }

    public void createWhittakerDiagram(double humidityRange, double minHumidity, double temperatureRange, double minTemperature){
        desertWhittakerDiagram = new WhittakerDiagram(humidityRange, minHumidity, temperatureRange, minTemperature);

        //Shapes altogether cover 0 - 100 on both axis
        desertWhittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, 30,71,71), TileTypes.SAND);
        desertWhittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, -1,71,32), TileTypes.DIRT);
        desertWhittakerDiagram.addToWhittakerDiagram(new Rectangle(70, 60,32,41), TileTypes.SAVANNA);
    }

    public TileTypes getTileBiome(double humidity, double temperature){
        return desertWhittakerDiagram.getBiome(humidity, temperature);
    }
}

package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

import java.awt.*;

public class TemperateForestGeneralBiome extends GeneralBiome{
    WhittakerDiagram temperateWhittakerDiagram;

    public TemperateForestGeneralBiome(double getBaseTemperature){
        super(getBaseTemperature);
    }

    public void createWhittakerDiagram(double humidityRange, double minHumidity, double temperatureRange, double minTemperature){
        temperateWhittakerDiagram = new WhittakerDiagram(humidityRange, minHumidity, temperatureRange, minTemperature);

        //Shapes altogether cover 0 - 100 on both axis
        temperateWhittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, -1,102,12), TileTypes.SNOW);
        temperateWhittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, 10,22,41), TileTypes.DIRT);
        temperateWhittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, 50,22,52), TileTypes.ROCK);
        temperateWhittakerDiagram.addToWhittakerDiagram(new Rectangle(20, 25,46,26), TileTypes.GRASSLAND);
        temperateWhittakerDiagram.addToWhittakerDiagram(new Rectangle(20, -1,46,26), TileTypes.TAIGA);
        temperateWhittakerDiagram.addToWhittakerDiagram(new Rectangle(20, 49,46,51), TileTypes.FOREST);
        temperateWhittakerDiagram.addToWhittakerDiagram(new Rectangle(65, -1,37,26), TileTypes.TAIGA);
        temperateWhittakerDiagram.addToWhittakerDiagram(new Rectangle(65, 25,37,76), TileTypes.FOREST);
    }

    public TileTypes getTileBiome(double humidity, double temperature){
        return temperateWhittakerDiagram.getBiome(humidity, temperature);
    }
}

package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import java.awt.Rectangle;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public class ArticGeneralBiome extends GeneralBiome{
    WhittakerDiagram articWhittakerDiagram;

    public ArticGeneralBiome(double getBaseTemperature){
        super(getBaseTemperature);
    }

    public void createWhittakerDiagram(double humidityRange, double minHumidity, double temperatureRange, double minTemperature){
        articWhittakerDiagram = new WhittakerDiagram(humidityRange, minHumidity, temperatureRange, minTemperature);
        
        //Shapes altogether cover 0 - 100 on both axis
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(-1, -1,102,31), TileTypes.ICE);
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(-1,30,102,30), TileTypes.SNOW);
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(-1,60,32,41), TileTypes.TUNDRA);
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(30,60,71,41), TileTypes.TAIGA);
    }

    public TileTypes getTileBiome(double humidity, double temperature){
        return articWhittakerDiagram.getBiome(humidity, temperature);
    }
    
}

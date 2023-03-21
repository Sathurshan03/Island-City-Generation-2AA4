package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import java.awt.Rectangle;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public class ArticGeneralBiome extends GeneralBiome{
    WhittakerDiagram articWhittakerDiagram;

    public ArticGeneralBiome(double getBaseTemperature){
        super(getBaseTemperature);
    }

    public void createWhittakerDiagram(double humidityRange, double minTemperature){
        articWhittakerDiagram = new WhittakerDiagram(humidityRange, minTemperature);
        
        //Shapes altogether cover 0 - 100 on both axis
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(0,-276,101,226), TileTypes.ICE);
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(0,-50,101,25), TileTypes.SNOW);
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(0,-25,40,40), TileTypes.TUNDRA);
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(40,-25,61,40), TileTypes.TAIGA);
    }

    public TileTypes getTileBiome(double humidity, double temperature){
        return articWhittakerDiagram.getBiome(humidity, temperature);
    }
    
}

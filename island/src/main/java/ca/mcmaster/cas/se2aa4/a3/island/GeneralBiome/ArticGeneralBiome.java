package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import java.awt.Rectangle;

import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public class ArticGeneralBiome extends GeneralBiome{
    WhittakerDiagram articWhittakerDiagram;

    public ArticGeneralBiome(double getBaseTemperature){
        super(getBaseTemperature);
    }

    public void createWhittakerDiagram(double maxHumidy, double maxTemperature){
        articWhittakerDiagram = new WhittakerDiagram(maxHumidy, maxTemperature);
        
        //Shapes altogether cover 0 - 100 on both axis
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(0,0,30,100), TileTypes.ICE);
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(30,0,30,100), TileTypes.SNOW);
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(60,0,40,30), TileTypes.TUNDRA);
        articWhittakerDiagram.addToWhittakerDiagram(new Rectangle(60,30,40,70), TileTypes.TAIGA);
    }
    
}

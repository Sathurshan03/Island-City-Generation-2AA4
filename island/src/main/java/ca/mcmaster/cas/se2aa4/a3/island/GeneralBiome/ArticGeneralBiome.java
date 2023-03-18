package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

public class ArticGeneralBiome extends GeneralBiome{
    WhittakerDiagram articWhittakerDiagram;

    public ArticGeneralBiome(double getBaseTemperature){
        super(getBaseTemperature);
    }

    public void createWhittakerDiagram(double maxHumidy, double maxTemperature){
        articWhittakerDiagram = new WhittakerDiagram(maxHumidy, maxTemperature);
    }
    
}

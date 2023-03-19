package ca.mcmaster.cas.se2aa4.a3.island.SoilProfile;

public class Humidity {

    private Double coefficient;
    private SoilTypes soil;

    public Humidity(SoilTypes soil){
        this.soil=soil;
        this.coefficient=soil.getSoilCoefficient();
    }

    public void SetHumidity(){

    }


}

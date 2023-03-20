package ca.mcmaster.cas.se2aa4.a3.island.Terrains;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;

public class Land {

    Tile landtile;
    SoilTypes soilType;
    Double humidity;

    Double soilCoefficient;


    public Land(Tile landtile){
        this.landtile=landtile;
        this.soilType= Mode.getSoil();
        this.humidity=0.0;
        this.soilCoefficient=soilType.getSoilCoefficient();
    }

    public void setHumidity(Double humidity){
        this.humidity=humidity;
    }

    public Double getHumidity(){
        return this.humidity;
    }

    public Double getCoefficient(){
        return this.soilCoefficient;
    }



    public Tile getTile(){
        return this.landtile;
    }
}

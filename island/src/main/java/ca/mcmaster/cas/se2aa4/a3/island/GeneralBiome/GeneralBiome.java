package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

public abstract class GeneralBiome {
    protected final double protectedTemperature;
    public GeneralBiome(double protectedTemperature){
        this.protectedTemperature = protectedTemperature;
    }

    public double getBaseTemperature()
    {
        return protectedTemperature;
    }
}

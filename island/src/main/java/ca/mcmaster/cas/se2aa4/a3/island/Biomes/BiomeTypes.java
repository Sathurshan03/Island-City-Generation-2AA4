package ca.mcmaster.cas.se2aa4.a3.island.Biomes;

public enum BiomeTypes {

    ARCTIC, TROPICAL_FORREST, TEMPERATE_FORREST,DESSERT;

    public String toString(){
        switch(this){
            case ARCTIC :
                return "arctic";
            case TROPICAL_FORREST:
                return "tropicalforrest";
            case TEMPERATE_FORREST:
                return "temperateforrest";
            case DESSERT:
                return "dessert";
        }
        return null;
    }

    public Double getBaseTemperature(){
        switch (this){
            case ARCTIC:
                return -6.0;
            case TROPICAL_FORREST:
                return 21.0;
            case TEMPERATE_FORREST:
                return 10.0;
            case DESSERT:
                return 40.0;

        }
        return null;

    }
}

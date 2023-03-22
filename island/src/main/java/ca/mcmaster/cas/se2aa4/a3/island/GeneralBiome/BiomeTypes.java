package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

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

    public GeneralBiome getGeneralBiome(){
        switch (this){
            case ARCTIC:
                return new ArticGeneralBiome(260.0);
            case TROPICAL_FORREST:
                return null;
            case TEMPERATE_FORREST:
                return null;
            case DESSERT:
                return null;

        }
        return null;
    }


}

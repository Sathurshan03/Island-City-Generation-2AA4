package ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome;

import com.google.protobuf.DescriptorProtos;

public enum BiomeTypes {

    ARCTIC("arctic", false), 
    TROPICAL_FORREST("tropical", true), 
    TEMPERATE_FORREST("temperate", false),
    DESSERT("dessert", false);

    private String name;
    private Boolean containsBeaches;

    private BiomeTypes(String name, Boolean containsBeaches){
        this.name = name;
        this.containsBeaches = containsBeaches;

    }
    public String toString(){
        return name;
    }

    public Boolean doesContaiBeaches(){
        return containsBeaches;
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
                return new DesertGeneralBiome(311.0);

        }
        return null;
    }


}

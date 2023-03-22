package ca.mcmaster.cas.se2aa4.a3.island.TilesTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.LandComposition;

import java.awt.Color;


public enum TileTypes {
    UNDETERMINEDLAND(new Color(153,76,0,254), TileElement.UNDETERMINED,LandComposition.NOTSOIL),
    BEACH(new Color(255,250,201,254),TileElement.LAND,LandComposition.SOIL),
    Ocean(new Color(0,76,153,254), TileElement.WATER,LandComposition.NOTSOIL),
    LAGOON(new Color(25,132,226, 254), TileElement.WATER,LandComposition.NOTSOIL),
    LAKE(new Color(15,94,196, 254), TileElement.WATER, LandComposition.NOTSOIL),
    GRASSLAND(new Color(0,153,0,254), TileElement.LAND,LandComposition.SOIL),
    ENDORHEICLAKE(new Color(15,94,196,254), TileElement.WATER,LandComposition.NOTSOIL),
    ICE(new Color(192,200,255,254), TileElement.LAND, LandComposition.NOTSOIL),
    SNOW(new Color(255,250,251,254), TileElement.LAND,LandComposition.NOTSOIL),
    TAIGA(new Color(0,110,0,254), TileElement.LAND,LandComposition.SOIL),
    TUNDRA(new Color(140,151,133,254), TileElement.LAND,LandComposition.SOIL),
    RAINFOREST(new Color(0,117,94,254), TileElement.LAND,LandComposition.SOIL),
    SWAMP(new Color(92,82,20,254), TileElement.LAND,LandComposition.NOTSOIL),
    MUD(new Color(112,84,62,254), TileElement.LAND,LandComposition.SOIL);




    public final Color tileColor;
    public final TileElement tileElement;
    public final LandComposition composition;
    private TileTypes(Color color, TileElement element, LandComposition comp){
        tileColor = color;
        tileElement = element;
        composition=comp;
    }
    public Color getColor(){
        return tileColor;
    }
    public TileElement getElememt(){
        return tileElement;
    }

    public LandComposition getComposition(){
        return composition;
    }

}

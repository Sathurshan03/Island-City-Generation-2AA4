package ca.mcmaster.cas.se2aa4.a3.island.TilesTypes;
import java.awt.Color;


public enum TileTypes {
    UNDETERMINEDLAND(new Color(153,76,0,254), TileElement.UNDETERMINED),
    BEACH(new Color(255,250,201,254),TileElement.LAND),
    Ocean(new Color(0,76,153,254), TileElement.WATER),
    LAGOON(new Color(25,132,226, 254), TileElement.WATER),
    LAKE(new Color(100,149,237, 254), TileElement.WATER),
    GRASSLAND(new Color(0,153,0,254), TileElement.LAND),
    ENDORHEICLAKE(new Color(0,76,153,254), TileElement.WATER),
    ICE(new Color(204,230,255,254), TileElement.LAND),
    SNOW(new Color(255,250,250,254), TileElement.LAND),
    TAIGA(new Color(0,110,0,254), TileElement.LAND),
    TUNDRA(new Color(160,82,45,254), TileElement.LAND);

    
    public final Color tileColor;
    public final TileElement tileElement;
    private TileTypes(Color color, TileElement element){
        tileColor = color;
        tileElement = element;
    }
    public Color getColor(){
        return tileColor;
    }
    public TileElement getElememt(){
        return tileElement;
    }

}

package ca.mcmaster.cas.se2aa4.a3.island.Tiles;
import java.awt.Color;


public enum TileTypes {
    UNDETERMINEDLAND(new Color(153,76,0,254), TileElement.LAND),
    BEACH(new Color(252,228,162,254),TileElement.LAND),
    Ocean(new Color(0,0,153,254), TileElement.WATER),
    LAGOON(new Color(51,153,255, 254), TileElement.WATER);



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

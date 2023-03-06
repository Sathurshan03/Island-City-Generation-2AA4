package ca.mcmaster.cas.se2aa4.a3.island.Tiles;
import java.awt.Color;


public enum TileTypes {
    EMPTY(new Color(204,204,204,254)),
    Ocean(Color.BLUE.darker());



    public final Color tileColor;
    private TileTypes(Color color){
        tileColor = color;
    }
    public Color getColor(){
        return tileColor;
    }
}

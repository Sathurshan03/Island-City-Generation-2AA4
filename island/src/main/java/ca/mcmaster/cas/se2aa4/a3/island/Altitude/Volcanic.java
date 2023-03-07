package ca.mcmaster.cas.se2aa4.a3.island.Altitude;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;

import java.util.List;

public class Volcanic implements LandAltitude{

    public AltitudeFunction function= (x,y)->{return (Mode.getWidth()/10)/((Mode.getWidth()/500000)*Math.pow((x-Mode.getWidth()/2),2)+(Mode.getWidth()/500000)*Math.pow(y-Mode.getHeight()/2,2)+1);};

    List<Tile> landTiles;

    public Volcanic(List<Tile> landTiles){
        this.landTiles=landTiles;
        SetElevation();
    }


    public void SetElevation(){

        for(Tile tile: landTiles){
            for (TileVertex vertex:tile.getTileVertices()){
                vertex.setElevation(function.valueAt(vertex.getX(), vertex.getY()));

            }
        }
    }

}

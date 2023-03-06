package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.util.List;
import java.util.ArrayList;

public abstract class Shape {
    List<Tile> unMarkedtiles;
    List<Tile> markedtiles;
    public Shape(){
        unMarkedtiles = new ArrayList<>();
        markedtiles = new ArrayList<>();
    }
    public abstract List<Tile> getMarkedTiles();
    public abstract List<Tile> getUnMarkedTiles();
    public abstract void markTiles();
}

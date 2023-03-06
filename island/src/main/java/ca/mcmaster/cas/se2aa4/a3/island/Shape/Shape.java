package ca.mcmaster.cas.se2aa4.a3.island.Shape;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import java.util.List;

public interface Shape {
    public List<Tile> getMarkedTiles();
    public List<Tile> getUnMarkedPolygons();
    public void markTiles();
}

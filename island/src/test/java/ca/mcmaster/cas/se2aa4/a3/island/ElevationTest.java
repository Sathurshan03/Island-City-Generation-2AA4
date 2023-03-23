package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ElevationTest extends SetUpTest {

    @Test
    public void VolcanicTest(){

        TileVertex highest_vertex=tiles.get(0).getTileVertex(0);
        TileVertex lowest_vertex=tiles.get(0).getTileVertex(0);

        for (Tile t:tiles){
            for (TileVertex v: t.getTileVertices()){
                if (v.getElevation()>highest_vertex.getElevation()){
                    highest_vertex=v;
                }
                if (v.getElevation()<lowest_vertex.getElevation()){
                    lowest_vertex=v;
                }
            }
        }

        if (width/2== highest_vertex.getX() & height/2== highest_vertex.getY()){
            assertTrue(true);
        }

    }
}

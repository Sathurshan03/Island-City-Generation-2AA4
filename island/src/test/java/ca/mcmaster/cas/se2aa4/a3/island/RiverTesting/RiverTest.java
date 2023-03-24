package ca.mcmaster.cas.se2aa4.a3.island.RiverTesting;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import ca.mcmaster.cas.se2aa4.a3.island.SetUpTest;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileSegment;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.River;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.WaterTerrains.RiverGenerator;

public class RiverTest extends SetUpTest{
    @Test
    public void riverFlowTest(){
        //checks if the river flows in decreasing flow 

        RiverGenerator riverGenerator = new RiverGenerator(tiles, 10);
        List<River> rivers = riverGenerator.getRivers();
        TileVertex newVertex;

        for (River river: rivers){
            List<TileVertex> vertices = river.getRiverVerticies();
            TileVertex compareVertex = vertices.get(0);
            for (int i = 0; i < vertices.size() -1; i ++){
                newVertex = vertices.get(i + 1);
                assertTrue(compareVertex.getElevation() >= newVertex.getElevation());
                compareVertex = newVertex;
            }
        }
    }

    @Test
    public void riverSegmentRepresentationTest(){
        //checks to see if the last segment corresponds to last two vertices in river list
        RiverGenerator riverGenerator = new RiverGenerator(tiles, 10);
        List<River> rivers = riverGenerator.getRivers();

        for (River river: rivers){
            TileVertex lastVertex = river.getRiverVerticies().get(river.getRiverSize());
            TileVertex secondLastVertex = river.getRiverVerticies().get(river.getRiverSize() -1);
            TileSegment segments = river.getRiverlastSegment();
            assertTrue(segments.containsTileVertex(lastVertex));
            assertTrue(segments.containsTileVertex(secondLastVertex));
        }
    }

    @Test
    public void riverSizeTest(){
        //checks to see if river size corresponds to number of vertices that represent the river 
        RiverGenerator riverGenerator = new RiverGenerator(tiles, 10);
        List<River> rivers = riverGenerator.getRivers();

        for (River river: rivers){
            assertEquals(river.getRiverSize(), river.getRiverVerticies().size() - 1);
        }
    }
    
}

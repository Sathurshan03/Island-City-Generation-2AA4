package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.Altitude;
import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ElevationTest extends SetUpTest {

    Altitude new_altitude=new Altitude();


    @Test
    public void VolcanicTest(){

        List<Tile> all_tiles=new ArrayList<>(tiles);


        new_altitude.SetElevation(AltitudeType.VOLCANIC, all_tiles);

        TileVertex highest_vertex=all_tiles.get(0).getTileVertex(0);
        TileVertex lowest_vertex=all_tiles.get(0).getTileVertex(0);

        for (Tile t:all_tiles){
            for (TileVertex v: t.getTileVertices()){
                if (v.getElevation()>highest_vertex.getElevation()){
                    highest_vertex=v;
                }
                if (v.getElevation()<lowest_vertex.getElevation()){
                    lowest_vertex=v;
                }
            }
        }



        if (width/2-width*0.25<= highest_vertex.getX() & height/2-height*0.25<=highest_vertex.getY()){
            assertTrue(true);
        }else{
            assertTrue(false);
        }

    }

    @Test
    public void CliffTest(){

        List<Tile> all_tiles=new ArrayList<>(tiles);


        new_altitude.SetElevation(AltitudeType.CLIFF, all_tiles);

        TileVertex highest_vertex=all_tiles.get(0).getTileVertex(0);
        TileVertex lowest_vertex=all_tiles.get(0).getTileVertex(0);

        for (Tile t:all_tiles){
            for (TileVertex v: t.getTileVertices()){
                if (v.getElevation()>highest_vertex.getElevation()){
                    highest_vertex=v;
                }
                if (v.getElevation()<lowest_vertex.getElevation()){
                    lowest_vertex=v;
                }
            }
        }

        if ((width/2-1<=highest_vertex.getX() & height/2-1<= highest_vertex.getY()) | (width>=highest_vertex.getX() & height/2-1<= highest_vertex.getY())|(width>=highest_vertex.getX() & height>=highest_vertex.getY()) | (width/2-1<=highest_vertex.getX() & height>= highest_vertex.getY())){
            assertTrue(true);
        }else{
            assertTrue(false);
        }

    }

    @Test
    public void FlatTest(){

        List<Tile> all_tiles=new ArrayList<>(tiles);


        new_altitude.SetElevation(AltitudeType.FLAT, all_tiles);

        TileVertex highest_vertex=all_tiles.get(0).getTileVertex(0);
        TileVertex lowest_vertex=all_tiles.get(0).getTileVertex(0);

        for (Tile t:all_tiles){
            for (TileVertex v: t.getTileVertices()){

                if (v.getElevation()>highest_vertex.getElevation()){
                    highest_vertex=v;
                }
                if (v.getElevation()<lowest_vertex.getElevation()){
                    lowest_vertex=v;
                }
            }
        }


        if (Math.abs(highest_vertex.getElevation()-lowest_vertex.getElevation())<=100.0){
            assertTrue(true);
        }else{
            assertTrue(false);
        }

    }
}

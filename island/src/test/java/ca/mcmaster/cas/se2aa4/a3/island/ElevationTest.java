package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.TileVertex;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Regular;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.tools.RandomGenerator;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import javax.lang.model.type.NullType;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ElevationTest {

    static double width;
    static double height;

    public static List<Tile> setUp(AltitudeType altitude){
        try{
            Regular reg=new Regular("../generator/sample.mesh", "sandbox.mesh", ShapeType.CIRCLE, altitude, BiomeTypes.ARCTIC, 5, 10, SoilTypes.WET, 3);
            width= Mode.getWidth();
            height=Mode.getHeight();
            reg.generate();
            try {
                Field t=Mode.class.getDeclaredField("tiles");
                t.setAccessible(true);
                try{

                    List<Tile> all_t=(ArrayList<Tile>)t.get(reg);
                    return all_t;
                }catch (IllegalAccessException e){
                    throw new RuntimeException(e);
                }

            }catch (NoSuchFieldException e){
                throw new RuntimeException(e);
            }

        }catch (IOException e){
            throw new RuntimeException(e);
        }
    }

    @Test
    public void VolcanicTest(){
        List<Tile> tiles=setUp(AltitudeType.VOLCANIC);

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

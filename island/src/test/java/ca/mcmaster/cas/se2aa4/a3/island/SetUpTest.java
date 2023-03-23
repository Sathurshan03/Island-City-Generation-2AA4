package ca.mcmaster.cas.se2aa4.a3.island;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.AltitudeType;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Mode;
import ca.mcmaster.cas.se2aa4.a3.island.Modes.Regular;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class SetUpTest {

    static double width;
    static double height;

    static List<Tile> tiles;

    @BeforeAll
    public static void setUp(){
        try{
            width= Mode.getWidth();
            height=Mode.getHeight();

            Regular reg=new Regular("../generator/sample.mesh", "sandbox.mesh", ShapeType.CIRCLE, AltitudeType.VOLCANIC, BiomeTypes.ARCTIC, 5, 10, SoilTypes.WET, 3);
            reg.generate();
            try {
                Field t=Mode.class.getDeclaredField("tiles");
                t.setAccessible(true);
                try{

                    List<Tile> all_t=(ArrayList<Tile>)t.get(reg);
                    tiles=all_t;
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

}

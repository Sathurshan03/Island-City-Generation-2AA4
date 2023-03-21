package ca.mcmaster.cas.se2aa4.a3.island.Modes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.mcmaster.cas.se2aa4.a3.island.Altitude.*;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.Land;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.Ocean;
import ca.mcmaster.cas.se2aa4.a3.island.Terrains.RiverGenerator;
import ca.mcmaster.cas.se2aa4.a3.island.BuildingBlocks.Tile;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.BiomeTypes;
import ca.mcmaster.cas.se2aa4.a3.island.GeneralBiome.GeneralBiome;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.Shape;
import ca.mcmaster.cas.se2aa4.a3.island.Shape.ShapeType;
import ca.mcmaster.cas.se2aa4.a3.island.SoilProfile.SoilTypes;
import ca.mcmaster.cas.se2aa4.a3.island.TilesTypes.TileTypes;

public class Regular extends Mode {
    private int maxNumLakes;
    private int maxNumRivers;
    private int numAquifers;
    

    public Regular(String inputMesh, String outputMesh, ShapeType shapeType, AltitudeType altitudeType, BiomeTypes biome, int maxLakes, int maxNumRivers, SoilTypes soil, int numAquifers) throws IOException{
        super(inputMesh, outputMesh, shapeType, altitudeType, biome, Integer.toString(maxLakes),soil);
        
        extractInformation();
        this.maxNumLakes = maxLakes;
        this.maxNumRivers = maxNumRivers;
        this.numAquifers = numAquifers;
    }

    public void generate(){
        //generate the map based on the user inputs
        Shape islandShape = shape.getShape(width, height, tiles);
        List<Tile> oceanTiles = islandShape.getOutOfRangeTiles();
        List<Tile> undecidedTiles = islandShape.getInRangeTiles();

        //set oceanTiles to their color
        for(Tile tile: oceanTiles){
            tile.setTileType(TileTypes.Ocean);
            Ocean ocean_tile=new Ocean(tile);
            allWater.add(ocean_tile);
        }

        // int numLakes = IslandCommandLineReader.randomGenerator.getNextInteger(0,maxNumLakes);
        // List<Tile> potentialLakeTiles = determineLakeTiles(undecidedTiles);
        // int maxLakeSize = (int) Math.floor(Math.sqrt((double) potentialLakeTiles.size()/(double) numLakes));
        // System.out.println(potentialLakeTiles.size());
        // System.out.println(numLakes);
        // System.out.println((double) potentialLakeTiles.size()/(double) numLakes);
        // System.out.println(Math.sqrt((double) potentialLakeTiles.size()/(double) numLakes));
        // System.out.println(maxLakeSize);

        altitude_gen.setAll(altitude, undecidedTiles, oceanTiles);

        RiverGenerator riverGenerator = new RiverGenerator(tiles, maxNumRivers);
        riverGenerator.createRivers();

        allWater.addAll(riverGenerator.getRivers());

        //Remove endorheic lake tiles from undecided tiles
        for (Tile endorheicLake : riverGenerator.getEndorheicLakes())
        {
            if (undecidedTiles.contains(endorheicLake)){
                undecidedTiles.remove(endorheicLake);
            }
        }

        //Generate the general biome of the map
        GeneralBiome generalBiome = biome.getGeneralBiome();

        //Set temperature to all land and water tiles
        temperature_gen.setTemperature(tiles, generalBiome.getBaseTemperature(), altitude_gen.getMinElevation());
        
        //Create Land tiles
        for(Tile tile: undecidedTiles){
            Land landtile=new Land(tile);
            allLand.add(landtile);
        }

        //Set humidity to all land tiles
        humidity.SetHumidity(allLand,allWater);



        //Set biomes to all land tiles based on their average temperature and humidity level
        TileTypes landBiome;
        generalBiome.createWhittakerDiagram(humidity.getHumidityRange(), humidity.getMinHumidity());
        for (Land landTile : allLand){
            landBiome = generalBiome.getTileBiome(landTile.getHumidity(), landTile.getAverageTemperature());
            landTile.setTileType(landBiome);
        }

        //Set humidity contrast colours to all land tiles
        humidity.setHumidityColors(allLand);

    }
    private List<Tile> determineLakeTiles(List<Tile> undecidedTiles){
        List<Tile> potentialLakeTiles = new ArrayList<>(undecidedTiles); // Create deep copy of undecided tiles, so we can remove beach tiles.
                                                                         // Lakes can only be formed on tiles which are not beach or ocean tiles.
        List<Tile> beachTiles = new ArrayList<>();

        for(Tile tile: undecidedTiles){
            for(Tile neighbouringTile: tile.getNeighbouringTile()){
                if (neighbouringTile.isTileWater()){
                    beachTiles.add(neighbouringTile);
                    break;
                }
            }
        }
        potentialLakeTiles.removeAll(beachTiles);
        return potentialLakeTiles;
    }
}

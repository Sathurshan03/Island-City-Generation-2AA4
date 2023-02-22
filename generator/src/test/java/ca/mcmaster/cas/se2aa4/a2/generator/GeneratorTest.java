package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;


public class GeneratorTest {

    @Test
    public void meshIsNotNull() throws IOException {
        Generator generator = new Generator();
        int numberPolygons = 100;
        Structs.Mesh aMesh = generator.generate(numberPolygons, 500, 500, MeshType.GRID, 5, 20);
        assertNotNull(aMesh);
    }

}

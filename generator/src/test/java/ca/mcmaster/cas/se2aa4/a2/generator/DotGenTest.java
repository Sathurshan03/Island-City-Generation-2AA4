package ca.mcmaster.cas.se2aa4.a2.generator;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class DotGenTest {

    @Test
    public void meshIsNotNull() {
        Generator generator = new Generator();
        int numberPolygons = 100;
        Structs.Mesh aMesh = generator.generate(numberPolygons);
        assertNotNull(aMesh);
    }

}

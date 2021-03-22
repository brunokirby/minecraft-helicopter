package uk.brunokirby.blockbench_import;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BlockbenchConverterTest {

    BlockbenchConverter bc = new BlockbenchConverter();

    @Test
    @DisplayName("Should convert entity classname")
    void shouldConvertClassname() {
        String inputLine="public class helicopter_entity_model extends EntityModel<Entity> {";
        String outputLine="public class HelicopterEntityModel extends EntityModel<HelicopterEntity> {";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }

    @Test
    void shouldConvertInstanceVariableDeclaration() {
        String inputLine="\tprivate final ModelRenderer full_heli;";
        String outputLine="\tprotected final ModelPart full_heli;";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }

    @Test
    @Disabled
    void shouldConvertAddCuboid() {
        String inputLine="bone_head.setTextureOffset(0, 0).addBox(5.5F, -15.0F, -4.0F, 1.0F, 3.0F, 1.0F, 0.0F, true);";
        String outputLine="head.addCuboid(\"ear_l\", 5.5F, -15.0F, -4.0F, 1, 3, 1, 0.0F, 0, 0);";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }

}
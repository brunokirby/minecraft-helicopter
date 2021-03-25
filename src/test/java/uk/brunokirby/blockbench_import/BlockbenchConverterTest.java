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
        String outputLine="@Environment(EnvType.CLIENT)\npublic class HelicopterEntityModel extends EntityModel<HelicopterEntity> {";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }

    @Test
    void shouldConvertInstanceVariableDeclaration() {
        String inputLine="\tprivate final ModelRenderer full_heli;";
        String outputLine="\tprotected final ModelPart full_heli;";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }

    @Test
    void shouldConvertAddCuboid() {
        String inputLine="bone_head.setTextureOffset(10, 20).addBox(5.5F, -15.0F, -4.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);";
        String outputLine="bone_head.addCuboid(\"banana\", 5.5F, -15.0F, -4.0F, 1, 3, 1, 0.0F, 10, 20);";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }
    @Test
    void shouldConvertConstructor() {
        String inputLine = "\tpublic helicopter_entity_model() {";
        String outputLine = "\tpublic HelicopterEntityModel() {";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }
    @Test
    void shouldConvertNewPart() {
        String inputLine = "\t\tfull_heli = new ModelRenderer(this);";
        String outputLine = "\t\tfull_heli = new ModelPart(this);";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }
    @Test
    void shouldConvertSetPivot() {
        String inputLine = "\t\tfull_heli.setRotationPoint(0.0F, 16.0F, 0.0F);";
        String outputLine = "\t\tfull_heli.setPivot(0.0F, 16.0F, 0.0F);";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }
    @Test
    void shouldConvertVertexConsumer() {
        String inputLine = "public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight";
        String outputLine = "public void render(MatrixStack matrixStack, VertexConsumer buffer, int packedLight";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }
    @Test
    void shouldConvertSetAngles() {
        String inputLine = "public void setRotationAngles(Entity entity, float limbSwing,";
        String outputLine = "public void setAngles(HelicopterEntity entity, float limbSwing,";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }



}
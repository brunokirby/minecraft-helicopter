package uk.brunokirby.blockbench_import;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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

    @Test
    void shouldConvertSetAnglePitch() {
        String inputLine = "setRotationAngle(main_rotors_back_r1, 0.3927F, 0.0F, 0.0F);";
        String outputLine = "main_rotors_back_r1.pitch = 0.3927F;";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }

    @Test
    void shouldConvertSetAngleYaw() {
        String inputLine = "setRotationAngle(main_rotors_back_r1, 0.0F, -0.3927F, 0.0F);";
        String outputLine = "main_rotors_back_r1.yaw = -0.3927F;";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }

    @Test
    void shouldConvertSetAngleRoll() {
        String inputLine = "setRotationAngle(main_rotors_back_r1, 0.0F, 0.0F, 0.3927F);";
        String outputLine = "main_rotors_back_r1.roll = 0.3927F;";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }

    @Test
    void shouldConvertSetAnglePitchYawRoll() {
        String inputLine = "setRotationAngle(main_rotors_back_r1, 0.3927F, -10.0F, 3.0F);";
        String outputLine = "main_rotors_back_r1.pitch = 0.3927F;\n"
                + "main_rotors_back_r1.yaw = -10.0F;\n"
                + "main_rotors_back_r1.roll = 3.0F;";
        Assertions.assertEquals(outputLine, bc.convertLine(inputLine));
    }

    @Test
    void shouldRemoveSetRotationAngle() {
        List<String>inputLines = new ArrayList<>();
        inputLines.add("\tpublic void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {\\n\",");
        inputLines.add("\t\tmodelRenderer.rotateAngleX = x;\n");
        inputLines.add("\t\tmodelRenderer.rotateAngleY = y;\n");
        inputLines.add("\t\tmodelRenderer.rotateAngleZ = z;\n");
        inputLines.add("\t}");
        String outputLine = "\n"
                          + "\n"
                          + "\n"
                          + "\n"
                          + "\n";
        String createdLines = "";
        for (String line:inputLines) {
            createdLines += bc.convertLine(line) + "\n";
        }
        Assertions.assertEquals(outputLine, createdLines);
    }



}
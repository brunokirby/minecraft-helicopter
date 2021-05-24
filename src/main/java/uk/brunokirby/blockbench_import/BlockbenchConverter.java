package uk.brunokirby.blockbench_import;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static java.nio.charset.StandardCharsets.UTF_8;

public class BlockbenchConverter {
    private final static String blockbenchInputFilename = "src/main/resources/helicopter_entity_model_bb_two.java";
    private final static String fabricOutputFilename = "src/main/java/uk/brunokirby/helicopter_mod/HelicopterEntityModel.java";
//    private final static String fabricOutputFilename = "src/main/resources/HelicopterEntityModel.java";
    private boolean deleteNextLine;

    public static void main(String[] args) {
        new BlockbenchConverter().appMain();
    }

    public void appMain() {
        deleteNextLine = false;
        System.out.println("bananas");

        File blockbenchInputFile = new File(blockbenchInputFilename);

        List<String> allLines = new ArrayList<>();
        try (Stream<String> lines = Files.lines(blockbenchInputFile.toPath())
                ) {
//            lines.forEach(line -> {
//                allLines.add(line);
//            });
            lines.forEach(allLines::add);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (Writer writer = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(fabricOutputFilename), UTF_8))) {

            // write header
            writer.write("package uk.brunokirby.helicopter_mod;\n" +
                    "\n" +
                    "import net.fabricmc.api.EnvType;\n" +
                    "import net.fabricmc.api.Environment;\n" +
                    "import net.minecraft.client.model.ModelPart;\n" +
                    "import net.minecraft.client.render.VertexConsumer;\n" +
                    "import net.minecraft.client.render.entity.model.EntityModel;\n" +
                    "import net.minecraft.client.util.math.MatrixStack;\n");

            for (String line: allLines) {

                System.out.println("Input line: " + line);
                String modified = convertLine(line);
                System.out.println("Output line: " + modified);
                System.out.println("---");

                writer.write(modified+"\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertLine(String original) {

        // TODO remember "@Environment(EnvType.CLIENT)"

        if (original.contains("public class")) {
            // class name
            return "@Environment(EnvType.CLIENT)\n" +
                    original.replaceAll(
                    "class helicopter_entity_model extends EntityModel<Entity>",
                    "class HelicopterEntityModel extends EntityModel<HelicopterEntity>");
        } else if (original.contains("private final ModelRenderer")) {
            // ModelPart instance variable
            return original.replaceAll(
                    "private final ModelRenderer",
                    "protected final ModelPart");
        } else if (original.contains("public helicopter_entity_model")) {
            // constructor
            return original.replaceAll(
                    "helicopter_entity_model",
                    "HelicopterEntityModel");
        } else if (original.contains("new ModelRenderer(this)")){
            //new model part
            return original.replaceAll(
                    "ModelRenderer",
                    "ModelPart");
        } else if (original.contains(".setRotationPoint(")) {
            return original.replaceAll(
                    "setRotationPoint",
                    "setPivot");
        } else if (original.contains("IVertexBuilder")) {
            return original.replaceAll(
                    "IVertexBuilder",
                    "VertexConsumer");
        } else if (original.contains("setRotationAngles")) {
            return original.replaceAll(
                    "setRotationAngles\\(Entity",
                    "setAngles(HelicopterEntity");
        } else if (original.contains(".render")) {
            return  "        // hack to reverse the entire helicopter\n"
                    +"        full_heli.yaw = (float) Math.PI;\n"
                    + original;
        } else if (original.contains("public void setRotationAngle(ModelRenderer modelRenderer")) {
            deleteNextLine = true;
            return "    public void animateModel(HelicopterEntity entity, float limbAngle, float limbDistance, float tickDelta) {\n"
                +"       if (entity.getFlying() == HelicopterEntity.Flying.IS_FLYING) {\n"
                +"            float animationProgress = (float) entity.age + tickDelta;\n"
                +"            main_rotors.yaw = animationProgress / 2.0F;\n"
                +"        } else {\n"
                +"            main_rotors.yaw = (float) Math.PI / 4.0F;\n"
                +"        }\n"
                +"    }\n";
        } else if (deleteNextLine) {
            if (original.contains("}")) {
                deleteNextLine = false;
            }
            return "";
        } else if (original.contains("setRotationAngle(")) {
            Pattern patternParseRotation = Pattern.compile(
                    "(.*)setRotationAngle\\("
                    + "([^,]+)" + ", "
                    + "(-?\\d+\\.\\d+F)" + ", "
                    + "(-?\\d+\\.\\d+F)" + ", "
                    + "(-?\\d+\\.\\d+F)" + "\\);"
            );
            Matcher matcherParseRotation = patternParseRotation.matcher(original);
            if(matcherParseRotation.find()) {
                List<String> return_string = new ArrayList<>();
                if (!"0.0F".equals(matcherParseRotation.group(3))) {
                    return_string.add(
                            matcherParseRotation.group(1)
                            + matcherParseRotation.group(2)
                            + ".pitch = "
                            + matcherParseRotation.group(3)
                            + ";"
                    );
                }
                if (!"0.0F".equals(matcherParseRotation.group(4))) {
                    return_string.add(
                            matcherParseRotation.group(1)
                            + matcherParseRotation.group(2)
                            + ".yaw = "
                            + matcherParseRotation.group(4)
                            + ";"
                    );
                }
                if (!"0.0F".equals(matcherParseRotation.group(5))) {
                    return_string.add(
                            matcherParseRotation.group(1)
                            + matcherParseRotation.group(2)
                            + ".roll = "
                            + matcherParseRotation.group(5)
                            + ";"
                    );
                }
                return String.join("\n", return_string);
            }
            else {
                // error
                throw new RuntimeException("eeek");
            }
        }

//        String inputLine="bone_head.setTextureOffset(10, 20).addBox(5.5F, -15.0F, -4.0F, 1.0F, 3.0F, 1.0F, 0.0F, false);";
//        String outputLine="bone_head.addCuboid(\"banana\", 5.5F, -15.0F, -4.0F, 1, 3, 1, 0.0F, 10, 20);";
        Pattern patternAddCuboid = Pattern.compile(
                "(.*)setTextureOffset\\("
                + "(\\d+, \\d+)"
                + "\\)\\.addBox\\("
                + "(-?\\d+\\.\\d+F, -?\\d+\\.\\d+F, -?\\d+\\.\\d+F, )"
                + "(\\d+)" + "\\.0F, "
                + "(\\d+)" + "\\.0F, "
                + "(\\d+)" + "\\.0F, "
                + "(-?\\d+\\.\\d+F)"
                + ", false\\);"
        );
        Matcher matcherAddCuboid = patternAddCuboid.matcher(original);

        if(matcherAddCuboid.find()) {
            return matcherAddCuboid.group(1)
                    + "addCuboid(\"banana\", "
                    + matcherAddCuboid.group(3)
                    + matcherAddCuboid.group(4) + ", "
                    + matcherAddCuboid.group(5) + ", "
                    + matcherAddCuboid.group(6) + ", "
                    + matcherAddCuboid.group(7) + ", "
                    + matcherAddCuboid.group(2)
                    + ");"
                    ;
        }

        return original;
    }
}

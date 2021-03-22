package uk.brunokirby.blockbench_import;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class BlockbenchConverter {
    private final static String blockbenchInputFilename = "src/main/resources/helicopter_entity_model_bb_two.java";
//    private final static String fabricOutputFilename = "src/main/java/uk/brunokirby/helicopter_mod/HelicopterEntityModel2.java";
    private final static String fabricOutputFilename = "src/main/resources/HelicopterEntityModel.java";

    public static void main(String[] args) {
        new BlockbenchConverter().appMain(args);
    }

    public void appMain(String[] args) {
        System.out.println("bananas");

        File blockbenchInputFile = new File(blockbenchInputFilename);

        List<String> allLines = new ArrayList<String>();
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
                new FileOutputStream(fabricOutputFilename), "utf-8"))) {

            for (String line: allLines) {

                System.out.println("Input line: " + line);
                String modified = convertLine(line);
                System.out.println("Output line: " + modified);
                System.out.println("---");

//                writer.write(modified+"\n");
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String convertLine(String original) {

        // TODO remember "@Environment(EnvType.CLIENT)"

        if (original.contains("public class")) {
            return original.replaceAll(
                    "class helicopter_entity_model extends EntityModel<Entity>",
                    "class HelicopterEntityModel extends EntityModel<HelicopterEntity>");
        } else if (original.contains("private final ModelRenderer")) {
            return original.replaceAll(
                    "private final ModelRenderer",
                    "protected final ModelPart");
        }

        return original;
    }
}

package hu.vtg;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reader {
    public ArrayList<Create> readFile() throws IOException {
        ArrayList<Create> list = new ArrayList<>();
        File file = new File("src/main/java/File/restauralasok.csv");
        try(Scanner sc = new Scanner(file, StandardCharsets.UTF_8)) {
            sc.nextLine();
            while(sc.hasNext()){
                String line = sc.nextLine();
                String [] lineArray = line.split(";");
                Create create = new Create();
                create.setId(Integer.parseInt(lineArray[0]));
                create.setPaintingid(Integer.parseInt(lineArray[1]));
                create.setDatum(LocalDate.parse(lineArray[2]));
                create.setMuvelet(lineArray[3]);
                create.setMegjegyzes(lineArray[4]);
                list.add(create);
            }
            sc.close();
        }
        catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
        return list;
    }
    public void FileWiriter(ArrayList<Create> createlist) {
            try (Writer writer = new OutputStreamWriter(
                new FileOutputStream("src/main/java/File/restauralasok.csv"),
                StandardCharsets.UTF_8)) {
            writer.write("id;paintingid;datum;muvelet;megjegyzes\n");
            for (Create create : createlist) {
                writer.write(
                    create.getId() + ";" +
                    create.getPaintingid() + ";" +
                    create.getDatum() + ";" +
                    create.getMuvelet() + ";" +
                    create.getMegjegyzes() + "\n"
                );
            }
        } catch (IOException e) {
            System.err.println("Hiba a fájl írásakor: " + e.getMessage());
        }
    }

}

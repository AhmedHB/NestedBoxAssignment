package file;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadFile {

    public static List<String> readLines(String filePath){
        List<String>  contents = new ArrayList<>();
        Path path = Paths.get(filePath);

        try{
            contents = Files.readAllLines(path);
        }catch(IOException ex){
            ex.printStackTrace();//handle exception here
        }
        return contents;
    }
}

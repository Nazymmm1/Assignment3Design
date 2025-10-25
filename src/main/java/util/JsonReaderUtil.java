package util;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import model.GraphWrapper;

import java.io.FileReader;
import java.io.IOException;

public class JsonReaderUtil {
    public static GraphWrapper readFromJson(String filename){
        Gson gson= new Gson();
        try (JsonReader reader = new JsonReader(new FileReader(filename))) {
            return gson.fromJson(reader, GraphWrapper.class);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
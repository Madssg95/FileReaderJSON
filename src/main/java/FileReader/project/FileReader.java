package practiceUnitTest.project;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;

public class FileReader {

  public static void main(String[] args) throws IOException, JSONException {

    FileReader text = new FileReader();
    String input =
        "{ \"userInput\" : { \"firstName\" : \"Mads\", \"lastName\" : \"Gadeberg\"} }";

    StringBuilder sb = new StringBuilder();
    List<String> lines = text.readSmallTextFile(FILE_NAME);

    if (lines.isEmpty()) {
      lines.add(input);
      text.writeSmallTextFile(lines, FILE_NAME);
    }

    for (String string : lines) {
      sb.append(string);
    }

    String readInput = sb.toString();

    JSONObject obj = new JSONObject(readInput);
    String firstName = obj.getJSONObject("userInput").getString("firstName");
    String lastName = obj.getJSONObject("userInput").getString("lastName");

    System.out.println(firstName + lastName);

  }

  private List<String> readSmallTextFile(String fileName) throws IOException {
    Path path = Paths.get(fileName);
    return Files.readAllLines(path, ENCODING);
  }

  private void writeSmallTextFile(List<String> lines, String fileName)
      throws IOException {
    Path path = Paths.get(fileName);
    Files.write(path, lines, ENCODING);

  }

  final static String FILE_NAME = "C:\\tmp\\input.txt";

  final static Charset ENCODING = StandardCharsets.UTF_8;

}

package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParsedNordeaEventList {

    private String tilinumero;
    private List<String> headers;
    private List<NordeaEvent> nordeaEvents;

    public String getTilinumero() { return tilinumero; }
    public List<String> getHeaders() { return headers; }
    public List<NordeaEvent> getNordeaEvents() { return nordeaEvents; }

    public void parse(String fileName) throws FileNotFoundException {
        List<String> lines = getLinesFromFile(fileName);
        parseNordeaEventsFromLines(lines);
    }

    private void parseNordeaEventsFromLines(List<String> lines) {
        nordeaEvents = new ArrayList<>();

        for (String line : lines) {
            if (line == null) { continue; }
            if (line.trim().length() == 0) { continue; }

            if (line.startsWith("Kirjauspäivä")) {
                parseHeaders(line);
                continue;
            }

            if (line.startsWith("Tilinumero")) {
                tilinumero = line;
                continue;
            }

            nordeaEvents.add(new NordeaEvent(line));
        }
    }

    private void parseHeaders(String tabSeparatedLine) {
        headers = new ArrayList<>();
        Scanner scanner = new Scanner(tabSeparatedLine);
        while(scanner.hasNext()) {
            headers.add(scanner.next());
        }
        scanner.close();
    }

    private List<String> getLinesFromFile(String fileName) throws FileNotFoundException {
        List<String> lines = new ArrayList<>();

        Scanner in = new Scanner(new FileReader(fileName));
        while(in.hasNext()) {
            lines.add(in.nextLine());
        }
        in.close();

        return lines;
    }

    public static void main(String[] args) throws IOException {
        String hc = "/Users/tade/Code/nordea-events-to-json/Tapahtumat_FI7814565000014684_20110101_20130331.txt";

        ParsedNordeaEventList parsedNordeaEventList = new ParsedNordeaEventList();
        parsedNordeaEventList.parse(hc);

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(parsedNordeaEventList);
        System.out.println(json);
    }
}

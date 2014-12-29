package parser;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ParsedNordeaEventList {

    private static final String SYSTEM_PROPERTY_TAPAHTUMALUETTELO = "tapahtumaluettelo";

    private String tilinumero;
    private List<String> headers;
    private List<NordeaEvent> nordeaEvents;

    public String getTilinumero() { return tilinumero; }
    public List<String> getHeaders() { return headers; }
    public List<NordeaEvent> getNordeaEvents() { return nordeaEvents; }

    private void parse(String fileName) throws FileNotFoundException {
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
                tilinumero = line.split("\t")[1];
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

        ParsedNordeaEventList parsedNordeaEventList = new ParsedNordeaEventList();
        parsedNordeaEventList.parse(System.getProperty(SYSTEM_PROPERTY_TAPAHTUMALUETTELO));

        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(parsedNordeaEventList);
        System.out.println(json);
    }
}

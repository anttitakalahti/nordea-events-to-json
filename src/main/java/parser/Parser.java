package parser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Parser {

    private List<String> headers;
    private List<NordeaEvent> nordeaEvents;

    public List<NordeaEvent> parse(String filename) throws FileNotFoundException {


        String hc = "/Users/tade/Code/nordea-events-to-json/Tapahtumat_FI7814565000014684_20110101_20130331.txt";
        List<String> lines = getLinesFromFile(hc);
        parseNordeaEventsFromLines(lines);

        System.out.println(nordeaEvents.size());


        return null;
    }

    private void parseNordeaEventsFromLines(List<String> lines) {
        nordeaEvents = new ArrayList<NordeaEvent>();

        for (String line : lines) {
            if (line == null) { continue; }
            if (line.trim().length() == 0) { continue; }
            if (line.startsWith("Tilinumero")) { continue; }
            if (line.startsWith("Kirjauspäivä")) {
                parseHeaders(line);
                continue;
            }


            nordeaEvents.add(new NordeaEvent(line));
            System.out.println("bar: " + line);
        }
    }

    private void parseHeaders(String tabSeparatedLine) {
        headers = new ArrayList<String>();
        Scanner scanner = new Scanner(tabSeparatedLine);
        while(scanner.hasNext()) {
            headers.add(scanner.next());
        }
        scanner.close();
    }

    private List<String> getLinesFromFile(String fileName) throws FileNotFoundException {

        List<String> lines = new ArrayList<String>();

        Scanner in = new Scanner(new FileReader(fileName));
        while(in.hasNext()) {
            lines.add(in.nextLine());
        }
        in.close();

        return lines;
    }

    public static void main(String[] args) throws FileNotFoundException {
        Parser parser = new Parser();
        parser.parse("foo");
    }
}

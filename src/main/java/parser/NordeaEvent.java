package parser;

public class NordeaEvent {
    private String rawLine;

    public NordeaEvent(String rawLine) {
        this.rawLine = rawLine;

        String[] splitted = rawLine.split("\t");


        if (splitted.length != 8 && splitted.length != 9 && splitted.length != 11 && splitted.length != 12 && splitted.length != 13) { System.out.println(splitted.length);System.out.println(rawLine);System.exit(-1); }
        System.out.println(splitted.length);
    }

}

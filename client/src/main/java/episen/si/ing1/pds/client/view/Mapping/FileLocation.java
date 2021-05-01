package episen.si.ing1.pds.client.view.Mapping;

public class FileLocation {
    private final static String red_icon = "C:\\Users\\kouma\\IdeaProjects\\tech-up\\client\\src\\main\\resources\\red_icon.png";
    private final static String blue_icon = "C:\\Users\\kouma\\IdeaProjects\\tech-up\\client\\src\\main\\resources\\blue_icon.png";
    private final static String open_space = "C:\\Users\\kouma\\IdeaProjects\\tech-up\\client\\src\\main\\resources\\open_space.png";
    private final static String  meeting_room = "C:\\Users\\kouma\\IdeaProjects\\tech-up\\client\\src\\main\\resources\\meeting_room.png";
    private final static String individual_office = "C:\\Users\\kouma\\IdeaProjects\\tech-up\\client\\src\\main\\resources\\individual_office.png";

    // TO DO : make a system variable to retrieve file location

    public static String getRed_icon() {
        return red_icon;
    }

    public static String getBlue_icon() {
        return blue_icon;
    }

    public static String getOpen_space() {
        return open_space;
    }

    public static String getMeeting_room() {
        return meeting_room;
    }

    public static String getIndividual_office() {
        return individual_office;
    }
}

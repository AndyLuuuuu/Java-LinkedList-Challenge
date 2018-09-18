public class Song {
    private String title;
    private String duration;
    private Song song;

    // Constructor
    public Song(String title, String duration) {
        this.title = title;
        this.duration = duration;
    }

    // Get song title.
    public String getTitle() {
        return title;
    }

    // Get song.
    public Song getSong() {
        return song;
    }

}

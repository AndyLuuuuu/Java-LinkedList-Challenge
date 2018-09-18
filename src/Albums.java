import java.util.ArrayList;

public class Albums {
    private String albumName;
    private ArrayList<Song> albums;

    public Albums(String albumName) {
        this.albumName = albumName.toLowerCase();
        albums = new ArrayList<>();
    }

    // Add songs to album
    // If album is empty, add song.
    // if album is not empty and the album does not contain song, add song.
    // if album is not empty and the album already contains song, display message.
    public void addSong(String songName, String duration) {
        if (albums.isEmpty()) {
            albums.add(new Song(songName.toLowerCase(),duration));
            System.out.println(songName + " has been added.");
        } else if (!albums.isEmpty()) {
            for (int i = 0; i < albums.size(); i++) {
                if (albums.get(i).getTitle().equals(songName.toLowerCase())) {
                    System.out.println("This song title already exists.");
                    break;
                } else {
                    albums.add(new Song(songName.toLowerCase(), duration));
                    System.out.println(songName + " has been added.");
                }
            }
        }

    }

    // Get album name.
    public String getAlbumName() {
        return albumName;
    }

    // Check if song exists in album or not.
    public String songExists(String songName) {
        for(int i = 0; i < albums.size(); i++) {
           if(albums.get(i).getTitle().equals(songName));
           return albums.get(i).getTitle();
        }
        return null;
    }
}

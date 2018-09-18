import java.util.LinkedList;

public class Playlist {
    LinkedList<String> playlist = new LinkedList<>();

    // Add a new song into playlist linked list.
    public void addSong(String songName) {
        playlist.add(songName);
        System.out.println("Song " + songName + " has been added to playlist.");
    }


    // Return the linkedlist of playlist.
    public LinkedList<String> displayPlaylist() {
        return playlist;
    }
}

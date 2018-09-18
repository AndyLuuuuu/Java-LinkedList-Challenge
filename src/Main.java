import java.util.ArrayList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static ArrayList<Albums> myAlbums = new ArrayList<>();
    public static String songName;
    public static Playlist playlist = new Playlist();

    public static void main(String[] args) {
    // Display directions and then ask for user input.
        boolean run = true;
        while (run) {
            System.out.println("\nWhat would you like to do?\n" + "\t1. Add a new album.\n" + "\t2. Add song to album.\n" + "\t3. Search for a song in an album.\n" + "\t4. Add a album song to playlist.\n" + "\t5. Play playlist.\n" + "\t6. Quit.");
            int userInput = scanner.nextInt();
            switch (userInput) {
                // Asks for album name.
                // If albums are empty, automatically add album.
                // If albums is not empty, search if album already exists.
                // If album in albums does not exist, add album.
                case 1:
                    System.out.println("Enter your album name: ");
                    String albumName = scanner.next().toLowerCase();
                    if (myAlbums.isEmpty()) {
                        addAlbum(albumName);
                        System.out.println("Album " + albumName + " added.");
                    } else if (!myAlbums.isEmpty()) {
                        for (int i = 0; i < myAlbums.size(); i++) {
                            if (myAlbums.get(i).getAlbumName().equals(albumName)) {
                                System.out.println("This album already exists.");
                                continue;
                            } else {
                                addAlbum(albumName);
                                System.out.println("Album " + albumName + " added.");
                                continue;
                            }
                        }
                    }
                    break;

                // Ask for which album to add song to.
                // If album exists, ask user for song name and duration.
                // If album does not exist, display message about not finding album.
                case 2:
                    System.out.println("Which album would you like to add to?");
                    String album = scanner.next().toLowerCase();
                    boolean couldNotFind = true;
                    for (int i = 0; i < myAlbums.size(); i++) {
                        if (myAlbums.get(i).getAlbumName().equals(album)) {
                            System.out.println("Enter the song name: ");
                            String songName = scanner.next();
                            System.out.println("Enter the duration of the song " + songName);
                            String duration = scanner.next();
                            if (songName.equals(myAlbums.get(i).songExists(songName))) {
                                System.out.println("This song already exists.");
                                couldNotFind = false;
                                break;
                            } else {
                                myAlbums.get(i).addSong(songName, duration);
                                System.out.println("You have successfully added " + songName + " to album " + album);
                                couldNotFind = false;
                                continue;
                            }
                        }
                    }
                    if (couldNotFind) {
                        System.out.println("Could not find album.");
                        break;
                    }
                    break;
                // Ask user which song they wish to find.
                // Search through albums for specific song.
                // If not found, display message.
                case 3:
                    System.out.println("Which song would you like to find?");
                    songName = scanner.next().toLowerCase();
                    String foundSongMessage = findSong(songName);
                    if (foundSongMessage != null) {
                        System.out.println("Your song: " + foundSongMessage + " was found.");
                        break;
                    } else {
                        System.out.println("We could not find your song.");
                        break;
                    }

                    // Ask user which song they would like to add to playlist.
                    // Search for songs in all albums, if song is found, add to playlist.
                    // If song not found, display message.
                case 4:
                    System.out.println("Which song would you like to add to playlist?");
                    songName = scanner.next().toLowerCase();
                    if (findSong(songName).equals(songName.toLowerCase())) {
                        playlist.addSong(songName);
                    } else {
                        System.out.println("You do not own the song.");
                    }
                    break;
                    // Loop through songs allowing user to skip to next song, go to previous or replay current song.
                case 5:
                    ListIterator<String> playlistIterator = playlist.displayPlaylist().listIterator();
                    boolean quit = false;
                    boolean goingForward = true;
                    if (playlist.displayPlaylist().isEmpty()) {
                        System.out.println("There is no songs in the playlist.");
                        break;
                    } else {
                        printMenu();
                        System.out.println("Currently playing: " + playlistIterator.next());
                    }

                    while (!quit) {
                        int action = scanner.nextInt();
                        scanner.nextLine();
                        switch (action) {
                            case 1:
                                if (!goingForward) {
                                    if (playlistIterator.hasNext()) {
                                        playlistIterator.next();
                                    }
                                    goingForward = true;
                                }
                                if (playlistIterator.hasNext()) {
                                    System.out.println("Currently playing: " + playlistIterator.next() + "\n");
                                    printMenu();
                                } else {
                                    System.out.println("We have reached end of your playlist.\n");
                                    printMenu();
                                }
                                break;
                            case 2:
                                if (goingForward) {
                                    if (playlistIterator.hasPrevious()) {
                                        playlistIterator.previous();
                                    }
                                    goingForward = false;
                                }
                                if (playlistIterator.hasPrevious()) {
                                    System.out.println("Currently playing: " + playlistIterator.previous() + "\n");
                                    printMenu();
                                } else if (!playlistIterator.hasPrevious()) {
                                    System.out.println("We have reached the start of your playlist.\n");
                                    printMenu();
                                }
                                break;
                            case 3:
                                    if(playlistIterator.hasPrevious()) {
                                        int index = playlistIterator.nextIndex();
                                        System.out.println("Replaying song: " + playlist.displayPlaylist().get(index-1) + "\n");
                                        printMenu();
                                        break;
                                    } else {
                                        System.out.println("Replaying song: " + playlist.displayPlaylist().get(0) + "\n");
                                        printMenu();
                                        break;
                                    }

                            case 4:
                                System.out.println("See you later!");
                                quit = true;

                        }
                    }
                case 6:
                    run = false;
                    scanner.close();
            }
        }
    }

    // Function to search through albums for specific album.
    public static void addAlbum(String albumName) {
        if (myAlbums.isEmpty()) {
            myAlbums.add(new Albums(albumName));
        } else if (!myAlbums.isEmpty()) {
            for (int i = 0; i < myAlbums.size(); i++) {
                if (myAlbums.get(i).getAlbumName().equals(albumName)) {
                    System.out.println("Album name already exists");
                    break;
                } else {
                    myAlbums.add(new Albums(albumName));
                }
            }
        }
    }

    // Function to search though albums for specific song.
    public static String findSong(String songName) {
        if (myAlbums.isEmpty()) {
            return "Your albums are empty";
        } else if (!myAlbums.isEmpty()) {
            for (int i = 0; i < myAlbums.size(); i++) {
                if (myAlbums.get(i).songExists(songName).equals(songName)) {
                    return myAlbums.get(i).songExists(songName).toLowerCase();
                } else {
                    return "Could not find your song.";
                }
            }
        }
        return null;
    }

    public static void printMenu() {
        System.out.println("1. Next song." + "\n2. Previous Song." + "\n3. Replay Current Song." + "\n4. Quit.\n");
    }
}

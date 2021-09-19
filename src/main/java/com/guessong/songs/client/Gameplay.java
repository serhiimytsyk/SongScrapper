package com.guessong.songs.client;

import com.guessong.songs.entity.Song;

import java.util.*;

public class Gameplay {
    private static final Scanner scanner = new Scanner(System.in);
    public int processOneRound(List<Song> songs) {
        System.out.println("New round!");
        Collections.shuffle(songs);
        for (int i = 0; i < songs.size(); i++) {
            Song song = songs.get(i);
            System.out.println((i + 1) + ". Song - " + song.getName() + ", author - " + song.getAuthor());
        }
        String correctAnswer = getCorrectAnswer(songs);

        System.out.println("Print numbers of all these songs from most to least popular");
        String input = scanner.nextLine().trim();

        if (correctAnswer.equals(input)) {
            System.out.println("Congratulations! You're correct!");
            return 1;
        } else {
            System.out.println("Oops. Correct order is " + correctAnswer);
            return 0;
        }
    }

    public String getCorrectAnswer(List<Song> songs) {
        StringBuilder sb = new StringBuilder();
        List<Song> sortedSongs = new ArrayList<>(songs);
        sortedSongs.sort(Comparator.comparingInt(Song::getPlace));
        for (Song song : sortedSongs) {
            sb.append(songs.indexOf(song) + 1).append(' ');
        }
        return sb.toString().trim();
    }

    public boolean askToStop() {
        System.out.println("Do you want to continue? Press N if no, otherwise press any other key");
        String input = scanner.nextLine();
        return input.startsWith("n") || input.startsWith("N");
    }

    public void printStats(int totalGames, int guesses) {
        System.out.println("You played " + totalGames + " games and won " + guesses + " of them.");
        double winRate = (guesses * 100.0) / totalGames;
        System.out.printf("Your win rate is %.2f", winRate);
        System.out.println("%");
    }
}

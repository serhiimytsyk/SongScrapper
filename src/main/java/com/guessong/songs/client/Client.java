package com.guessong.songs.client;

import com.guessong.songs.entity.Song;

import java.util.*;

public class Client {
    private static final int NUMBER_OF_NUMBERS_PER_GROUP = 3;
    private static final int NUMBER_OF_SONGS_PER_ROUND = 3;

    public static void main(String[] args) {
        SongSelector selector = new SongSelector();
        Gameplay gameplay = new Gameplay();
        try {
            List<Song> songs = selector.getSongList();
            int totalGames = 0;
            int guesses = 0;
            do {
                for (int i = 0; i < NUMBER_OF_NUMBERS_PER_GROUP; i++) {
                    List<Song> randomSongs = selector.getRandomSongs(songs, NUMBER_OF_SONGS_PER_ROUND);
                    totalGames++;
                    guesses += gameplay.processOneRound(randomSongs);
                }
            } while (!gameplay.askToStop());
            gameplay.printStats(totalGames, guesses);
        } catch (Exception e) {
            System.out.println("Something went wrong. Please check if server is started and restart the program");
        }
    }
}

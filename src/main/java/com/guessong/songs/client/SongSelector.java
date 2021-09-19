package com.guessong.songs.client;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.guessong.songs.entity.Song;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

public class SongSelector {
    private static final String URL = "http://localhost:8080/api/songs";

    public List<Song> getSongList() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder(
                URI.create(URL))
                .header("accept", "application/json")
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().
                send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(response.body(), new TypeReference<>(){});
    }

    public List<Song> getRandomSongs(List<Song> songs, int number) {
        List<Song> newSongs = new ArrayList<>();
        int size = songs.size();
        if (number <= size) {
            int minIndex = 0;
            int maxIndex = size - number;
            int index;
            for (int i = 0; i < number; i++) {
                index = minIndex + (int) (Math.random() * (maxIndex - minIndex + 1));
                minIndex = index + 1;
                maxIndex++;
                newSongs.add(songs.get(index));
            }
        }
        return newSongs;
    }
}

package com.guessong.songs.parser;

import com.guessong.songs.entity.Song;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HTMLParser {
    private static final int SONGS = 100;
    private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:23.0) Gecko/20100101 Firefox/23.0";

    public Document getDocument(String url) throws IOException {
        Connection.Response response = Jsoup.connect(url).header("Accept-Encoding", "gzip, deflate").
                userAgent(USER_AGENT).
                maxBodySize(0).
                timeout(600000).execute();
        return response.parse();
    }

    public List<Song> getSongs(Document document) {
        List<Song> songs = new ArrayList<>();
        Elements elements = document.getElementsByTag("html").get(0).
                getElementsByTag("body").get(0).
                getElementById("main").
                getElementById("charts").
                getElementsByTag("div").get(0).
                getElementsByClass("charts-container").get(0).
                getElementsByClass("chart-list container").get(0).
                getElementsByTag("ol").get(0).
                getElementsByTag("li");
        for (int i = 1; i <= SONGS; i++) {
            Element element = elements.get(i - 1);
            Song song = parseSong(element);
            song.setPlace(i);
            songs.add(song);
        }
        return songs;
    }

    public Song parseSong(Element element) {
        String name = element.
                getElementsByClass("chart-element__information__song text--truncate color--primary").
                get(0).text();
        String artist = element.
                getElementsByClass("chart-element__information__artist text--truncate color--secondary").
                get(0).text();
        return new Song(0, name, artist);
    }
}
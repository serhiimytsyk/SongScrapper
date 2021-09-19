package com.guessong.songs.controller;

import com.guessong.songs.entity.Song;
import com.guessong.songs.parser.HTMLParser;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class ApiController {
    private static final String URL = "https://www.billboard.com/charts/hot-100";

    private final HTMLParser parser;

    @Autowired
    public ApiController(HTMLParser parser) {
        this.parser = parser;
    }

    @GetMapping("/songs")
    public List<Song> getTop100Songs() throws IOException {
        Document document = parser.getDocument(URL);
        return parser.getSongs(document);
    }
}

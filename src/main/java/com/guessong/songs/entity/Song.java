package com.guessong.songs.entity;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Song {
    private int place;
    private String name;
    private String author;
}

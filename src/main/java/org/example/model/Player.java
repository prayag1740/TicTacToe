package org.example.model;

public class Player {

    public String name;
    public PlayingPiece playingPiece ;

    public Player(String name, PlayingPiece playingPiece) {
        this.name = name ;
        this.playingPiece = playingPiece ;
    }

    public String getName() {
        return name;
    }
}

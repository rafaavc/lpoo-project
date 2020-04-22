package com.g19.breakout;

import com.g19.breakout.elements.*;
import com.g19.breakout.graphics.Graphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;

import java.io.IOException;


public class ArenaView {
    private final Graphics graphics;
    //private TerminalScreen screen;
    private final String backgroundColor = "#000000";
    private final String playerBarColor = "#FFFFFF";

    public ArenaView(ArenaModel arena, Graphics graphics) throws IOException {
        this.graphics = graphics;
        graphics.init(arena.getWidth(), arena.getHeight());
    }

    public void draw(ArenaModel arena) throws IOException {
        graphics.startDrawing();

        drawBackground(arena);
        drawPlayerBar(arena.getPlayerBar());
        drawBall(arena.getBall());

        graphics.stopDrawing();
    }

    private void drawBall(Ball ball) {
        graphics.drawCenteredString(ball.getPosition(), "██", "#0000ff");
    }

    private void drawPlayerBar(PlayerBar playerBar) {
        graphics.drawCenteredString(playerBar.getPosition(), "██████", playerBarColor);
    }

    private void drawBackground(ArenaModel arena) {
        graphics.drawRectangle(new Position(0, 0), new Position(arena.getWidth(), arena.getHeight()), ' ', backgroundColor);
    }

    public ArenaController.COMMAND readInput() throws IOException {
        return graphics.readInput();
    }
}

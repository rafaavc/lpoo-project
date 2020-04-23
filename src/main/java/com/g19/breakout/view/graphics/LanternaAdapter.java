package com.g19.breakout.view.graphics;

import com.g19.breakout.model.ElementModel;
import com.g19.breakout.elements.Position;
import com.g19.breakout.view.ArenaView;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class LanternaAdapter implements Graphics {
    private TerminalScreen screen;
    private TextGraphics textGraphics;

    public LanternaAdapter(int terminalWidth, int terminalHeight) throws IOException {
        TerminalSize terminalSize = new TerminalSize(terminalWidth, terminalHeight);

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(terminalSize);

        Terminal terminal = terminalFactory.createTerminal();
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);   // we don't need a cursor
        screen.startScreen();             // screens must be started
        screen.doResizeIfNecessary();     // resize screen if necessary
    }

    public void startDrawing() {
        //screen.clear();
        textGraphics = screen.newTextGraphics();
    }

    public void stopDrawing() throws IOException {
        screen.refresh();
    }

    public void drawString(Position pos, String text, String foreColor) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString(foreColor));
        textGraphics.putString(pos.getDiscreteX(), pos.getDiscreteY(), text);
    }

    public void drawCenteredString(Position pos, String text, String foreColor){
        drawString(new Position(pos.getX()-(text.length()/2.), pos.getY()), text, foreColor);
    }

    public void drawString(Position pos, String text, String foreColor, String backColor) {
        textGraphics.setForegroundColor(TextColor.Factory.fromString(foreColor));
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(backColor));
        textGraphics.putString(pos.getDiscreteX(), pos.getDiscreteY(), text);
    }

    public void drawCenteredString(Position pos, String text, String foreColor, String backColor){
        drawString(new Position(pos.getX()-(text.length()/2.), pos.getY()), text, foreColor, backColor);
    }

    public void drawElement(ElementModel element, String stringRep, String color) {
        drawCenteredString(element.getPosition(), stringRep, color);
    }

    public void drawRectangle(Position leftUpperCorner, Position size, char fill, String backColor) {
        textGraphics.setBackgroundColor(TextColor.Factory.fromString(backColor));
        textGraphics.fillRectangle(
            new TerminalPosition(leftUpperCorner.getDiscreteX(),leftUpperCorner.getDiscreteY()),
            new TerminalSize(size.getDiscreteX(), size.getDiscreteY()),
            fill);
    }

    public ArenaView.Keys readInput() throws IOException {
        KeyStroke key = screen.pollInput();
        if (key != null) {
            KeyType keyType = key.getKeyType();
            if (keyType == KeyType.ArrowLeft) return ArenaView.Keys.ARROWLEFT;
            if (keyType == KeyType.ArrowRight) return ArenaView.Keys.ARROWRIGHT;
            if (keyType == KeyType.EOF) return ArenaView.Keys.EOF;
        }
        return ArenaView.Keys.NONE;
    }
}

package com.g19.breakout.model;

import com.g19.breakout.elements.Dimensions;
import com.g19.breakout.elements.Position;

public abstract class Element {
    Position position;
    Dimensions dimensions;

    public Element(Position position, Dimensions dimensions) {
        this.position = position;
        this.dimensions = dimensions;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Dimensions getDimensions() {
        return dimensions;
    }
}

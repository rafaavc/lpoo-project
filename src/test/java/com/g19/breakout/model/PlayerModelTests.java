package com.g19.breakout.model;

import com.g19.breakout.elements.Position;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PlayerModelTests {

    @Test
    public void playerBarConstructor(){
        PlayerModel playerBar = new PlayerModel(new Position(10, 5));

        assertEquals(10, playerBar.getPosition().getDiscreteX());
        assertEquals(5, playerBar.getPosition().getDiscreteY());
    }
}
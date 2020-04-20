package com.g19.breakout;

import com.g19.breakout.graphics.LanternaAdapter;

import java.io.IOException;

public class Game {
    public static void main(String[] args) throws IOException {
        Arena arena = new Arena(120, 40);
        View view = new View(arena, new LanternaAdapter());
        Controller controller = new Controller(arena);
        view.draw();
        while (controller.getNextCommand(view)){
            view.draw();
        }

    }
}

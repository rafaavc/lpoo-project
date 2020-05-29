package com.g19.breakout.controller.state;

import com.g19.breakout.controller.GameController;
import com.g19.breakout.controller.MenuController;
import com.g19.breakout.model.utilities.Position;
import com.g19.breakout.model.ElementModel;
import com.g19.breakout.model.PlayerModel;
import com.g19.breakout.view.GameOverView;
import com.g19.breakout.view.LeaderboardView;
import com.g19.breakout.view.MainMenuView;
import com.g19.breakout.view.PauseView;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

public class StateTests {

    @Test
    public void MainMenuGameStateTest(){
        GameController controller = Mockito.mock(GameController.class);
        MainMenuView mainMenuView = Mockito.mock(MainMenuView.class);
        MenuController menu = Mockito.mock(MenuController.class);
        PlayerModel playerModel = Mockito.mock(PlayerModel.class);
        StateFactory stateFactory = Mockito.mock(StateFactory.class);
        MainMenuGameState mainMenuGameState = new MainMenuGameState(controller, mainMenuView, menu, playerModel, stateFactory);

        Mockito.when(playerModel.getPosition()).thenReturn(new Position(10, 10));

        Mockito.doNothing().when(controller).moveElement(any(Position.class), any(ElementModel.class));

        assertTrue(mainMenuGameState.commandLeft());
        assertTrue(mainMenuGameState.commandRight());
        Mockito.verify(controller, Mockito.times(2)).moveElement(any(Position.class), any(ElementModel.class));

        assertFalse(mainMenuGameState.commandQ());

        PlayingGameState playingGameState = Mockito.mock(PlayingGameState.class);

        Mockito.when(stateFactory.createPlayingGameState(any(GameController.class))).thenReturn(playingGameState);
        Mockito.doNothing().when(controller).setState(any(GameState.class));
        assertTrue(mainMenuGameState.commandP());
        Mockito.verify(controller, Mockito.times(1)).setState(any(GameState.class));
    }

    @Test
    public void PauseGameStateTest(){
        PlayingGameState playingGameState = Mockito.mock(PlayingGameState.class);
        PlayerModel playerModel = Mockito.mock(PlayerModel.class);
        PauseView view = Mockito.mock(PauseView.class);
        GameController controller = Mockito.mock(GameController.class);
        MenuController menu = Mockito.mock(MenuController.class);
        StateFactory stateFactory = Mockito.mock(StateFactory.class);
        PauseGameState pauseGameState = new PauseGameState(playingGameState, playerModel, view, controller, menu, stateFactory);


        MainMenuGameState mainMenuGameState = Mockito.mock(MainMenuGameState.class);

        Mockito.when(stateFactory.createMainMenuGameState(any(GameController.class))).thenReturn(mainMenuGameState);

        Mockito.doNothing().when(controller).setState(any(GameState.class));

        assertTrue(pauseGameState.commandP());
        assertTrue(pauseGameState.commandQ());
        Mockito.verify(controller, Mockito.times(2)).setState(any(GameState.class));
    }

    @Test
    public void LeaderboardGameStateTest(){
        GameController gameController = Mockito.mock(GameController.class);
        LeaderboardView leaderboardView = Mockito.mock(LeaderboardView.class);
        MenuController menuController = Mockito.mock(MenuController.class);
        PlayerModel playerModel = new PlayerModel(new Position(10, 10));
        StateFactory stateFactory = Mockito.mock(StateFactory.class);
        LeaderboardGameState leaderboardGameState = new LeaderboardGameState(gameController, leaderboardView, menuController, playerModel, stateFactory);

        Mockito.when(stateFactory.createMainMenuGameState(gameController)).thenReturn(Mockito.mock(MainMenuGameState.class));
        Mockito.doNothing().when(gameController).setState(any(GameState.class));

        leaderboardGameState.commandQ();
        Mockito.verify(gameController, Mockito.times(1)).setState(any(MainMenuGameState.class));
    }

    @Test
    public  void GameOverGameStateTest(){
        GameController gameController = Mockito.mock(GameController.class);
        GameOverView gameOverView = Mockito.mock(GameOverView.class);
        MenuController menuController = Mockito.mock(MenuController.class);
        PlayerModel playerModel = new PlayerModel(new Position(10, 10));
        StateFactory stateFactory = Mockito.mock(StateFactory.class);
        Mockito.when(stateFactory.createMainMenuGameState(gameController)).thenReturn(Mockito.mock(MainMenuGameState.class));
        Mockito.doNothing().when(gameController).setState(any(GameState.class));

        GameOverGameState gameOverGameState = new GameOverGameState(playerModel, gameOverView, gameController, menuController, stateFactory);


        Mockito.when(stateFactory.createPlayingGameState(gameController)).thenReturn(Mockito.mock(PlayingGameState.class));
        Mockito.when(stateFactory.createMainMenuGameState(gameController)).thenReturn(Mockito.mock(MainMenuGameState.class));
        Mockito.doNothing().when(gameController).setState(any(GameState.class));

        gameOverGameState.commandQ();
        Mockito.verify(gameController, Mockito.times(1)).setState(any(MainMenuGameState.class));

        gameOverGameState.commandP();
        Mockito.verify(gameController, Mockito.times(1)).setState(any(PlayingGameState.class));

    }
}

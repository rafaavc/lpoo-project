package com.g19.breakout.model.factory;

import com.g19.breakout.model.utilities.Dimensions;
import com.g19.breakout.model.utilities.Position;
import com.g19.breakout.model.ArenaModel;
import com.g19.breakout.model.BallModel;
import com.g19.breakout.model.PlayerModel;

public interface ModelFactory {
    BallModel createBallModel(Dimensions arenaDimensions);
    PlayerModel createPlayerModel(Dimensions arenaDimensions);
    ArenaModel createArenaModel(Dimensions dimensions);
    PlayerModel createPlayerModel(Position position);
}

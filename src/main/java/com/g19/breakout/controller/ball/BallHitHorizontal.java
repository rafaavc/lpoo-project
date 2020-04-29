package com.g19.breakout.controller.ball;

import com.g19.breakout.model.BallModel;

public class BallHitHorizontal extends BallHit {
    private final BallHit ballHit;

    public BallHitHorizontal(BallModel ball, BallHit ballHit) {
        super(ball);
        this.ballHit = ballHit;
    }

    public void updateDirection() {
        ball.setDirection(ball.getDirection().hitTopOrBottom());
        if (ballHit != null) ballHit.updateDirection();
    }
}

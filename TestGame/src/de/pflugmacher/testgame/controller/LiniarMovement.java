package de.pflugmacher.testgame.controller;

import de.pflugmacher.testgame.model.GlobalPosition;

public class LiniarMovement implements MovementPattern {
    public double speed;
    public LiniarMovement(double startX, double startY,double speed){
        this.currentPos.x=startX;
        this.currentPos.y=startY;
        this.speed=speed;
    }
    

    @Override
    public void move(double delta) {
        // TODO Auto-generated method stub
        this.currentPos.y += this.speed * delta * 1.0;
    }

}

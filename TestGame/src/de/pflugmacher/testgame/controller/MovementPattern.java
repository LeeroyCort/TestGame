package de.pflugmacher.testgame.controller;

import de.pflugmacher.testgame.model.GlobalPosition;

public interface MovementPattern { 
    public GlobalPosition currentPos=new GlobalPosition();
    public void move(double delta);
}

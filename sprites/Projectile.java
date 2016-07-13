package com.dannykrebs.chroma.sprites;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public abstract class Projectile extends Sprite {
    protected Vector2 position;
    protected Vector2 velocity;
    protected Vector2 scaledVel;
    protected PlayChar owner;
    protected float damage;
    protected float speed;

    protected Projectile(){
        super();
        velocity = new Vector2();
        position = new Vector2();
        scaledVel = new Vector2();
    }

    protected abstract void update(float dt);
    public abstract void dispose();

    @Override
    public float getX() {
        return position.x;
    }

    @Override
    public float getY() {
        return position.y;
    }
}
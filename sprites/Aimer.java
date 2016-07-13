package com.dannykrebs.chroma.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Aimer {
    private Vector2 position;
    private float angle;
    private Texture aimImg;

    public Aimer(int x, int y) {
        position = new Vector2(x, y);
        aimImg = new Texture("aimer.gif");
    }

    public void update(float dt, Vector2 charPos) {
        this.position.x = (float) ((Math.cos(angle)) * 200 + charPos.x);
        this.position.y = (float) ((Math.sin(angle)) * 200 + charPos.y);
    }

    public Vector2 getPosition() {
        return position;
    }

    public Texture getAimImg() {
        return aimImg;
    }

    public void setAngle(float newAngle) {
        angle = newAngle;
    }

    public float getAngle(){
        return angle;
    }
}
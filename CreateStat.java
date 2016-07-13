package com.dannykrebs.chroma;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;

public class CreateStat{
    private String id;
    private boolean active;
    private ShapeRenderer shapeRenderer;
    private int x;
    private int y;
    private Color color;
    private float a;

    public CreateStat(String name, int x, int y, Color color){
        id = name;
        active = false;
        shapeRenderer = new ShapeRenderer();
        this.color = color;
        this.x = x;
        this.y = y;
    }

    public void draw(OrthographicCamera cam){
        shapeRenderer.setProjectionMatrix(cam.combined);
        if(active){
            a = (float) 0;
        }
        if(color == Color.YELLOW){
            shapeRenderer.setColor(1, 1, 0, 0.5f);
        }else if(color == Color.RED){
            shapeRenderer.setColor(1, 0, 0, a);
        }else if(color == Color.BLUE){
            shapeRenderer.setColor(0, 0, 1, a);
        }else if(color == Color.GRAY){
            shapeRenderer.setColor(1, 1, 1, a);
        }
        shapeRenderer.begin(ShapeType.Filled);
        shapeRenderer.rect(this.x, this.y, 50, 100);
        shapeRenderer.end();
    }
    public String getId() {
        return id;
    }

    public boolean isActive() {
        return active;
    }

    public void switchOnOff(){
        if(active){
            active = false;
        } else{
            active = true;
        }
    }
}

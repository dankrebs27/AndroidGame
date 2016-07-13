package com.dannykrebs.chroma.sprites;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class PlayChar extends Actor{
    private Vector2 position;
    private Vector2 velocity;
    private Vector2 scaledVel;
    private int width = 50;
    private int height = 50;
    private Aimer aimer;
    private float speedMod = 5;
    protected Texture basicImg;
    private Texture charImg;
    private boolean moving = false;
    protected float damage;
    protected float basicSpeed;
    private List<Projectile> projectileList;

    public PlayChar(int x, int y){
        position = new Vector2(x, y);
        velocity = new Vector2(0, 0);
        charImg = new Texture("circle-256.gif");
        basicImg = new Texture("misc.png");
        aimer = new Aimer(50, 250);
        scaledVel = new Vector2();
        projectileList = new ArrayList<Projectile>();
        basicSpeed = 800;
    }

    public void update(float dt){
        if (moving){
            scaledVel.x = (velocity.x*speedMod)*dt;
            scaledVel.y = (velocity.y*speedMod)*dt;
            position.add(scaledVel);
        }
        aimer.update(dt, position);
        if(projectileList.size() > 0){
            for(Projectile p : projectileList){
                p.update(dt);
            }
        }
    }

    public Vector2 getPosition() {
        return position;
    }

    public float getX(){
        return position.x;
    }

    public float getY(){
        return position.y;
    }

    public Texture getCharImg() {
        return charImg;
    }

    public void setVelocity(Vector2 addVel){
        velocity = addVel;
    }

    public void moveSwitch(boolean move){
        moving = move;
    }

    public Aimer getAimer(){
        return aimer;
    }

    public void basicAttack(){
        projectileList.add(new BasicAttack(this));
    }

    public Texture getBasicImg(){
        return basicImg;
    }

    public List<Projectile> getProjectiles(){
        return projectileList;
    }
}

package com.dannykrebs.chroma;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.dannykrebs.chroma.sprites.PlayChar;

public class Control implements InputProcessor{
    private Rectangle controlBox;
    private Rectangle moveRect;
    private Rectangle aimRect;
    private Rectangle scrollRect;
    private Vector2 aimAnchor;
    private Texture controlImg;
    private Vector2 initialChar = new Vector2();
    private Vector2 deltaChar = new Vector2();
    private Vector2 deltaAim = new Vector2();
    private Vector2 initialScroll = new Vector2();
    private Integer charIndex = 5;
    private Integer aimIndex = 5;
    private Integer scrollIndex = 5;
    private Vector3 touch = new Vector3();
    private OrthographicCamera cam;
    private PlayChar playChar;
    private float topY;

    public Control(OrthographicCamera camera, PlayChar playerChar){
        controlBox = new Rectangle(0, 0, 480, 120);
        moveRect = new Rectangle(0, 0, 215, 120);
        aimRect = new Rectangle(215, 0, 215, 120);
        scrollRect = new Rectangle(430, 0 , 50, 120);
        controlImg = new Texture("controls1.png");
        aimAnchor = new Vector2(322, -60);
        cam = camera;
        playChar = playerChar;
        topY = 0;
    }

    public void update(){
        controlBox.setPosition(0, (cam.position.y - (cam.viewportHeight / 2)));
        moveRect.setPosition(0, controlBox.getY());
        aimRect.setPosition(215, controlBox.getY());
        scrollRect.setPosition(430, controlBox.getY());
        aimAnchor.y = controlBox.getY() + 60;
        topY = controlBox.getY() + 120;
    }

    public Texture getImage(){
        return controlImg;
    }

    public float getY(){
        return controlBox.getY();
    }

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        cam.unproject(touch.set(screenX, screenY, 0));

        if(touch.x < 215 && touch.y < topY){
            playChar.moveSwitch(true);
            initialChar = new Vector2(touch.x, touch.y);
            charIndex = pointer;
        }

        if(touch.x > 215 && touch.y < topY){
            aimIndex = pointer;
        }

        if(touch.x > 430 && touch.y < topY){
            scrollIndex = pointer;
            initialScroll.x = touch.x;
            initialScroll.y = touch.y;
        }

        if(touch.y > 300){
            playChar.basicAttack();
        }

        System.out.println(aimAnchor);
        System.out.println(touch);

        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        cam.unproject(touch.set(screenX, screenY, 0));
        if(pointer == charIndex){
            playChar.moveSwitch(false);
            deltaChar.x = 0;
            deltaChar.y = 0;
            charIndex = 5;
        }

        if(pointer == aimIndex){
            deltaAim.x = 0;
            deltaAim.y = 0;
            aimIndex = 5;
        }

        if(pointer == scrollIndex){
            scrollIndex = 5;

        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        cam.unproject(touch.set(screenX, screenY, 0));

        if(pointer == charIndex){
            Vector2 newTouch = new Vector2(touch.x, touch.y);
            deltaChar = newTouch.cpy().sub(initialChar);

            if(deltaChar.x > 100) deltaChar.x = 100;
            if(deltaChar.x < -100) deltaChar.x = -100;
            if(deltaChar.y > 100) deltaChar.y = 100;
            if(deltaChar.y < -100) deltaChar.y = -100;

            playChar.setVelocity(deltaChar);
        }

        if(pointer ==  aimIndex){
            float rad = (float) Math.atan2((touch.y - aimAnchor.y), (touch.x - aimAnchor.x));
            playChar.getAimer().setAngle(rad);
        }

        if(pointer == scrollIndex){
            if(touch.y > initialScroll.y){
                cam.translate(0, 10);
            }
            else if(touch.y < initialScroll.y){
                cam.translate(0, -10);
            }
        }
        return true;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}

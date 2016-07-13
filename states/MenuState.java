package com.dannykrebs.chroma.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.dannykrebs.chroma.Chroma;

public class MenuState extends State implements InputProcessor {
    private Texture background;
    private Texture playBtn;
    private Vector3 touch = new Vector3();

    public MenuState(GameStateManager gsm) {
        super(gsm);
        background = new Texture("bg.png");
        playBtn = new Texture("play.png");
        cam.setToOrtho(false, Chroma.WIDTH, Chroma.HEIGHT);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void handleInput() {

    }

    @Override
    protected void update(float dt) {
        cam.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(background, 0, 0);
        sb.draw(playBtn, 210, 500);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        playBtn.dispose();
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

        if(touch.x > 210 && touch.x < (210+playBtn.getWidth()) && touch.y > 500 && touch.y < 500+(playBtn.getHeight())){
            gsm.set(new PlayState(gsm));
            dispose();
        }

        if(touch.y < 300){
            gsm.set(new CreateState(gsm));
            dispose();
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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

package com.dannykrebs.chroma.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.dannykrebs.chroma.Chroma;
import com.dannykrebs.chroma.Control;
import com.dannykrebs.chroma.sprites.PlayChar;
import com.dannykrebs.chroma.sprites.Projectile;

public class PlayState extends State {
    public PlayChar playChar;
    private Texture mapTexture;
    private Control vController;

    public PlayState(GameStateManager gsm) {
        super(gsm);
        playChar = new PlayChar(50, 150);
        cam.setToOrtho(false, Chroma.WIDTH, Chroma.HEIGHT);
        cam.position.y = 280;
        mapTexture = new Texture("bg.png");
        vController = new Control(cam, playChar);
        Gdx.input.setInputProcessor(vController);
    }

    @Override
    protected void handleInput() {
    }

    @Override
    protected void update(float dt) {
        playChar.update(dt);
        cam.update();
        vController.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        sb.draw(mapTexture, 0, 0);
        sb.draw(playChar.getCharImg(), playChar.getPosition().x, playChar.getPosition().y);
        sb.draw(vController.getImage(), 0, vController.getY(), cam.viewportWidth, 120);
        sb.draw(playChar.getAimer().getAimImg(), playChar.getAimer().getPosition().x, playChar.getAimer().getPosition().y);
        for(Projectile p : playChar.getProjectiles()){
            sb.draw(playChar.getBasicImg(), p.getX(), p.getY());
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }

    public PlayChar getPlayer() {
        return playChar;
    }


    public Control getController() {
        return vController;
    }
}
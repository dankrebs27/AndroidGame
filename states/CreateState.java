package com.dannykrebs.chroma.states;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.dannykrebs.chroma.Chroma;
import com.dannykrebs.chroma.CreateStat;

import java.util.ArrayList;
import java.util.List;

public class CreateState extends State {
    private Texture background;
    private Button startBtn;
    private SpriteDrawable drawable;
    private SpriteDrawable drawable2;
    private SpriteDrawable drawableMain;
    private Stage stage;
    private Image bgImage;
    private Image mainImage;
    private List<CreateStat> yellowStack;
    private List<CreateStat> redStack;
    private List<CreateStat> blueStack;
    private List<CreateStat> whiteStack;
    private List<List> allStacks;
    private FitViewport viewport;
    private CreateStat testStat;

    public CreateState(GameStateManager gsm) {
        super(gsm);
        cam.setToOrtho(false, Chroma.WIDTH, Chroma.HEIGHT);
        viewport = new FitViewport(Chroma.WIDTH, Chroma.HEIGHT, cam);
        //Create background image
        background = new Texture("createbg.png");
        bgImage = new Image(background);
        //Create play button
        drawable = new SpriteDrawable(new Sprite(new Texture("play.png")));
        drawable2 = new SpriteDrawable(new Sprite(new Texture("play.png")));
        startBtn = new Button(drawable, drawable2);
        //Create main image character thing
        mainImage = new Image(new Texture("createmain.png"));
        //Setup stat Icons
        allStacks = new ArrayList();
        this.setYellows();
        this.setReds();
        this.setBlues();
        this.setWhites();

//        yellow1.addListener(new ClickListener() {
//            @Override
//            public void clicked(InputEvent event, float x, float y) {
//                System.out.println("Weeeooooo");
//            }
//        });
        bgImage.addListener(new ActorGestureListener() {
            @Override
            public void pan(InputEvent event, float x, float y, float deltaX, float deltaY) {
                cam.translate(deltaX/2, deltaY/2);
                System.out.println("paaaaning!");
                System.out.println(cam.position);
                System.out.println(mainImage.getX());
            }

            @Override
            public void zoom(InputEvent event, float initialDistance, float distance) {
                cam.zoom += (distance - initialDistance) / 10000;
            }
        });

        stage = new Stage(viewport);
        //Add actors
        stage.addActor(bgImage);
        stage.addActor(mainImage);
        stage.addActor(startBtn);
        mainImage.setPosition(165, 90);

        Gdx.input.setInputProcessor(stage);
    }

    @Override
    protected void handleInput() {

    }

    @Override
    protected void update(float dt) {
        cam.update();
        stage.act(dt);
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(cam.combined);
        sb.begin();
        stage.draw();
        for(List c : allStacks){
            c.draw(cam);
        }
        for(CreateStat c : redStack){
            c.draw(cam);
        }
        for(CreateStat c : blueStack){
            c.draw(cam);
        }
        for(CreateStat c : whiteStack){
            c.draw(cam);
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }

    private void setYellows(){
        CreateStat yellow1 = new CreateStat("hello", 35, 250, Color.YELLOW);
        CreateStat yellow2 = new CreateStat("hello", 35, 360, Color.YELLOW);
        CreateStat yellow3 = new CreateStat("hello", 35, 470, Color.YELLOW);
        CreateStat yellow4 = new CreateStat("hello", 35, 580, Color.YELLOW);
        CreateStat yellow5 = new CreateStat("hello", 35, 690, Color.YELLOW);
        yellowStack = new ArrayList();
        yellowStack.add(yellow1);
        yellowStack.add(yellow2);
        yellowStack.add(yellow3);
        yellowStack.add(yellow4);
        yellowStack.add(yellow5);
        allStacks.add(yellowStack);
    }

    private void setReds(){
        CreateStat red1 = new CreateStat("hello", 155, 250, Color.RED);
        CreateStat red2 = new CreateStat("hello", 155, 360, Color.RED);
        CreateStat red3 = new CreateStat("hello", 155, 470, Color.RED);
        CreateStat red4 = new CreateStat("hello", 155, 580, Color.RED);
        CreateStat red5 = new CreateStat("hello", 155, 690, Color.RED);
        redStack = new ArrayList();
        redStack.add(red1);
        redStack.add(red2);
        redStack.add(red3);
        redStack.add(red4);
        redStack.add(red5);
        allStacks.add(redStack);
    }

    private void setBlues(){
        CreateStat blue1 = new CreateStat("hello", 275, 250, Color.BLUE);
        CreateStat blue2 = new CreateStat("hello", 275, 360, Color.BLUE);
        CreateStat blue3 = new CreateStat("hello", 275, 470, Color.BLUE);
        CreateStat blue4 = new CreateStat("hello", 275, 580, Color.BLUE);
        CreateStat blue5 = new CreateStat("hello", 275, 690, Color.BLUE);
        blueStack = new ArrayList();
        blueStack.add(blue1);
        blueStack.add(blue2);
        blueStack.add(blue3);
        blueStack.add(blue4);
        blueStack.add(blue5);
        allStacks.add(blueStack);
    }

    private void setWhites(){
        CreateStat white1 = new CreateStat("hello", 395, 250, Color.WHITE);
        CreateStat white2 = new CreateStat("hello", 395, 360, Color.WHITE);
        CreateStat white3 = new CreateStat("hello", 395, 470, Color.WHITE);
        CreateStat white4 = new CreateStat("hello", 395, 580, Color.WHITE);
        CreateStat white5 = new CreateStat("hello", 395, 690, Color.WHITE);
        whiteStack = new ArrayList();
        whiteStack.add(white1);
        whiteStack.add(white2);
        whiteStack.add(white3);
        whiteStack.add(white4);
        whiteStack.add(white5);
        allStacks.add(whiteStack);
    }
}

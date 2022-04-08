package com.metalball.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.metalball.MetalBallApp;
import com.metalball.screens.GameScreen;

import static com.metalball.util.Constants.METERS_X_LEVEL;
import static com.metalball.util.Constants.PIXELS_IN_METER;
import static com.metalball.util.Constants.PLAYER_INIT_Y;
import static com.metalball.util.Constants.PLAYER_SPPED;
import static com.metalball.util.Constants.WORLD_HEIGHT;
import static com.metalball.util.Constants.WORLD_WIDTH;

public class GameGUI implements Disposable {

    GameScreen gameScreen;

    public Stage stage;

    private MetalBallApp game;

    private TextButton pause;

    private Label coinsLabel;
    private Texture coinTexture;
    private Integer coins;

    private Label altitudeLabel;
    private Label altitudeWarnLabel;
    private float altitude;


    public GameGUI(MetalBallApp game, final GameScreen gameScreen){
        this.game = game;
        this.gameScreen = gameScreen;

        stage = new Stage((new FillViewport(WORLD_WIDTH,WORLD_HEIGHT)));

        //GUI--
        coins = 0;
        altitude = 999;
        //Generate the font
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/dealerplate california.ttf"));
        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        //Set the font size (12 as an example)
        param.size = 120;
        style.font = generator.generateFont(param);
        //Set the new style to the button
        pause = new TextButton("=", style);
        pause.setSize(200, 200);
        pause.setPosition(450, 100);
        pause.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                gameScreen.getClickSound().play();
                if(pause.getText().toString().equals("=")){
                    pause.setText(">");
                    gameScreen.pause();
                }else{
                    pause.setText("=");
                    gameScreen.resume();
                }
            }
        });

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        param.size = 80;
        labelStyle.font = generator.generateFont(param);
        //Set the new style to the button
        coinsLabel = new Label(String.format("%03d", coins), labelStyle);
        coinsLabel.setPosition(40, 1800);

        coinTexture = this.game.getManager().get("images/coin/COIN0003.png");
        Image itemImage = new Image();
        itemImage.setPosition(140, 1810);
        itemImage.setDrawable(new TextureRegionDrawable(new TextureRegion(coinTexture)));
        itemImage.setSize(80, 80);

        stage.addActor(coinsLabel);
        stage.addActor(itemImage);

        altitudeLabel = new Label(String.format("%04d", new Float(altitude).intValue())+" m", labelStyle);
        altitudeLabel.setPosition(300, 1800);
        stage.addActor(altitudeLabel);

        altitudeWarnLabel = new Label("", labelStyle);
        altitudeWarnLabel.setPosition(300, 900);
        stage.addActor(altitudeWarnLabel);

        Skin skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        final Touchpad touchpad = new Touchpad(20, skin);
        touchpad.setBounds(0, 0, 1080, 1980);
        touchpad.setColor(0,0,0,0);
        stage.addActor(touchpad);

        stage.addListener(new ActorGestureListener() {
            public void fling (InputEvent event, float velocityX, float velocityY, int button) {
                System.out.println("swipe!! " + velocityX + ", " + velocityY);
                if(Math.abs(velocityX)>Math.abs(velocityY)){
                    if(velocityX>0){
                        //RIGHT
                    }else{
                        //LEFT
                    }
                }else{
                    if(velocityY>0){
                        //UP
                        gameScreen.player.brake();
                    }else{
                        gameScreen.player.speedUp();
                        //DOWN
                    }
                }
            }
        });
        touchpad.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // This is run when anything is changed on this actor.
                float deltaX = ((Touchpad) actor).getKnobPercentX();
                float deltaY = ((Touchpad) actor).getKnobPercentY();
                    gameScreen.player.getBody().setLinearVelocity(deltaX*PLAYER_SPPED*2, gameScreen.player.getBody().getLinearVelocity().y);
                    if(deltaX<0) {
                        gameScreen.player.setxOriented(0);
                    }else{
                        gameScreen.player.setxOriented(1);
                    }

            }

        });
        Table table = new Table();
        table.setPosition(400,900);
        table.setFillParent(true);
        table.add(pause).width(200).height(200);
        stage.addActor(table);
//        table.setDebug(true); // This is optional, but enables debug lines for tables.

    }
    @Override
    public void dispose() {
        stage.dispose();
    }

    public void addCoin(){
        coins++;
        coinsLabel.setText(String.format("%03d", coins));
    }

    float currentNextAlarm = 900;
    float winY = 0;
    public void updateAltitude(float yPos){
        altitude = METERS_X_LEVEL-((PLAYER_INIT_Y-yPos)/PIXELS_IN_METER);
        altitudeLabel.setText(String.format("%04d", new Integer(new Float(altitude).intValue()))+" m");
        if(altitude<currentNextAlarm && currentNextAlarm>0){
            if(currentNextAlarm==50){
                if(altitude<10){
                    currentNextAlarm=0;
                }
                altitudeWarnLabel.setText("50 Meters! Opening Parachute!");
                altitudeWarnLabel.addAction(Actions.sequence(
                        Actions.moveTo(300, 1200, 0.5f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                altitudeWarnLabel.setText("");
                            }
                        }),
                        Actions.moveTo(300, 900)));
                gameScreen.getWarnSound().play();
            }else {
                altitudeWarnLabel.setText("Last " + currentNextAlarm + " Meters");
                altitudeWarnLabel.addAction(Actions.sequence(
                        Actions.moveTo(300, 1200, 1f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                altitudeWarnLabel.setText("");
                            }
                        }),
                        Actions.moveTo(300, 900)));
                if (currentNextAlarm == 100) {
                    currentNextAlarm = 50;
                } else {
                    currentNextAlarm = currentNextAlarm - 100;
                }
                gameScreen.getWarnSound().play();
            }

        }
    }

    public void clearValues() {
        coins=0;
        coinsLabel.setText(String.format("%03d", coins));
        altitude=999;
        altitudeLabel.setText(String.format("%04d", new Float(altitude).intValue())+" m");
        altitudeWarnLabel.setText("");
    }

    public boolean checkWin(float y) {
        if(altitude<=0){
            if(!gameScreen.player.isWin()) {
                altitudeWarnLabel.setText("Level Succeed!");
                altitudeWarnLabel.addAction(Actions.sequence(
                        Actions.moveTo(300, 1200, 0.5f),
                        Actions.run(new Runnable() {
                            @Override
                            public void run() {
                                altitudeWarnLabel.setText("");
                            }
                        }),
                        Actions.moveTo(300, 900)));
            }
            return true;
        }
        return false;
    }
}
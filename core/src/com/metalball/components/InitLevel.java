package com.metalball.components;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.metalball.MetalBallApp;
import com.metalball.levels.backgrounds.Background;
import com.metalball.screens.GameScreen;
import com.metalball.util.State;

import static com.metalball.util.Constants.WORLD_HEIGHT;
import static com.metalball.util.Constants.WORLD_WIDTH;

public class InitLevel implements Disposable {

    private MetalBallApp game;

    private GameScreen gameScreen;

    public Stage stage;

    private Texture planeTexture;

    private Texture backgroundTexture;

    private Texture foxTexture,
            cloudTexture1,cloudTexture2,cloudTexture3,cloudTexture4;

    private Image plane, background, fox, cloud1,cloud2,cloud3,cloud4;

    private Music planeSound;

    public InitLevel(MetalBallApp game, final GameScreen gameScreen){
        this.game = game;
        this.gameScreen = gameScreen;

        stage = new Stage((new FillViewport(WORLD_WIDTH,WORLD_HEIGHT)));

        backgroundTexture = this.game.getManager().get("images/background.jpg");
        background = new Image();
        background.setPosition(0, -Background.BG_HEIGHT +WORLD_HEIGHT);
        background.setDrawable(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
        background.setSize(WORLD_WIDTH, Background.BG_HEIGHT);

        stage.addActor(background);


        cloudTexture1=game.getManager().get("images/clouds/Nuvol_1.png");
        cloudTexture2=game.getManager().get("images/clouds/Nuvol_2.png");
        cloudTexture3=game.getManager().get("images/clouds/Nuvol_3.png");
        cloudTexture4=game.getManager().get("images/clouds/Nuvol_4.png");
        cloud1=new Image();
        cloud1.setPosition(-400, 800);
        cloud1.setDrawable(new TextureRegionDrawable(new TextureRegion(cloudTexture1)));
        cloud1.setSize(300, 125);
        cloud2=new Image();
        cloud2.setPosition(-400, 900);
        cloud2.setDrawable(new TextureRegionDrawable(new TextureRegion(cloudTexture2)));
        cloud2.setSize(300, 125);
        cloud3=new Image();
        cloud3.setPosition(-700, 1000);
        cloud3.setDrawable(new TextureRegionDrawable(new TextureRegion(cloudTexture3)));
        cloud3.setSize(300, 125);
        cloud4=new Image();
        cloud4.setPosition(-700, 1300);
        cloud4.setDrawable(new TextureRegionDrawable(new TextureRegion(cloudTexture4)));
        cloud4.setSize(300, 125);
        stage.addActor(cloud1);
        stage.addActor(cloud2);
        stage.addActor(cloud3);
        stage.addActor(cloud4);

        foxTexture=game.getManager().get("images/player/fox.png");
        fox=new Image();
        fox.setPosition(800, 1100);
        fox.setDrawable(new TextureRegionDrawable(new TextureRegion(foxTexture)));
        fox.setSize(300, 300);

        planeTexture=game.getManager().get("images/avioneta/Avioneta_Sprits0000.png");
        plane = new Image();
        plane.setPosition(1080, 1500);
        plane.setDrawable(new TextureRegionDrawable(new TextureRegion(planeTexture)));
        plane.setSize(450, 253);
        stage.addActor(plane);

        planeSound = game.getManager().get("audio/plane.mp3");

        plane.addAction(Actions.sequence(
                                        Actions.run(new Runnable() {
                                            @Override
                                            public void run() {
                                                isTurning=false;
                                                planeSound.play();
                                            }
                                        }),
                                        Actions.parallel(
                                                Actions.moveTo(800, 900, 03f),
                                                Actions.run(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        cloud1.addAction(Actions.moveTo(2000,800, 3f));
                                                    }
                                                })),
                                        Actions.parallel(
                                                Actions.moveTo(800, 1000, 03f),
                                                Actions.run(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        cloud2.addAction(Actions.moveTo(2000,900, 3f));
                                                        cloud4.addAction(Actions.moveTo(1700,1300, 3f));
                                                        cloud1.addAction(Actions.moveTo(-400,800));
                                                    }
                                                })),
                                        Actions.parallel(
                                                Actions.moveTo(800, 950, 03f),
                                                Actions.run(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        cloud1.addAction(Actions.moveTo(2000,800, 3f));
                                                        cloud3.addAction(Actions.moveTo(1700,1000, 3f));
                                                        cloud2.addAction(Actions.moveTo(-400,900));
                                                    }
                                                })),
                                        Actions.parallel(
                                                Actions.run(new Runnable() {
                                                    @Override
                                                    public void run() {isTurning=true;}}),
                                                Actions.moveTo(0, 500, 3f),
                                                Actions.scaleBy(1.5f,1.5f, 3f)
                                         ),
                                         Actions.parallel(
                                                 Actions.moveTo(300, 500, 3f),
                                                 Actions.scaleBy(1.5f,1.5f, 3f)),

                                         Actions.run(new Runnable() {
                                             @Override
                                             public void run() {
                                                 stage.addActor(fox);
                                                 gameScreen.getJumpSound().play();
                                                 fox.addAction(Actions.sequence(
                                                                    Actions.moveTo(300, 500, 1f),
                                                                    Actions.moveTo(100, -500, 1f),
                                                                     Actions.run(new Runnable() {
                                                                         @Override
                                                                         public void run() {
                                                                             planeSound.stop();
                                                                             gameScreen.getBgMusic().play();
                                                                             gameScreen.getBgMusic().setVolume(0.75f);
                                                                             gameScreen.setState(State.RUN);
                                                                         }
                                                                     })));
                                             }
                                         } )));
    }
    @Override
    public void dispose() {
        stage.dispose();
    }

    private int spriteCounter =0;
    private int textureCounter =0;
    private int direction=1;
    private boolean isTurning = false;
    public void updatePlane(){
        spriteCounter++;
        if(!isTurning) {
            if (spriteCounter % 10 == 0) {
                if(direction==1) {
                    textureCounter++;
                }else{
                    textureCounter--;
                }
                if (textureCounter > 5) {
                    direction=-1;
                    textureCounter--;
                }
                if (textureCounter < 0) {
                    direction=1;
                    textureCounter++;
                }
            }
        }else{
            if (spriteCounter % 10 == 0) {
                if(direction==1) {
                    textureCounter++;
                }else{
                    textureCounter--;
                }
                if (textureCounter > 15) {
                    direction=-1;
                    textureCounter--;
                }
                if (textureCounter < 6 && direction==-1) {
                    direction=1;
                    textureCounter++;
                }
            }
        }

        planeTexture=game.getManager().get("images/avioneta/Avioneta_Sprits00"+String.format("%02d", new Float(textureCounter).intValue())+".png");
        plane.setDrawable(new TextureRegionDrawable(new TextureRegion(planeTexture)));
    }

}
package com.metalball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.metalball.MetalBallApp;

import static com.metalball.util.Constants.WORLD_HEIGHT;
import static com.metalball.util.Constants.WORLD_WIDTH;

public class MenuScreen extends BaseScreen {
    private Stage stage;

    private Skin skin;

    private Image background, logo;

    private Texture playTextureUp,playTextureDown;
    private TextureRegion playTextureRegionUp,playTextureRegionDown;
    private TextureRegionDrawable playTextureRegionDrawableUp,playTextureRegionDrawableDown;
    private ImageButton playButton;

    private Texture settingsTextureUp,settingsTextureDown;
    private TextureRegion settingsTextureRegionUp,settingsTextureRegionDown;
    private TextureRegionDrawable settingsTextureRegionDrawableUp,settingsTextureRegionDrawableDown;
    private ImageButton settingsButton;

    private Texture shopTextureUp,shopTextureDown;
    private TextureRegion shopTextureRegionUp,shopTextureRegionDown;
    private TextureRegionDrawable shopTextureRegionDrawableUp,shopTextureRegionDrawableDown;
    private ImageButton shopButton;

    private Texture userTextureUp,userTextureDown;
    private TextureRegion userTextureRegionUp,userTextureRegionDown;
    private TextureRegionDrawable userTextureRegionDrawableUp,userTextureRegionDrawableDown;
    private ImageButton userButton;

    public Music menuSong;

    private Sound clickSound;

    public MenuScreen(final MetalBallApp game) {
        super(game);

        stage = new Stage(new FillViewport(WORLD_WIDTH, WORLD_HEIGHT));
        skin = new Skin(Gdx.files.internal("skin/uiskin.json"));
        clickSound = game.getManager().get("audio/click.ogg");

        background = new Image(game.getManager().get("images/MenuAssets/BG/MenuLayout_BG.jpg", Texture.class));
        background.setSize(WORLD_WIDTH,WORLD_HEIGHT);
        background.setPosition(0, 0);

        logo = new Image(game.getManager().get("images/MenuAssets/Logo/Logo.png", Texture.class));
        logo.setSize(758,556);
        logo.setPosition(WORLD_WIDTH/2 - logo.getWidth() / 2, 635 + WORLD_HEIGHT/2 - logo.getHeight() / 2);


        playTextureUp = new Texture(Gdx.files.internal("images/MenuAssets/Buttons/Play/Play.png"));
        playTextureRegionUp = new TextureRegion(playTextureUp);
        playTextureRegionDrawableUp = new TextureRegionDrawable(playTextureRegionUp);
        playTextureDown = new Texture(Gdx.files.internal("images/MenuAssets/Buttons/Play/Play_ON.png"));
        playTextureRegionDown = new TextureRegion(playTextureDown);
        playTextureRegionDrawableDown = new TextureRegionDrawable(playTextureRegionDown);
        playButton = new ImageButton(playTextureRegionDrawableUp,playTextureRegionDrawableDown); //Set the button up
        playButton.setSize(572,293);
        playButton.setPosition(270, 350);
        playButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
                game.setScreen(game.levelSelectionScreen);
//                game.gameScreen.setState(State.INIT);
//                game.gameScreen.init = new InitLevel(game, game.gameScreen);
//                game.setScreen(game.gameScreen);
            }
        });

        settingsTextureUp = new Texture(Gdx.files.internal("images/MenuAssets/Buttons/Settings/Settings.png"));
        settingsTextureRegionUp = new TextureRegion(settingsTextureUp);
        settingsTextureRegionDrawableUp = new TextureRegionDrawable(settingsTextureRegionUp);
        settingsTextureDown = new Texture(Gdx.files.internal("images/MenuAssets/Buttons/Settings/Settings_ON.png"));
        settingsTextureRegionDown = new TextureRegion(settingsTextureDown);
        settingsTextureRegionDrawableDown = new TextureRegionDrawable(settingsTextureRegionDown);
        settingsButton = new ImageButton(settingsTextureRegionDrawableUp,settingsTextureRegionDrawableDown); //Set the button up
        settingsButton.setSize(252,260);
        settingsButton.setPosition(50, 50);
        settingsButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
            }
        });

        shopTextureUp = new Texture(Gdx.files.internal("images/MenuAssets/Buttons/Shop/Shop.png"));
        shopTextureRegionUp = new TextureRegion(shopTextureUp);
        shopTextureRegionDrawableUp = new TextureRegionDrawable(shopTextureRegionUp);
        shopTextureDown = new Texture(Gdx.files.internal("images/MenuAssets/Buttons/Shop/Shop_ON.png"));
        shopTextureRegionDown = new TextureRegion(shopTextureDown);
        shopTextureRegionDrawableDown = new TextureRegionDrawable(shopTextureRegionDown);
        shopButton = new ImageButton(shopTextureRegionDrawableUp,shopTextureRegionDrawableDown); //Set the button up
        shopButton.setSize(252,260);
        shopButton.setPosition(425, 50);
        shopButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
            }
        });

        userTextureUp = new Texture(Gdx.files.internal("images/MenuAssets/Buttons/User/User.png"));
        userTextureRegionUp = new TextureRegion(userTextureUp);
        userTextureRegionDrawableUp = new TextureRegionDrawable(userTextureRegionUp);
        userTextureDown = new Texture(Gdx.files.internal("images/MenuAssets/Buttons/User/User_ON.png"));
        userTextureRegionDown = new TextureRegion(userTextureDown);
        userTextureRegionDrawableDown = new TextureRegionDrawable(userTextureRegionDown);
        userButton = new ImageButton(userTextureRegionDrawableUp,userTextureRegionDrawableDown); //Set the button up
        userButton.setSize(252,260);
        userButton.setPosition(800, 50);
        userButton.addCaptureListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                clickSound.play();
            }
        });

        stage.addActor(background);
        stage.addActor(playButton);
        stage.addActor(settingsButton);
        stage.addActor(shopButton);
        stage.addActor(userButton);
        stage.addActor(logo);
        menuSong= game.getManager().get("audio/menu_song.ogg");
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        menuSong.setLooping(true);
        menuSong.play();
    }

    @Override
    public void hide() {
        menuSong.stop();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        skin.dispose();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}

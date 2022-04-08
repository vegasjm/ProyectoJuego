package com.metalball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.ui.ImageTextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.SpriteDrawable;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.metalball.MetalBallApp;
import com.metalball.components.InitLevel;
import com.metalball.util.State;

import java.util.ArrayList;
import java.util.List;

import static com.metalball.util.Constants.WORLD_HEIGHT;
import static com.metalball.util.Constants.WORLD_WIDTH;

public class LevelSelectionScreen extends BaseScreen{
    private Stage stage;
    private Image background;
    private List<TextButton> buttons;
    private Sound clickSound;

    public Music bgSong;

    private float[][] levelPos = new float[][]{
            { 70, 290}, { 500, 340},{ 850, 465}, { 600, 570},{ 250, 590},
            { 70, 735}, { 250, 870},{ 575, 830}, { 820, 850},{ 800, 1120},
            { 450, 1140}, { 100, 1160},{ 100, 1400}, { 380, 1410},{ 760, 1425},
            { 950, 1675}, { 500, 1625},{ 175, 1660}, { 325, 1900},{ 700, 1900}};
    public LevelSelectionScreen(final MetalBallApp game) {
        super(game);
        stage = new Stage(new FillViewport(WORLD_WIDTH, WORLD_HEIGHT));
        clickSound = game.getManager().get("audio/click.ogg");
        background = new Image(game.getManager().get("images/world/WorldBG.jpg", Texture.class));
        background.setSize(WORLD_WIDTH,4218);
        background.setPosition(0, 0);
        stage.addActor(background);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Revamped.otf"));
        FreeTypeFontGenerator.FreeTypeFontParameter param = new FreeTypeFontGenerator.FreeTypeFontParameter();
        param.size = 60;
        BitmapFont labelFont = generator.generateFont(param);
        Texture buttonTex = game.getManager().get("images/world/Button_ON.png");
        Texture buttonDisabledTex = game.getManager().get("images/world/Button_OFF.png");
        SpriteDrawable buttonBgSprite = new SpriteDrawable(new Sprite(buttonTex));
        SpriteDrawable buttonBgDisabledSprite =new SpriteDrawable(new Sprite(buttonDisabledTex));
        ImageTextButton.ImageTextButtonStyle buttonStyle = new ImageTextButton.ImageTextButtonStyle(buttonBgSprite,
                buttonBgSprite, buttonBgDisabledSprite, labelFont);
        buttonStyle.disabled = buttonBgDisabledSprite;


        buttons = new ArrayList<>();
        for(int i=0;i<levelPos.length;i++) {
            ImageTextButton button = new ImageTextButton("" + (i+1), buttonStyle);
            button.setSize(150, 150);
            button.setPosition(levelPos[i][0], levelPos[i][1]);
            button.addCaptureListener(new ChangeListener() {
                @Override
                public void changed(ChangeEvent event, Actor actor) {
                    clickSound.play();
                    bgSong.stop();
                    game.gameScreen.setState(State.RUN);
                    game.gameScreen.setState(State.INIT);
                    game.gameScreen.init = new InitLevel(game, game.gameScreen);
                    game.setScreen(game.gameScreen);
                }
            });
            if(i+1>game.getUserProgress().getCurrentLevel()) {
                button.setDisabled(true);
            }
            stage.addActor(button);
        }


        bgSong= game.getManager().get("audio/LevelSelectionSong.ogg");
    }

    @Override
    public void show() {
        bgSong.setLooping(true);
        bgSong.play();
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void hide() {
        bgSong.stop();
        Gdx.input.setInputProcessor(null);
    }

    @Override
    public void dispose() {
        stage.dispose();
        buttons.clear();
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0.4f, 0.5f, 0.8f, 1f);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }
}

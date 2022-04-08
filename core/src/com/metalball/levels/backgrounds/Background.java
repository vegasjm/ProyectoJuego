package com.metalball.levels.backgrounds;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Disposable;
import com.metalball.MetalBallApp;
import com.metalball.screens.GameScreen;

import static com.metalball.util.Constants.METERS_X_LEVEL;
import static com.metalball.util.Constants.PIXELS_IN_METER;
import static com.metalball.util.Constants.WORLD_HEIGHT;
import static com.metalball.util.Constants.WORLD_WIDTH;

public class Background implements Disposable {

    private Texture backgroundTexture;

    private MetalBallApp game;

    private Image itemImage;

    public static float BG_HEIGHT = 6950f;

    public Background(MetalBallApp game, final GameScreen gameScreen) {
        this.game = game;
        backgroundTexture = this.game.getManager().get("images/background.jpg");
        itemImage = new Image();
        itemImage.setPosition(0, -BG_HEIGHT+WORLD_HEIGHT);
        itemImage.setDrawable(new TextureRegionDrawable(new TextureRegion(backgroundTexture)));
        itemImage.setSize(WORLD_WIDTH, BG_HEIGHT);

        gameScreen.stage.addActor(itemImage);
    }

    @Override
    public void dispose() {

    }

    public void update(float y) {
        itemImage.setPosition(0,y-BG_HEIGHT+WORLD_HEIGHT + (-(y/(PIXELS_IN_METER*METERS_X_LEVEL))*(BG_HEIGHT-WORLD_HEIGHT-950)));
    }
}

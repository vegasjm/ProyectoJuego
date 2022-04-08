package com.metalball.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.List;
import java.util.UUID;

import static com.metalball.util.Constants.PIXELS_IN_METER;

public class CoinEntity extends Actor {
    private List<Texture> textures;

    private World world;

    private Body body;

    private Fixture fixture;


    public Fixture getFixture() {
        return fixture;
    }

    public Body getBody() {
        return body;
    }

    public CoinEntity(World world, List<Texture> textures, float x, float y) {
        this.world = world;
        this.textures = textures;

        BodyDef def = new BodyDef();
        def.position.set(x, y + 0.5f);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);
        body.setUserData(UUID.randomUUID().toString());

        PolygonShape box = new PolygonShape();
        box.setAsBox(0.5f, 0.5f);
        fixture = body.createFixture(box, 0);
        fixture.setUserData("coin");
        box.dispose();

        setPosition((x - 0.5f) * PIXELS_IN_METER, y * PIXELS_IN_METER);
        setSize(PIXELS_IN_METER, PIXELS_IN_METER);
    }

    private int spriteCounter =0;
    private int textureCounter =0;
    @Override
    public void draw(Batch batch, float parentAlpha) {
        spriteCounter++;
        if(spriteCounter%5==0) {
            textureCounter++;
            if (textureCounter > 10) {
                textureCounter = 0;
            }
        }
        batch.draw(textures.get(textureCounter), getX(), getY(), getWidth(), getHeight());
    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
        this.remove();
    }
}

package com.metalball.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.metalball.util.Constants.PIXELS_IN_METER;

public class SpikeEntity extends Actor {
    private Texture texture;

    private World world;

    private Body body;

    private Fixture fixture;

    public Body getBody() {
        return body;
    }

    public SpikeEntity(World world, Texture texture, float x , float y){
        this.world = world;
        this. texture = texture;

        BodyDef def = new BodyDef();
        def.position.set(x+0.5f, y + 1f);
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        Vector2[] vertexs = new Vector2[3];
        vertexs[0] = new Vector2(-1f, -1f);
        vertexs[1] = new Vector2(1f, -1f);
        vertexs[2] = new Vector2(0, 1f);
        box.set(vertexs);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("spike");
        box.dispose();

        setPosition((x-0.5f)*PIXELS_IN_METER+2f, y*PIXELS_IN_METER);
        setSize(PIXELS_IN_METER*2, PIXELS_IN_METER*2);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
        this.remove();
    }
}

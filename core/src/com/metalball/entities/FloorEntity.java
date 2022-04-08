package com.metalball.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import java.util.ArrayList;

import static com.metalball.util.Constants.PIXELS_IN_METER;

public class FloorEntity extends Actor {

    private Texture floor, floorSide, overfloor, overfloorSide, bottomSide;

    private World world;

    private Body body, leftBody;

    private Fixture fixture, leftFixture;

    private ArrayList<Body> matrixBodies;

    private int width;
    private int height;
    static int BOX_ITEM_DIAMETER =2;

    public FloorEntity(World world, Texture floor,Texture floorSide,Texture overfloor,Texture overfloorSide,Texture bottomSide, float x, int height, int width, float y) {
        this.world = world;
        this.floor = floor;
        this.floorSide = floorSide;
        this.overfloor = overfloor;
        this.overfloorSide = overfloorSide;
        this.bottomSide = bottomSide;
        this.matrixBodies = new ArrayList<>();
        this.width = width;
        this.height = height;

        for(int i=0; i<width; i++){
            for(int j=0; j<height; j++) {
                BodyDef def = new BodyDef();
                def.position.set(1 + x + (i * BOX_ITEM_DIAMETER), -1 + (BOX_ITEM_DIAMETER / 2) + y + (j * BOX_ITEM_DIAMETER) );
                def.type = BodyDef.BodyType.StaticBody;
                body = world.createBody(def);

                PolygonShape box = new PolygonShape();
                box.setAsBox(BOX_ITEM_DIAMETER / 2, BOX_ITEM_DIAMETER / 2);
                fixture = body.createFixture(box, 1);
                fixture.setUserData("floor");
                box.dispose();
                matrixBodies.add(body);
            }
        }

        setSize( PIXELS_IN_METER*2, BOX_ITEM_DIAMETER * PIXELS_IN_METER);
        setPosition(x* PIXELS_IN_METER, (y - 1)* PIXELS_IN_METER);
    }

    public FloorEntity(World world, Texture floor, Texture overfloor, float x, float height, float y){
        this.world = world;
        this.floor = floor;
        this.overfloor = overfloor;

        BodyDef def = new BodyDef();
        def.position.set(+1f+x , -0.5f+y+(height/2));
        def.type = BodyDef.BodyType.StaticBody;
        body = world.createBody(def);

        PolygonShape box = new PolygonShape();
        box.setAsBox(1f , -0.5f+height/2);
        fixture = body.createFixture(box, 1);
        fixture.setUserData("floor");
        box.dispose();

        BodyDef leftDef = new BodyDef();
        leftDef.position.set(+1f+x, -0.5f+y+(height/2));
        leftDef.type = BodyDef.BodyType.StaticBody;
        leftBody = world.createBody(leftDef);

        PolygonShape leftBox = new PolygonShape();
        leftBox.setAsBox(0.95f , -0.5f+height/2+0.02f);
        leftFixture = leftBody.createFixture(leftBox, 1);
        leftFixture.setUserData("floor");
        leftBox.dispose();

        setSize( PIXELS_IN_METER*2, height * PIXELS_IN_METER);
        setPosition(x* PIXELS_IN_METER, (y - 1)* PIXELS_IN_METER);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        if(matrixBodies!=null && matrixBodies.size()>0){
            for(int i=0; i < width; i++){
                for(int j=0; j < height; j++) {
                    Texture current = floor;
                    boolean flip = false;
                    if(j == height-1){
                        current = overfloor;
                    }
                    if(i == 0 && width>1){
                        current = floorSide;
                    }
                    if(i == 0 && j == height-1 && width>1){
                        current =overfloorSide;
                    }
                    if(i == width-1 && width>1){
                        current = floorSide;
                    }
                    if(i == width-1 && j == height-1 && width>1){
                        current = overfloorSide;
                    }
                    if(i == 0 && j == 0 && width>1){
                        current =bottomSide;
                    }
                    if(i == width-1 && j == 0 && width>1){
                        current = bottomSide;
                    }
                    if(i == width-1){
                        flip = true;
                    }
                    TextureRegion tr = new TextureRegion(current);
                    batch.draw(tr, getX() + (i * BOX_ITEM_DIAMETER * PIXELS_IN_METER)+ (flip?BOX_ITEM_DIAMETER*PIXELS_IN_METER:0),
                            getY() + (j * BOX_ITEM_DIAMETER * PIXELS_IN_METER),
                            getOriginX(), getOriginY(), getWidth(), getHeight(),(flip?-1:1)*getScaleX(), getScaleY(), 0);

                }
            }
        }else{
            batch.draw(floor,getX(),getY(),getWidth(),getHeight());
        }
    }

    public void detach(){
        body.destroyFixture(fixture);
        leftBody.destroyFixture(leftFixture);
        world.destroyBody(body);
        this.remove();
    }
}

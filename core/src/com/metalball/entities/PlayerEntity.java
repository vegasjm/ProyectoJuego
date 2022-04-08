package com.metalball.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.ChainShape;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.metalball.util.Constants.PIXELS_IN_METER;
import static com.metalball.util.Constants.PLAYER_SPPED;

public class PlayerEntity extends Actor {

    private Texture texture, texture1, texture2, texture3, texture4,
                    textureSpeed, textureSpeed1,
                    textureLR, textureLR1, textureLR2,
                    textureStop, textureStop1;

    private World world;

    private Body body;

    private Fixture fixture;

    private  boolean alive = true;

    private  boolean win = false;

    private Vector2 speed;

    private int xOriented=0;

    private float speedRefresh = 0f;

    public Body getBody() {
        return body;
    }

    public Vector2 getSpeed() {
        return speed;
    }

    public void setSpeed(Vector2 speed) {
        this.speed = speed;
    }

    public boolean isAlive() {
        return alive;
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public int getxOriented() {
        return xOriented;
    }

    public void setxOriented(int xOriented) {
        this.xOriented = xOriented;
    }

    public boolean isWin() {
        return win;
    }

    public void setWin(boolean win) {
        this.win = win;
    }

    public PlayerEntity(World world, Texture texture, Texture texture1, Texture texture2, Texture texture3, Texture texture4, Texture textureSpeed, Texture textureSpeed1,
                        Texture textureLR, Texture textureLR1, Texture textureLR2, Texture textureStop, Texture textureStop1, Vector2 position){
        this.world = world;
        this.texture = texture;
        this.texture1 = texture1;
        this.texture2 = texture2;
        this.texture3 = texture3;
        this.texture4 = texture4;
        this.textureSpeed = textureSpeed;
        this.textureSpeed1 = textureSpeed1;
        this.textureLR = textureLR;
        this.textureLR1 = textureLR1;
        this.textureLR2 = textureLR2;
        this.textureStop = textureStop;
        this.textureStop1 = textureStop1;

        BodyDef def = new BodyDef();
        def.position.set(position);
        def.type = BodyDef.BodyType.DynamicBody;
        body = world.createBody(def);
        CircleShape circle = new CircleShape();
        circle.setRadius(1.5f);
        ChainShape box = new ChainShape();
        Vector2[] vertexs = new Vector2[9];
        vertexs[0] = new Vector2(0, -1.80f);
        vertexs[1] = new Vector2(-0.5f, -0.55f);
        vertexs[2] = new Vector2(-1.5f,-0.3f);
        vertexs[3] = new Vector2(-0.5f, -0.05f);
        vertexs[4] = new Vector2(0, 2);
        vertexs[5] = new Vector2(0.5f, -0.05f);
        vertexs[6] = new Vector2(1.5f, -0.3f);
        vertexs[7] = new Vector2(0.5f, -0.55f);
        vertexs[8] = new Vector2(0, -1.80f);
        box.createChain(vertexs);//1.95,1.65f
        fixture = body.createFixture(circle,1);
        fixture.setUserData("player");
        box.dispose();
        setSize(PIXELS_IN_METER*4, PIXELS_IN_METER*3.6f);
    }

    private int spriteCounter =0;
    private int textureCounter =0;
    private int textureLRCounter =0;
    private int textureStopCounter =0;
    private int textureSpeedCounter =0;
    @Override
    public void draw(Batch batch, float parentAlpha) {
        setPosition((body.getPosition().x - (xOriented==1?-2:2)) * PIXELS_IN_METER,
                (body.getPosition().y - 1.7f) * PIXELS_IN_METER);
        TextureRegion tr = null;

        if(body.getLinearVelocity().y>-10){
            if(textureStopCounter==0){
                tr = new TextureRegion(textureStop);
            }else if(textureStopCounter==1) {
                tr = new TextureRegion(textureStop1);
            }
        }else
        if(body.getLinearVelocity().y<-30){
            if(textureSpeedCounter==0){
                tr = new TextureRegion(textureSpeed);
            }else if(textureSpeedCounter==1) {
                tr = new TextureRegion(textureSpeed1);
            }
        }else
        if(body.getLinearVelocity().x>3 || body.getLinearVelocity().x<-3){
            if(textureLRCounter==0){
                tr = new TextureRegion(textureLR);
            }else if(textureLRCounter==1){
                tr = new TextureRegion(textureLR1);
            }else if(textureLRCounter==2){
                tr = new TextureRegion(textureLR2);
            }
        }else {
            textureLRCounter=0;
            textureStopCounter=0;
            textureSpeedCounter=0;
            if (textureCounter == 0) {
                tr = new TextureRegion(texture);
            } else if (textureCounter == 1) {
                tr = new TextureRegion(texture1);
            } else if (textureCounter == 2) {
                tr = new TextureRegion(texture2);
            } else if (textureCounter == 3) {
                tr = new TextureRegion(texture3);
            } else if (textureCounter == 4) {
                tr = new TextureRegion(texture4);
            }
        }

        spriteCounter++;
        if(spriteCounter%10==0){
            textureCounter++;
            if(textureCounter>4){
                textureCounter=0;
            }
            textureLRCounter++;
            if(textureLRCounter>2){
                textureLRCounter=2;
            }
            textureSpeedCounter++;
            if(textureSpeedCounter>1){
                textureSpeedCounter=1;
            }
            textureStopCounter++;
            if(textureStopCounter>1){
                textureStopCounter=1;
            }
        }
            batch.draw(tr, getX(), getY(), getOriginX(), getOriginY(), getWidth(), getHeight(),(xOriented==1?-1:1)*getScaleX(), getScaleY(), 0);
    }

    @Override
    public void act(float delta) {
        if(!win) {
            //pass walls
            float y = body.getPosition().y;
            if (body.getPosition().x < 0f) {//pass left to right
                body.setTransform(21, y, 0);
            }
            if (body.getPosition().x > 22) {//pass right to left
                body.setTransform(0f, y, 0);
            }

            speed = body.getLinearVelocity();

            if (Gdx.input.isTouched()) {
                if (Gdx.input.getX() < Gdx.graphics.getWidth() / 2) {
                    this.setxOriented(0);
                    System.out.println("LEFT");
                } else {
                    this.setxOriented(1);
                    System.out.println("RIGHT");
                }
            }

            // Y Speed management: TO DO --> improve
            speedRefresh++;
            if (speedRefresh % 30 == 0) {
                float maxSpeed = -PLAYER_SPPED * 2;
                if (maxSpeed < speed.y) {
                    maxSpeed = speed.y - 5;
                }
                if (maxSpeed > speed.y) {
                    maxSpeed = speed.y + 5;
                }
                body.setLinearVelocity(body.getLinearVelocity().x, maxSpeed);
            }
        }else{
            body.setLinearVelocity(0,0);
        }

    }

    public void brake(){
        body.setLinearVelocity(body.getLinearVelocity().x, 0);
    }

    public void speedUp(){
        body.setLinearVelocity(body.getLinearVelocity().x, -40);
    }

    public void detach(){
        body.destroyFixture(fixture);
        world.destroyBody(body);
        this.remove();
    }

}

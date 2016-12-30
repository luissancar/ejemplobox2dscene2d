package com.mygdx.game.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.Fixture;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;

import static com.mygdx.game.Constans.PIXELS_IN_METER;

/**
 * Created by luissancar on 30/12/16.
 */

public class FloorEntiy extends Actor{
    private Texture floor, overfloor;
    private World world;
    private Body body;
    private Fixture fixture;

    public FloorEntiy(World word, Texture floor,Texture overfloor, float x, float width, float y){
        this.world=word;
        this.floor=floor;
        this.overfloor=overfloor;

        BodyDef def=new BodyDef();
        def.position.set(x+width/2,y-0.5f);
        body=word.createBody(def);

        PolygonShape box=new PolygonShape();
        box.setAsBox(width/2,0.5f);
        fixture=body.createFixture(box,1);
        box.dispose();

        setSize(PIXELS_IN_METER,PIXELS_IN_METER);
        setPosition((x-width/2)*PIXELS_IN_METER,(y-1)*PIXELS_IN_METER);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // box2d toma como refeencia el centro del cuerpo por es -0.5
        setPosition((body.getPosition().x-0.5f)*PIXELS_IN_METER,
                (body.getPosition().y-0.5f)*PIXELS_IN_METER);
        batch.draw(floor,getX(),getY(),getWidth(),getHeight());

    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}
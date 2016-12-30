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

public class PlayerEntity extends Actor{
    private Texture texture;
    private World world;
    private Body body;
    private Fixture fixture;
    private boolean alive=true, jumping=false;

    public PlayerEntity(World word, Texture texture, Vector2 position){
        this.world=word;
        this.texture=texture;

        BodyDef def=new BodyDef();
        def.position.set(position);
        def.type= BodyDef.BodyType.DynamicBody;
        body=word.createBody(def);

        PolygonShape box=new PolygonShape();
        box.setAsBox(0.5f,0.5f);
        fixture=body.createFixture(box,3);
        box.dispose();

        setSize(PIXELS_IN_METER,PIXELS_IN_METER);

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        // box2d toma como refeencia el centro del cuerpo por es -0.5
        setPosition((body.getPosition().x-0.5f)*PIXELS_IN_METER,
                (body.getPosition().y-0.5f)*PIXELS_IN_METER);
        batch.draw(texture,getX(),getY(),getWidth(),getHeight());

    }

    public void detach() {
        body.destroyFixture(fixture);
        world.destroyBody(body);
    }
}

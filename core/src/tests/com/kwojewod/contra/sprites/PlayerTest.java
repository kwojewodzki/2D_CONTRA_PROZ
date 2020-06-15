package com.kwojewod.contra.sprites;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.World;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerTest {

    @Test
    public void textureExists(){
        assertTrue("Mozna zaladowac tekstury bohatera", Gdx.files.internal("../core/assets/graphics/Sprites/Hero_and_Enemies.png").exists());
    }

    @Test
    public void update() {
    }

    @Test
    public void getFrame() {


    }

    @Test
    public void getState() {

    }

    @Test
    public void definePlayer() {
    }

    @Test
    public void fire() {
    }

    @Test
    public void draw() {
    }

    @Test
    public void isDead() {
    }

    @Test
    public void getStateTimer() {

    }
}
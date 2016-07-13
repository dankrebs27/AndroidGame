package com.dannykrebs.chroma.sprites;

public class BasicAttack extends Projectile {

    protected BasicAttack(PlayChar playChar) {
        super();
        owner = playChar;
        speed = owner.basicSpeed;
        velocity.x = (float) Math.cos(owner.getAimer().getAngle()) * speed;
        velocity.y = (float) Math.sin(owner.getAimer().getAngle()) * speed;
        position.x = owner.getX();
        position.y = owner.getY();
        damage = owner.damage;
    }

    @Override
    protected void update(float dt) {
        scaledVel.x = velocity.x*dt;
        scaledVel.y = velocity.y*dt;
        position.add(scaledVel);
    }



    @Override
    public void dispose() {

    }
}

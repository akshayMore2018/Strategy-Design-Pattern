public class Coin extends GameObject
{
    private Point initialPos;
    private float MAX_DEVIATION;
    private GameObject owner;
    public static Coin instance;
    private BEHAVIOR keyBehavior;
    public Coin()
    {
        angularVelocity = 6;
        MAX_DEVIATION = 2;
        keyHoverBehavior();
        initialPos = new Point(position);
        instance = this;
    }

    @Override
    public boolean onCollision(GameObject go)
    {
        if (go.ID == Constants.ID_PLAYER || go.ID == Constants.ID_AUTORUN_CHARACTER)
        {
            if (owner == null)
            {
                setOwner(go);
                keyLerpToOwnerBehavior(go);

            }
        }
        return false;
    }

    @Override
    public void onExternalEvent(int typeOfInteraction, Entity entity)
    {
        switch (typeOfInteraction)
        {
            case Constants.ExternalEvents.OWNER_DEAD:
                keyLerpToLocationBehavior(initialPos);
                //keyHoverBehavior();

        }
    }

    private void keyMechanics(Mechanics mechanic)
    {
        if (mechanic != null)
            mechanic.run();
    }


    private void keyLerpToOwnerBehavior(GameObject go)
    {
        keyBehavior = BEHAVIOR.LERP_TO_OWNER;
        keyBehavior.setOwner(go);
    }

    private void keyLerpToLocationBehavior(Point targetPos)
    {
        keyBehavior = BEHAVIOR.LERP_TO_LOCATION;
        keyBehavior.setTargetPos(targetPos);
    }

    private void keyHoverBehavior()
    {
        setOwner(null);
        keyBehavior = BEHAVIOR.HOVER;
        keyBehavior.setMaxDeviation(MAX_DEVIATION);
        keyBehavior.setAngularVelocity(angularVelocity);
    }

    public GameObject getOwner()
    {
        return owner;
    }

    public void setOwner(GameObject owner)
    {
        this.owner = owner;
    }


    @Override
    public void update()
    {

        keyMechanics(keyBehavior);
        animation.updateFrame();
        collision.update();
    }

    @Override
    public void draw(PolygonSpriteBatch g)
    {
       //draw object
    }

}

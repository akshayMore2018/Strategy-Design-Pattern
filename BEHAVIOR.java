enum BEHAVIOR implements Mechanics
{
    LERP_TO_OWNER
            {
                @Override
                public void run()
                {
                    int dir = owner.facingDirection;
                    float targetPosX = owner.position.x - 70 * dir;
                    float targetPosY = owner.position.y - 70;
                    Coin.instance.position.x = Utility.lerp(Coin.instance.position.x, targetPosX, 0.1f);
                    Coin.instance.position.y = Utility.lerp(Coin.instance.position.y, targetPosY, 0.1f);
                }
            },
    LERP_TO_LOCATION
            {
                @Override
                public void run()
                {
                    Coin.instance.position.x = Utility.lerp(Coin.instance.position.x, targetPos.x, 0.01f);
                    Coin.instance.position.y = Utility.lerp(Coin.instance.position.y, targetPos.y, 0.01f);
                }
            },
    HOVER
            {

                private float hoverAngle = 0;

                @Override
                public void run()
                {
                    Coin.instance.position.y = Coin.instance.position.y + maxDeviation * Utility.getSin(hoverAngle);
                    hoverAngle += angularVelocity;
                }
            };

    protected GameObject owner;
    protected Point targetPos;
    protected float maxDeviation;
    protected float angularVelocity;

    public void setOwner(GameObject owner)
    {
        this.owner = owner;
    }

    public void setTargetPos(Point targetPos)
    {
        this.targetPos = targetPos;
    }

    public void setMaxDeviation(float maxDeviation)
    {
        this.maxDeviation = maxDeviation;
    }

    public void setAngularVelocity(float angularVelocity)
    {
        this.angularVelocity = angularVelocity;
    }
}


public class Player {
  
      //the name of the file of the
      //player sprite in assets
      private String spriteFile = "redSquare.png";
  
      //Texture containing the image
      //information for the local
      //Player Sprite
      private Texture spriteTexture;
  
      // sprite kept locally
      //to represent the player

      //The rate at which friction is applied to the player.
      //ie how quickly xSpd is decremented
      float xFriction = 0.10f;

  
      //The CONTSTRUCTOR for the Player object
      //@param the value to set logicalX upon

      public Player(int startX, int startY) {
          setLogicalX(startX);
          setLogicalY(startY);
          spriteTexture = new Texture(spriteFile);
          localSprite = new Sprite(spriteTexture);
      }
  
      //accsessor for xSpd
     public void update() {
          if (getY() > 0) {
              inAir = true;
              addToYspd(-0.5f);
          }
          if (getY() <= 0) {
              if (getY() < 0) {
     public void update() {
                  jumps = 4;
                  inAir = false;
              }

          }
    
          //update x and y location
          setLogicalX(getX() + getXspd());
          setLogicalY(getY() + getYspd());
          localSprite.setX(getX());
          localSprite.setY(getY());
         
      }
   
  
  }

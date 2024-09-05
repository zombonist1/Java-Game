package Entity;

import main.GamePanel;
import main.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;

        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down";
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/king/kingUp1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/king/kingUp2.png"));
            up3 = ImageIO.read(getClass().getResourceAsStream("/king/kingIdleUp.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/king/kingDown1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/king/kingDown2.png"));
            down3 = ImageIO.read(getClass().getResourceAsStream("/king/kingIdleDown.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/king/kingLeft1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/king/kingLeft2.png"));
            left3 = ImageIO.read(getClass().getResourceAsStream("/king/kingIdleLeft.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/king/kingRight1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/king/kingRight2.png"));
            right3 = ImageIO.read(getClass().getResourceAsStream("/king/kingIdleRight.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {
        if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true) {
            if(keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            }else if(keyH.downPressed == true) {
                direction = "down";
                y += speed;
            }else if(keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            }else if(keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }

            spriteCounter++;
            if (spriteCounter > 15) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {
        BufferedImage image = null;
        int releaseTrigger = 0;

        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                releaseTrigger = 1;
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                releaseTrigger = 2;
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                releaseTrigger = 3;
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                releaseTrigger = 4;
                break;
        };

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.rightPressed == true || keyH.leftPressed == true) {
            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }else {
            if (releaseTrigger == 1) {
                image = up3;
            }else if (releaseTrigger == 2) {
                image = down3;
            }else if (releaseTrigger == 3) {
                image = left3;
            }else if (releaseTrigger == 4) {
                image = right3;
            }

            g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);
        }

    }
}
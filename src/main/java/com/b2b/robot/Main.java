/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.b2b.robot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Rzubakov
 */
public class Main {

    public static void main(String... args) throws AWTException, IOException, InterruptedException {

        Robot robot = new Robot();
        int width = Toolkit.getDefaultToolkit().getScreenSize().width;
        int height = Toolkit.getDefaultToolkit().getScreenSize().height;
        
        Thread.sleep(30000);
        System.out.println("get screen page");
        Rectangle area = new Rectangle(0, 0, width, height);
        BufferedImage bufferedImage = robot.createScreenCapture(area);
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                //rgb(194, 0, 212)
                if ((color.getRed() == 212 && color.getGreen() == 14 && color.getBlue() == 229) | (color.getRed() == 236 && color.getGreen() == 165 && color.getBlue() == 33)) {
                    System.out.println("mouse move="+x+":"+y);
                    robot.mouseMove(x, y);
                    Thread.sleep(1000+(Math.round(1L)*3));
                    System.out.println("mouse click="+x+":"+y);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    Thread.sleep(1000+(Math.round(1L)*3));
                    subSearch(x, y, robot);
                    robot.keyPress(KeyEvent.VK_CONTROL);
                    robot.keyPress(KeyEvent.VK_W);
                    robot.keyRelease(KeyEvent.VK_W);
                    robot.keyRelease(KeyEvent.VK_CONTROL);
                    System.out.println(x + " " + y);
                    Thread.sleep(1000+(Math.round(1L)*3));
                    bufferedImage = robot.createScreenCapture(area);
                }

            }
        }
        System.out.println("end search");

    }

    public static void subSearch(int posX, int posY, Robot robot) throws IOException, InterruptedException {
        System.out.println("subSearch="+posX+":"+posY);
        Rectangle area = new Rectangle(posX, posY, 200, 70);
        BufferedImage bufferedImage = robot.createScreenCapture(area);
        for (int x = 0; x < 200; x++) {
            for (int y = 0; y < 70; y++) {
                Color color = new Color(bufferedImage.getRGB(x, y));
                if (color.getRed() == 255 && color.getGreen() == 0 && color.getBlue() == 0) {
                    Thread.sleep(1000);
                    robot.mouseMove(x + posX, y + posY);
                    Thread.sleep(1000);
                    robot.mousePress(InputEvent.BUTTON1_MASK);
                    robot.mouseRelease(InputEvent.BUTTON1_MASK);
                    System.out.println("mouse click in subSearch="+x+":"+y);
                    Thread.sleep(15000);
                    return;
                }
            }
        }

    }
}

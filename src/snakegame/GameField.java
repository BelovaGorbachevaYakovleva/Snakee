/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 *
 * @author Evgenia
 */
public class GameField extends JPanel implements ActionListener{
    private final int SIZE=320;//поле выходит 20х20
    private final int DOT_SIZE=16;//размер одной ячейки, яблочка
    private final int ALL_DOTS=400;//количество игровых единиц, помещающихся в поле
    private Image dot;
    private Image apple;
    private int appleX;//позиция яблочка по Х
    private int appleY;//позиция яблочка по У
    private int[] x = new int[ALL_DOTS];//позиция змейки с помощью массива по х
    private int[] y = new int[ALL_DOTS];//позиция змейки с помощью массива по у
    private int dots;//размер змейки в данный момент
    private Timer timer;
    private boolean left = false;//направление змейки
    private boolean right = true;//направление змейки
    private boolean up = false;//направление змейки
    private boolean down = false;//направление змейки
    private boolean inGame = true;//в игре ли змейка
    
    public GameField()
    {
        setBackground(Color.yellow);
        loadImages();
        initGame();
        addKeyListener(new FieldKeyListener());
        setFocusable(true);//включение взаимодействия с клавишами
     
    }
    
    public void initGame()//инициализирует начало игры 
    {
        dots=3;
        for (int i=0; i<dots; i++)// позиции змейки, первая ячейка на 48-ом пикселе(отсчет от верхней левой точки)
            
        {
            x[i]=48-i*DOT_SIZE;
            y[i]=48;
        }
        timer=new Timer(250,this);
        timer.start();
        createApple();//создание яблока

    }
    public void createApple()
    {
        appleX=new Random().nextInt(20)*DOT_SIZE;
        appleY=new Random().nextInt(20)*DOT_SIZE;
    
    }
            
    
    public void loadImages()//метод для загрузки картинок
    {
        ImageIcon iia= new ImageIcon("src/apple.png");
        apple = iia.getImage();
        ImageIcon iid= new ImageIcon("src/dot.png");
        dot = iid.getImage();
    
    }
    
    @Override
    protected void paintComponent(Graphics g)//перерисовка
    {
        super.paintComponent(g);
        if (inGame)
        {
            g.drawImage(apple,appleX,appleY,this);
            for (int i=0; i<dots;i++)
            {
                g.drawImage(dot,x[i],y[i],this);
            }
        
        }
        else //настройка окна законченной игры
        {String str="Game Over";
        Font f = new Font("Arial",Font.BOLD,16);
        g.setColor(Color.white);
        g.setFont(f);
        g.drawString(str,125,SIZE/2);
        }
    
    }
    
    public void move()
    {
        for (int i=dots; i>0;i--)//перемещение позиций тела змейки друг за другом
        {
            x[i]=x[i-1];
            y[i]=y[i-1];
            
        }
        if (left) {x[0]-=DOT_SIZE;}//перемещение влево головы
        if (right) {x[0]+=DOT_SIZE;}//перемещение вправо головы
        if (up) {y[0]-=DOT_SIZE;}//перемещение вверх головы
        if (down) {y[0]+=DOT_SIZE;}//перемещение вниз головы
    
    }
    
    public void checkApple()
    {
        if (x[0]==appleX && y[0]==appleY)
        {
            dots++;
            createApple();
        
        }
    
    }
    
    public void checkCollisions()
    {
        for (int i=dots; i>0;i--)//проверка столкновения головы змейки с телом
        {
            if (i>4 && x[0]==x[i]&& y[0]==y[i])
            {
                inGame=false;
            
            }
        
        }
        if (x[0]>SIZE)
        {
            inGame=false;
        }
        if (x[0]<0)
        {
            inGame=false;
        }
        if (y[0]>SIZE)
        {
            inGame=false;
        }
        if (y[0]<0)
        {
            inGame=false;
        }
    
    }

    @Override
    public void actionPerformed(ActionEvent e)//вызывется при каждом тиканье таймера(через каждые 250 мс)
    {
        if (inGame)
        {
            checkApple();//проверка не попалось ли яблоко
            checkCollisions();//проверка на столкновение
            move();
        
        }
        repaint();//перерисовка поля
     
    }
    
    class FieldKeyListener extends KeyAdapter//работа клавишь
    {
        @Override
        public void keyPressed(KeyEvent e)
        {
            super.keyPressed(e);
            int key=e.getKeyCode();//код нажатой клавиши
            if(key==KeyEvent.VK_LEFT && ! right)
            {
                left=true;
                up=false;
                down=false;
            }
            if(key==KeyEvent.VK_RIGHT && ! left)
            {
                right=true;
                up=false;
                down=false;
            }
            if(key==KeyEvent.VK_UP && ! down)
            {
                up=true;
                right=false;
                left=false;
            }
            if(key==KeyEvent.VK_DOWN && ! up)
            {
                down=true;
                right=false;
                left=false;
            }
        }
    }
}

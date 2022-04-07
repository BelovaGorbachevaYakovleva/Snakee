/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package snakegame;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 *
 * @author Evgenia
 */
public class MainWindow extends JFrame{
    public MainWindow()
    {
        setTitle("Змейка");//название
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);//остановка программы при нажатие крестика
        setSize(370,380);//ширина окна
        setLocation(400,400);
        add(new GameField());//добавление игровое поле
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        MainWindow mw= new MainWindow();//экземпляр окна, начало работы программы
    
    }
}

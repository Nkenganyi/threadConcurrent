/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package threadprintingchar;


import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author nkeng
 */
class Thread1 extends Thread{
    public BlockingDeque<Integer>q = new LinkedBlockingDeque<>();
    
    public int number = 64;

    @Override
    public void run() {
        if(q.isEmpty()){
            q.add(0);
        }
        
        for (int i = 0; i < 26; i++) {
            int ran = this.random();
            System.out.println("Thread1 "+ ran);
            q.add(ran);
            
            try {
                Thread.sleep(800);
            } catch (InterruptedException ex) {
                Logger.getLogger(Thread1.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
       
    }
    
    public int random(){
        int rand = ++number; 
     
     return rand;
    }

}

class Thread2 extends Thread{
    Thread1 t1;
    public Thread2(){
       
    }

    @Override
    public void run() {
       if(t1.q.isEmpty()){
           t1.q.add(0);
       }
        for (int i = 0; i < 26; i++) {
//            System.out.println("Thread 2 "+ t1.q.getLast());
            print(t1.q.getLast());
            
            try {
                Thread.sleep(830);
            } catch (InterruptedException ex) {
                Logger.getLogger(Thread2.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    private void print(int number){
       
       char c = (char) number;
        for (int i = 65; i <= 'Z'; i++) {
            if(i == c){
                System.out.println("Thread2 " + number +" " + c);
            }
        }
        
            
       
    }

}
public class ThreadPrintingChar {

    public static void main(String[] args) {
        
        Thread1 t1 = new Thread1();
        Thread2 t2 = new Thread2();
        
        t2.t1 = t1;
        t1.start();
       
        t2.start();
    }
    
}

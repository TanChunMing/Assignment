/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asd;

/**
 *
 * @author USER
 */
public interface Queue<T> {
    public void enqueue(T data);
    public T dequeue();
    public T getFront();
    public boolean isEmpty();
}

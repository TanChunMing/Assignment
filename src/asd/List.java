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
public interface List<T> {
    public void add(T item);
    public T get(int i);
    public void add(int i, T item);
    public boolean remove(int i);
}

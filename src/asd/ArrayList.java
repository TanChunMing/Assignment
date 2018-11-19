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
public class ArrayList<T> implements List<T> {
    private T[] array;
    private int totalItem;
    
    public ArrayList(){
        array = (T[])new Object[5];
        totalItem = 0;
    }
    
    public void add(T item){
        if(totalItem == array.length){
            expandArray();
        }
        array[totalItem] = item;
        totalItem++;
    }
    public T get(int i){
        T item = null;
        if (i<totalItem){
            item = array[i];
        }
        return item;
    }
    public void add(int i, T item){
        if(totalItem == array.length){
            expandArray();
        }
        if(i>=1 && i<=totalItem+1){
            makeRoom(i);
            array[i-1] = item;
            totalItem++;
            System.out.println("Item add successfully");
        }
        else
            System.out.println("Failed to add item");
    }
    public boolean remove(int i){
        boolean result = false;
        
        if (i>=1 && i<=totalItem){
            array[i-1]=null;
            result = true;
            if (i<totalItem)
                removeGap(i);
            totalItem--;
        }
        
        return result;
    }
    private void expandArray(){
        T[] oldArray = array;
        int size = oldArray.length;
        
        array = (T[])new Object[size*2];
        
        for(int i=0; i<size; i++){
            array[i] = oldArray[i];
        }
    }
    private void makeRoom(int i){
        int newIndex = i-1;
        int lastIndex = totalItem-1;
        
        for (int index = lastIndex; index >= newIndex; index--){
            array[index+1]=array[index];
        }
    }
    private void removeGap(int i){
        int removedIndex = i-1;
        int lastIndex = totalItem-1;
        
        for (int index = removedIndex; index < lastIndex; index++){
            array[index] = array[index + 1];
    }

    }
    public boolean isEmpty(){
        if (totalItem == 0)
            return true;
        else
            return false;
    }
    
    public void clear(){
        for(int i=0; i<array.length; i++){
            array[i] = null;
        }
        totalItem = 0;
    }
}

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
public class CircularLinkedQueue<T> implements Queue<T> {
    Node topNode = null;
    
    public void enqueue(T data){
        Node newNode = new Node(data);
        
        if (isEmpty()){
            topNode = newNode;
            topNode.next = newNode;
        }
        else{
            newNode.next = topNode.next;
            topNode.next = newNode;
            topNode = newNode;
        }
    }
    public T dequeue(){
        T data = null;
        
        if (!isEmpty()){
            data = topNode.next.data;
            if (topNode.next == topNode){
                topNode = null;
            }
            else{
                topNode.next = topNode.next.next;
            }
        }
        
        return data;
    }
    public T getFront(){
        T data = null;
        
        if (!isEmpty()){
            data = topNode.next.data;
        }
        
        return data;
    }
    public boolean isEmpty(){
        if (topNode == null)
            return true;
        else
            return false;
    }
    public class Node{
        private T data;
        private Node next;
        
        public Node(T data){
            this.data=data;
        }
    }
}

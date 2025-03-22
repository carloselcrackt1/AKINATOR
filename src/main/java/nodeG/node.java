/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nodeG;

/**
 *
 * @author Carlos Auqui
 */
public class node<E> {
    public E data;
    public node<E> left;
    public node<E> right;

    public node(E data){
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public node<E> getLeft() {
        return left;
    }

    public void setLeft(node<E> left) {
        this.left = left;
    }

    public node<E> getRight() {
        return right;
    }

    public void setRight(node<E> right) {
        this.right = right;
    }
    
}

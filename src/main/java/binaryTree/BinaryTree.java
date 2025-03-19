package binaryTree;

import java.util.Comparator;

/**
 *
 * @author Carlos Auqui
 */
public class BinaryTree <E> {
    private Node<E> root;

    class Node<E>{
        E data;
        Node<E> left;
        Node<E> right;

        Node(E data){
            this.data = data;
        }
    }

   // hay que arreglar el arbol
    public BinaryTree(Node<E> e){
        this.root = e;
    }

    /*public boolean add(E q, E a){
        if(q ==null || a == null) return false;
        this.root = add(q,a,this.root);
        return true;
        
    }
        private Node<E> add(E e,E i, Node<E> p){
        if(p.right == null || p.left == null){
            p = new Node<>(e); // representa si la raiz es nula o el subarbol esta vacip
        }

        if (f.compare(e, p.data)>0) {
            p.right = add(e, p.right);
        }
        else if(f.compare(e, p.data)<0){
            p.left = add(e, p.left);
        }
        return p;
    }*/

    public void preOrden(){
        preOrden(this.root);
        System.out.println();
    }

    // funciones que consultan no devuelven nodo
    private void preOrden(Node<E> p){
        if(p!=null)
        {
            System.out.print(p.data+" ");
            preOrden(p.left); 
            preOrden(p.right); 
        }
    }

    public void posOrden(){
        posOrden(this.root);
        System.out.println(); 
    }

    // funciones que consultan no devuelven nodo
    private void posOrden(Node<E> p){
        if(p!=null)
        {
            posOrden(p.left); 
            posOrden(p.right); 
            System.out.print(p.data+" ");
        }
    }

    public void enOrden(){
        enOrden(this.root);
        System.out.println(); 
    }

 
    private void enOrden(Node<E> p){
        if(p!=null)
        {
            enOrden(p.left); 
            System.out.print(p.data+" ");
            enOrden(p.right); 
           
        }
    }

    public int countLeaves(){
        return countLeaves(this.root);
    }

    private int countLeaves(Node<E> p ){
        if(p==null){
            return 0; 
        }
        else if (p.left == null && p.right ==null)
            return 1;
        return countLeaves(p.left)+countLeaves(p.right);

    }

    public int size(){
        return size(this.root);
    }

    private int size(Node<E> p){
        if(p==null)
            return 0;
        return 1 + size(p.left) + size(p.right);

    }

    /*public boolean estaEnRango(E min, E max) 
    {
        if(f.compare(min, max) < 0)
            return estaEnRango(this.root,min,max);  
        else
            System.out.println("el rango debe ser de menor a mayor");
            return false;
    }

    private boolean estaEnRango(Node<E> p, E min , E max){

        if( p == null){ 
            return false;
        }

        if( (f.compare(p.data, min) >= 0 ) && (f.compare(p.data,max) <= 0) )
            return true;

        else{
            if(f.compare(p.data,max) > 0 )
                return estaEnRango(p.left, min, max);
            else if(f.compare(p.data,max) < 0){
                return estaEnRango(p.right, min, max);
            }
            else{
                return false;
            }
        }
    }*/

}


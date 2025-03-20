package binaryTree;

import java.util.ArrayList;
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


    public ArrayList<E> posOrden(){
        ArrayList<E> treeList = new ArrayList<>();
        posOrden(this.root,treeList);
        return treeList;
    }

    // funciones que consultan no devuelven nodo
    private void posOrden(Node<E> p, ArrayList<E>treeList){
        
        if(p!=null)
        {
            posOrden(p.left,treeList); 
            posOrden(p.right,treeList); 
            treeList.add(p.data);
        }
    }


    public int size(){
        return size(this.root);
    }

    private int size(Node<E> p){
        if(p==null)
            return 0;
        return 1 + size(p.left) + size(p.right);

    }
}


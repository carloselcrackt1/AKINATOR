package binaryTree;

import java.util.ArrayList;
import java.util.ListIterator;
import nodeG.node;


/**
 *
 * @author Carlos Auqui
 */ 
public class BinaryTree <E> {
    private node<E> root;
    private GenericArrayList<Boolean> ruta;

    public BinaryTree(node<E> root){
        this.root = root;
    }

    public void add(E q, E r, Boolean ubiR){
        if(q ==null && r == null) 
            throw new UnsupportedOperationException("data nula");
        else{
            ListIterator<Boolean> rutaIteradora = ruta.listIterator();
            this.root = add(q,r,this.root,rutaIteradora,ubiR);
        }
        
    }
    private node<E> add(E p, E c, node<E> t, ListIterator<Boolean> decision,Boolean ubiR){
        if (!decision.hasNext()) { // aqui estamos en el ultimo nodo 
            node<E> temp = t;
            t = new node<E>(p);
            if (ubiR) {
                t.right = new node<E>(c);
                t.left = temp;
            }else{
                t.right =  temp;
                t.left = new node<E>(c);
            }
        }else{
            Boolean nextDecision= decision.next();
            if(nextDecision)
                t.right = add(p, c, t.right, decision, ubiR);
            else
                t.left = add(p, c, t.left, decision, ubiR);
        }
        return t;
    }

    // funcion que me devolvera un arraylist en posOrden de los elementos del arbol(servira para actualizar el archivo)
    public ArrayList<E> posOrden(){
        ArrayList<E> treeList = new ArrayList<>();
        posOrden(this.root,treeList);
        return treeList;
    }
    private void posOrden(node<E> p, ArrayList<E>treeList){
        
        if(p!=null)
        {
            posOrden(p.left,treeList); 
            posOrden(p.right,treeList); 
            treeList.add(p.data);
        }
    }

 
    public void decisionJugador(Boolean decision){
        if (this.ruta == null){
            this.ruta = new GenericArrayList();
        }
        ruta.add(decision);
    }

    public node<E> getRoot() {
        return root;
    }

    public GenericArrayList<Boolean> getRuta() {
        return ruta;
    }

    
}



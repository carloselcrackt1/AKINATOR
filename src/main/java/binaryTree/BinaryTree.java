package binaryTree;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.ListIterator;
import nodeG.node;
import questionAnswerClass.QAndA;


/**
 *
 * @author Carlos Auqui
 */ 
public class BinaryTree <E> {
    private node<E> root;
    private GenericArrayList<Boolean> ruta;
    private GenericArrayList<E> charactersAlive;
    private node<E> tmpRoot;

    public BinaryTree(node<E> root){
        this.root = root;
        this.tmpRoot = root;
    }

    public void add(E q, E r, Boolean ubiR) {
        if (q == null && r == null) 
            throw new UnsupportedOperationException("data nula");
        else {
            ListIterator<Boolean> rutaIteradora = ruta.listIterator();
            this.root = add(q, r, this.root, rutaIteradora, ubiR);

            try {
                String basePath = System.getProperty("user.home") + "/Akinator/data";
                File dir = new File(basePath);
                if (!dir.exists()) dir.mkdirs();

                File file = new File(dir, "tree.ser");

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                    for (E e : posOrden()) {
                        oos.writeObject(e);
                    }
                }

                System.out.println("Archivo guardado correctamente en: " + file.getAbsolutePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
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
    public GenericArrayList<E> posOrden(){
        GenericArrayList<E> treeList = new GenericArrayList<>();
        posOrden(this.root,treeList);
        return treeList;
    }
    private void posOrden(node<E> p, GenericArrayList<E>treeList){
        if(p!=null)
        {
            posOrden(p.left,treeList); 
            posOrden(p.right,treeList); 
            treeList.add(p.data);
        }
    }
    
    public E getAQ(){
        return tmpRoot.getData();
    }

    public void jugar(boolean choice) {
        if (choice) {
            tmpRoot = tmpRoot.right;
            decisionJugador(true);
        } else{
            tmpRoot = tmpRoot.left;
            decisionJugador(false);
        }
    }
    
    public GenericArrayList<E> getPosiblesAnswers(){
        charactersAlive = new GenericArrayList<>();
        getPosiblesAnswers(this.tmpRoot, charactersAlive);
        return charactersAlive;
    }
    
    public GenericArrayList<E> getPosiblesAnswers(node<E> p, GenericArrayList<E> gal){
        if(p==null)
            return null;
        else if(p.left == null && p.right == null){
            gal.add(p.data);
            return gal;
        }
        getPosiblesAnswers(p.left,gal);
        getPosiblesAnswers(p.right, gal);
        return gal;
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



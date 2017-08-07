package model.population;

import java.util.ArrayList;
//import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class TreeNode<T> {

    T data;
    TreeNode<T> parent;
    List<TreeNode<T>> children;
    private int depth = 0;
    private int nodes = 0;

   public void updateFunction (T data) {
	   this.data = data;
   }
    
    
	public void interchangeChildren(TreeNode<T> arbol){
		
		
		// Guardamos el numero de hijo que son estos arboles para luego.
		
		if(arbol.getParent() != null){
			int indexarbol = arbol.getParent().getChildren().indexOf(arbol);
			arbol.getParent().getChildren().set(indexarbol, this);
		}
		
		if(this.getParent() != null){
			int indexthis = this.parent.getChildren().indexOf(this);
			this.parent.getChildren().set(indexthis, arbol);
		}
		
		
		// Cambiamos el padre de los arboles actuales.
		TreeNode<T> aux;
		aux=this.parent;
		this.parent= arbol.getParent();
		arbol.setParent(aux);
	}


	private void setParent(TreeNode<T> p) {
		this.parent = p;
		
	}



	public TreeNode(T data, List<TreeNode<T>> children) {
        this.data = data;
        this.children = children;
        for (TreeNode<T> child : children) {
        	nodes += child.nodes;
        	child.parent = this;
        	if (depth < child.getDepth()+1) {
        		depth = child.getDepth()+1;
        	}
        }
    }
    
    public TreeNode(T data) {
        this.data = data;
        this.children = new LinkedList<TreeNode<T>>();
        nodes++;
        depth++;
    }

    public TreeNode<T> addChild(T child) {
    	if(children.isEmpty()) {
    		depth++;
    		parent.addDepthToParent(depth);
    	}
        TreeNode<T> childNode = new TreeNode<T>(child);
        childNode.parent = this;
        children.add(childNode);
        nodes++;
        if(parent != null) parent.addNodesToParent(1);
        return childNode;
    }
    
    public void addChild (TreeNode<T> hijo){
    	hijo.parent = this;
        this.children.add(hijo);
        if (hijo.getDepth() > depth-1) {
	        depth = hijo.getDepth()+1;
	        if(parent != null) parent.addDepthToParent(depth);
        }
        nodes += hijo.getNodes();
        if(parent != null) parent.addNodesToParent(hijo.getNodes());
    }
    
    private void addNodesToParent(int numnodesnewson) {
    	
    	nodes += numnodesnewson;
    	if (parent != null) {
	    	parent.addNodesToParent(numnodesnewson);
    	}
    }
    
    public void mutateChildrenPositions(){
        TreeNode<T> aux;
        int numofchildren = children.size()-1;
        
        for(int i=0;i<children.size()/2;i++){
         aux=children.get(i);
         children.set(i,children.get(numofchildren-i));
         children.set(numofchildren-i,aux);
        }   
       }
    
    private void addDepthToParent(int newdepth) {
    	
    	if(newdepth+1 > depth){
			depth = newdepth+1;
		}
    	if (parent != null) {
    		parent.addDepthToParent(depth);
    	}
    }
    
    public void removeChild (int childid){
    	TreeNode<T> hijo = children.get(childid);
    	this.children.remove(childid);
    	
    	if (this.depth == hijo.getDepth()+1){ //El hijo que estamos borrando era el que marcaba la longitud asique hay que buscar al nuevo hijo que la marque.
	        int maxdepth = 0;
	        for(int i=0;i<children.size();i++){
	        	if(children.get(i).getDepth()+1 > maxdepth){
	        		maxdepth = children.get(i).getDepth()+1;
	        	}
	        }
    		
    		depth = maxdepth;
    		if(parent != null) parent.recalculateDepthToParent();
        }
        
        nodes = hijo.getNodes()+1;
        if(parent != null) parent.modifyNodesToParent();
    }
    
    private void modifyNodesToParent() {
    	int nodes=1;
    	for(int i=0;i<children.size();i++){
    		nodes+=children.get(i).getNodes();
    	}
    	this.nodes=nodes;
    	
    	if (parent != null) {
    		parent.modifyNodesToParent();
    	}
    }
    
    private void recalculateDepthToParent() {
    	int maxdepth = 0;
    	for(int i=0;i<children.size();i++){
    		if(maxdepth < children.get(i).getDepth() +1){
    			maxdepth =children.get(i).getDepth() +1;
    		}
    	}
    	this.depth = maxdepth;
    	
    	if (parent != null) {
    		parent.recalculateDepthToParent();
    	}
    }
    
	public int getDepth() {
		return depth;
	}
	
	public void setDepth(int depth) {
		this.depth = depth;
	}
	
	public int getNodes() {
		return nodes;
	}
	
	public void setData(T data) {
	  this.data = data;
	 }

	@Override
	public TreeNode<T> clone() {//creo que aqui el depth no va a salir bien pero ya veremos.
		TreeNode<T> newTree = new TreeNode<T>(data);
		for (TreeNode<T> hijo : children)
			newTree.addChild(hijo.clone());
		return newTree;
		
	}
	
	public boolean equals(TreeNode<T> t) {
		if (t == null) 
			return false;
		else if (!t.getData().equals(data)) 
			return false;
		else if (t.getChildren().size() != children.size())
			return false;
		else {
			List <Boolean> boolList = new ArrayList<Boolean>();
			for (int i = 0; i < children.size(); i++) {
				boolList.add(children.get(i).equals(t.getChildren().get(i)));
			}
			boolean equal = true;
			for (Boolean b : boolList) {
				equal &= b;
			}
			return equal;
		}
	}
	
	
	
	public TreeNode<T> getParent() {
		return parent;
	}

	public T getData() {
		return data;
	}

	public List<TreeNode<T>> getChildren() {
		return children;
	}
    	
}
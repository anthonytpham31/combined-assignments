package com.cooksys.ftd.assignments.collections;

import com.cooksys.ftd.assignments.collections.hierarchy.Hierarchy;
import com.cooksys.ftd.assignments.collections.model.Capitalist;
import com.cooksys.ftd.assignments.collections.model.FatCat;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

import javax.swing.tree.DefaultTreeCellEditor.EditorContainer;

public class MegaCorp implements Hierarchy<Capitalist, FatCat> {

	HashSet<Capitalist> hierarchyMegaCorp = new HashSet<>();
    /**
     * Adds a given element to the hierarchy.
     * <p>
     * If the given element is already present in the hierarchy,
     * do not add it and return false
     * <p>
     * If the given element has a parent and the parent is not part of the hierarchy,
     * add the parent and then add the given element
     * <p>
     * If the given element has no parent but is a Parent itself,
     * add it to the hierarchy
     * <p>
     * If the given element has no parent and is not a Parent itself,
     * do not add it and return false
     *
     * @param capitalist the element to add to the hierarchy
     * @return true if the element was added successfully, false otherwise
     */
    @Override
    public boolean add(Capitalist capitalist) {
    	
    	//Catch if capitalist points to null
    	if(capitalist == null){
    		return false;
    	}
    	//If element is in the hierarchy
    	if(hierarchyMegaCorp.contains(capitalist)){
    		return false;
    	}
    	//if element has a parent, but not part of hierarchy	
    	if(capitalist.hasParent() && !hierarchyMegaCorp.contains(capitalist.getParent())){
    		if(add(capitalist.getParent())){
    			//Add parent and child after checking if parent exist using recursion
    			hierarchyMegaCorp.add(capitalist.getParent());
    			hierarchyMegaCorp.add(capitalist);
    			return true;
    		}
    	}
    	//No parent but is a parent itself
    	if(capitalist.hasParent() == false && capitalist instanceof FatCat){
    		hierarchyMegaCorp.add(capitalist);
    		return true;
    	}
    	//Default to false when all other conditions fail (no parent and not a parent)
    	return false;
    }

    /**
     * @param capitalist the element to search for
     * @return true if the element has been added to the hierarchy, false otherwise
     */
    @Override
    public boolean has(Capitalist capitalist) {
        
    	if(capitalist == null){
    		return false;
    	}
    	
    	if(hierarchyMegaCorp.contains(capitalist)){
    		return true;
    	}
    	return false;
    }

    /**
     * @return all elements in the hierarchy,
     * or an empty set if no elements have been added to the hierarchy
     */
    @Override
    public Set<Capitalist> getElements() {
    	
    	HashSet<Capitalist> elementsMegaCorp = new HashSet<>(hierarchyMegaCorp);
    	
    	return elementsMegaCorp;
    }

    /**
     * @return all parent elements in the hierarchy,
     * or an empty set if no parents have been added to the hierarchy
     */
    @Override
    public Set<FatCat> getParents() {
        
    	HashSet<FatCat> parentsMegaCorp = new HashSet<>();
    	
    	for (Iterator<Capitalist> iterator = hierarchyMegaCorp.iterator(); iterator.hasNext();) {
			FatCat parentsHolder = (FatCat) iterator.next();
			parentsMegaCorp.add(parentsHolder);
		}
    	
    	
    	
    	return parentsMegaCorp;
    }

    /**
     * @param fatCat the parent whose children need to be returned
     * @return all elements in the hierarchy that have the given parent as a direct parent,
     * or an empty set if the parent is not present in the hierarchy or if there are no children
     * for the given parent
     */
    @Override
    public Set<Capitalist> getChildren(FatCat fatCat) {

    	HashSet<Capitalist> childrenMegaCorp = new HashSet<>();
    	
    	
    	
    	return childrenMegaCorp;
    }

    /**
     * @return a map in which the keys represent the parent elements in the hierarchy,
     * and the each value is a set of the direct children of the associate parent, or an
     * empty map if the hierarchy is empty.
     */
    @Override
    public Map<FatCat, Set<Capitalist>> getHierarchy() {
    	
    	
    	HashMap<FatCat, Capitalist> hierarchyMap = new HashMap<FatCat, Capitalist>();
    	
    	
    	
    	return hierarchyMap;
    }

    /**
     * @param capitalist
     * @return the parent chain of the given element, starting with its direct parent,
     * then its parent's parent, etc, or an empty list if the given element has no parent
     * or if its parent is not in the hierarchy
     */
    @Override
    public List<FatCat> getParentChain(Capitalist capitalist) {
        throw new NotImplementedException();
    }
}

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team4element.components;

import edu.wpi.first.wpilibj.util.SortedVector;

/**
 *
 * @author sysadmin
 */
public class LookupTable {
    public SortedVector table;
    
    public class TableEntry {
        public double key ;
        public double value;
        
        public TableEntry() {}
        public TableEntry(double k, double v) {
            key = k;
            value = v;
        }   
    }
    
    public class TableEntryComparator implements SortedVector.Comparator {

        public int compare(Object object1, Object object2) {
            TableEntry a = (TableEntry)object1;
            TableEntry b = (TableEntry)object2;
            
            if (a.key == b.key)
                return 0;
            return a.key > b.key?1:-1;
        }
    }
    
    public LookupTable() {
        table = new SortedVector(new TableEntryComparator());
    }
    
    public void addEntry(double key, double value) {
        table.addElement(new TableEntry(key, value));
        table.sort();
    }
    
    public double calculate(double key) {
        //Handle weird edge-cases where we don't have enough entries
        if (table.size() < 2) {
            if (table.size() == 0)
                return 0;
            return ((TableEntry)table.firstElement()).value;
        }
        
        //Find the index of the entry that the key is less than
        //(.sort() sorts greatest first)
        int index = 0;
        for (;index < table.size(); index++) {
            if (key <= ((TableEntry)table.elementAt(index)).key)
                break;
        }
        //if the key was larger than the first value, or
        //we got to the end without finding a smaller value
        if (index == 0 || index == table.size() - 1)
            return ((TableEntry)table.elementAt(index)).value;
        
        TableEntry bigger = ((TableEntry)table.elementAt(index - 1));
        TableEntry smaller = ((TableEntry)table.elementAt(index));
        
        double slope = (bigger.value - smaller.value) / (bigger.key - smaller.key);
        double keydiff = key - smaller.key;
        
        //this is basically y = mx + b
        //but we set the y-axis to be centered around the key value of the
        //smaller key-value pair (yay linear algebra!)
        return (slope * keydiff) + smaller.key;
    }
}

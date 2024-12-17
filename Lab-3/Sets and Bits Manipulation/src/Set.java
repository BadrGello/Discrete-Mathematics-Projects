import java.util.ArrayList;
import java.util.HashMap;

public class Set {

    ArrayList<String> Universe;
    HashMap<String, Integer> Sets; // hashmap: key: set name, value: set as bitwise
    private BitsManipulator bitsMani;

    public Set(ArrayList<String> UniverseIn){
        this.Universe = removeRepetitions(UniverseIn);

        this.Sets = new HashMap<String, Integer>();
    
        this.bitsMani = new BitsManipulator();
    }

    // remove repeated elements in a list (a set should not have repeated elements)
    public ArrayList<String> removeRepetitions(ArrayList<String> In){
        HashMap<String, Boolean> repeated = new HashMap<String, Boolean>(); //to know if the element was encountered before.
        ArrayList<String> reducedList = new ArrayList<String>();

        for (String item : In){
            if (repeated.containsKey(item)){
                continue; // if encountered the element before, skip it.
            }
            else{
                repeated.put(item, true); // if never encountered the element, put it in the map and set its value as true. Also, put it in the new reduced list
                reducedList.add(item);
            }
        }

        return reducedList;
    }

    // Checks if an element is in the universe
    public Boolean isInUniverse(String string){
    
        if (this.Universe.indexOf(string) == -1){
            return false;
        }
        
        return true;
    
    }

    // Checks if a set exists in the sets. (Not used)
    public Boolean isInSets(String string){
    
        if (!this.Sets.containsKey(string)){
            return false;
        }
        
        return true;
    
    }


    //Add a string "element" to the set of name "set"
    public void addToSet(String set, String element){
        
        // Check if string/element is in the universe
        int index = this.Universe.indexOf(element);

        if (index != -1){
            // If set is new, put it in the sets map
            if (!this.Sets.containsKey(set)){
                this.Sets.put(set, 0);
            }

            // Add the element to the set by setting the bit at the position index, 
            // ex :Universe{ red, blue, green }, Set A {}, add to set A "blue", so get the value of old set A -from the map Sets- and do setBit(set A bitwise, 1)
            // now set A is ..00010 which means only blue is in set A
            int newSetValue = this.bitsMani.setBit((int)Sets.get(set), index);  
            this.Sets.put(set, newSetValue);
        }

        else {
            // error, will not add it to the set
            // should be handled in the main
        }
    }

    //Union
    public ArrayList<String> union(String set1, String set2){
        int set1Bits = this.Sets.get(set1);
        int set2Bits = this.Sets.get(set2);

        int unionSet = set1Bits | set2Bits; // (A union B)

        return getSetFromBits(unionSet);
    }

    //Intersection
    public ArrayList<String> intersection(String set1, String set2){
        int set1Bits = this.Sets.get(set1);
        int set2Bits = this.Sets.get(set2);

        int intersectionSet = set1Bits & set2Bits; // (A intersect B)
        
        return getSetFromBits(intersectionSet);
    }

    //Complement
    public ArrayList<String> complement(String set){
        int setBits = this.Sets.get(set);

        int complementSet = ~setBits; // (not A)

        return getSetFromBits(complementSet);
    }

    //Difference
    public ArrayList<String> difference(String set1, String set2){
        int set1Bits = this.Sets.get(set1);
        int set2Bits = this.Sets.get(set2);

        int differenceSet = set1Bits & ~set2Bits; // Based on: A - B = A intersect (not B)

        return getSetFromBits(differenceSet);
    }

    //Cardinality
    public int cardinality(String set){
        return Integer.bitCount(this.Sets.get(set));
    }

    // Print
    // returns a list of strings as the set, the input is the name of the set (string)
    public ArrayList<String> getSetFromString(String set){
        return getSetFromBits((int)this.Sets.get(set));
    }

    // returns a list of strings as the set, the input is the bitwise set
    public ArrayList<String> getSetFromBits(int setBits){
        
        ArrayList<String> setList = new ArrayList<String>();
        for (int i=0; i<this.Universe.size(); i++){
            int bit = this.bitsMani.getBit(setBits, i);
            if (bit == 1){
                setList.add(this.Universe.get(i));
            }
        }
        return setList;
    }

    public ArrayList<String> getUniverse(){
        return this.Universe;
    }
}

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class Set {

    ArrayList<String> Universe;
    HashMap<String, Integer> Sets;
    private BitsManipulator bitsMani;

    public Set(ArrayList<String> UniverseIn){
        this.Universe = removeRepetitions(UniverseIn);

        this.Sets = new HashMap<String, Integer>();
    
        this.bitsMani = new BitsManipulator();
    }

    public ArrayList<String> removeRepetitions(ArrayList<String> In){
        HashMap<String, Boolean> repeated = new HashMap<String, Boolean>();
        ArrayList<String> reducedList = new ArrayList<String>();

        for (String item : In){
            if (repeated.containsKey(item)){
                continue;
            }
            else{
                repeated.put(item, true);
                reducedList.add(item);
            }
        }

        return reducedList;
    }

    public Boolean isInUniverse(String string){
    
        if (this.Universe.indexOf(string) == -1){
            return false;
        }
        
        return true;
    
    }

    public Boolean isInSets(String string){
    
        if (!this.Sets.containsKey(string)){
            return false;
        }
        
        return true;
    
    }


    //Add string to set
    public void addToSet(String set, String element){
        
        // Check if string/element is in the universe
        int index = this.Universe.indexOf(element);

        if (index != -1){
            // If set is new, put it in the sets map
            if (!this.Sets.containsKey(set)){
                this.Sets.put(set, 0);
            }

            int newSetValue = this.bitsMani.setBit((int)Sets.get(set), index);  
            this.Sets.put(set, newSetValue);
        }

        else {
            //error
        }
    }

    //Union
    public ArrayList<String> union(String set1, String set2){
        int set1Bits = this.Sets.get(set1);
        int set2Bits = this.Sets.get(set2);

        int unionSet = set1Bits | set2Bits;

        return getSetFromBits(unionSet);
    }

    //Intersection
    public ArrayList<String> intersection(String set1, String set2){
        int set1Bits = this.Sets.get(set1);
        int set2Bits = this.Sets.get(set2);

        int intersectionSet = set1Bits & set2Bits;
        
        return getSetFromBits(intersectionSet);
    }

    //Complement
    public ArrayList<String> complement(String set){
        int setBits = this.Sets.get(set);

        int complementSet = ~setBits;

        return getSetFromBits(complementSet);
    }

    //Difference
    public ArrayList<String> difference(String set1, String set2){
        int set1Bits = this.Sets.get(set1);
        int set2Bits = this.Sets.get(set2);

        int differenceSet = set1Bits & ~set2Bits;

        return getSetFromBits(differenceSet);
    }

    //Cardinality
    public int cardinality(String set){
        return Integer.bitCount(this.Sets.get(set));
    }

    //Print
    public ArrayList<String> getSetFromString(String set){
        return getSetFromBits((int)this.Sets.get(set));
    }

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

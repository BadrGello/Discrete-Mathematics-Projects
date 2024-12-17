public class BitsManipulator {

    public int getBit(int number, int position){
        int newNumber = number >> position; // 0010 becomes 0001
        return newNumber & 1;
        
    }

    public int setBit(int number, int position){
        int one = 1 << position;
        return number | one;
        
    }

    public int clearBit(int number, int position){
        int one = 1 << position; 
        return number & ~one;
        
    }

    public int updateBit(int number, int position, boolean value){
        if (value){
            return setBit(number, position);
        }

        else{
            return clearBit(number, position);
        }
        
    }


}

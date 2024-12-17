public class Part1 {

    public static void main(String[] args) throws Exception {
        BitsManipulator bitsManipulator = new BitsManipulator();

        System.out.println("Bit is " + bitsManipulator.getBit(4, 2));
        System.out.println("Number after, " + bitsManipulator.clearBit(4, 2));
        System.out.println("Number after, " + bitsManipulator.setBit(4, 2));
        System.out.println("Number after, " + bitsManipulator.updateBit(4, 2, false));
        System.out.println("Number after, " + bitsManipulator.updateBit(4, 2, true));


    }
    
}

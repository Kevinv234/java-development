package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class CallCode extends ByteCode {
    /* CALL
       CALL <funcname> - transfer control to the indicated function.
       Make sure to save where the function should return.
       ex: CALL f
           CALL f<<3>>
           Note: CALL f and Call f<<3>> are executed in the same way.
     */
    private String label, label2;
    private int address;
    StringTokenizer tokens;
    private ArrayList<Integer> arr;
    @Override
    public void init(ArrayList<String> arr){
        if(!arr.isEmpty()){
            label = arr.get(0);
        }

    }

    @Override
    public void execute(VirtualMachine vm){
       int currPC = vm.getPC();

       vm.pushAddrss(currPC);
       vm.setPC(address);

       arr = new ArrayList<>();

       for(int i = 0; i < vm.getCurrentFrame(); i++){
           arr.add(vm.getRunStack(vm.peekFrame() + i));
       }
    }
    // getter for label
    public String getLabel(){
        return label;
    }
    //address
    public void replaceAddress(int index){
        address = index;
    }
    @Override
    public String toString (){
        String output = "CALL " + label + "\t " + label2 + "(";
        this.tokens = new StringTokenizer(label, "<");
        label2 = tokens.nextToken();

        if(arr.size() == 0 ){
            output += ")";
        }
        else{
            for(int i = 0 ; i < arr.size(); i++){
                if(i != (arr.size() - 1)){
                    output += arr.get(i) + ", ";
                }
                else{
                    output += arr.get(i) + ")";
                }
            }
        }
        return output;

    }
}

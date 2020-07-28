package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class FalseBranchCode extends ByteCode {
    /*FALSEBRANCH
      FALSEBRANCH <label> pop the top of the stack; if it is false (0) then
      branch to <label>, else execute the next bytecode
      ex: FALSEBRANCH xyz<<3>>
     */
    private String label;
    private int num;
    @Override
    public void init(ArrayList<String> arr){
        if(!arr.isEmpty()){
            label = arr.get(0);
        }
    }

    @Override
    public void execute(VirtualMachine vm){

        if(vm.popRS() == 0){
            vm.setPC(num);
        }
    }
    @Override
    public String toString (){
        return "FALSEBRANCH " + label;
    }
    public String getLabel(){
        return label;
    }

    public void replaceAddress(int adrs){
         num= adrs;
    }
}

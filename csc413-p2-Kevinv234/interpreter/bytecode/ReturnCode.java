package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class ReturnCode extends ByteCode {
    /* RETURN
       RETURN <funcname>; Return from the current;
       <funcname> is used as a comment to indicate the current function.
       RETURN is generated for intrinsic functions.
       ex: RETURN f<<2>>
           RETURN
           Note: returns with labels functions
           EXECUTE THE same as returns without labels.
     */
    private String num1, label;
    @Override
    public void init(ArrayList<String> arr){
        if(!arr.isEmpty()){
            label = arr.get(0);
        }
    }

    @Override
    public void execute(VirtualMachine vm){
        vm.popFrame();
        vm.setPC(vm.popReturnAdrs());
    }
    @Override
    public String toString (){
       return "RETURN " + label;
    }
}

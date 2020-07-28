package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class WriteCode extends ByteCode {
    /* WRITE
       WRITE; Write the value of the top of
       the stack to output. Leave the value on the
       top of the stack.
       ex: WRITE
     */
    @Override
    public void init(ArrayList<String> arr){

    }

    //Prints the top value on the runstack
    @Override
    public void execute(VirtualMachine vm){

        System.out.println(vm.peekRS());

    }
    @Override
    public String toString (){
        return "WRITE";
    }
}

package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class ArgsCode extends ByteCode {
    /* ARGS
       ARGS b ; Used prior to calling a function
       n = # of args this instruction is immediately followed by the CALL instruction
       the function has n args so ARGS n instructs the interpreter to set up a new frame
       n down from the top of the runtime stack;
       ex: ARGS 4
           ARGS 0
           ARGS 2
     */
    private int num;
    @Override
    public void init(ArrayList<String> arr){
        //Cast number retrieved from array
        if(!arr.isEmpty()){
            num = Integer.parseInt(arr.get(0));
        }
    }

    @Override
    public void execute(VirtualMachine vm){
        vm.newFrameAt(num);
    }
    @Override
    public String toString (){
        return "ARGS " + num ;
    }
}

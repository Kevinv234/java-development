package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class DumpCode extends ByteCode{
    /* DUMP
       This bytecode is used to set the state of dumping in the virtual machine.
       When dump is on, after the execution of each bytecode, the state of the
       runtime stack is dumped to the console.
       ex: DUMP on
           DUMP off

     */
    private String label;
    @Override
    public void init(ArrayList<String> arr) {
        if(!arr.isEmpty()){
            label = arr.get(0);
        }

    }

    @Override
    public void execute(VirtualMachine vm){
        if(label.equals("ON")){
            vm.dumpState(true);
        }
        else{
            vm.dumpState(false);
        }
    }
    @Override
    public String toString (){
        return "DUMP " + label;
    }
}

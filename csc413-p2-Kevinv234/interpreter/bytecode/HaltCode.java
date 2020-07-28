package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class HaltCode extends ByteCode{
    /*HALT
      Halt the execution of a program
      ex: HALT
    */
    @Override
    public void init(ArrayList arr){ }

    //Change the state of halt that correlates to dumping
    @Override
    public void execute(VirtualMachine vm){
        vm.haltVM();
    }
    @Override
    public String toString (){
        return "HALT";
    }

}

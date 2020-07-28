package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class LitCode extends ByteCode {
    /* LIT
       LIT n - load the literal value n
       LIT 0 i - this form of lit was generated to load 0 on the stack
       to initialize the variable i to  the value 0 and reserve space on
       the runtime stack for i.
       ex: LIT 5
           LIT 0 i
     */


    private String label;
    private int num, numOfArgs;

    @Override
    public void init(ArrayList<String> arr){
        numOfArgs = arr.size();

        if(!arr.isEmpty()){
            num = Integer.parseInt(arr.get(0));

            if (arr.size() > 1) {
                label = arr.get(1);
            }
        }
    }

    @Override
    public void execute(VirtualMachine vm){
        vm.pushRS(num);
    }
    @Override
    public String toString (){
        String output = "";

        if(numOfArgs == 1){
            output = "LIT " + num;
        }
        if(numOfArgs == 2){
            output = "LIT " + num + " " + label + "\t\tint " + label;
        }

        return output;
    }
}

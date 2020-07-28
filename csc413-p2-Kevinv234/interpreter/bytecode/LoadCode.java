package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;


public class LoadCode extends ByteCode{
    /* LOAD
       Load n <id> ; push the value in the slot which is offset n from
       the start of the frame onto the top of the stack; <id> is used
       as a comment and for dumping, its the variable name where the data
       is loaded.
       ex : LOAD 3
            LOAD 2 i
     */
    private int num, numOfArgs;
    private String label;
    @Override
    public void init(ArrayList<String> arr){
        // cast both values into string
        numOfArgs = arr.size();

        if(!arr.isEmpty()){
            num = Integer.parseInt(arr.get(0));

            if(numOfArgs > 1){
                label = arr.get(1);
            }
        }

    }

    // call the load function to load the frame and push the value
    @Override
    public void execute(VirtualMachine vm){
        vm.loadCode(num);
    }
    @Override
    public String toString (){
        String printS = "";
        //check how many argumennts we retrieved from the array list
        if(numOfArgs == 1 ){
            printS =  "LOAD " + num;
        }

        if(numOfArgs == 2){
            printS =  "LOAD " + num + " " + label + "     " + "<load " + label + ">";
        }
        return printS;
    }
}


package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class StoreCode extends ByteCode{
    /*STORE
    STORE N <id> - pop the top of the stack; store the value into the offset n
    from the start of the frame; <id> is used as a comment and for dumping,
    its the variable name where the data is stored.
    ex: STORE 3 i
        STORE 2 i
     */
    private String label;
    private int value,numOfArgs;

    @Override
    public void init(ArrayList<String> arr){
      numOfArgs = arr.size();

      if(!arr.isEmpty()){
          value = Integer.parseInt(arr.get(0));

          if(arr.size() > 1){
              label = arr.get(1);
          }
      }
    }

    @Override
    public void execute(VirtualMachine vm){
        vm.storeRS(value);
    }
    @Override
    public String toString (){
     return "STORE " + value + " " + label;
    }
}


package interpreter.bytecode;
import interpreter.VirtualMachine;
import java.util.ArrayList;

public class PopCode extends ByteCode{
    /*POP
      Pop n: pop the top n levels of the runtime
      ex: POP 5
          POP 0
     */
    private int num;

    @Override
    public void init(ArrayList<String> arr){
        if(!arr.isEmpty()){
            num = Integer.parseInt(arr.get(0));
        }

    }

    @Override
    public void execute(VirtualMachine vm){
        //
        if(num > vm.getCurrentFrame()){
            for(int i = 0; i < vm.getCurrentFrame(); i++){
                vm.popRS();
            }
        }
        else{
            for(int i = 0; i < num; i++){
                vm.popRS();
            }
        }
    }

    @Override
    public String toString (){
        return "POP " + num;
    }
}

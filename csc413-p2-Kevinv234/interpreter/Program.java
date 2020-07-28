package interpreter;

import java.lang.Integer;
import interpreter.bytecode.*;
import java.util.HashMap;

import interpreter.bytecode.ByteCode;
import interpreter.bytecode.LabelCode;


import java.util.ArrayList;

public class Program {


    private ArrayList<ByteCode> program;
    private static HashMap<String,Integer> hashLabel;


    public Program() {
        program = new ArrayList<>();
    }
    protected ByteCode getCode(int pc) {
        return this.program.get(pc);
    }
    public int getSize() {
        return this.program.size();
    }

    /**
     * This function should go through the program and resolve all addresses.
     * Currently all labels look like LABEL <<num>>>, these need to be converted into
     * correct addresses so the VirtualMachine knows what to set the Program Counter(PC)
     * HINT: make note what type of data-stucture bytecodes are stored in.
     *
     * @param //Program object that holds a list of ByteCodes
     */

    public void resolveAddrs(Program program) {
        hashLabel = new HashMap<>();


        //write a for loop to get all the data from the the program with individual bytecode classes
        for(int i = 0; i < program.getSize(); i++){
            ByteCode bc = program.getCode(i);
            if (bc instanceof LabelCode){
                hashLabel.put(((LabelCode) bc).getLabel(), i);
            }
        }

        for(int i = 0; i < program.getSize(); i++){
            ByteCode bc = program.getCode(i);

            //create a if statement to check for when we find that specific bytecode
            if(bc instanceof CallCode){
                if(hashLabel.containsKey(((CallCode) bc).getLabel())){
                    ((CallCode) bc).replaceAddress(hashLabel.get(((CallCode) bc).getLabel()));
                }
            }

            //create a if statement to check for when we find that specific bytecode
            if(bc instanceof  FalseBranchCode){
                if (hashLabel.containsKey(((FalseBranchCode) bc).getLabel())) {
                    ((FalseBranchCode) bc).replaceAddress(hashLabel.get(((FalseBranchCode) bc).getLabel()));
                }
            }

            //create a if statement to check for when we find the specific bytecode
            if(bc instanceof GotoCode){
                if (hashLabel.containsKey(((GotoCode) bc).getLabel())) {
                    ((GotoCode) bc).replaceAddress(hashLabel.get(((GotoCode) bc).getLabel()));
                }
            }
        }
    }

    public void addByteCode(ByteCode byteCode){
        program.add(byteCode);
    }

}

package interpreter;
import interpreter.bytecode.ByteCode;
import java.util.Stack;

public class VirtualMachine {

    private RunTimeStack runStack;
    private Stack  returnAddrs;
    private Program program;
    private int pc;
    private boolean isRunning, isDump;


    protected VirtualMachine(Program program) {
        this.program = program;
    }


    // method is used to execute the program
    public void executeProgram(){
        pc = 0;
        runStack = new RunTimeStack();
        returnAddrs = new Stack<Integer>();
        isRunning = true;
        isDump = false;

        //create a while loop for when we run the program
        while(isRunning){
            ByteCode code = program.getCode(pc);
            code.execute(this);
            //runStack.dump();
           // if(isDump && !(code instanceof DumpCode)) {
            //    System.out.println(code.toString());
            //    runStack.dump();
           // }
            pc++;
        }
    }
    // functions for VM
    // on off state for dump
    public void dumpState(boolean flag){
        isDump = flag;
    }

    //functions for the address
    public void setPC(int address){
        pc = address;
    }
    public int getPC(){
        return pc;
    }


    //address functions
    public void pushAddrss(int adrs){
         returnAddrs.push(adrs);
    }
    // RunTimeStack functions

    public int push(int val){ return runStack.push(val);}
    public void loadCode(int val){  runStack.load(val);}


    public int popReturnAdrs(){
        if(returnAddrs.isEmpty()){
            return 0;
        }
        return (int) returnAddrs.pop();
    }


    public void haltVM(){
        isRunning = false;
    }

    // RunTimeStack functions
    public int peekRS(){ return runStack.peek();}
    public int popRS(){ return runStack.pop();}
    public void pushRS(Integer val){  runStack.push(val);}
    public int getRunStack(int index){return runStack.get(index); }
    public void popFrame(){ runStack.popFrame();}
    public void newFrameAt( int offset) {runStack.newFrameAt(offset);}

    public int storeRS(int val){ return runStack.store(val);}


    // FramePointer functions

    public int getCurrentFrame(){ return runStack.getCurrentFrame();}
    public int peekFrame(){ return runStack.peek();}
}


package interpreter;

import interpreter.bytecode.ByteCode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class ByteCodeLoader extends Object {

    private BufferedReader byteSource;
    private Program program;
    /**
     * Constructor Simply creates a buffered reader.
     * YOU ARE NOT ALLOWED TO READ FILE CONTENTS HERE
     * THIS NEEDS TO HAPPEN IN LOADCODES.
     */
    public ByteCodeLoader(String file) throws IOException {
        this.byteSource = new BufferedReader(new FileReader(file));
    }
    /**
     * This function should read one line of source code at a time.
     * For each line it should:
     *      Tokenize string to break it into parts.
     *      Grab THE correct class name for the given ByteCode from CodeTable
     *      Create an instance of the ByteCode class name returned from code table.
     *      Parse any additional arguments for the given ByteCode and send them to
     *      the newly created ByteCode instance via the init function.
     *       while(reader.ready()){
     *             line = reader.readline();
     *
     *             Scanner in = new Scanner(line);
     *
     *             while(in.hasNext()){
     *                 mylist.add(in.next());
     *             }
     *             bc.init(mylist);
     *             pro.add(bc);
     *
     *         }
     *         return prop.resolveAddrs();
     */
    public Program loadCodes(){
        try{
            //Create all data fields
            program = new Program();
            ArrayList<String> args = new ArrayList<String>();
            CodeTable.init();
            String readLine = byteSource.readLine();

            //Read the file and tokenize the class names to search within the codeTable
            while(readLine != null) {
                StringTokenizer tok = new StringTokenizer(readLine);
                args.clear();
                String codeClass = CodeTable.getClassName(tok.nextToken());

                //Collect byteCode into tokens
                while(tok.hasMoreTokens()) {
                    args.add(tok.nextToken());
                }

                //MY PROGRAM BREAKS AT THIS LINE, IM SORRY
                ByteCode byteCode = (ByteCode)(Class.forName("interpreter.bytecode."
                        + codeClass).newInstance());
                byteCode.init(args);
                program.addByteCode(byteCode);
                readLine = byteSource.readLine();
            }
            program.resolveAddrs(program);
        }


        //Catch all possible exceptions being made.
        catch (IOException i){
            System.out.println("There is an error detected for the input/output.");
        }
        catch (ClassNotFoundException c){
            System.out.println("There is an error finding the class.");
            c.printStackTrace();
            System.out.println(c.toString());
        }
        catch(IllegalAccessException l){
            System.out.println("There is illegal access being made.");
        }
        catch(InstantiationException inst){
            System.out.println("There is a instantiation exception being made.");
        }
        return program;
    }
}

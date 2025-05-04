import java.rmi.*;

public class AddServer {
public static void main(String args[]) {
try { 
//create remote object
AddServerImpl addServerImpl = new AddServerImpl(); //Instantiates the remote object.

//bind the remote object
Naming.rebind("AddServer", addServerImpl);//Binds it to the name "AddServer" so clients can look it up.
}
catch (Exception e) {
System.out.println("Exception: "+ e);
}}}



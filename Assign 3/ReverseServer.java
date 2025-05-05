import ReverseModule.*; 

import org.omg.CosNaming.*;

import org.omg.CosNaming.NamingContextPackage.*;

import org.omg.CORBA.*; 

import org.omg.PortableServer.*;



class ReverseServer

{

	public static void main(String[] args)

	{

		try

		{ 	// initialize the ORB

			org.omg.CORBA. ORB orb = org.omg.CORBA.ORB.init(args, null);

			

			// initialize the BOA/POA

			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA")); 

			rootPOA.the_POAManager().activate();

			// creating the calculator object 

			ReverseImpl rvr = new ReverseImpl();

			

			// get the object reference from the servant class 

			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(rvr);

			

			System.out.println("Step1");

			Reverse h_ref = ReverseModule.ReverseHelper.narrow(ref); 

			System.out.println("Step2");

			

			org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");

			

			System.out.println("Step3");

			NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

 

			System.out.println("Step4");

			

			String name = "Reverse";

			NameComponent path[] = ncRef.to_name(name); 

			ncRef.rebind(path,h_ref);

			

			System.out.println("Reverse Server reading and waiting....");

			orb.run();

		}

		catch (Exception e)

		{

			e.printStackTrace();

		}

	}

}

/*Great — you’ve uploaded the CORBA project files: ReverseModule.idl, ReverseServer.java, ReverseClient.java, and ReverseImpl.java. Here’s a step-by-step guide to run your CORBA assignment in Visual Studio Code on Windows.

✅ STEP 1: Install Prerequisites
Install Java 8 JDK

Go to Adoptium JDK 8 Downloads

Choose:

Version: 8

OS: Windows

Arch: x64

Download .msi installer → Install it.

Install Java CORBA tools

Java 8 includes idlj, tnameserv, and orbd. These are required for CORBA.

Set Environment Variables

Ensure Java 8 is in PATH and JAVA_HOME:

sh
Copy
Edit
echo %JAVA_HOME%
javac -version
idlj -version
✅ STEP 2: Set Up Project in VS Code
Create a project folder (e.g., CORBAReverse)

Place all files:

Copy
Edit
CORBAReverse/
├── ReverseModule.idl
├── ReverseImpl.java
├── ReverseServer.java
├── ReverseClient.java
✅ STEP 3: Generate Java stubs from IDL
Open a terminal in VS Code and run:
idlj -fall ReverseModule.idl
This generates:

mathematica
Copy
Edit
ReverseModule/
├── Reverse.java
├── ReverseHelper.java
├── ReverseHolder.java
├── ReverseOperations.java
├── ReversePOA.java
✅ STEP 4: Compile All Java Files
sh
Copy
Edit
javac ReverseModule/*.java ReverseImpl.java ReverseServer.java ReverseClient.java
✅ STEP 5: Run the CORBA Naming Service
Open a new terminal window and run:

sh
Copy
Edit
tnameserv -ORBInitialPort 1050
Leave this terminal running.

✅ STEP 6: Run the Server
In another terminal, run:

sh
Copy
Edit
java ReverseServer -ORBInitialPort 1050 -ORBInitialHost localhost
Expected output:

python-repl
Copy
Edit
Reverse Object Created
Step1
Step2
...
Reverse Server reading and waiting....
✅ STEP 7: Run the Client
In another terminal, run:

sh
Copy
Edit
java ReverseClient -ORBInitialPort 1050 -ORBInitialHost localhost
Then enter a string (e.g., hello) → Output:

mathematica
Copy
Edit
Enter String=
olleh
✅ Summary of Terminals
You should keep 3 terminals:

tnameserv

ReverseServer

ReverseClient

Would you like me to help you create a run.bat or tasks.json file to automate the steps?








 */
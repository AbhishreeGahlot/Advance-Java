REMOTE Method Invocation : Implemented a calculator which performs addition,subtraction,multiplication,division;
Interface --- Calculator.java

package rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Calculator extends Remote {
	 int add(int a, int b) throws RemoteException;
	 int sub(int a, int b) throws RemoteException;
	 int multiply(int a, int b) throws RemoteException;
}


Class ---- ClaculatorImpl.java

package rmi;

import java.rmi.RemoteException;
import java.rmi.server.RMIClientSocketFactory;
import java.rmi.server.RMIServerSocketFactory;
import java.rmi.server.UnicastRemoteObject;

public class CalculatorImpl extends UnicastRemoteObject implements Calculator {

	protected CalculatorImpl() throws RemoteException {
		super();
	}

	@Override
	public int add(int a, int b) throws RemoteException {
		return a+b;
	}
	
	@Override
	public int sub(int a, int b) throws RemoteException {
		return a-b;
	}
	
	@Override
	public int multiply(int a, int b) throws RemoteException {
		return a*b;
	}

}

Class --- RMIServer.java

package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
	public static void main(String[] args) {
		try {
			Calculator calculator = new CalculatorImpl();
            
            Registry registry = LocateRegistry.createRegistry(1088);
            
            registry.bind("CalculatorService", calculator);
            
            System.out.println("Server is running...");
		}catch (Exception e) 
        {
            e.printStackTrace();
        }

	}

}


Class --- RMIClient.java

package rmi;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIClient {

	public static void main(String[] args) {
		try {
            Registry registry = LocateRegistry.getRegistry("localhost", 1088);
            
            Calculator calculator = (Calculator) registry.lookup("CalculatorService");
            
            int result1 = calculator.add(5, 3);
            int result2 = calculator.sub(10, 6);
            int result3 = calculator.multiply(25,10);
            
            System.out.println("Result 1: " + result1);
            System.out.println("Result 2: " + result2);
            System.out.println("Result 3: " + result3);
         
        } catch (Exception e) {
            e.printStackTrace();
        }

	}

}

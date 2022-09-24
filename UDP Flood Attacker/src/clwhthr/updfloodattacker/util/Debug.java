package clwhthr.updfloodattacker.util;

public class Debug {
	public static boolean isDebugMode = false;
	/**
	 * to do
	 */
	public static void log(String format,Object...objects) {
		if(!isDebugMode)return;
		System.out.printf(format,objects);
	}
	public static void log(Object obj) {
		if(!isDebugMode)return;
		log(obj, true);
	}
	public static void log(Object obj,boolean flag) {
		if(!isDebugMode)return;
		if(flag)System.out.println(obj.toString());
		else System.out.print(obj.toString());
	}
	
	public static void printUsage() {
		System.out.println("Usage:");
		System.out.println("udpflood <ip> <port>");
	}
	public static void errorArg() {
		System.out.println("Error:Wrong arguments!");
		printUsage();
	}
}

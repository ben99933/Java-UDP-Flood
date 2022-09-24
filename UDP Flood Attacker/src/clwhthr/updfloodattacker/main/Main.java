package clwhthr.updfloodattacker.main;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import clwhthr.updfloodattacker.util.ParameterHandler;
import clwhthr.updfloodattacker.util.Debug;
import clwhthr.updfloodattacker.util.PacketSender;

public class Main {
	static {
		Debug.isDebugMode = false;
	}
	public static void main(String args[]) {
		ParameterHandler handler = new ParameterHandler(args);
		PacketSender packetSender = PacketSender.getInstance();
		boolean succ = handler.handle();
		if(!succ)System.exit(0);
		packetSender.startAttack();
		Scanner input = new Scanner(System.in);
		while(input.hasNext()) {
			String line = input.nextLine();
			if(line.equals("stop") || line.length()==0)break;
		}
		input.close();
		System.out.println("stop attack");
		packetSender.stop();
		
	}
}

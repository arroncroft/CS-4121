package messageSegmentation;

public class Message_Segmentation {
	//variables
	static final int MESSAGE_LENGTH = 16;
	static final int TRANSMISSION_RATE = 4;
	static final int LINKS = 3;
	//static double headerFields = 0;	//test 1
	//static double headerFields = 0.01;//test 2
	static double headerFields = 0.02;	//test 3
	static double packetSize = 4;
	static double numPackets = MESSAGE_LENGTH / packetSize;
	static double delay;

	public static void main(String[] args) {
		System.out.println("****************************************");
		System.out.println("Message length = " + MESSAGE_LENGTH + " kb");
		System.out.println("Transmission rate = " + TRANSMISSION_RATE + " kbps");
		System.out.println("Current header fields overhead = " + headerFields + " kb");
		System.out.println("****************************************");
		calculate();
		print();
		packetSize = packetSize/2;
		numPackets = MESSAGE_LENGTH / packetSize;
		calculate();
		print();
		packetSize = packetSize/2;
		numPackets = MESSAGE_LENGTH / packetSize;
		calculate();
		print();
		packetSize = packetSize/2;
		numPackets = MESSAGE_LENGTH / packetSize;
		calculate();
		print();
		packetSize = packetSize/2;
		numPackets = MESSAGE_LENGTH / packetSize;
		calculate();
		print();
		packetSize = packetSize/2;
		numPackets = MESSAGE_LENGTH / packetSize;
		calculate();
		print();
	}

	public static void print(){
		System.out.println("Number of packets = " + numPackets + " packets");
		System.out.println("Size of each packet = " + packetSize + " kb");
		System.out.println("End-to-end delay = " + delay + " seconds");
		System.out.println();
	}

	public static void calculate(){
		delay = (((packetSize + headerFields) / TRANSMISSION_RATE) * LINKS) +
				((numPackets - 1) * ((packetSize + headerFields) / TRANSMISSION_RATE));
	}
}
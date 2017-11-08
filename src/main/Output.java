package main;

import java.io.ObjectOutputStream;

public class Output {
	
	ObjectOutputStream out;
	int sign;
	
	public Output(ObjectOutputStream out, int sign) {
		super();
		this.out = out;
		this.sign = sign;
	}

	public ObjectOutputStream getOut() {
		return out;
	}

	public int getSign() {
		return sign;
	}
	
	

}

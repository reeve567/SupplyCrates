package org.koalakode.reeve.SupplyCrates.Util;
// made by reeve
// on 8:53 PM

import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ConfigCheck {
	private JavaPlugin main;
	private String license;

	public ConfigCheck(JavaPlugin main) {
		this.main = main;
		license = main.getConfig().getString("key");
	}

	public boolean Init() {
		if (license != null) {

			try {
				Socket socket = new Socket("52.232.190.52",6564);

				PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

				out.println(license);
				if (in.readLine().equals("true")) {
					socket.close();
					return true;
				}
				else {
					socket.close();
					return false;
				}
			}
			catch (IOException e) {
				System.err.println("Setup may have not completed properly!");
			}
		}
		return false;
	}


}

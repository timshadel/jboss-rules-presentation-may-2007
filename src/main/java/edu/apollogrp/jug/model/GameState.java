package edu.apollogrp.jug.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class GameState {
	private int step = 1;
	private String sayThis = "";
	private String response = "";
	private boolean needResponse = false;
	private InputStreamReader isr = new InputStreamReader( System.in );
	private BufferedReader stdin = new BufferedReader( isr );

	public void sayIt() {
		System.out.println(sayThis);
	}
	
	public void askForResponse() {
		try {
			setResponse( stdin.readLine() );
		} catch (IOException e) {
			setResponse("Something unintelligible.");
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("STATE:");
		sb.append("\n  ");
		sb.append(step);
		sb.append("\n  ");
		sb.append(sayThis);
		sb.append("\n  ");
		sb.append(needResponse);
		sb.append("\n  ");
		sb.append(response);
		sb.append("\n  ");
		return sb.toString();
	}

	/* *********  GETTERS / SETTERS  ********* */
	
	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}

	public String getSayThis() {
		return sayThis;
	}

	public void setSayThis(String sayThis) {
		this.sayThis = sayThis;
	}

	public int getStep() {
		return step;
	}

	public void setStep(int step) {
		this.step = step;
	}

	public boolean isNeedResponse() {
		return needResponse;
	}

	public void setNeedResponse(boolean needResponse) {
		this.needResponse = needResponse;
	}	
}

package com.financial.transaction.proxy.dto;

import java.io.Serializable;

public class CheckBalanceAvailableDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private String numberCard;
	private boolean availability;

	public CheckBalanceAvailableDTO() {
	}
	
	public CheckBalanceAvailableDTO(String numberCard, boolean availability) {
		this.numberCard = numberCard;
		this.availability = availability;
	}

	public String getNumberCard() {
		return numberCard;
	}

	public void setNumberCard(String numberCard) {
		this.numberCard = numberCard;
	}

	public boolean isAvailability() {
		return availability;
	}

	public void setAvailability(boolean availability) {
		this.availability = availability;
	}

}

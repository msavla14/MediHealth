package com.medihealth.beans;

/**
 * The Class MedicalService contains Medical service info such as service name
 * enrolled by a patient, default price of a service and discounted price after
 * applying insurance based discounts and age related discounts.
 * 
 * @author Mithun Savla
 */
public class MedicalService {

	/** The service name. */
	private String serviceName;

	/** The default price of a service. */
	private double defaultPrice;

	/** The discounted price after all the discounts are applied. */
	private double discountedPrice;

	/**
	 * Gets the service name.
	 *
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}

	/**
	 * Sets the service name.
	 *
	 * @param serviceName
	 *            the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	/**
	 * Gets the default price.
	 *
	 * @return the defaultPrice
	 */
	public double getDefaultPrice() {
		return defaultPrice;
	}

	/**
	 * Sets the default price.
	 *
	 * @param defaultPrice
	 *            the defaultPrice to set
	 */
	public void setDefaultPrice(double defaultPrice) {
		this.defaultPrice = defaultPrice;
	}

	/**
	 * Gets the discounted price.
	 *
	 * @return the discountedPrice
	 */
	public double getDiscountedPrice() {
		return discountedPrice;
	}

	/**
	 * Sets the discounted price.
	 *
	 * @param discountedPrice
	 *            the discountedPrice to set
	 */
	public void setDiscountedPrice(double discountedPrice) {
		this.discountedPrice = discountedPrice;
	}
}

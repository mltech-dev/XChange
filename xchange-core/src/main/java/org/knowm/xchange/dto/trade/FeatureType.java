package org.knowm.xchange.dto.trade;

public enum FeatureType {

	FEATURE, SWAP;

	public static FeatureType getFeatureType(String s) {
		try {
			return FeatureType.valueOf(s);
		} catch (Exception e) {
			throw new RuntimeException("Unknown feature type " + s + ".");
		}
	}
//	public 

}

package com.wiki.calculator;

public class NormalizeImp implements Normalize {
	private float max = 100;
	@Override
	public float[] normalize(float[] values) {
		float total = 0.0f;
		if(values!=null){
			for(int i =0;i<values.length;i++){
				total +=values[i];
			}
			float[] normalized = new float[values.length];
			
			for(int i =0;i<normalized.length;i++){
				normalized[i] = (values[i]/total)*max;
			}
			return normalized;
		}else{
			return null;
		}
		
		
	}

	@Override
	public void max(float max) {
		this.max = max ;
	}
	

}

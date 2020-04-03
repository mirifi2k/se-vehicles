package ro.upt.se;

import ro.upt.se.clauses.Clause;

public class Response {
	private Clause engineType;
	private Clause prefferedBrand;
	private Clause engineCapacity;
	private Clause powerCapacity;
	private Clause prefferedBody;
	private Clause seatsNumber;
	private Clause desiredPrice;
	private Clause prefferedTransmission;
	private Clause awd;
	
	public Clause getEngineType() {
		return engineType;
	}
	
	public void setEngineType(Clause engineType) {
		this.engineType = engineType;
	}
	
	public Clause getPrefferedBrand() {
		return prefferedBrand;
	}
	
	public void setPrefferedBrand(Clause prefferedBrand) {
		this.prefferedBrand = prefferedBrand;
	}
	
	public Clause getEngineCapacity() {
		return engineCapacity;
	}
	
	public void setEngineCapacity(Clause engineCapacity) {
		this.engineCapacity = engineCapacity;
	}
	
	public Clause getPowerCapacity() {
		return powerCapacity;
	}
	
	public void setPowerCapacity(Clause powerCapacity) {
		this.powerCapacity = powerCapacity;
	}
	
	public Clause getPrefferedBody() {
		return prefferedBody;
	}
	
	public void setPrefferedBody(Clause prefferedBody) {
		this.prefferedBody = prefferedBody;
	}
	
	public Clause getSeatsNumber() {
		return seatsNumber;
	}
	
	public void setSeatsNumber(Clause seatsNumber) {
		this.seatsNumber = seatsNumber;
	}
	
	public Clause getDesiredPrice() {
		return desiredPrice;
	}
	
	public void setDesiredPrice(Clause desiredPrice) {
		this.desiredPrice = desiredPrice;
	}
	
	public Clause getPrefferedTransmission() {
		return prefferedTransmission;
	}
	
	public void setPrefferedTransmission(Clause prefferedTransmission) {
		this.prefferedTransmission = prefferedTransmission;
	}
	
	public Clause getAwd() {
		return awd;
	}
	
	public void setAwd(Clause awd) {
		this.awd = awd;
	}
}

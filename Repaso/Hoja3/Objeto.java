/**
 * 
 */
package Hoja3;

import java.math.BigInteger;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Objeto {

	String name;
	String sex;
	String birht;
	String city;
	String country;
	String continent;
	String occupation;
	String industry;
	String domain;
	Double languajes;
	Double views;
	Double average;
	Double popularity;
	
	public Objeto(String name, String sex, String birht, String city, String country, String continent,
			String occupation, String industry, String domain, Double languajes, Double views, Double average,
			Double popularity) {
		super();
		this.name = name;
		this.sex = sex;
		this.birht = birht;
		this.city = city;
		this.country = country;
		this.continent = continent;
		this.occupation = occupation;
		this.industry = industry;
		this.domain = domain;
		this.languajes = languajes;
		this.views = views;
		this.average = average;
		this.popularity = popularity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirht() {
		return birht;
	}

	public void setBirht(String birht) {
		this.birht = birht;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public String getDomain() {
		return domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public Double getLanguajes() {
		return languajes;
	}

	public void setLanguajes(Double languajes) {
		this.languajes = languajes;
	}

	public Double getViews() {
		return views;
	}

	public void setViews(Double views) {
		this.views = views;
	}

	public Double getAverage() {
		return average;
	}

	public void setAverage(Double average) {
		this.average = average;
	}

	public Double getPopularity() {
		return popularity;
	}

	public void setPopularity(Double popularity) {
		this.popularity = popularity;
	}
	
}

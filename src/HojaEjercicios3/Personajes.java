/**
 * 
 */
package HojaEjercicios3;

/**
 * @author Ivan.Perez
 *
 *  https://github.com/IvanPerez9
 */
public class Personajes {

	private String name;
	private String sex;
	private Integer birth;
	private String city;
	private String country;
	private String continent;
	private String occupation;
	private String industry;
	private String domain;
	private Integer languajes;
	private Integer views;
	private Integer average;
	private Integer popularity;
	
	@Override
	public String toString() {
		return "Personajes [name=" + name + ", sex=" + sex + ", birth=" + birth + ", city=" + city + ", country="
				+ country + ", continent=" + continent + ", occupation=" + occupation + ", industry=" + industry
				+ ", domain=" + domain + ", languajes=" + languajes + ", views=" + views + ", average=" + average
				+ ", popularity=" + popularity + "]";
	}
	
	/**
	 * @param name
	 * @param sex
	 * @param birth
	 * @param city
	 * @param country
	 * @param continent
	 * @param occupation
	 * @param industry
	 * @param domain
	 * @param languajes
	 * @param views
	 * @param average
	 * @param popularity
	 */
	public Personajes(String name, String sex, Integer birth, String city, String country, String continent,
			String occupation, String industry, String domain, Integer languajes, Integer views, Integer average,
			Integer popularity) {
		super();
		this.name = name;
		this.sex = sex;
		this.birth = birth;
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
	public Integer getBirth() {
		return birth;
	}
	public void setBirth(Integer birth) {
		this.birth = birth;
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
	public Integer getLanguajes() {
		return languajes;
	}
	public void setLanguajes(Integer languajes) {
		this.languajes = languajes;
	}
	public Integer getViews() {
		return views;
	}
	public void setViews(Integer views) {
		this.views = views;
	}
	public Integer getAverage() {
		return average;
	}
	public void setAverage(Integer average) {
		this.average = average;
	}
	public Integer getPopularity() {
		return popularity;
	}
	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}
	
	
	
}

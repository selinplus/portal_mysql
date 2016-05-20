package org.ytgs.portal.common.model;


public class Weather {
	
private String id;
private String content;
private String source;
private String weatherTime;
private String weatherId;
private String createTime;
private String week;
private String title;

public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getTitle() {
	return title;
}
public void setTitle(String title) {
	this.title = title;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getSource() {
	return source;
}
public void setSource(String source) {
	this.source = source;
}
public String getWeatherTime() {
	return weatherTime;
}
public void setWeatherTime(String weatherTime) {
	this.weatherTime = weatherTime;
}
public String getWeatherId() {
	return weatherId;
}
public void setWeatherId(String weatherId) {
	this.weatherId = weatherId;
}
public String getCreateTime() {
	return createTime;
}
public void setCreateTime(String createTime) {
	this.createTime = createTime;
}
public String getWeek() {
	return week;
}
public void setWeek(String week) {
	this.week = week;
}

}

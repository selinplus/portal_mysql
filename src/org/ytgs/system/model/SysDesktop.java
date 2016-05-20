package org.ytgs.system.model;

import java.util.List;
import org.ytgs.xzjx.model.XzjxGztz;

public class SysDesktop {
 
 private List<XzjxGztz> noticeList;
 private String basePath;
 
public List<XzjxGztz> getNoticeList() {
	return noticeList;
}

public void setNoticeList(List<XzjxGztz> noticeList) {
	this.noticeList = noticeList;
}

public String getBasePath() {
	return basePath;
}

public void setBasePath(String basePath) {
	this.basePath = basePath;
}





}

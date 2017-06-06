package com.clover.base.jdbc;

import java.util.List;

/**
 * @desc 数据库查询分页工具类
 * @copyright Copyright (c) 2017
 * @company clover
 * @version V1.0
 * @author qiang900714@126.com
 * @date 2017-6-5 下午5:44:19
 */
public class DBPage {
	public static final int NUMBERS_PER_PAGE = 10;
	private int numPerPage;
	private int totalRows;
	private int totalPages;
	private int currentPage;
	private int startIndex;
	private int lastIndex;
	private List dataList;
	private boolean firstFlag = true;
	private boolean prevFlag = true;
	private boolean nextFlag = true;
	private boolean lastFlag = true;
	private String buttonType = "";

	/**
	 * @desc 构造方法
	 * @author zhangdq
	 * @param currentPage
	 * @param numPerPage
	 */
	public DBPage(int currentPage, int numPerPage) {
		this.numPerPage = numPerPage;
		this.currentPage = currentPage;
	}

	/**
	 * @desc 设置分页总行数
	 * @author zhangdq
	 * @time 2017-6-5 下午5:47:19
	 * @param totalRows 分页总行数
	 */
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;

		calcTotalPages();

		calcStartIndex();

		calcLastIndex();

		setPageButton();
	}

	private void setPageButton() {
		if ((this.totalPages == 0) || (this.totalPages == 1)) {
			this.firstFlag = false;
			this.prevFlag = false;
			this.nextFlag = false;
			this.lastFlag = false;
			return;
		}

		if (this.currentPage > this.totalPages) {
			this.currentPage = this.totalPages;
		}

		if ((this.currentPage <= 1) || (this.currentPage == 0)) {
			this.firstFlag = false;
			this.prevFlag = false;
		}
		if ((this.currentPage >= this.totalPages) || (this.totalPages == 0)) {
			this.lastFlag = false;
			this.nextFlag = false;
		}
	}

	/**
	 * @desc 计算总共可以分成几页
	 * @author zhangdq
	 * @time 2017-6-6 下午1:02:50
	 * @param 
	 * @return
	 */
	private void calcTotalPages() {
		if (this.totalRows % this.numPerPage == 0) {
			this.totalPages = (this.totalRows / this.numPerPage);
		} else {
			this.totalPages = (this.totalRows / this.numPerPage + 1);
		}
	}

	/**
	 * @desc 计算分页查询的起始页数
	 * @author zhangdq
	 * @time 2017-6-6 下午1:03:22
	 * @param 
	 * @return
	 */
	private void calcStartIndex() {
		this.startIndex = ((this.currentPage - 1) * this.numPerPage);
	}

	/**
	 * @desc 计算分页查询的结束页数
	 * @author zhangdq
	 * @time 2017-6-6 下午1:08:34
	 * @param 
	 * @return
	 */
	private void calcLastIndex() {
		if (this.totalRows < this.numPerPage) {
			this.lastIndex = this.totalRows;
		} else if ((this.totalRows % this.numPerPage == 0) || ((this.totalRows % this.numPerPage != 0) && (this.currentPage < this.totalPages))) {
			this.lastIndex = (this.currentPage * this.numPerPage);
		} else if ((this.totalRows % this.numPerPage != 0) && (this.currentPage == this.totalPages)) {
			this.lastIndex = this.totalRows;
		}
	}

	private String renderButton(String show, boolean disabled, int page) {
		if (this.buttonType.equalsIgnoreCase("text")) {
			return renderText(show, disabled, page);
		}
		String temp = "";
		if (disabled == true)
			temp = "disabled";
		return "<input type=\"submit\" class=\"pageButton\" value=\"" + show + "\" onclick=\"goToPage(" + page + ",this.form)\" " + temp + ">\n";
	}

	private String renderText(String show, boolean disabled, int page) {
		if (disabled == true)
			return show;
		return "<a class=\"pageLink\" href=\"javascript:toPage(" + page + ")\">" + show + "</a>";
	}

	public String first(String show) {
		if (!this.firstFlag)
			return renderButton(show, true, 0);
		return renderButton(show, false, 1);
	}

	public String preview(String show) {
		if (!this.prevFlag)
			return renderButton(show, true, 0);
		return renderButton(show, false, this.currentPage - 1);
	}

	public String next(String show) {
		if (!this.nextFlag)
			return renderButton(show, true, 0);
		return renderButton(show, false, this.currentPage + 1);
	}

	public String last(String show) {
		if (!this.lastFlag)
			return renderButton(show, true, 0);
		return renderButton(show, false, this.totalPages);
	}

	public void setButtonType(String buttonType) {
		this.buttonType = buttonType;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public int getNumPerPage() {
		return this.numPerPage;
	}

	public List getData() {
		if ((this.dataList != null) && (this.dataList.size() > getNumPerPage())) {
			return this.dataList.subList(getStartIndex(), getLastIndex());
		}
		return this.dataList;
	}

	public int getTotalPages() {
		return this.totalPages;
	}

	public int getTotalRows() {
		return this.totalRows;
	}

	public int getStartIndex() {
		return this.startIndex;
	}

	public int getLastIndex() {
		return this.lastIndex;
	}

	public void setData(List dataList) {
		this.dataList = dataList;
	}

	public String getLinkStr(String url, String path) {
		String linkStr = "";
		String scriptTmp = "";

		int pageNumber = this.currentPage;
		int pages = this.totalPages;
		int total = this.totalRows;

		linkStr = linkStr + "共 <b>" + total + "</b> 条&nbsp;当前 <b>" + pageNumber + "</b>/<b>" + pages + "</b> 页&nbsp;&nbsp;";

		if (url.indexOf("?") > 0) {
			url = url + "&";
		} else {
			url = url + "?";
		}
		if ((pageNumber == 1) && (pageNumber < pages)) {
			linkStr = linkStr + "首页&nbsp;上一页&nbsp;<a href='" + url + "&pageNumber=" + (pageNumber + 1) + "'>下一页</a>&nbsp;<a href='" + url + "&pageNumber=" + pages + "'>尾页</a>&nbsp;跳到<input type=text name=jump id=jump size=3 style=\"text-align:center\" value='" + pageNumber + "'>页&nbsp;<img border=\"0\" src=\"" + path + "/images/button020.gif\" width=\"20\" height=\"18\"  style=\"cursor:hand;\" onclick=\"javascript:checkPage();\">";
		} else if ((pageNumber > 1) && (pageNumber < pages)) {
			linkStr = linkStr + "<a href='" + url + "&pageNumber=1'>首页</a>&nbsp;<a href='" + url + "&pageNumber=" + (pageNumber - 1) + "'>上一页</a>&nbsp;<a href='" + url + "&pageNumber=" + (pageNumber + 1) + "'>下一页&nbsp;<a href='" + url + "&pageNumber=" + pages + "'>尾页</a>&nbsp;跳到<input type=text name=jump id=jump size=3 style=\"text-align:center\" value='" + pageNumber + "'>页&nbsp;<img border=\"0\" src=\"" + path + "/images/button020.gif\" width=\"20\" height=\"18\"  style=\"cursor:hand;\" onclick=\"javascript:checkPage();\">";
		} else if ((pageNumber == pages) && (pages > 1)) {
			linkStr = linkStr + "<a href='" + url + "&pageNumber=1'>首页</a>&nbsp;<a href='" + url + "&pageNumber=" + (pageNumber - 1) + "'>上一页</a>&nbsp;下一页&nbsp;尾页&nbsp;跳到<input type=text name=jump id=jump size=3 style=\"text-align:center\" value='" + pageNumber + "'>页&nbsp;<img border=\"0\" src=\"" + path + "/images/button020.gif\" width=\"20\" height=\"18\" style=\"cursor:hand;\" onclick=\"javascript:checkPage();\">";
		}

		scriptTmp = scriptTmp + "<SCRIPT LANGUAGE=\"JavaScript\">\n<!--\nfunction checkPage()\n {\n if(document.getElementById(\"jump\").value > " + pages + ") {\n alert('您输入的页码超出范围，请重新输入！');\n document.getElementById('jump').focus();\n return false;\n} else if(document.getElementById('jump').value < 1) {\n alert('您输入的页码超出范围，请重新输入！');\n document.getElementById('jump').focus();\n return false;\n} else {\n jumpTo('" + url + "');\n}\n}\n//-->\n</SCRIPT>";

		scriptTmp = scriptTmp + "<SCRIPT LANGUAGE=\"JavaScript\">\n<!--\nfunction jumpTo(url)\n {\n self.location.href=\"" + url + "&pageNumber=\"+" + "document.getElementById(\"jump\").value;\n}\n//-->\n</SCRIPT>";

		linkStr = linkStr + scriptTmp;

		return linkStr;
	}
}
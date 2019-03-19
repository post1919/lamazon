package com.lamazon.model;

import java.io.Serializable;
import java.util.List;

public class AdminMenu implements Serializable {

	private static final long serialVersionUID = 2762728572443537484L;

	//@NotEmpty
	//@Size(max=100)
	//@NotNull
	//@DateTimeFormat(pattern="yyyy/MM/dd")

	private List<AdminMenu> CHILD;
	private List<AdminMenu> CHILD1;
	private List<AdminMenu> CHILD2;

	private int AM_PK;
	private String AM_CODE;
	private String AM_NAME;
	private String AM_FULL_NAME;
	private String AM_AUTH;
	private String AM_AUTH2;
	private String AM_PARENT;
	private String AM_DEPTH;
	private String AM_IS_MAIN;
	private String AM_ORDERING;
	private String AM_MEMO;
	private String AM_URI;
	private String AM_ICON;
	private String AM_STATUS;
	private String AM_INCODE;
	private String AM_INDATE;
	private String AM_MOCODE;
	private String AM_MODATE;
	public int getAM_PK() {
		return AM_PK;
	}
	public void setAM_PK(int aM_PK) {
		AM_PK = aM_PK;
	}
	public String getAM_CODE() {
		return AM_CODE;
	}
	public void setAM_CODE(String aM_CODE) {
		AM_CODE = aM_CODE;
	}
	public String getAM_NAME() {
		return AM_NAME;
	}
	public void setAM_NAME(String aM_NAME) {
		AM_NAME = aM_NAME;
	}
	public String getAM_FULL_NAME() {
		return AM_FULL_NAME;
	}
	public void setAM_FULL_NAME(String aM_FULL_NAME) {
		AM_FULL_NAME = aM_FULL_NAME;
	}
	public String getAM_AUTH() {
		return AM_AUTH;
	}
	public void setAM_AUTH(String aM_AUTH) {
		AM_AUTH = aM_AUTH;
	}
	public String getAM_AUTH2() {
		return AM_AUTH2;
	}
	public void setAM_AUTH2(String aM_AUTH2) {
		AM_AUTH2 = aM_AUTH2;
	}
	public String getAM_PARENT() {
		return AM_PARENT;
	}
	public void setAM_PARENT(String aM_PARENT) {
		AM_PARENT = aM_PARENT;
	}
	public String getAM_DEPTH() {
		return AM_DEPTH;
	}
	public void setAM_DEPTH(String aM_DEPTH) {
		AM_DEPTH = aM_DEPTH;
	}
	public String getAM_IS_MAIN() {
		return AM_IS_MAIN;
	}
	public void setAM_IS_MAIN(String aM_IS_MAIN) {
		AM_IS_MAIN = aM_IS_MAIN;
	}
	public String getAM_ORDERING() {
		return AM_ORDERING;
	}
	public void setAM_ORDERING(String aM_ORDERING) {
		AM_ORDERING = aM_ORDERING;
	}
	public String getAM_MEMO() {
		return AM_MEMO;
	}
	public void setAM_MEMO(String aM_MEMO) {
		AM_MEMO = aM_MEMO;
	}
	public String getAM_URI() {
		return AM_URI;
	}
	public void setAM_URI(String aM_URI) {
		AM_URI = aM_URI;
	}
	public String getAM_ICON() {
		return AM_ICON;
	}
	public void setAM_ICON(String aM_ICON) {
		AM_ICON = aM_ICON;
	}
	public String getAM_STATUS() {
		return AM_STATUS;
	}
	public void setAM_STATUS(String aM_STATUS) {
		AM_STATUS = aM_STATUS;
	}
	public String getAM_INCODE() {
		return AM_INCODE;
	}
	public void setAM_INCODE(String aM_INCODE) {
		AM_INCODE = aM_INCODE;
	}
	public String getAM_INDATE() {
		return AM_INDATE;
	}
	public void setAM_INDATE(String aM_INDATE) {
		AM_INDATE = aM_INDATE;
	}
	public String getAM_MOCODE() {
		return AM_MOCODE;
	}
	public void setAM_MOCODE(String aM_MOCODE) {
		AM_MOCODE = aM_MOCODE;
	}
	public String getAM_MODATE() {
		return AM_MODATE;
	}
	public void setAM_MODATE(String aM_MODATE) {
		AM_MODATE = aM_MODATE;
	}
	public List<AdminMenu> getCHILD() {
		return CHILD;
	}
	public void setCHILD(List<AdminMenu> cHILD) {
		CHILD = cHILD;
	}
	public List<AdminMenu> getCHILD1() {
		return CHILD1;
	}
	public void setCHILD1(List<AdminMenu> cHILD1) {
		CHILD1 = cHILD1;
	}
	public List<AdminMenu> getCHILD2() {
		return CHILD2;
	}
	public void setCHILD2(List<AdminMenu> cHILD2) {
		CHILD2 = cHILD2;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	@Override
	public String toString() {
		return "AdminMenu [CHILD1=" + CHILD1 + ", CHILD2=" + CHILD2 + ", AM_PK=" + AM_PK + ", AM_CODE=" + AM_CODE
				+ ", AM_NAME=" + AM_NAME + ", AM_FULL_NAME=" + AM_FULL_NAME + ", AM_AUTH=" + AM_AUTH + ", AM_AUTH2="
				+ AM_AUTH2 + ", AM_PARENT=" + AM_PARENT + ", AM_DEPTH=" + AM_DEPTH + ", AM_IS_MAIN=" + AM_IS_MAIN
				+ ", AM_ORDERING=" + AM_ORDERING + ", AM_MEMO=" + AM_MEMO + ", AM_URI=" + AM_URI + ", AM_ICON="
				+ AM_ICON + ", AM_STATUS=" + AM_STATUS + ", AM_INCODE=" + AM_INCODE + ", AM_INDATE=" + AM_INDATE
				+ ", AM_MOCODE=" + AM_MOCODE + ", AM_MODATE=" + AM_MODATE + "]";
	}
}

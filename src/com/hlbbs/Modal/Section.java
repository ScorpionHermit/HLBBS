package com.hlbbs.Modal;

public class Section
{
	private int m_id;
	private String m_nvcSectionName;
	private int m_intModerator;
	private int m_intPosts;
	public int getID()
	{
		return m_id;
	}
	public void setID(int id)
	{
		this.m_id = id;
	}
	public String getNvcSectionName()
	{
		return m_nvcSectionName;
	}
	public void setNvcSectionName(String nvcSectionName)
	{
		this.m_nvcSectionName = nvcSectionName;
	}
	public int getIntModerator()
	{
		return m_intModerator;
	}
	public void setIntModerator(int intModerator)
	{
		this.m_intModerator = intModerator;
	}
	public int getIntPosts()
	{
		return m_intPosts;
	}
	public void setIntPosts(int intPosts)
	{
		this.m_intPosts = intPosts;
	}
}

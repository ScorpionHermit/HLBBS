package com.hlbbs.Modal;

public class Section
{
	int m_id;
	String m_nvcSectionName;
	int m_intModerator;
	int m_intPosts;
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

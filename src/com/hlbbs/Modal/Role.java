package com.hlbbs.Modal;

public class Role
{
	public static final int Visitor = 1;
	public static final int NormalUser = 2;
	public static final int SectionManager = 4;
	public static final int WebManager = 8;
	
	public static String toString(int roleType)
	{
		StringBuffer sb = new StringBuffer();
		if((roleType & Visitor) != 0)
			sb.append(roleName[0]).append('|');
		if((roleType & NormalUser) != 0)
			sb.append(roleName[1]).append('|');
		if((roleType & SectionManager) != 0)
			sb.append(roleName[2]).append('|');
		if((roleType & WebManager) != 0)
			sb.append(roleName[3]).append('|');
		return sb.substring(0, sb.length() - 1);
	}
	
	public static boolean isWebManager(int roleType)
	{
		return ((roleType & WebManager) == 0 ? false : true);
	}
	
	private static String[] roleName = {"游客", "用户", "版主", "管理员"};
}

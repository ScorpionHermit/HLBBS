package com.hlbbs.DAO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.hlbbs.Modal.Section;

public class SectionDAO extends DAO
{
	
	Section m_section;
	
	public SectionDAO(Section section)
	{
		m_section = section;
	}
	
	private void FillAttribute(Section section, ResultSet rs) throws SQLException
	{
		section.setID(rs.getInt("id"));
        section.setNvcSectionName(rs.getString("nvcSectionName"));
        section.setIntPosts(rs.getInt("intPosts"));
        section.setIntModerator(rs.getInt("intModerator"));
	}
	
	public ArrayList<Section> getAllSection() 
    {
        String sql  =  "SELECT * FROM t_hlbbs_section";
        PreparedStatement ps = null;
        ResultSet rs = null;
        ArrayList<Section> secList = new ArrayList<Section>();
        
        try 
        {
            ps = m_con.prepareStatement(sql);
            rs = ps.executeQuery();

            while(rs.next())
            {
            	Section section = new Section();
                FillAttribute(section, rs);
                secList.add(section);
            }
        } catch (Exception e) {
            e.printStackTrace();                                    // 处理异常
        } finally {
            ClosePreStatement(ps);
            CloseResultSet(rs);
        }
        return secList;
    }

    /**
     * 根据版块id查找版块
     * @param boardId
     * @return
     */
    public void findBoardByID() 
    {
        String sql  =  "SELECT * FROM t_hlbbs_section where id= ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        if(m_section == null)
        	return;
        	
        try 
        {
            ps = m_con.prepareStatement(sql);
            ps.setInt(1, m_section.getID());
            rs = ps.executeQuery();

            if(rs.first())
            {
                FillAttribute(m_section, rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	ClosePreStatement(ps);
            CloseResultSet(rs);
        }
    }
    
    /**
     * 添加新板块
     * @return
     */
    public boolean InsertSection()
    {
    	String sql = "INSERT INTO t_hlbbs_section(nvcSectionName, intModerator) VALUES(?, ?)";
        PreparedStatement ps = null;
        boolean isSuccess = false;
        if(m_section == null)
        	return isSuccess;
        try 
        {
            ps = m_con.prepareStatement(sql);
            ps.setString(1, m_section.getNvcSectionName());
            ps.setInt(2, m_section.getIntModerator());
            if(ps.executeUpdate() > 0)
            	isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	ClosePreStatement(ps);
        }
        return isSuccess;
    }
    
    /**
     * 修改板块信息
     * @return
     */
    public boolean ModifySection()
    {
    	String sql = "UPDATE t_hlbbs_section SET nvcSectionName = ?, intModerator = ? WHERE id = ?";
        PreparedStatement ps = null;
        boolean isSuccess = false;
        if(m_section == null)
        	return isSuccess;
        try 
        {
            ps = m_con.prepareStatement(sql);
            ps.setString(1, m_section.getNvcSectionName());
            ps.setInt(2, m_section.getIntModerator());
            ps.setInt(3, m_section.getID());
            if(ps.executeUpdate() > 0)
            	isSuccess = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	ClosePreStatement(ps);
        }
        return isSuccess;
    }
}


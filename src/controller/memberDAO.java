package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import model.memberShipVO;

public class memberDAO {
	
	// ==================== ��� ���̺� ���� ==================== // 
	
	public static ArrayList<memberShipVO> dbArrayListMember = new ArrayList<>();
	
	public static int insertMemberData(memberShipVO member) throws ClassNotFoundException {
		
		
		String dml = "insert into memberTBL "
				+ "(memberID, memberPassWord, memberName, memberEmail)" + " values "
				+ "(? ,?, ?, ?)";
		
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null; // ������ ������
		
		int count = 0;
		
		try {
			
			connection = DBUtil.getConnection();
			
			ps = connection.prepareStatement(dml);
			ps.setString(1, member.getMemberID());
			ps.setString(2, member.getMemberPassWord());
			ps.setString(3, member.getMemberName());
			ps.setString(4, member.getMemberEmail());
			
			count = ps.executeUpdate();
			
			if(count == 0) {
				memberController.alertDisplay(3, "����","���Կ� �����ϼ̽��ϴ�.","insert query Error : ���������Խ���");
				return count;
			}
		} catch (SQLException e) {
			memberController.alertDisplay(3, "����","���Կ� �����ϼ̽��ϴ�.","insert Error : �����ͺ��̽� ���Խ���");
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(connection !=null) {
					connection.close();
				}
			}catch (SQLException e) {
				memberController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");
			}
		}
		return count;
	}
	
	// ȸ�� �˻� ���
	public static void checkMemberID(memberShipVO member) {
		Connection con;
		try {
			con = DBUtil.getConnection();
			PreparedStatement ps = null;
			String checkID= "select memberID from memberTBL where memberID=? ";
			ps=con.prepareStatement(checkID);
			ps.setString(1, member.getMemberID());
			ResultSet rs = ps.executeQuery();
			if(member.getMemberID().equals("")) {
				memberController.alertDisplay(3, "����","�Է� ����","�ߺ�üũ�� ���̵� �Է� : ���̵� �Է��ϼ���.");						
			}else if(rs.next()) {
				memberController.alertDisplay(3, "�ߺ� ����","��Ͽ� �����ϼ̽��ϴ�.","���̵� �ߺ� : ���̵� �ߺ��Ǿ����ϴ�\n�ٸ� ���̵� �Է��ϼ���.");
			}else {
				memberController.alertDisplay(5, "Ȯ��","���� ����","���̵� ���� ���� : ��밡���� ���̵��Դϴ�.");
			}
			
		}catch(Exception e) {}
	}
	
	// ȸ�� ��ü ����Ʈ
	public static ArrayList<memberShipVO> getMemberTotalData() throws ClassNotFoundException{
		String selectMemberAll = "select * from memberTBL ";
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			connection = DBUtil.getConnection();
			ps = connection.prepareStatement(selectMemberAll);
			rs = ps.executeQuery();
			if(rs==null) {
				memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
				return null;
			}
			dbArrayListMember.clear();
			while(rs.next()) {
				memberShipVO member = new memberShipVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				dbArrayListMember.add(member);
			}
		}catch (SQLException e) {
	         memManageController.alertDisplay(3,"����","���Կ� �����߽��ϴ�.","���Խ���:�����ͺ��̽� ���Խ���");
	      } finally {
	         // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            memManageController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");
	         }
	      }
		
		return dbArrayListMember;
	}
	
	// ���� ���
	public static int updateMemberData(memberShipVO member, memberShipVO selectedSuper) {
		StringBuffer updateMember = new StringBuffer();
		updateMember.append("update memberTBL set ");
		updateMember.append("memberPassWord=?, memberName=?,memberEmail=? where memberID=? ");
		Connection connection = null;
		PreparedStatement ps = null;
		int count=0;
		try {
			connection = DBUtil.getConnection();
			ps=connection.prepareStatement(updateMember.toString());
			ps.setString(1, member.getMemberPassWord());
			ps.setString(2, member.getMemberName());
			ps.setString(3, member.getMemberEmail());
			ps.setString(4, member.getMemberID());
			
			count = ps.executeUpdate();
		 if (count == 0) {
	            memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
	            return count;
	         }
		}catch(Exception e) {
			// memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","Update Error :  �����ͺ��̽� ������Ʈ����");
			System.out.println(e.getMessage());
		}finally {
	         // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            memManageController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");

	         }
	      }
		
		return count;
	}
	
	// ���� ���
	public static int deleteMemberData(String memberID) throws ClassNotFoundException {
		String deleteMember = "delete from memberTBL where memberID=? ";
		Connection con = null;
		PreparedStatement ps = null;
		int count = 0;
		try {
			con = DBUtil.getConnection();
			ps = con.prepareStatement(deleteMember);
			ps.setString(1, memberID);
			count = ps.executeUpdate();
			 if (count == 0) {
	           memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
	           return count;
	         }			
		}catch(SQLException e) {
			memManageController.alertDisplay(3,"����","������ ������ �����߽��ϴ�.","select Error : select ������ ����");
		}finally {
	         // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (con != null) {
	               con.close();
	            }
	         } catch (SQLException e) {
	            memManageController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");

	         }
	      }
		
		return count;
	}

	public static ArrayList<memberShipVO> getMemberSelectData(String s) throws ClassNotFoundException{
		
		String selectMember = "select * from memberTBL where memberID = ?";
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = DBUtil.getConnection();
			
			ps = connection.prepareStatement(selectMember);
			ps.setString(1, s);
			
			rs = ps.executeQuery();
				
			if(rs==null) {
				memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
				return null;
			}
			
			while(rs.next()) {
				memberShipVO member = new memberShipVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				
				dbArrayListMember.add(member);
				System.out.println(dbArrayListMember);
			}
			
		}catch (SQLException e) {
	         memManageController.alertDisplay(3,"����","���Կ� �����߽��ϴ�.","���Խ���:�����ͺ��̽� ���Խ���");
	      } finally {
	         // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            memManageController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");
	         }
	      }
		
		return dbArrayListMember;
	}
	
	// ���â �˸�
	public static void alertDisplay(int type, String title, String headerText, String contentText) {
		
		Alert alert = null;  // ���� 1. Alert �̰��� ���� ������ �α��� ����� ���̾�α�â�� ���� ���������� �۾��� ������.
		
		switch(type) {
			case 1: alert = new Alert(AlertType.WARNING); break;
			case 2: alert = new Alert(AlertType.CONFIRMATION); break;
			case 3: alert = new Alert(AlertType.ERROR); break;
			case 4: alert = new Alert(AlertType.NONE); break;
			case 5: alert = new Alert(AlertType.INFORMATION); break;
		}
		
		alert.setTitle(title);					
		alert.setHeaderText(headerText);
		alert.setContentText(headerText+"\n"+contentText);
		alert.setResizable(false);
		alert.show();
		
	}
	
}



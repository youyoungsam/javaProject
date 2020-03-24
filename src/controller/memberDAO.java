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
	
	// ==================== 멤버 테이블 관리 ==================== // 
	
	public static ArrayList<memberShipVO> dbArrayListMember = new ArrayList<>();
	
	public static int insertMemberData(memberShipVO member) throws ClassNotFoundException {
		
		
		String dml = "insert into memberTBL "
				+ "(memberID, memberPassWord, memberName, memberEmail)" + " values "
				+ "(? ,?, ?, ?)";
		
		
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet rs = null; // 데이터 가져옴
		
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
				memberController.alertDisplay(3, "실패","삽입에 실패하셨습니다.","insert query Error : 쿼리문삽입실패");
				return count;
			}
		} catch (SQLException e) {
			memberController.alertDisplay(3, "실패","삽입에 실패하셨습니다.","insert Error : 데이터베이스 삽입실패");
		} finally {
			try {
				if(ps != null) {
					ps.close();
				}
				if(connection !=null) {
					connection.close();
				}
			}catch (SQLException e) {
				memberController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");
			}
		}
		return count;
	}
	
	// 회원 검색 기능
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
				memberController.alertDisplay(3, "오류","입력 실패","중복체크할 아이디 입력 : 아이디를 입력하세요.");						
			}else if(rs.next()) {
				memberController.alertDisplay(3, "중복 오류","등록에 실패하셨습니다.","아이디 중복 : 아이디가 중복되었습니다\n다른 아이디를 입력하세요.");
			}else {
				memberController.alertDisplay(5, "확인","생성 가능","아이디 생성 가능 : 사용가능한 아이디입니다.");
			}
			
		}catch(Exception e) {}
	}
	
	// 회원 전체 리스트
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
				memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
				return null;
			}
			dbArrayListMember.clear();
			while(rs.next()) {
				memberShipVO member = new memberShipVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				dbArrayListMember.add(member);
			}
		}catch (SQLException e) {
	         memManageController.alertDisplay(3,"실패","삽입에 실패했습니다.","삽입실패:데이터베이스 삽입실패");
	      } finally {
	         // 1.6 자원객체를 닫아야한다.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            memManageController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");
	         }
	      }
		
		return dbArrayListMember;
	}
	
	// 수정 기능
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
	            memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
	            return count;
	         }
		}catch(Exception e) {
			// memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","Update Error :  데이터베이스 업데이트실패");
			System.out.println(e.getMessage());
		}finally {
	         // 1.6 자원객체를 닫아야한다.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            memManageController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");

	         }
	      }
		
		return count;
	}
	
	// 삭제 기능
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
	           memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
	           return count;
	         }			
		}catch(SQLException e) {
			memManageController.alertDisplay(3,"실패","데이터 삭제에 실패했습니다.","select Error : select 쿼리문 실패");
		}finally {
	         // 1.6 자원객체를 닫아야한다.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (con != null) {
	               con.close();
	            }
	         } catch (SQLException e) {
	            memManageController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");

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
				memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
				return null;
			}
			
			while(rs.next()) {
				memberShipVO member = new memberShipVO(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
				
				dbArrayListMember.add(member);
				System.out.println(dbArrayListMember);
			}
			
		}catch (SQLException e) {
	         memManageController.alertDisplay(3,"실패","삽입에 실패했습니다.","삽입실패:데이터베이스 삽입실패");
	      } finally {
	         // 1.6 자원객체를 닫아야한다.
	         try {
	            if (ps != null) {
	               ps.close();
	            }
	            if (connection != null) {
	               connection.close();
	            }
	         } catch (SQLException e) {
	            memManageController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");
	         }
	      }
		
		return dbArrayListMember;
	}
	
	// 경고창 알림
	public static void alertDisplay(int type, String title, String headerText, String contentText) {
		
		Alert alert = null;  // 로직 1. Alert 이것을 쓰지 않으면 로그인 사용자 다이얼로그창을 따로 만들정도로 작업이 많아짐.
		
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



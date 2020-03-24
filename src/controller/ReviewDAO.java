package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.productionVO;
import model.reviewVO;

public class ReviewDAO {

	// ===================== ���� ���̺� ====================== // 
	
	public static int insertReviewData(reviewVO pvo) {
        
        String dml = "insert into reviewtbl "
              +"(no, productName, memberID, review)"+ " values "
              +"(null, ?, ?, ?)";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        int count = 0;
        
        try {
           
           con = DBUtil.getConnection(); 
           
           pstmt = con.prepareStatement(dml);
           
           pstmt.setString(1,  pvo.getProductName());
           pstmt.setString(2,  pvo.getMemberID());
           pstmt.setString(3,  pvo.getReview());
           
           
           count = pstmt.executeUpdate();
           
           if(count == 0) {
              memberController.alertDisplay(3, "����","���Կ� �����ϼ̽��ϴ�.","insert query Error : ���������Խ���");
              return count;
           }
        } catch (SQLException e) {
           memberController.alertDisplay(3, "����","���Կ� �����ϼ̽��ϴ�.","insert Error : �����ͺ��̽� ���Խ���");
        } finally {
           try {
              if(pstmt != null) {
                 pstmt.close();
              }
              if(con !=null) {
                 con.close();
              }
           }catch (SQLException e) {
              memberController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");
           }
        }
        return count;
     }
     
     // 2. ��� ����� ��ǰ ����Ʈ ( select )
     public static ArrayList<reviewVO> selectReviewList(String s) {
//         String[] array = s.split(" ");
//         System.out.println(array[0]);
        
        ArrayList<reviewVO> list = new ArrayList<reviewVO>();
        
        String dml = "select * from reviewtbl where productName = ?";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null; 
        
        reviewVO rvVO = null;
        
        try {
           con = DBUtil.getConnection();
           
           pstmt = con.prepareStatement(dml);
           pstmt.setString(1, s);  // 1�� ?���� s �� ����ã���̸�
           
           rs = pstmt.executeQuery();
           
           if(rs==null) {
              memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
              return null;
           }
           
           while (rs.next()) { 
              rvVO = new reviewVO(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4)
                 );
              list.add(rvVO); //4���ǰ�ü���������.
           }
        
           
        } catch (SQLException e) {
             memManageController.alertDisplay(3,"����","���Կ� �����߽��ϴ�.","���Խ���:�����ͺ��̽� ���Խ���");
           } finally {
              // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
              try {
                 if (pstmt != null) {
                    pstmt.close();
                 }
                 if (con != null) {
                    con.close();
                 }
              } catch (SQLException e) {
                 memManageController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");
              }
           }
        
        return list;
     }
     
     // 3. ��ǰ ���� ��� ( delete )
     public static void deleteReviewData(String review) {
        
        String deleteMember = "delete from reviewtbl where review=? ";
        Connection con = null;
        PreparedStatement pstmt = null;
     
        try {
           
           con = DBUtil.getConnection();
           
           pstmt = con.prepareStatement(deleteMember);
           pstmt.setString(1, review);
           
           int i = pstmt.executeUpdate();
           
            if (i == 0) {
                memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
              }else if(i != 0) {
                 memManageController.alertDisplay(5,"����","���� �Ϸ�","������ �Ϸ��Ͽ����ϴ�.");
              }
            
        }catch(SQLException e) {
           memManageController.alertDisplay(3,"����","������ ������ �����߽��ϴ�.","select Error : select ������ ����");
        }finally {
              // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
              try {
                 if (pstmt != null) {
                    pstmt.close();
                 }
                 if (con != null) {
                    con.close();
                 }
              } catch (SQLException e) {
                 memManageController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");

              }
           }
     }
     // 4. ��ǰ ���� ��� ( delete )
     public static void deleteReviewDataManage(String prod, String id) {
        
        String deleteMember = "delete from reviewtbl where productName=? and memberID=?";
        Connection con = null;
        PreparedStatement pstmt = null;
        
        try {
           
           con = DBUtil.getConnection();
           
           pstmt = con.prepareStatement(deleteMember);
           pstmt.setString(1, prod);
           pstmt.setString(2, id);
           
           int i = pstmt.executeUpdate();
           
           if (i == 0) {
              memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
           }else if(i != 0) {
              memManageController.alertDisplay(5,"����","���� �Ϸ�","������ �Ϸ��Ͽ����ϴ�.");
           }
           
        }catch(SQLException e) {
           memManageController.alertDisplay(3,"����","������ ������ �����߽��ϴ�.","select Error : select ������ ����");
        }finally {
           // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
           try {
              if (pstmt != null) {
                 pstmt.close();
              }
              if (con != null) {
                 con.close();
              }
           } catch (SQLException e) {
              memManageController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");
              
           }
        }
     }
     
     // 4. ��ǰ ���� ��� ( update )
     public reviewVO updateProdData(reviewVO pvo, int no) {
        
        String dml = "update reviewtbl set " + " pName=?, item=?, info=?, filename=?,"
              + " where no = ?";
        
        Connection con = null;
        PreparedStatement pstmt = null;
        
        con = DBUtil.getConnection();
        
        int count = 0;
        
        try {
           
           pstmt = con.prepareStatement(dml);
           
           pstmt.setString(1,  pvo.getProductName());
           pstmt.setString(2,  pvo.getMemberID());
           pstmt.setString(3,  pvo.getReview());
           
            if (count == 0) {
                    memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
                 }
           }catch(Exception e) {
              memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","Update Error :  �����ͺ��̽� ������Ʈ����");
              System.out.println(e.getMessage());
           }finally {
                 // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
                 try {
                    if (pstmt != null) {
                       pstmt.close();
                    }
                    if (con != null) {
                       con.close();
                    }
                 } catch (SQLException e) {
                    memManageController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");

                 }
              }
           
           return pvo;
     }

     // 5. ��� ����� ���䳻�� �������� ( select )
           public static ArrayList<reviewVO> selectReview(String s) {
//               String[] array = s.split(" ");
//               System.out.println(array[0]);
              
              ArrayList<reviewVO> list = new ArrayList<reviewVO>();
              
              String dml = "select * from reviewtbl where review = ?";
              
              Connection con = null;
              PreparedStatement pstmt = null;
              ResultSet rs = null; 
              
              reviewVO rvVO = null;
              
              try {
                 con = DBUtil.getConnection();
                 
                 pstmt = con.prepareStatement(dml);
                 pstmt.setString(1, s);  // 1�� ?���� s �� ����ã���̸�
                 
                 rs = pstmt.executeQuery();
                 
                 if(rs==null) {
                    memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
                    return null;
                 }
                 
                 while (rs.next()) { 
                    rvVO = new reviewVO(rs.getInt(1), rs.getString(2),
                          rs.getString(3), rs.getString(4)
                       );
                    list.add(rvVO); //4���ǰ�ü���������.
                 }
              
                 
              } catch (SQLException e) {
                   memManageController.alertDisplay(3,"����","���Կ� �����߽��ϴ�.","���Խ���:�����ͺ��̽� ���Խ���");
                 } finally {
                    // 1.6 �ڿ���ü�� �ݾƾ��Ѵ�.
                    try {
                       if (pstmt != null) {
                          pstmt.close();
                       }
                       if (con != null) {
                          con.close();
                       }
                    } catch (SQLException e) {
                       memManageController.alertDisplay(3, "����","�ڿ� �ݳ��� �����ϼ̽��ϴ�.","�ڿ��ݱ� ���� : ����");
                    }
                 }
              
              return list;
           }
	
}

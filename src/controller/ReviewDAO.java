package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.productionVO;
import model.reviewVO;

public class ReviewDAO {

	// ===================== 리뷰 테이블 ====================== // 
	
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
              memberController.alertDisplay(3, "실패","삽입에 실패하셨습니다.","insert query Error : 쿼리문삽입실패");
              return count;
           }
        } catch (SQLException e) {
           memberController.alertDisplay(3, "실패","삽입에 실패하셨습니다.","insert Error : 데이터베이스 삽입실패");
        } finally {
           try {
              if(pstmt != null) {
                 pstmt.close();
              }
              if(con !=null) {
                 con.close();
              }
           }catch (SQLException e) {
              memberController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");
           }
        }
        return count;
     }
     
     // 2. 디비에 저장된 상품 리스트 ( select )
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
           pstmt.setString(1, s);  // 1은 ?개수 s 는 내가찾을이름
           
           rs = pstmt.executeQuery();
           
           if(rs==null) {
              memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
              return null;
           }
           
           while (rs.next()) { 
              rvVO = new reviewVO(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4)
                 );
              list.add(rvVO); //4개의객체가들어있음.
           }
        
           
        } catch (SQLException e) {
             memManageController.alertDisplay(3,"실패","삽입에 실패했습니다.","삽입실패:데이터베이스 삽입실패");
           } finally {
              // 1.6 자원객체를 닫아야한다.
              try {
                 if (pstmt != null) {
                    pstmt.close();
                 }
                 if (con != null) {
                    con.close();
                 }
              } catch (SQLException e) {
                 memManageController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");
              }
           }
        
        return list;
     }
     
     // 3. 제품 삭제 기능 ( delete )
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
                memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
              }else if(i != 0) {
                 memManageController.alertDisplay(5,"삭제","삭제 완료","삭제를 완료하였습니다.");
              }
            
        }catch(SQLException e) {
           memManageController.alertDisplay(3,"실패","데이터 삭제에 실패했습니다.","select Error : select 쿼리문 실패");
        }finally {
              // 1.6 자원객체를 닫아야한다.
              try {
                 if (pstmt != null) {
                    pstmt.close();
                 }
                 if (con != null) {
                    con.close();
                 }
              } catch (SQLException e) {
                 memManageController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");

              }
           }
     }
     // 4. 제품 삭제 기능 ( delete )
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
              memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
           }else if(i != 0) {
              memManageController.alertDisplay(5,"삭제","삭제 완료","삭제를 완료하였습니다.");
           }
           
        }catch(SQLException e) {
           memManageController.alertDisplay(3,"실패","데이터 삭제에 실패했습니다.","select Error : select 쿼리문 실패");
        }finally {
           // 1.6 자원객체를 닫아야한다.
           try {
              if (pstmt != null) {
                 pstmt.close();
              }
              if (con != null) {
                 con.close();
              }
           } catch (SQLException e) {
              memManageController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");
              
           }
        }
     }
     
     // 4. 제품 수정 기능 ( update )
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
                    memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
                 }
           }catch(Exception e) {
              memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","Update Error :  데이터베이스 업데이트실패");
              System.out.println(e.getMessage());
           }finally {
                 // 1.6 자원객체를 닫아야한다.
                 try {
                    if (pstmt != null) {
                       pstmt.close();
                    }
                    if (con != null) {
                       con.close();
                    }
                 } catch (SQLException e) {
                    memManageController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");

                 }
              }
           
           return pvo;
     }

     // 5. 디비에 저장된 리뷰내용 가져오기 ( select )
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
                 pstmt.setString(1, s);  // 1은 ?개수 s 는 내가찾을이름
                 
                 rs = pstmt.executeQuery();
                 
                 if(rs==null) {
                    memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
                    return null;
                 }
                 
                 while (rs.next()) { 
                    rvVO = new reviewVO(rs.getInt(1), rs.getString(2),
                          rs.getString(3), rs.getString(4)
                       );
                    list.add(rvVO); //4개의객체가들어있음.
                 }
              
                 
              } catch (SQLException e) {
                   memManageController.alertDisplay(3,"실패","삽입에 실패했습니다.","삽입실패:데이터베이스 삽입실패");
                 } finally {
                    // 1.6 자원객체를 닫아야한다.
                    try {
                       if (pstmt != null) {
                          pstmt.close();
                       }
                       if (con != null) {
                          con.close();
                       }
                    } catch (SQLException e) {
                       memManageController.alertDisplay(3, "실패","자원 반납에 실패하셨습니다.","자원닫기 실패 : 실패");
                    }
                 }
              
              return list;
           }
	
}

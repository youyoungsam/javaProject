package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.productionVO;

public class ProductionDAO {

	// ====================== 제품 테이블 ===================== // 
	
	// 1. 신규 상품 등록 ( insert )
	public static int insertProductionData(productionVO pvo) {
		
		String dml = "insert into producttbl "
				+"(no, productName, item, info, filename, filename2, filename3)"+" values "
				+"(null, ?, ?, ?, ?, null, null)";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int count = 0;
		
		try {
			
			con = DBUtil.getConnection(); 
	
			pstmt = con.prepareStatement(dml);
			
			pstmt.setString(1,  pvo.getProductName());
			pstmt.setString(2,  pvo.getItem());
			pstmt.setString(3,  pvo.getInfo());
			pstmt.setString(4,  pvo.getFilename());
			
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
	
	// 2. 제품 전체 리스트
	public ArrayList<productionVO> getProductTotal() {
		
		ArrayList<productionVO> list = new ArrayList<productionVO>();
		String dml = "select * from producttbl"; // schoolchild의 모든 내용을 가져와라

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // 데이터베이스 값을 임시로 저장하는 장소 제공하는 객체. 
		productionVO prodVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			rs = pstmt.executeQuery();
			while (rs.next()) { // 다음것이 있느냐? 테이블 내용을 위에서 부터 가져온다. 계속 올라가서 그다음 레코드.
				prodVO = new productionVO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)
						);
				list.add(prodVO); // 9개 객체가 들어있음.
			}
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				
			}
		}
		return list;
	}
	
	// 2-1. 디비에 저장된 상품 리스트 ( select )
	public static ArrayList<productionVO> selectProductionList(String s) {
		// String[] array = s.split(" ");
		// System.out.println(array[0]);
		
		ArrayList<productionVO> list = new ArrayList<productionVO>();
		
		String dml = "select * from producttbl where productName = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; 
		
		productionVO prodVO = null;
		
		try {
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, s);
			
			rs = pstmt.executeQuery();
			
			if(rs==null) {
				memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
				return null;
			}
			
			while (rs.next()) { 
				prodVO = new productionVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				list.add(prodVO); 
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
	public static void deleteProdData(int no) {
		
		System.out.println(no);
		
		String deleteMember = "delete from producttbl where no = ?";
		Connection con = null;
		PreparedStatement pstmt = null;
	
		try {
			
			con = DBUtil.getConnection();
			
			pstmt = con.prepareStatement(deleteMember);
			pstmt.setInt(1, no);
			
			int i = pstmt.executeUpdate();
			
			 if (i == 0) {
	           memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","select Error : select 쿼리문 실패");
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
	public static int updateProdData(productionVO pvo) {
		
		System.out.println(pvo.getNo());
		
		String dml = "update producttbl set " + " productName=?, item=?, info=?, filename=?"
				+ " where no = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		
		
		
		int count = 0;
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			
			pstmt.setString(1, pvo.getProductName());
			pstmt.setString(2, pvo.getItem());
			pstmt.setString(3, pvo.getInfo());
			pstmt.setString(4, pvo.getFilename());
			pstmt.setInt(5, pvo.getNo());

			count = pstmt.executeUpdate();
			System.out.println(count);
			
			if(count == 0) {
				memberController.alertDisplay(3, "실패","삽입에 실패하셨습니다.","insert query Error : 쿼리문삽입실패");			
				return count;
			}
			
			}catch(Exception e) {
				memManageController.alertDisplay(3,"실패","창을 불러오는데 실패했습니다.","Update Error :  데이터베이스 업데이트실패");
				// System.out.println(e.getMessage());
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
			
			return count;
	}

	// 5. 제품 검색 기능
	public ArrayList<productionVO> getProdCheck(String productName) {
		
		ArrayList<productionVO> list = new ArrayList<productionVO>();
		
		String dml = "select * from producttbl where productName like ?";
		// "select * from producttbl where pName = ?";
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		productionVO retval = null;
		
		String name2 = "%"+productName+"%";
		
		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			pstmt.setString(1, name2);
			rs = pstmt.executeQuery();
			
			while (rs.next()) {
				retval = new productionVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4)
						, rs.getString(5), rs.getString(6), rs.getString(7));
				
				list.add(retval);
				
			}
			
		} catch (SQLException se) {
			System.out.println(se);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException se) {
				
			}
		}
		return list;
	}
	
	// 이미지 이벤트 1. 
	public static String getSelectSkin1(String cn) {  

      StringBuffer checkskinName = new StringBuffer( "select filename from producttbl where productName = ?");
      String imageFile1 = null;
      
      Connection con = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      try {
         con = DBUtil.getConnection();
         psmt = con.prepareStatement(checkskinName.toString());
         // 첫번째 물음표 자리 -> studentID 매치 시켜주는 작업
         psmt.setString(1, cn);

         // 3.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
         // executeQuery -> 쿼리문 실행해서 결과를 *!가져올때!* 사용하는 번개문
         // executeUpdate-> 쿼리문 실행해서 결과를 *!가지고 갈때!* 사용하는 번개문
         rs = psmt.executeQuery();

         while (rs.next()) {
            
            imageFile1 = rs.getString(1);
    
         }
 
      } catch (SQLException e) {
    	  memberController.alertDisplay(3, "실패", "이미지 부르기 실패", "다시 확인해주세요.");
         e.printStackTrace();
      } finally {
         try {
            // 1. CLOSE DataBase psmt object
            // 제일 먼저 불렀던 것을 제일 나중에 닫는다.
            // 반드시 있으면 닫아라.
            if (psmt != null)
               psmt.close();
            if (con != null)
               con.close();
         } catch (SQLException e) {
        	 memberController.alertDisplay(3, "실패", "자원 반납 실패", "다시 확인해주세요.");
         }
      }

      
      return imageFile1;
   }
	
	// 이미지 이벤트 2.
	public static String getSelectSkin2(String cn) {  

		StringBuffer checkskinName = new StringBuffer("select filename2 from producttbl where productName = ?");
		String imageFile1 = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			psmt = con.prepareStatement(checkskinName.toString());
			// 첫번째 물음표 자리 -> studentID 매치 시켜주는 작업
			psmt.setString(1, cn);

			// 3.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 *!가져올때!* 사용하는 번개문
			// executeUpdate-> 쿼리문 실행해서 결과를 *!가지고 갈때!* 사용하는 번개문
			rs = psmt.executeQuery();

			while (rs.next()) {

				imageFile1 = rs.getString(1);

			}

		} catch (SQLException e) {
			memberController.alertDisplay(3, "실패", "이미지 부르기 실패", "다시 확인해주세요.");
			e.printStackTrace();
		} finally {
			try {
				// 1. CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				memberController.alertDisplay(3, "실패", "자원 반납 실패", "다시 확인해주세요.");
			}
		}

		return imageFile1;
	}
	
	// 이미지 이벤트 3. 
	public static String getSelectSkin3(String cn) {  

		StringBuffer checkskinName = new StringBuffer("select filename3 from producttbl where productName = ?");
		String imageFile1 = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			psmt = con.prepareStatement(checkskinName.toString());
			// 첫번째 물음표 자리 -> studentID 매치 시켜주는 작업
			psmt.setString(1, cn);

			// 3.5 실제 데이터를 연결한 쿼리문 실행하라 데이터 베이스에게 명령(번개문)
			// executeQuery -> 쿼리문 실행해서 결과를 *!가져올때!* 사용하는 번개문
			// executeUpdate-> 쿼리문 실행해서 결과를 *!가지고 갈때!* 사용하는 번개문
			rs = psmt.executeQuery();

			while (rs.next()) {

				imageFile1 = rs.getString(1);

			}

		} catch (SQLException e) {
			memberController.alertDisplay(3, "실패", "이미지 부르기 실패", "다시 확인해주세요.");
			e.printStackTrace();
		} finally {
			try {
				// 1. CLOSE DataBase psmt object
				// 제일 먼저 불렀던 것을 제일 나중에 닫는다.
				// 반드시 있으면 닫아라.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				memberController.alertDisplay(3, "실패", "자원 반납 실패", "다시 확인해주세요.");
			}
		}

		return imageFile1;
	}
}

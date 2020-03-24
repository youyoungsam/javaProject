package controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.productionVO;

public class ProductionDAO {

	// ====================== ��ǰ ���̺� ===================== // 
	
	// 1. �ű� ��ǰ ��� ( insert )
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
	
	// 2. ��ǰ ��ü ����Ʈ
	public ArrayList<productionVO> getProductTotal() {
		
		ArrayList<productionVO> list = new ArrayList<productionVO>();
		String dml = "select * from producttbl"; // schoolchild�� ��� ������ �����Ͷ�

		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; // �����ͺ��̽� ���� �ӽ÷� �����ϴ� ��� �����ϴ� ��ü. 
		productionVO prodVO = null;

		try {
			con = DBUtil.getConnection();
			pstmt = con.prepareStatement(dml);
			rs = pstmt.executeQuery();
			while (rs.next()) { // �������� �ִ���? ���̺� ������ ������ ���� �����´�. ��� �ö󰡼� �״��� ���ڵ�.
				prodVO = new productionVO(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7)
						);
				list.add(prodVO); // 9�� ��ü�� �������.
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
	
	// 2-1. ��� ����� ��ǰ ����Ʈ ( select )
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
				memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
				return null;
			}
			
			while (rs.next()) { 
				prodVO = new productionVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
						rs.getString(5), rs.getString(6), rs.getString(7));
				list.add(prodVO); 
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
	           memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","select Error : select ������ ����");
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
				memberController.alertDisplay(3, "����","���Կ� �����ϼ̽��ϴ�.","insert query Error : ���������Խ���");			
				return count;
			}
			
			}catch(Exception e) {
				memManageController.alertDisplay(3,"����","â�� �ҷ����µ� �����߽��ϴ�.","Update Error :  �����ͺ��̽� ������Ʈ����");
				// System.out.println(e.getMessage());
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
			
			return count;
	}

	// 5. ��ǰ �˻� ���
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
	
	// �̹��� �̺�Ʈ 1. 
	public static String getSelectSkin1(String cn) {  

      StringBuffer checkskinName = new StringBuffer( "select filename from producttbl where productName = ?");
      String imageFile1 = null;
      
      Connection con = null;
      PreparedStatement psmt = null;
      ResultSet rs = null;
      
      try {
         con = DBUtil.getConnection();
         psmt = con.prepareStatement(checkskinName.toString());
         // ù��° ����ǥ �ڸ� -> studentID ��ġ �����ִ� �۾�
         psmt.setString(1, cn);

         // 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
         // executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������
         // executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������
         rs = psmt.executeQuery();

         while (rs.next()) {
            
            imageFile1 = rs.getString(1);
    
         }
 
      } catch (SQLException e) {
    	  memberController.alertDisplay(3, "����", "�̹��� �θ��� ����", "�ٽ� Ȯ�����ּ���.");
         e.printStackTrace();
      } finally {
         try {
            // 1. CLOSE DataBase psmt object
            // ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
            // �ݵ�� ������ �ݾƶ�.
            if (psmt != null)
               psmt.close();
            if (con != null)
               con.close();
         } catch (SQLException e) {
        	 memberController.alertDisplay(3, "����", "�ڿ� �ݳ� ����", "�ٽ� Ȯ�����ּ���.");
         }
      }

      
      return imageFile1;
   }
	
	// �̹��� �̺�Ʈ 2.
	public static String getSelectSkin2(String cn) {  

		StringBuffer checkskinName = new StringBuffer("select filename2 from producttbl where productName = ?");
		String imageFile1 = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			psmt = con.prepareStatement(checkskinName.toString());
			// ù��° ����ǥ �ڸ� -> studentID ��ġ �����ִ� �۾�
			psmt.setString(1, cn);

			// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������
			// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������
			rs = psmt.executeQuery();

			while (rs.next()) {

				imageFile1 = rs.getString(1);

			}

		} catch (SQLException e) {
			memberController.alertDisplay(3, "����", "�̹��� �θ��� ����", "�ٽ� Ȯ�����ּ���.");
			e.printStackTrace();
		} finally {
			try {
				// 1. CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				memberController.alertDisplay(3, "����", "�ڿ� �ݳ� ����", "�ٽ� Ȯ�����ּ���.");
			}
		}

		return imageFile1;
	}
	
	// �̹��� �̺�Ʈ 3. 
	public static String getSelectSkin3(String cn) {  

		StringBuffer checkskinName = new StringBuffer("select filename3 from producttbl where productName = ?");
		String imageFile1 = null;

		Connection con = null;
		PreparedStatement psmt = null;
		ResultSet rs = null;

		try {
			con = DBUtil.getConnection();
			psmt = con.prepareStatement(checkskinName.toString());
			// ù��° ����ǥ �ڸ� -> studentID ��ġ �����ִ� �۾�
			psmt.setString(1, cn);

			// 3.5 ���� �����͸� ������ ������ �����϶� ������ ���̽����� ���(������)
			// executeQuery -> ������ �����ؼ� ����� *!�����ö�!* ����ϴ� ������
			// executeUpdate-> ������ �����ؼ� ����� *!������ ����!* ����ϴ� ������
			rs = psmt.executeQuery();

			while (rs.next()) {

				imageFile1 = rs.getString(1);

			}

		} catch (SQLException e) {
			memberController.alertDisplay(3, "����", "�̹��� �θ��� ����", "�ٽ� Ȯ�����ּ���.");
			e.printStackTrace();
		} finally {
			try {
				// 1. CLOSE DataBase psmt object
				// ���� ���� �ҷ��� ���� ���� ���߿� �ݴ´�.
				// �ݵ�� ������ �ݾƶ�.
				if (psmt != null)
					psmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				memberController.alertDisplay(3, "����", "�ڿ� �ݳ� ����", "�ٽ� Ȯ�����ּ���.");
			}
		}

		return imageFile1;
	}
}

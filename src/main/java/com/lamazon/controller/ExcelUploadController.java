package com.lamazon.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lamazon.properties.Properties;
import com.lamazon.service.ExcelService;
import com.lamazon.service.LoginService;
import com.lamazon.service.OrderService;
import com.lamazon.util.ContextUtil;

@Controller
@RequestMapping("manage/excelUpload")
public class ExcelUploadController {

	private static final Logger logger = LoggerFactory.getLogger(ExcelUploadController.class);

	@Autowired(required=false)
	LoginService loginService;

	@Autowired(required=false)
	ExcelService excelService;

	@Autowired(required=false)
	OrderService orderService;
	
	@Autowired
	PlatformTransactionManager txManager;
	
	@PostMapping(value="upload")
	public String upload(HttpServletRequest request, HttpServletResponse response
			, @RequestParam(value="files",  required=false) MultipartFile excelFile
			, Model model
			) throws Exception {
		logger.debug(" >>>>>>>>>> ExcelUploadController.upload()");

		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setName("orderUploadTx");
		def.setReadOnly(false);
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		TransactionStatus status = txManager.getTransaction(def);
		
		
		int login_u_pk = loginService.getUPK(ContextUtil.getRequest());
		int rescnt = 0;
		int O_PK = -1;
		String resultMessage = "";
		String current_num = "";
		try {
			// 엑셀파일
			File convFile = new File(excelFile.getOriginalFilename());
			excelFile.transferTo(convFile);

			// 엑셀 파일 오픈
			XSSFWorkbook wb = new XSSFWorkbook(new FileInputStream(convFile));

			Cell cell = null;

			HashMap<String, String> detailMap = new HashMap<String, String>();
			
			Map masterMap = new HashMap();
			
			//sheet 내용 읽기
			for (Row row : wb.getSheetAt(0)) {
				//처음 ROW무시
				if (row.getRowNum() < 1) {
					continue;
				}

				detailMap = new HashMap();
				detailMap.put("OD_INCODE", login_u_pk+"");
				
				//순번
				if( !"".equals(cellValue(row.getCell(0))) ) {
					
					String row_value = cellValue(row.getCell(0));
					
					//순번이 바뀔때마다 새로운 주문으로 ORDER_MASTER INSERT
					if( !row_value.equals(current_num) ) {
						
						//바뀐 순번 새로 셋팅
						current_num = row_value;
						
						//포인트 부족
						if( loginService.getPoint(ContextUtil.getRequest()) < Properties.POINT ) {
							//return Properties.JSON_MESSAGE_POINT;
							resultMessage = "포인트 부족";
							throw new Exception("포인트 부족");
						}
	
						masterMap = new HashMap();
						masterMap.put("R_U_PK", login_u_pk+"");
						masterMap.put("O_INCODE", login_u_pk+"");
						masterMap.put("O_NUMBER", new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date())); //주문번호
						masterMap.put("O_STATUS", 1000); //미입금 1000
						masterMap.put("O_POINT_BEFORE", loginService.getPoint(ContextUtil.getRequest())); //차감전 포인트
						masterMap.put("O_POINT", Properties.POINT); //차감될 포인트
						masterMap.put("O_POINT_AFTER", loginService.getPoint(ContextUtil.getRequest())-Properties.POINT); //차감전 포인트-차감될포인트
						
						//제목
						if( !"".equals(cellValue(row.getCell(1))) ) {
							masterMap.put("O_TITLE", cellValue(row.getCell(1)));
						}
						
						//마켓구분
						//(라자다 1000 / 쇼피 2000)
						if( !"".equals(cellValue(row.getCell(2))) ) {
							masterMap.put("O_MARKET_GUBUN", cellValue(row.getCell(2)));
						}
						
						int orderMasterInsertCnt = orderService.order_insert(masterMap);
						
						if( masterMap.get("O_PK") != null ) {
							O_PK = (int)(long)masterMap.get("O_PK");
						}
	
						if( O_PK > -1 ) {
							HashMap uPointMap = new HashMap();
							uPointMap.put("U_POINT", masterMap.get("O_POINT_AFTER"));
							uPointMap.put("U_PK", loginService.getUPK(ContextUtil.getRequest()));
							//회원정보의 포인트 수정
							int update_cnt = orderService.user_info_u_point_update(uPointMap);
	
							if( update_cnt > 0 ) {
								//로그인 세션갱신
								loginService.setUser(ContextUtil.getRequest());
							}
						}
					}
					
				}
				
				//상품명
				if( !"".equals(cellValue(row.getCell(3))) ) {
					detailMap.put("OD_NAME", cellValue(row.getCell(3)));
				}
				
				//수량
				if( !"".equals(cellValue(row.getCell(4))) ) {
					detailMap.put("OD_COUNT", cellValue(row.getCell(4)));
				}
				
				//색상
				if( !"".equals(cellValue(row.getCell(5))) ) {
					detailMap.put("OD_COLOR", cellValue(row.getCell(5)));
				}
				
				//사이즈
				if( !"".equals(cellValue(row.getCell(6))) ) {
					detailMap.put("OD_SIZE", cellValue(row.getCell(6)));
				}
				
				//브랜드
				if( !"".equals(cellValue(row.getCell(7))) ) {
					detailMap.put("OD_BRAND", cellValue(row.getCell(7)));
				}
				
				//운송장번호	
				if( !"".equals(cellValue(row.getCell(8))) ) {
					detailMap.put("OD_INVOICE", cellValue(row.getCell(8)));
				}
				
				//메모	
				if( !"".equals(cellValue(row.getCell(9))) ) {
					detailMap.put("OD_MEMO", cellValue(row.getCell(9)));
				}
				
				detailMap.put("R_O_PK", O_PK+"");
				
				int od_insert_cnt = orderService.order_detail_insert(detailMap);
			}

		} catch (FileNotFoundException fe) {
			fe.printStackTrace();
			resultMessage = "FileNotFoundException - " + fe.toString();
			txManager.rollback(status);
			
		} catch (IOException ie) {
			ie.printStackTrace();
			resultMessage = "IOException - " + ie.toString();
			txManager.rollback(status);
			
		} catch (Exception ex) {
			ex.printStackTrace();
			resultMessage = "Exception - " + ex.toString();
			txManager.rollback(status);
		}
		
		//request.getSession().setAttribute("TOTAL_CNT", rescnt);
		
		if( !status.isCompleted() ) txManager.commit(status);
		
		//로그인 세션갱신
		loginService.setUser(ContextUtil.getRequest());
		
		//에러났을경우
		if( !"".equals(resultMessage) ) {
			model.addAttribute("MESSAGE", "완료 되지 못했습니다. 에러) "+resultMessage);
		}
		
		model.addAttribute("OPRNER_NEXT_URL", "/order/list");
		return "exception/message_out_popup_close";
	}

   public String cellValue(Cell cell) {

       String value = null;
       if (cell == null) value = "";
       else {
           switch (cell.getCellType()) { //cell 타입에 따른 데이타 저장
           case Cell.CELL_TYPE_FORMULA:
               value = cell.getCellFormula();
               break;
           case Cell.CELL_TYPE_NUMERIC:
               if (DateUtil.isCellDateFormatted(cell)) {
                   //you should change this to your application date format
                   SimpleDateFormat objSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                   value = "" + objSimpleDateFormat.format(cell.getDateCellValue());
               } else {
                   value = "" + String.format("%.0f", new Double(cell.getNumericCellValue()));
               }
               break;
           case Cell.CELL_TYPE_STRING:
               value = "" + cell.getStringCellValue();
               break;
           case Cell.CELL_TYPE_BLANK:
               //value=""+cell.getBooleanCellValue();
               value = "";
               break;
           case Cell.CELL_TYPE_ERROR:
               value = "" + cell.getErrorCellValue();
               break;
           default:
           }
       }

       return value.trim();
   }
}

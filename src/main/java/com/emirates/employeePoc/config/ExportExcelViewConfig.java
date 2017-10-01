package com.emirates.employeePoc.config;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import com.emirates.employeePoc.model.Employee;

/**
* <h1>Employee Management</h1>
* The Employee Management implements an application that
* performs all CRUD operations, search operation, 
* bulk upload via excel sheet
* download list in excel sheet of current Employees.
* <p>
* <b>Note:</b> This ExportExcelViewConfig class helps to export employee
* details in Excel file.
*
* @author  Paaks
* @version 1.0
* @since   2017-09-29
*/
public class ExportExcelViewConfig extends AbstractXlsxView  {

	@SuppressWarnings("unchecked")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		 response.setHeader( "Content-disposition", "attachment; filename=\"employee.xlsx\"" );
		 
		List<Employee> employees = (List<Employee>)model.get("employeeList");
		
        Sheet sheet = workbook.createSheet("Employees");

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Firstname");
        header.createCell(2).setCellValue("Lastname");
        header.createCell(3).setCellValue("EmailID");
        header.createCell(4).setCellValue("Contact");
        header.createCell(5).setCellValue("Department");
        header.createCell(6).setCellValue("Designation");

        // Create data cells
        int rowCount = 1;
        for(Employee emp : employees) {
        	Row courseRow = sheet.createRow(rowCount++);
            courseRow.createCell(0).setCellValue(emp.getId());
            courseRow.createCell(1).setCellValue(emp.getFirstName());
            courseRow.createCell(2).setCellValue(emp.getLastName());
            courseRow.createCell(3).setCellValue(emp.getEmailId());
            courseRow.createCell(4).setCellValue(emp.getContact());
            courseRow.createCell(5).setCellValue(emp.getDept());
            courseRow.createCell(6).setCellValue(emp.getDesignation());
        }
    }

}
